package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    private static final String TABLE_NAME = "`testDB`.`User_Table`";

    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            boolean exists = connection.getMetaData().getTables(null, null, TABLE_NAME, null).next();
            if (!exists) {
                statement.executeUpdate("CREATE TABLE `testDB`.`User_Table` (" + "`id` INT NOT NULL AUTO_INCREMENT, " + "`name` VARCHAR(45) NOT NULL, " + "`lastName` VARCHAR(45) NULL, " + "`age` TINYINT(3) NULL, " + "PRIMARY KEY (`id`)) " + "ENGINE = InnoDB " + "DEFAULT CHARACTER SET = utf8");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        getConnectionAndExecute("DROP TABLE " + TABLE_NAME);
    }

    public void saveUser(String name, String lastName, byte age) {
        getConnectionAndExecute("INSERT " + TABLE_NAME + "(name, lastName, age) VALUES ('" + name +
                "', '" + lastName + "', " + age + ")");
    }

    public void removeUserById(long id) {
        getConnectionAndExecute("DELETE FROM " + TABLE_NAME + " WHERE Id = " + id);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from " + TABLE_NAME)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString(2), rs.getString(3), rs.getByte(4));
                user.setId(rs.getLong(1));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        getConnectionAndExecute("TRUNCATE TABLE " + TABLE_NAME);
    }

    private void getConnectionAndExecute(String command) {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            boolean exists = connection.getMetaData().getTables(null, null, TABLE_NAME, null).next();
            if (exists) {
                statement.executeUpdate(command);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
