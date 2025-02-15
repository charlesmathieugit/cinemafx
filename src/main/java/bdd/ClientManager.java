package bdd;

import java.sql.*;

public class ClientManager {

    public void addClient( String name, String birthdate, String adresse, String email) {
        BDDManager bddManager = new BDDManager();
        Connection Connection = bddManager.connection();
        String sql_request = "INSERT INTO clients (name, birthdate, adresse, email) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = Connection.prepareStatement(sql_request);
            pstmt.setString(1, name);
            pstmt.setString(2, birthdate);
            pstmt.setString(3, adresse);
            pstmt.setString(4, email);

            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public ResultSet getClients(){
        BDDManager bdd = new BDDManager();
        Connection connection = bdd.connection();
        ResultSet rs = null;
        String sql_request = "SELECT * FROM clients";
        try {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(sql_request);
            //System.out.println(rs);
            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteClient(int id) {
        BDDManager bdd = new BDDManager();
        Connection connection = bdd.connection();
        String sql_request = "DELETE FROM clients WHERE id = ?";
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
