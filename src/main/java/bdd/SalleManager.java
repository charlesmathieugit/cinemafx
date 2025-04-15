package bdd;

import java.sql.*;

public class SalleManager {

        public void addSalle( String nom_salle, String nb_places_total) {
            BDDManager bddManager = new BDDManager();
            Connection Connection = bddManager.connection();
            String sql_request = "INSERT INTO salle (nom_salle, nb_places_total) VALUES (?, ?)";
            try {
                PreparedStatement pstmt = Connection.prepareStatement(sql_request);
                pstmt.setString(1, nom_salle);
                pstmt.setString(2, nb_places_total);
                pstmt.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }





        public ResultSet getSalle(){
            BDDManager bdd = new BDDManager();
            Connection connection = bdd.connection();
            ResultSet rs = null;
            String sql_request = "SELECT * FROM salle";
            try {
                Statement stmt = connection.createStatement();
                rs = stmt.executeQuery(sql_request);
                //System.out.println(rs);
                return rs;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public void deleteSalle(int id) {
            BDDManager bdd = new BDDManager();
            Connection connection = bdd.connection();
            String sql_request = "DELETE FROM salle WHERE id = ?";
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

    public void updateNbPlacesSalle(int salleId, int newNbPlaces) {
        try {
            BDDManager bdd = new BDDManager();
            Connection connection = bdd.connection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE salle SET nb_places_total = ? WHERE id = ?");
            stmt.setInt(1, newNbPlaces);
            stmt.setInt(2, salleId);
            stmt.executeUpdate();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getNbPlaces(int salleId) {
        int nbPlaces = -1;
        try {
            BDDManager bdd = new BDDManager();
            Connection connection = bdd.connection();
            PreparedStatement stmt = connection.prepareStatement("SELECT nb_places_total FROM salle WHERE id = ?");
            stmt.setInt(1, salleId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                nbPlaces = rs.getInt("nb_places_total");
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nbPlaces;
    }

    public void updateSalle(models.Salle salle) {
        updateNbPlacesSalle(salle.getId(), salle.getNbPlaces());
    }

    }

