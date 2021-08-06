package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public static final String TABLE_NAME = "`users`.`new_table`";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            "`id` INT NOT NULL AUTO_INCREMENT, " +
            "`name` VARCHAR(45) NOT NULL, " +
            "`lastName` VARCHAR(45) NULL, " +
            "`age` TINYINT(3) NULL, " +
            "PRIMARY KEY (`id`)) " +
            "ENGINE = InnoDB " +
            "DEFAULT CHARACTER SET = utf8";
    public static final String DROP_TABLE = "DROP TABLE " + TABLE_NAME;

    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()){
            boolean exists = connection.getMetaData().getTables(null, null, TABLE_NAME, null).next();
            if (!exists) {
                statement.executeUpdate(CREATE_TABLE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()){
            boolean exists = connection.getMetaData().getTables(null, null, TABLE_NAME, null).next();
            if (exists) {
                statement.executeUpdate(DROP_TABLE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
