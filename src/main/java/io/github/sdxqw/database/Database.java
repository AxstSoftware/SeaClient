package io.github.sdxqw.database;

import com.google.common.collect.Lists;
import io.github.sdxqw.utils.interfaces.ILogger;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Database {
    public static final Database INSTANCE;
    protected final List<Connection> activeConnections;

    public Database() {
        this.activeConnections = Lists.newArrayList();
    }

    public Connection initConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:seaclient.db";
            java.sql.Connection connection = DriverManager.getConnection(url);
            createCosmetics( connection );
            return new Connection(connection);
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            ILogger.error("Connection failed!");
            return null;
        }
    }

    public void createCosmetics( java.sql.Connection connection) {
        Statement statement;
        try {
            statement = connection.createStatement();
            String createTable = "CREATE TABLE IF NOT EXISTS cosmetics" +
                    "(ID INT PRIMARY KEY NOT NULL," +
                    "data TEXT NOT NULL," +
                    "name TEXT," +
                    "REAL)";
            statement.executeUpdate(createTable);
        } catch (SQLException e) {
            throw new RuntimeException( e );
        }
    }

    public void insertCapes(String data, String name) {
        String sql = "INSERT INTO cosmetics(data,name) VALUES(?,?)";
        try (PreparedStatement prepareStatement = Database.INSTANCE.initConnection().prepareStatement(sql)) {
            prepareStatement.setString(1, data);
            prepareStatement.setString(2, name);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static {
        INSTANCE = new Database();
    }

    public static class Connection {
        public final java.sql.Connection connection;

        public Connection(final java.sql.Connection connection) {
            this.connection = connection;
            Database.INSTANCE.activeConnections.add(this);
        }

        public PreparedStatement prepareStatement(final String statement) throws SQLException {
            return this.connection.prepareStatement(statement);
        }
    }
}
