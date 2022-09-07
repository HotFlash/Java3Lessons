package chat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import chat.auth.AuthService;
import chat.auth.User;
import com.newage.command.CommandType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogService {

    private static final Logger LOGGER = LogManager.getLogger(AuthService.class);
    private static Connection connection;
    private static Statement statement;

    private static User user;

    public void start() {
        try {
            LOGGER.info("LogService has been started");
            connect();
            createTable();
        } catch (SQLException e) {
            LOGGER.error(e);
            LOGGER.debug(e.toString(), e);
        }
    }

    public void stop() {
        LOGGER.info("LogService has been stopped");
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            LOGGER.debug(e.toString(), e);
        }
    }

    public void addMessage(CommandType type, String  login, User username, String text) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO log (type, login, name, text) VALUES (?, ?, ?, ?)")) {
            ps.setObject(1, type);
            ps.setString(2, login);
            ps.setObject(3, username);
            ps.setString(4, text);
            ps.executeUpdate();
            LOGGER.debug("Message added to db");
        }
    }

//    public ArrayList<Message> readLog(int size) throws SQLException {
//        ArrayList<Message> result = new ArrayList<>();
//        String sql = "SELECT * FROM (SELECT * FROM log ORDER BY id DESC LIMIT " + size + ") t ORDER BY id";
//        //String sql = "SELECT * FROM log LIMIT " + count;
//        try (ResultSet rs = statement.executeQuery(sql)) {
//            while (rs.next()) {
//                Message message = new Message();
//                message.setMessageType(MessageType.fromInt(rs.getInt("type")));
//                message.setLogin(rs.getString("login"));
//                message.setName(rs.getString("name"));
//                message.setText(rs.getString("text"));
//                result.add(message);
//            }
//        }
//        return result;
//    }

    private void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:server/db/history_db");
        statement = connection.createStatement();
    }

    private void createTable() throws SQLException {
        statement.executeUpdate("""
        CREATE TABLE IF NOT EXISTS log (
            id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\s
            type INTEGER NOT NULL,\s
            login TEXT,\s
            name TEXT,\s
            text TEXT\s
        );""");
    }

}
