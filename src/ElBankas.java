import java.sql.*;

public class ElBankas {

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

    public static boolean saskLikutisElBanke(PrisijungesVart vart, PrisijungesVart slap) {

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
}
