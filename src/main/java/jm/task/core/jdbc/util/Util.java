package jm.task.core.jdbc.util;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class Util {

    public static Connection connectDB() throws SQLException {
        String login = "admin";
        String pass = "admin";
        String params = "jdbc:mysql://localhost:3306/testdb_task";
        return DriverManager.getConnection(params, login, pass);
    }




}
