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

        String vardas = null;
        String pavarde = null;
        String elpastas = null;
        String slaptazodis = null;
        String PIN = null;
        String suma = null;

        Scanner ivedimas = new Scanner(System.in);

        while ((vardas == null || vardas.isEmpty()) || (pavarde == null || pavarde.isEmpty()) ||
                (elpastas == null || elpastas.isEmpty()) || (slaptazodis == null || slaptazodis.isEmpty()) ||
                (PIN == null || PIN.isEmpty()) || (suma == null || suma.isEmpty())) {

            if (vardas == null || vardas.isEmpty()) {
                System.out.println("Iveskite savo varda: ");
                vardas = ivedimas.nextLine();
            }

            if (pavarde == null || pavarde.isEmpty()) {
                System.out.println("Iveskite savo pavarde: ");
                pavarde = ivedimas.nextLine();
            }

            if (elpastas == null || elpastas.isEmpty()) {
                System.out.println("Iveskite savo elektroninio pasto adresa: ");
                elpastas = ivedimas.nextLine();
            }

            if (slaptazodis == null || slaptazodis.isEmpty()) {
                System.out.println("Iveskite slaptazodi: ");
                slaptazodis = ivedimas.nextLine();
            }

            if (PIN == null || PIN.isEmpty()) {
                System.out.println("Iveskite PIN koda: ");
                PIN = ivedimas.nextLine();
            }

            if (suma == null || suma.isEmpty()) {
                System.out.println("Iveskite suma EUR kuria norite inesti: ");
                suma = ivedimas.nextLine();
            }
        }

        /* System.out.println("Iveskite savo varda: ");
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
            } */

        String sql = "INSERT INTO Registracija(vardas, pavarde, elpastas, slaptazodis, PIN, suma) VALUES (?, ?, ?, ?, ?, ?)";

        ResultSet rs;

        try (PreparedStatement myStmt = connect().prepareStatement(sql)) {

            myStmt.setString(1, vardas);
            myStmt.setString(2, pavarde);
            myStmt.setString(3, elpastas);
            myStmt.setString(4, slaptazodis);
            myStmt.setString(5, PIN);
            myStmt.setString(6, suma);

            myStmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
