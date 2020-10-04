package jm.task.core.jdbc.util;


import java.sql.*;

public class Util {

        private static Connection connection;
        private static final String URL = "jdbc:mysql://localhost:3306/timdb?useSSL=false&serverTimezone=UTC";
        private static final String USERNAME = "root";
        private static final String PASSWORD = "root";

        public Util() {
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
                statement.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public Connection getConnection() {
            return connection;
        }
    }