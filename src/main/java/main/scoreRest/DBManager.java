/*
 * Copyright (C) 2020 pierpaolo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package main.scoreRest;

import entities.Score;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author pierpaolo
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

    public void connect() throws SQLException {
        dbprops = new Properties();
        dbprops.setProperty("user", "user");
        dbprops.setProperty("password", "1234");
        conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/db/store", dbprops);
        Statement stm = conn.createStatement();
//        stm.executeUpdate("TRUNCATE TABLE scores");
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
