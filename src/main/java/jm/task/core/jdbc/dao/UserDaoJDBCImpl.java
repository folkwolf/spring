package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sqlRequest = "create table users (id int auto_increment not null primary key , name varchar(60) not null, lastName varchar(60) not null , age int not null);";
        try (Connection connect = Util.connectDB(); Statement statement = connect.createStatement()) {
            statement.executeUpdate(sqlRequest);
        } catch (SQLException ignore) {

        }
    }

    public void dropUsersTable() {
        String sqlRequest = "drop table users;";
        try (Connection connect = Util.connectDB(); Statement statement = connect.createStatement()) {
            statement.executeUpdate(sqlRequest);
        } catch (SQLException ignore) {

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String preparedStat = "insert into users (name, lastName, age) values (?, ?, ?);";
        try (Connection connect = Util.connectDB(); PreparedStatement statement = connect.prepareStatement(preparedStat)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String preparedStat = "delete from users where id = ?;";
        try (Connection connect = Util.connectDB(); PreparedStatement statement = connect.prepareStatement(preparedStat)) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> listUsers = new ArrayList<>();
        User newUser;
        String sqlRequest = "select * from users;";
        try (Connection connect = Util.connectDB(); Statement statement = connect.createStatement()) {
            ResultSet users = statement.executeQuery(sqlRequest);
            while (users.next()) {
                int id = users.getInt(1);
                String name = users.getString(2);
                String lastName = users.getString(3);
                int age = users.getInt(4);
                newUser = new User(name, lastName, (byte) age);
                newUser.setId((long)id);
                listUsers.add(newUser);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listUsers;
    }

    public void cleanUsersTable() {
        String sqlRequest = "delete from users;";
        try (Connection connect = Util.connectDB(); Statement statement = connect.createStatement()) {
            statement.executeUpdate(sqlRequest);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
