package db;

import entity.enums.State;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class Datasource {
    public static HashMap<Long, State> userState = new HashMap<>();

    private static final String URL = "jdbc:postgresql://localhost:5432/db_nomi";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Root1234";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

