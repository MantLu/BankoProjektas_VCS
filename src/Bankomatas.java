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
        System.out.println("Iveskite PIN koda: ");
        int Pin = ivedimas3.nextInt();

        boolean arPavyko = false;

        String sql = "SELECT COUNT(*) FROM Registracija WHERE PIN = ? AND elpastas = ?";

        ResultSet rs;

        try (PreparedStatement myStm = connect().prepareStatement(sql)) {

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

            System.out.println("Saskaitos likutis: " + sumaspausd + " EUR");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arPavyko;
    }

    public static boolean piniguInesimas(PrisijungesVart vart, PrisijungesVart slap) {

        Scanner ivedimas4 = new Scanner(System.in);
        System.out.println("Inesama suma: ");
        int pridedamaSuma = ivedimas4.nextInt();

        boolean arPavyko = false;

        String sql = "SELECT suma FROM Registracija WHERE elpastas = ? AND slaptazodis = ?";

        try (PreparedStatement myStm = connect().prepareStatement(sql)) {

            myStm.setString(1, vart.prisVardas);
            myStm.setString(2, slap.prisSlapt);

            ResultSet rs = myStm.executeQuery();

            int sumaSudeta = rs.getInt(1);
            int naujaSuma = sumaSudeta + pridedamaSuma;

            System.out.println("Naujas saskaitos likutis: " + naujaSuma + " EUR");



        String sql1 = "UPDATE Registracija SET suma = ? WHERE elpastas = ? AND slaptazodis = ?";

        try (PreparedStatement mystm = connect().prepareStatement(sql1)) {

            mystm.setString(1, vart.prisVardas);
            mystm.setString(2, slap.prisSlapt);

            mystm.setInt(1, naujaSuma);

            mystm.executeUpdate();

        }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arPavyko;

    }

    public static void gryztiAtgal() {

    }
}
