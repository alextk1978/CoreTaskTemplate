package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        User user1 = new User("Alex", "Ivanov", (byte) 23);
        User user2 = new User("Ivan", "Petrov", (byte) 55);
        User user3 = new User("Andrey", "Sidorov", (byte) 19);
        User user4 = new User("Katya", "Pirogova", (byte) 30);

        addUser(user1, userService);
        addUser(user2, userService);
        addUser(user3, userService);
        addUser(user4, userService);

        List<User> users = userService.getAllUsers();
        users.forEach(System.out::println);

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }

    public static void addUser(User user, UserService userService) {
        userService.saveUser(user.getName(), user.getLastName(), user.getAge());
        System.out.printf("User с именем – %s добавлен в базу данных", user.getName());
        System.out.println();
    }
}
