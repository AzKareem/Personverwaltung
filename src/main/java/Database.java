import java.sql.Connection;
import java.sql.DriverManager;

public class Database {



    public static Connection getConnection() {
        try {

            String databaseUrl = "jdbc:mysql://localhost:3306/personenverwaltung";
            String username = "root";
            String password = "";
            Connection conn = DriverManager.getConnection(databaseUrl, username, password);
            System.out.println("Database Connected");
            return conn;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }
}
