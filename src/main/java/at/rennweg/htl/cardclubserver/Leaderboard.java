package at.rennweg.htl.cardclubserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for handling leaderboard MySQL database connections
 *
 * @author Raven Burkard
 */
public class Leaderboard {
    /**
     * Url of the database
     */
    public static String databaseUrl = "";

    /**
     * Database user for retrieving and saving leaderboard data
     */
    public static String databaseUser = "";

    /**
     * Password for the specified user
     */
    public static String databasePassword = "";

    /**
     * Get the MySQL connection
     *
     * @return connection to the database
     * @throws SQLException if one of the parameters is invalid
     */
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
    }

    /**
     * Create a new database if needed
     */
    public static void setupDatabase() {
        try {
            Connection connection = getConnection();

            connection.prepareStatement("CREATE TABLE IF NOT EXISTS CCLeaderboard ("
                    + "id VARCHAR(64) PRIMARY KEY, "
                    + "username VARCHAR(64), "
                    + "wins INT, "
                    + "losses INT"
                    + ");").executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the leaderboard from the database
     *
     * @param amount how many entries to get
     * @return the leaderboard entries
     */
    public static List<User> getLeaderboard(int amount) {
        final int maxEntries = 1000;
        List<User> users = new ArrayList<>();

        try {
            if (amount > maxEntries) {
                amount = maxEntries;
            }

            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM CCLeaderboard ORDER BY wins DESC LIMIT ?;");
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

    /**
     * Add a new win to the leaderboard
     *
     * @param user user to add the win to
     */
    public static void leaderBoardWin(User user) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO CCLeaderboard"
                    + " (id, username, wins, losses)"
                    + " VALUES (?, ?, 1, 0)"
                    + " ON DUPLICATE KEY UPDATE"
                    + " username = VALUES(username),"
                    + " wins = wins + 1;");

            statement.setString(1, user.getId());
            statement.setString(2, user.getName());

            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a new loss to the leaderboard
     *
     * @param user user to add the loss to
     */
    public static void leaderBoardLoss(User user) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO CCLeaderboard"
                    + " (id, username, wins, losses)"
                    + " VALUES (?, ?, 0, 1)"
                    + " ON DUPLICATE KEY UPDATE"
                    + " username = VALUES(username),"
                    + " losses = losses + 1;");

            statement.setString(1, user.getId());
            statement.setString(2, user.getName());

            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
