package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "CREATE TABLE IF NOT EXISTS `testDB`.`User` (" +
                "`id` INT NOT NULL AUTO_INCREMENT, " + "`name` VARCHAR(45) NOT NULL, " +
                "`lastName` VARCHAR(45) NULL, " +
                "`age` TINYINT(3) NULL, " +
                "PRIMARY KEY (`id`)) ";

        session.createSQLQuery(sql).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "DROP TABLE IF EXISTS `testDB`.`User`";
        session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        List<User> userList = session.createQuery("from User", User.class).getResultList();
        session.close();
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from User").executeUpdate();
        transaction.commit();
        session.close();
    }
}
