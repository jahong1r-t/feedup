package db;


import entity.User;
import entity.enums.State;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DataSource {
    public static Map<Long, State> states = new HashMap<>();
    public static final Map<Long, User> userSession = new HashMap<>();


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
