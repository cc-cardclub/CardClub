package at.rennweg.htl.cardclubserver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Leaderboard {
    public static String databaseUrl = "";
    public static String databaseUser = "";
    public static String databasePassword = "";

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
    }

    public static void setupDatabase() {
        try {
            Connection connection = getConnection();

            connection.prepareStatement("CREATE TABLE IF NOT EXISTS CCLeaderboard (" +
                    "id VARCHAR(64) PRIMARY KEY, " +
                    "username VARCHAR(64), " +
                    "wins INT, " +
                    "losses INT" +
                    ");").executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<User> getLeaderboard(int amount) {
        List<User> users = new ArrayList<>();

        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM CCLeaderboard" +
                    " ORDER BY wins DESC LIMIT ?;");
            statement.setInt(1, amount);

            ResultSet leaderboard = statement.executeQuery();

            while (leaderboard.next()) {
                users.add(new User(leaderboard.getString("username"),
                        leaderboard.getString("id"),
                        leaderboard.getInt("wins"),
                        leaderboard.getInt("losses")));
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public static void leaderBoardWin(User user) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO CCLeaderboard" +
                    " (id, username, wins, losses)" +
                    " VALUES (?, ?, 1, 0)" +
                    " ON DUPLICATE KEY UPDATE" +
                    " username = VALUES(username)," +
                    " wins = wins + 1;");

            statement.setString(1, user.getId());
            statement.setString(2, user.getName());

            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void leaderBoardLoss(User user) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO CCLeaderboard" +
                    " (id, username, wins, losses)" +
                    " VALUES (?, ?, 0, 1)" +
                    " ON DUPLICATE KEY UPDATE" +
                    " username = VALUES(username)," +
                    " losses = losses + 1;");

            statement.setString(1, user.getId());
            statement.setString(2, user.getName());

            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
