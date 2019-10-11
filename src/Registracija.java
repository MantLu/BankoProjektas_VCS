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

    public static boolean registracija() {

        boolean arPavyko = false;

        String vardas = null;
        String pavarde = null;
        String elpastas = null;
        String slaptazodis = null;
        String suma = null;
        int PIN;

        Scanner ivedimas = new Scanner(System.in);

        while ((vardas == null || vardas.isEmpty()) || (pavarde == null || pavarde.isEmpty()) ||
                (elpastas == null || elpastas.isEmpty()) || (slaptazodis == null || slaptazodis.isEmpty())
                || (suma == null || suma.isEmpty())) {

            if (vardas == null || vardas.isEmpty()) {
                System.out.println("Įveskite savo vardą: ");
                vardas = ivedimas.nextLine();
            }

            if (pavarde == null || pavarde.isEmpty()) {
                System.out.println("Įveskite savo pavardę: ");
                pavarde = ivedimas.nextLine();
            }

            if (elpastas == null || elpastas.isEmpty()) {
                System.out.println("Įveskite savo elektroninio pašto adresą: ");
                elpastas = ivedimas.nextLine();
            }

            if (slaptazodis == null || slaptazodis.isEmpty()) {
                System.out.println("Įveskite slaptažodį: ");
                slaptazodis = ivedimas.nextLine();
            }

            if (suma == null || suma.isEmpty()) {
                System.out.println("Įveskite sumą EUR kurią norite įnešti: ");
                suma = ivedimas.nextLine();
            }
        }

        PIN = (int)(Math.random()*9000)+1000;

        String sql = "INSERT INTO Registracija(vardas, pavarde, elpastas, slaptazodis, PIN, suma) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement myStmt = connect().prepareStatement(sql)) {

            myStmt.setString(1, vardas);
            myStmt.setString(2, pavarde);
            myStmt.setString(3, elpastas);
            myStmt.setString(4, slaptazodis);
            myStmt.setInt(5, PIN);
            myStmt.setString(6, suma);

            int i = myStmt.executeUpdate();

            if (i > 0) {
                arPavyko = true;
                System.out.println("Registracija sėkminga!");
                System.out.println("Jūsų PIN kodas: " + PIN + "\n" + "Galite prisijungti");
                Meniu.pradinisMeniu();
            } else {
                System.out.println("Prisiregistruoti nepavyko!" + "\n" + "Prašome bandykite dar kartą");
                Meniu.pradinisMeniu();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arPavyko;
    }
}
