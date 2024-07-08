package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        try {
            UserServiceImpl userService = new UserServiceImpl();
            userService.createUsersTable();
            userService.saveUser("Илья", "Максимов", (byte)23);
            userService.saveUser("Иван", "Иванов", (byte)35);
            userService.saveUser("Степан", "Степанов", (byte)46);
            userService.saveUser("Василий", "Васильев", (byte)15);
            userService.getAllUsers().forEach(System.out::println);
            userService.cleanUsersTable();
            userService.dropUsersTable();
        } catch (Exception ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
        }
    }
}
