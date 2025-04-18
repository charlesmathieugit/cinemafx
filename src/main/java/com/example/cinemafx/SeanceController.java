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
    private TextField horaire;

    @FXML
    private TextField date;

    @FXML
    private ListView<Seance> seances_list;


    private ObservableList<Seance> items2 = FXCollections.observableArrayList();


    @FXML
    public void initialize(){
        //lier items à la listeView au démarrage
        seances_list.setItems(items2);
        loadSeances();
    }


    public void loadSeances() {
        this.items2.clear();

        try {
            SeanceManager cm = new SeanceManager();
            ResultSet rs = cm.getSeances();


            while (rs.next()) {
                String film = rs.getString("film");
                String client = rs.getString("client");
                int id = rs.getInt("id");
                String salle = rs.getString("salle");
                String places = rs.getString("places");

                Seance seance = new Seance(id, client, film, places,salle);
                this.items2.add(seance);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

    // Charger des données de séances (exemple de séances)
}