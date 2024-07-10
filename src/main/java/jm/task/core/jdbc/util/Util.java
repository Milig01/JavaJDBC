package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final SessionFactory sessionFactory;
    private final Connection connection;

    public Util() throws SQLException {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/usersdb");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "ilya0008");
        configuration.addAnnotatedClass(User.class);
        sessionFactory = configuration.buildSessionFactory();

        String URL = "jdbc:mysql://localhost:3306/usersdb";
        String ROOT = "root";
        String PASSWORD = "ilya0008";
        try {
            connection = DriverManager.getConnection(URL, ROOT, PASSWORD);
        } catch (SQLException ex) {
            throw new SQLException("Ошибка при подключении к базе данных");
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Connection getConnection() {return connection;}
}
