package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private PreparedStatement preparedStatement;
    private final Util connect = new Util();
    private Statement statement;
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            statement = connect.getConnection().createStatement();
            statement.executeUpdate("CREATE TABLE USERS " +
                    "(id BIGINT not NULL PRIMARY KEY AUTO_INCREMENT, " +
                    " name VARCHAR(255) not NULL, " +
                    " lastName VARCHAR(255) not NULL, " +
                    " age TINYINT UNSIGNED not NULL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            statement = connect.getConnection().createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS USERS");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        user.setName(name);
        try {
            statement = connect.getConnection().createStatement();
            preparedStatement = connect.getConnection().prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + user.getName() + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            statement = connect.getConnection().createStatement();
            preparedStatement = connect.getConnection().prepareStatement("DELETE FROM users WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            statement = connect.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, name, lastName, age FROM USERS");
            while (resultSet.next()) {
                list.add(new User(resultSet.getString(2),
                        resultSet.getString(3), resultSet.getByte(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try {
            statement = connect.getConnection().createStatement();
            statement.executeUpdate("TRUNCATE TABLE USERS");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
