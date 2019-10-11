import java.sql.*;
import java.util.Scanner;
import java.lang.String;

public class Bankomatas {

    private static Connection connect() {

        String url = "jdbc:sqlite:/Users/mantasluksa/IdeaProjects/BankoProjektas_VCS/Registracija.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static boolean prisijungtiPrieBankomato(PrisijungesVart vart) {

        Scanner ivedimas3 = new Scanner(System.in);
        System.out.println("Įveskite PIN kodą: ");
        int Pin = ivedimas3.nextInt();

        boolean arPavyko = false;

        String sql = "SELECT COUNT(*) FROM Registracija WHERE PIN = ? AND elpastas = ?";

        ResultSet rs;

        try {

            Connection c = connect();
            PreparedStatement myStm = c.prepareStatement(sql);
            myStm.setString(1, String.valueOf(Pin));
            myStm.setString(2, vart.prisVardas);

            rs = myStm.executeQuery();

            int userAmount1 = rs.getInt(1);
            rs.close();
            if (userAmount1 > 0) {
                arPavyko = true;
                Meniu.bankomatasMeniu();
            } else {
                System.out.println("Blogas PIN kodas");
                Meniu.bankomArElbankMeniu();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arPavyko;
    }

    public static boolean saskaitosLikutis(PrisijungesVart vart, PrisijungesVart slap) {

        boolean arPavyko = false;

        String sql = "SELECT suma FROM Registracija WHERE elpastas = ? AND slaptazodis = ?";

        try (PreparedStatement myStm = connect().prepareStatement(sql)) {

            myStm.setString(1, vart.prisVardas);
            myStm.setString(2, slap.prisSlapt);

            ResultSet rs = myStm.executeQuery();

            int sumaspausd = rs.getInt(1);

            System.out.println("Sąskaitos likutis: " + sumaspausd + " EUR");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arPavyko;
    }

    public static boolean piniguInesimas(PrisijungesVart vart, PrisijungesVart slap) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Įnešama suma: ");
        int pridedamaSuma = scanner.nextInt();

        boolean arPavyko = false;

        String sql = "SELECT suma FROM Registracija WHERE elpastas = ? AND slaptazodis = ?";

        try {

            Connection c = connect();
            PreparedStatement myStm = c.prepareStatement(sql);
            myStm.setString(1, vart.prisVardas);
            myStm.setString(2, slap.prisSlapt);

            ResultSet rs = myStm.executeQuery();

            int sumaSudeta = rs.getInt(1);
            int naujaSuma = sumaSudeta + pridedamaSuma;

            System.out.println("Naujas sąskaitos likutis: " + naujaSuma + " EUR");

            String sql1 = "UPDATE Registracija SET suma = ? WHERE elpastas = ? AND slaptazodis = ?";

            PreparedStatement mystm = c.prepareStatement(sql1);

            mystm.setInt(1, naujaSuma);
            mystm.setString(2, vart.prisVardas);
            mystm.setString(3, slap.prisSlapt);

            mystm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arPavyko;
    }

    public static boolean pasiimtiPinigu(PrisijungesVart vart, PrisijungesVart slap) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Išimama suma: ");
        int isimamaSuma = scanner.nextInt();

        boolean arPavyko = false;

        String sql = "SELECT suma FROM Registracija WHERE elpastas = ? AND slaptazodis = ?";

        try {

            Connection c = connect();
            PreparedStatement myStm = c.prepareStatement(sql);
            myStm.setString(1, vart.prisVardas);
            myStm.setString(2, slap.prisSlapt);

            ResultSet rs = myStm.executeQuery();

            int sumaSudeta = rs.getInt(1);
            int naujaSuma = sumaSudeta - isimamaSuma;

            System.out.println("Naujas sąskaitos likutis: " + naujaSuma + " EUR");

            String sql1 = "UPDATE Registracija SET suma = ? WHERE elpastas = ? AND slaptazodis = ?";

            PreparedStatement mystm = c.prepareStatement(sql1);

            mystm.setInt(1, naujaSuma);
            mystm.setString(2, vart.prisVardas);
            mystm.setString(3, slap.prisSlapt);

            mystm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arPavyko;
    }

    public static boolean pakeistiPIN(PrisijungesVart vart, PrisijungesVart slap) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Įveskite naują PIN kodą: ");
        int naujasPIN = scanner.nextInt();
        System.out.println("Pakartokite PIN kodą: ");
        int pakartotPIN = scanner.nextInt();

        if (naujasPIN == pakartotPIN) {

            boolean arPavyko = false;

            String sql = "SELECT PIN FROM Registracija WHERE elpastas = ? AND slaptazodis = ?";

            try {

                Connection c = connect();
                PreparedStatement myStm = c.prepareStatement(sql);
                myStm.setString(1, vart.prisVardas);
                myStm.setString(2, slap.prisSlapt);

                myStm.executeQuery();

                System.out.println("PIN kodas pakeistas!" + "\n" + "Naujas PIN kodas: " + naujasPIN);

                String sql1 = "UPDATE Registracija SET PIN = ? WHERE elpastas = ? AND slaptazodis = ?";

                PreparedStatement mystm = c.prepareStatement(sql1);

                mystm.setInt(1, naujasPIN);
                mystm.setString(2, vart.prisVardas);
                mystm.setString(3, slap.prisSlapt);

                mystm.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return arPavyko;

        } else {
            System.out.println("PIN kodas nesutampa!" + "\n" + "Bandykite iš naujo");
            pakeistiPIN(vart, slap);
        }
        return false;
    }
}
