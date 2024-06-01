import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/dbminimarket";
    private static final String USERNAME = "root"; // Ganti dengan username MySQL Anda
    private static final String PASSWORD = ""; // Ganti dengan password MySQL Anda

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                System.out.println("Gagal terhubung ke database.");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            System.out.println("Gagal menutup koneksi ke database.");
            e.printStackTrace();
        }
    }
}
