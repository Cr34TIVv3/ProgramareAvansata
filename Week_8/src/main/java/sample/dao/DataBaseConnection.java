package sample.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private Connection connection;
    private static DataBaseConnection instance = null;

    private DataBaseConnection() throws SQLException {
        final String databaseURL = "jdbc:oracle:thin:@localhost:1521:XE";
        final String username = "MOVIE";
        final String password = "MOVIE";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            this.connection = DriverManager.getConnection(databaseURL, username, password);
        }catch (ClassNotFoundException exception) {
            System.out.println("Connection failed: " + exception.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DataBaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DataBaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DataBaseConnection();
        }
        return instance;
    }

    public void closeConnection() throws Exception {
        connection.close();
    }
}
