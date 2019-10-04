import java.sql.*;
import java.util.Scanner;
import java.lang.String;

public class Registracija {

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

    public static void registracija() {

        Scanner ivedimas = new Scanner(System.in);

        System.out.println("Iveskite savo varda: ");
        String vardas = ivedimas.nextLine();

            if (vardas != null && vardas.isEmpty()) {
                System.out.println("Tuscias laukas!");
            }

        System.out.println("Iveskite savo pavarde: ");
        String pavarde = ivedimas.nextLine();

            if (pavarde != null && pavarde.isEmpty()) {
                System.out.println("Tuscias laukas!");
            }

        System.out.println("Iveskite savo elektroninio pasto adresa: ");
        String elpastas = ivedimas.nextLine();

            if (elpastas != null && elpastas.isEmpty()) {
                System.out.println("Tuscias laukas!");
            }

        System.out.println("Iveskite slaptazodi: ");
        String slaptazodis = ivedimas.nextLine();

            if (slaptazodis != null && slaptazodis.isEmpty()) {
                System.out.println("Tuscias laukas!");
            }

        System.out.println("Iveskite PIN koda: ");
        String PIN = ivedimas.nextLine();

            if (PIN != null && PIN.isEmpty()) {
                System.out.println("Tuscias laukas!");
            }

        System.out.println("Iveskite suma EUR kuria norite inesti: ");
        Integer suma = Integer.valueOf(ivedimas.nextLine());

            if (suma <= 0) {
                System.out.println("Neteisinga suma!");
            }

        String sql = "INSERT INTO Registracija(vardas, pavarde, elpastas, slaptazodis, PIN, suma) VALUES (?, ?, ?, ?, ?, ?)";

        ResultSet rs;

            try (PreparedStatement myStmt = connect().prepareStatement(sql)) {

            myStmt.setString(1, vardas);
            myStmt.setString(2, pavarde);
            myStmt.setString(3, elpastas);
            myStmt.setString(4, slaptazodis);
            myStmt.setString(5, PIN);
            myStmt.setInt(6, suma);

            myStmt.executeUpdate();

            //if priregino - gryzti i pradini langa
            //else nepriregino

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
