package db;


import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataSource {
    static Connection connection;

    @SneakyThrows
    public static Statement statement() {
        if (connection == null) {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/feedup", "postgres", "Root1234");
        }
        return connection.createStatement();
    }
}
