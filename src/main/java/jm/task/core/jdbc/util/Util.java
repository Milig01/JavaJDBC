package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final Connection connection;

    public Util() throws SQLException {
        String URL = "jdbc:mysql://localhost:3306/usersdb";
        String ROOT = "root";
        String PASSWORD = "ilya0008";
        try {
            connection = DriverManager.getConnection(URL, ROOT, PASSWORD);
        } catch (SQLException ex) {
            throw new SQLException("Ошибка при подключении к базе данных");
        }
    }

    public Connection getConnection() {
        return connection;
    }


}
