package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public Connection connection;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String SQL = "CREATE TABLE User(" +
                "id BIGINT NOT NULL AUTO_INCREMENT ," +
                "name VARCHAR(100) NOT NULL," +
                "lastName VARCHAR(100) NOT NULL," +
                "age TINYINT NOT NULL," +
                "PRIMARY KEY (id))";
        try (Statement statement = connection.createStatement()) {
            statement.execute(SQL);
        } catch (SQLException ex) {
            System.err.println("Ошибка при попытке создать таблицу");
        }
    }

    public void dropUsersTable() {
        String SQL = "DROP TABLE User";
        try (Statement statement = connection.createStatement()) {
            statement.execute(SQL);
        } catch (SQLException ex) {
            System.err.println("Ошибка при попытке удалить таблицу");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String SQL = "INSERT INTO User (name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            System.out.printf("User с именем %s добавлен в базу данных\n", name);
        } catch (SQLException ex) {
            System.err.println("Ошибка при попытке добавить пользователя");
        }
    }

    public void removeUserById(long id) {
        String SQL = "DELETE FROM User WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.err.println("Ошибка при попытке удалить пользователя");
        }
    }

    public List<User> getAllUsers() {
        String SQL = "SELECT * FROM User";
        List<User> result = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()) {
                User user = new User(rs.getString(2), rs.getString(3), rs.getByte(4));
                user.setId(rs.getLong(1));
                result.add(user);
            }
        } catch (SQLException ex) {
            System.err.println("Ошибка при попытке получить всех пользователей");
        }
        return result;
    }

    public void cleanUsersTable() {
        String SQL = "DELETE FROM User";
        try (Statement statement = connection.createStatement()) {
            statement.execute(SQL);
        } catch (SQLException ex) {
            System.err.println("Ошибка при попытке очистить таблицу");
        }
    }
}
