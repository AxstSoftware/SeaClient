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
    public String url = "jdbc:sqlite:seaclient.db";
    protected final List<Connection> activeConnections;

    public Database() {
        this.activeConnections = Lists.newArrayList();
    }

    public java.sql.Connection initConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            java.sql.Connection connection = DriverManager.getConnection(url);
            createCosmetics( connection );
            insertCapes( "SuchSpeed", "cape" );
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            ILogger.error("Connection failed!");
        }
        return null;
    }

    public void createCosmetics(java.sql.Connection connection) {
        Statement statement;
        try {
            statement = connection.createStatement();
            String createTable = "CREATE TABLE IF NOT EXISTS cosmetics(" +
                    "`player_name` TEXT NOT NULL," +
                    "`cape_name` TEXT NOT NULL" +
                    ");";
            statement.executeUpdate(createTable);
        } catch (SQLException e) {
            throw new RuntimeException( e );
        }
    }

    public void insertCapes(String player, String cape) {
        String sql = "INSERT INTO cosmetics(`player_name`,`cape_name`) VALUES(?,?)";
        try (PreparedStatement prepareStatement = Database.INSTANCE.initConnection().prepareStatement(sql)) {
            prepareStatement.setString(1, player);
            prepareStatement.setString(2, cape);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static {
        INSTANCE = new Database();
    }
}
