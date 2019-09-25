import java.sql.*;
import java.util.Scanner;
import java.lang.String;

public class Prisijungimas {

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

    public static void prisijungimas() {
        Scanner ivedimas2 = new Scanner(System.in);

        System.out.println("Iveskite savo elektroninio pasto adresa: ");
        String pastas = ivedimas2.nextLine();

        System.out.println("Iveskite slaptazodi: ");
        String slaptazod = ivedimas2.nextLine();

        String sql = "SELECT COUNT(*) FROM Registracija WHERE elpastas = ? AND slaptazodis = ?";

        ResultSet rs;
        try (PreparedStatement mySt = connect().prepareStatement(sql)) {

            mySt.setString(1, pastas);
            mySt.setString(2, slaptazod);

            rs = mySt.executeQuery();

            System.out.println(rs.getInt(1));

            if () {
                Meniu.bankomArElbank();
            } else {
                System.out.println("Nepavyko");
            }

            // 1. pavyko - langas A 123
            // 2. nepavyko - langas B

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
