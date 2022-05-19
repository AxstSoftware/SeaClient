package io.github.sdxqw.database;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SeaDatabase {
    public static final SeaDatabase INSTANCE;
    public String url = "jdbc:sqlite:seaclient.db";

    public void startDatabase() {
        createDb();
        createTable();
        insert( "cape", "cape_name" );
    }

    private void createDb() {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                conn.getMetaData();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() {
        final String createTable = "CREATE TABLE IF NOT EXISTS cosmetics_seaclient(" +
                "cape_name TEXT PRIMARY KEY NOT NULL ," +
                "player_name TEXT NOT NULL"+
                ");";
        try (Connection con = getConnection(); Statement statement = con.createStatement()) {
            statement.executeUpdate(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }

    public void insert(String cape, String name) {
        String sql = "INSERT INTO cosmetics_seaclient VALUES(?,?)";
        try (PreparedStatement prepareStatement = getConnection().prepareStatement(sql)) {
            prepareStatement.setString(1, cape);
            prepareStatement.setString(2, name);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static {
        INSTANCE = new SeaDatabase();
    }
}
