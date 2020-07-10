package main.scoreRest;

import entities.Score;

import java.sql.*;
import java.util.Properties;

/**
 * Il DBManager è una classe singleton che gestisce il database.
 * <br>Esso è richiamato esclusivamente tramite API REST
 */
public class DBManager {

    private static DBManager instance;

    public static final String CREATE_TABLE_ITEM = "CREATE TABLE IF NOT EXISTS scores (id INTEGER PRIMARY KEY AUTO_INCREMENT,nome VARCHAR(128), points INTEGER , totalPoints INTEGER ,tempo TIME , game VARCHAR(128))";

    private Connection conn;

    private Properties dbprops;

    private DBManager() {

    }

    public synchronized static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
            try {
                instance.connect();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
        return instance;
    }

    private void connect() throws SQLException {
        dbprops = new Properties();
        dbprops.setProperty("user", "user");
        dbprops.setProperty("password", "1234");
        conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/db/store", dbprops);
        Statement stm = conn.createStatement();
//        stm.executeUpdate("DROP TABLE scores");
        stm.executeUpdate(CREATE_TABLE_ITEM);
        stm.close();
    }

    private void reconnect() throws SQLException {
        if (conn != null && !conn.isValid(0)) {
            conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/db/store", dbprops);
        }
    }

    public void insertScore(Score score) throws SQLException {
        reconnect();
        PreparedStatement pstm = conn.prepareStatement("INSERT INTO scores VALUES (null,?,?,?,?,?)");
        pstm.setString(1, score.getName());
        pstm.setInt(2, score.getPoints());
        pstm.setInt(3, score.getTotalPoints());
        pstm.setTime(4, score.getFinalTime());
        pstm.setString(5, score.getGameName());
        pstm.executeUpdate();
        pstm.close();
    }

    public Score getScore(int id) throws SQLException {
        reconnect();
        PreparedStatement stm = conn.prepareStatement("SELECT nome,points,totalPoints,tempo,game FROM scores WHERE id = ?");
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        Score score = null;
        if (rs.next()) {
            score = new Score(rs.getTime(4),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getString(5));
            score.setName(rs.getString(1));
        }
        rs.close();
        stm.close();
        return score;
    }

}
