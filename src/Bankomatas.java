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

        boolean arPavyko = false;

        System.out.println("Iveskite PIN koda: ");
        int Pin = ivedimas3.nextInt();

        String sql = "SELECT COUNT(*) FROM Registracija WHERE PIN = ? AND elPastas = ?";

        ResultSet rs;

        try (PreparedStatement myStm = connect().prepareStatement(sql)) {

            myStm.setString(1, String.valueOf(Pin));
            myStm.setString(2, vart.prisVardas);

            rs = myStm.executeQuery();

            int userAmount1 = rs.getInt(1);

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

    public static void saskaitosLikutis() {

    }

    public static void piniguInesimas() {

    }

    public static void gryztiAtgal() {

    }
}
