package io.github.sdxqw.database;

import io.github.sdxqw.utils.interfaces.ILogger;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SeaDatabase {
    public static final SeaDatabase INSTANCE;
    public String url = "jdbc:sqlite:seaclient.db";
    public Connection connection;
    public Statement statement;

    public void initConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            String createTable = "CREATE TABLE IF NOT EXISTS cosmetics_seaclient(" +
                    "cape_name TEXT NOT NULL PRIMARY KEY," +
                    "player_name TEXT NOT NULL"+
                    ");";
            statement.executeUpdate(createTable);
            insertCapes( "cape" );
            insertName( "SuchSpeed" );
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            ILogger.error("Connection failed!");
        }
    }

    public void insertCapes(String cape) {
        String sql = "INSERT INTO cosmetics_seaclient(cape_name) VALUES(?)";
        try (PreparedStatement prepareStatement = connection.prepareStatement(sql)) {
            prepareStatement.setString(1, cape);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertName(String name) {
        String sql = "INSERT INTO cosmetics_seaclient(player_name) VALUES(?)";
        try (PreparedStatement prepareStatement = connection.prepareStatement(sql)) {
            prepareStatement.setString(1, name);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static {
        INSTANCE = new SeaDatabase();
    }
}
