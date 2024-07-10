package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory;

    public UserDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createUsersTable() {
        String SQL = "CREATE TABLE User(" +
                "id BIGINT NOT NULL AUTO_INCREMENT," +
                "name VARCHAR(100) NOT NULL," +
                "lastName VARCHAR(100) NOT NULL," +
                "age TINYINT NOT NULL," +
                "PRIMARY KEY (id))";
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery(SQL).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.err.println(ex.getClass().getName() + " Ошибка при создании таблицы");
        }
    }

    @Override
    public void dropUsersTable() {
        String SQL = "DROP TABLE User";
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery(SQL).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.err.println(ex.getClass().getName() + " Ошибка при удалении таблицы");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            System.out.printf("User с именем %s добавлен в базу данных\n", name);
        } catch (Exception ex) {
            System.err.println(ex.getClass().getName() + " Ошибка при добавлении пользователя");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.find(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.err.println(ex.getClass().getName() + " Ошибка при удалении пользователя");
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            return session.createQuery("FROM User", User.class).getResultList();
        } catch (Exception ex) {
            System.err.println(ex.getClass().getName() + " Ошибка при получении всех пользователей");
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM User");
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.err.println(ex.getClass().getName() + " Ошибка при очищении таблицы");
        }
    }
}
