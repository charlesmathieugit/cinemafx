package bdd;

import java.sql.*;

public class SeanceManager {

    public void addSeance( String film, String client, String salle, String places, String horaires, String date) {
        BDDManager bddManager = new BDDManager();
        Connection Connection = bddManager.connection();
        String sql_request = "INSERT INTO seances (film, client, salle, places, horaires, date) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = Connection.prepareStatement(sql_request);
            pstmt.setString(1, film);
            pstmt.setString(2, client);
            pstmt.setString(3, salle);
            pstmt.setString(4, places);
            pstmt.setString(5, horaires);
            pstmt.setString(6, date);

            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }





    public ResultSet getSeances(){
        BDDManager bdd = new BDDManager();
        Connection connection = bdd.connection();
        ResultSet rs = null;
        String sql_request = "SELECT * FROM seances";
        try {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(sql_request);
            //System.out.println(rs);
            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteSeance(int id) {
            BDDManager bdd = new BDDManager();
            Connection connection = bdd.connection();
            String sql_request = "DELETE FROM seances WHERE id = ?";
            try {
                PreparedStatement pstmt = connection.prepareStatement(sql_request);
                pstmt.setInt(1, id);
                pstmt.execute();
            } catch

            (SQLException e)

            {
                throw new RuntimeException(e);
            }
        }

}
