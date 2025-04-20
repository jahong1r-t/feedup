package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSource {
    public static final String URL = "jdbc:postgresql://localhost:5432/feedup";
    public static final String USER = "postgres";
    public static final String PASSWORD = "Root1234";

    static {
        try {
            Class.forName("org.postgresql.Driver");
            initializeTables();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void initializeTables() {
        String createUserTable = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT PRIMARY KEY, " +
                "username VARCHAR(100), " +
                "full_name VARCHAR(200), " +
                "phone_number VARCHAR(20), " +
                "language VARCHAR(10), " +
                "role VARCHAR(20))" +
                "is_register BOOLEAN";

        String createProductTable = "CREATE TABLE IF NOT EXISTS products (" +
                "id uuid default gen_random_uuid() PRIMARY KEY, " +
                "name VARCHAR, " +
                "descriptionUz VARCHAR, " +
                "descriptionEn VARCHAR, " +
                "descriptionRu VARCHAR, " +
                "price DECIMAL," +
                "photo_url VARCHAR);";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(createUserTable);
            stmt.executeUpdate(createProductTable);
            System.out.println("All tables created");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}
