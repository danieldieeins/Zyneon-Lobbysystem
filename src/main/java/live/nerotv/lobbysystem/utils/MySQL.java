package live.nerotv.lobbysystem.utils;

import live.nerotv.api.utils.storage.types.SQLite;
import live.nerotv.lobbysystem.api.ConfigAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    public static String host = ConfigAPI.CFG.getString("Core.Settings.MySQL.host");
    public static String port = ConfigAPI.CFG.getString("Core.Settings.MySQL.port");
    public static String database = ConfigAPI.CFG.getString("Core.Settings.MySQL.database");
    public static String username = ConfigAPI.CFG.getString("Core.Settings.MySQL.username");
    public static String password = ConfigAPI.CFG.getString("Core.Settings.MySQL.password");
    public static boolean isConnected() {
        return (con != null);
    }
    public static Connection getConnection() {
        return con;
    }
    public static Connection con;

    public static SQLite sql = new SQLite("Nero/SQL/lobby.sql");

    public static void connect() {
        if (!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database,username,password);
            } catch (SQLException e) {
                con = sql.getConnection();
            }
        }
    }

    public static void disconnect() {
        if (isConnected()) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        con = null;
    }
}