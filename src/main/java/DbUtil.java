import java.sql.*;

public class DbUtil {
    private static final String DB_HOSTNAME = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "coderslab";

    public static Connection connect(String database) throws SQLException {
        return DriverManager.getConnection(getUrl(database), DB_USERNAME, DB_PASSWORD);
    }

    private static String getUrl(String database) {
        String parameters = "useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
        return String.format("jdbc:mysql://%s:%s/%s?%s", DB_HOSTNAME, DB_PORT, database, parameters);
    }

    // MySQL QUERIES

    private static final String DELETE_QUERY = "DELETE * FROM tableName where id = ?";
    private static final String LIST_USER_WITH_ID = "SELECT * FROM tableName WHERE id = ?";
    private static final String LIST_ALL = "SELECT * FROM tableName";
    private static final String UPDATE = "UPDATE tableName SET ? = ? WHERE id = ?";
    private static final String INSERT = "INSERT INTO users (email, username, password) VALUES (?, ?, ?)";


    // METHODS

    public static void remove(Connection connection, String tableName, int id) {
        try (PreparedStatement statement =
                     connection.prepareStatement(DELETE_QUERY.replace("tableName", tableName));) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void listUserWithId(Connection connection, String tableName, int id) {
        try (PreparedStatement statement =
                     connection.prepareStatement(LIST_USER_WITH_ID.replace("tableName", tableName));) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void listAll(Connection connection, String tableName) {
        try (PreparedStatement statement =
                     connection.prepareStatement(LIST_ALL.replace("tableName", tableName));) {

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateUserData(Connection connection, String tableName, int id, String... params) {
        try (PreparedStatement statement =
                     connection.prepareStatement(UPDATE.replace("tableName", tableName));) {
            statement.setString(1, params[0]);
            statement.setString(2, params[1]);
            statement.setInt(3, id);

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertUser(Connection connection, String tableName,  String... params) {
        try (PreparedStatement statement =
                     connection.prepareStatement(INSERT.replace("tableName", tableName));) {
            statement.setString(1, params[0]);
            statement.setString(2, params[1]);
            statement.setString(3, params[2]);

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
