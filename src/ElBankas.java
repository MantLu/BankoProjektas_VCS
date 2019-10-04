import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    public static void saskLikutisElBanke() {

    }
}
