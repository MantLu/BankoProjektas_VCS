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

        System.out.println("Iveskite savo pavarde: ");
        String pavarde = ivedimas.nextLine();

        System.out.println("Iveskite savo elektroninio pasto adresa: ");
        String elpastas = ivedimas.nextLine();

        System.out.println("Iveskite slaptazodi: ");
        String slaptazodis = ivedimas.nextLine();

        System.out.println("Iveskite PIN koda: ");
        String PIN = ivedimas.nextLine();

        System.out.println("Iveskite suma kuria norite inesti: ");
        String suma = ivedimas.nextLine();

        String sql = "insert into Registracija(vardas, pavarde, elpastas, slaptazodis, PIN, suma) values (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement myStmt = connect().prepareStatement(sql)) {

            myStmt.setString(1, vardas);
            myStmt.setString(2, pavarde);
            myStmt.setString(3, elpastas);
            myStmt.setString(4, slaptazodis);
            myStmt.setString(5, PIN);
            myStmt.setString(6, suma);

            myStmt.executeUpdate();

            //if priregino - gryzti i pradini langa
            //else nepriregino

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
