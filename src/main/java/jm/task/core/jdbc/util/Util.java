package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Properties;

public class Util {
    private static String path = "jdbc:mysql://localhost:3306/testdb_task";
    private static String login = "admin";
    private static String pass = "admin";

    private static SessionFactory sessionFactory;

    public static Connection connectDB() throws SQLException {
        return DriverManager.getConnection(path, login, pass);
    }

    public static SessionFactory getSF () {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            Properties properties = new Properties();

            properties.setProperty(Environment.URL, path);
            properties.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            properties.setProperty(Environment.USER, login);
            properties.setProperty(Environment.PASS, pass);
            properties.setProperty(Environment.SHOW_SQL, "false");
            properties.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

            sessionFactory = configuration.addProperties(properties).addAnnotatedClass(User.class).buildSessionFactory();
        }

        return sessionFactory;
    }
}
