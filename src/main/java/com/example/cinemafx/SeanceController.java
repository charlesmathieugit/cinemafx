package com.example.cinemafx;

import bdd.ClientManager;
import bdd.SeanceManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Seance;

import java.sql.ResultSet;
import java.sql.SQLException;


public class SeanceController {

    // Déclaration des éléments de l'interface


    // Liste observable des séances


    @FXML
    private TextField film;


    @FXML
    private TextField places;

    @FXML
    private TextField salle;

    @FXML
    private ListView<Seance> seances_list;


    private ObservableList<Seance> items2 = FXCollections.observableArrayList();


    @FXML
    public void initialize(){
        //lier items à la listeView au démarrage
        seances_list.setItems(items2);
        loadSeances();
    }

    @FXML
    public void add_seance(ActionEvent event) {
        String film = this.film.getText();
        String places = this.places.getText();
        String salle = this.salle.getText();

        if (film.isEmpty() || places.isEmpty() || salle.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs avec des informations valides");
            alert.showAndWait();
        } else {
            SeanceManager cm = new SeanceManager();
            cm.addSeance(film, places, salle);
            this.loadSeances();
        }

    }
    public void loadSeances() {
        this.items2.clear();

        try {
            SeanceManager cm = new SeanceManager();
            ResultSet rs = cm.getSeances();


            while (rs.next()) {
                String film = rs.getString("film");
                int id = rs.getInt("id");
                String salle = rs.getString("salle");
                String places = rs.getString("places");

                Seance seance = new Seance(id, film, places,salle);
                this.items2.add(seance);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

    // Charger des données de séances (exemple de séances)
}