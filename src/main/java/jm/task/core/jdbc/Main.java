package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("Максим","Максимов",(byte)23);
        service.saveUser("Иван","Иванов",(byte)24);
        service.saveUser("Сергей","Сергеев",(byte)25);
        service.saveUser("Василий","Васильев",(byte)26);

        System.out.println(service.getAllUsers());

        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
