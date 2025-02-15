package com.example.cinemafx;

import bdd.ClientManager;
import bdd.SeanceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import models.Client;
import models.Seance;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CinemaController {

    @FXML
    private Button add_client;

    @FXML
    private TextField adresse;

    @FXML
    private TextField birthdate;

    @FXML
    private ListView<Client> clients_List;

    @FXML
    private Button delete_client;

    @FXML
    private TextField email;

    @FXML
    private TextField name;

    @FXML
    private TextField film;


    @FXML
    private TextField places;

    @FXML
    private TextField salle;

    @FXML
    private ListView<Seance> seances_list;

    private ObservableList<Client> items = FXCollections.observableArrayList();
    private ObservableList<Seance> items2 = FXCollections.observableArrayList();


    @FXML
    public void initialize(){
        //lier items à la listeView au démarrage
        clients_List.setItems(items);
        seances_list.setItems(items2);
        loadSeances();
        loadClients();

    }
    @FXML
    public void add_client(ActionEvent event) {


        String name = this.name.getText();
        String adresse = this.adresse.getText();
        String email = this.email.getText();
        String birthdate = this.birthdate.getText();

        if (name.isEmpty() || adresse.isEmpty() || email.isEmpty() || birthdate.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs avec des informations valides");
            alert.showAndWait();
        } else if (!email.matches("^(.+)@(.+)$")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer une adresse email valide");
            alert.showAndWait();
        } else {
            ClientManager cm = new ClientManager();
            cm.addClient(name, adresse, email,birthdate);
            this.loadClients();
        }

    }
    public void loadClients() {
        this.items.clear();

        try {
            ClientManager cm = new ClientManager();
            ResultSet rs = cm.getClients();


            while (rs.next()) {
                String name = rs.getString("name");
                int id = rs.getInt("id");
                String adresse = rs.getString("adresse");
                String email = rs.getString("email");
                String birthdate = rs.getString("birthdate");

                Client client = new Client(id, name, adresse,email, birthdate);
                this.items.add(client);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);


        }

    }


    @FXML
    public void delete_client(ActionEvent event) {
        Client client = (Client) clients_List.getSelectionModel().getSelectedItem();
        if (client != null) {
            System.out.println(client.getId());
            ClientManager cm = new ClientManager();
            cm.deleteClient(client.getId());
            this.loadClients();
        }

    }
    @FXML
    public void delete_seance(ActionEvent event) {
        Seance seance = (Seance) seances_list.getSelectionModel().getSelectedItem();
        if (seance != null) {
            System.out.println(seance.getId());
            SeanceManager cm = new SeanceManager();
            cm.deleteSeance(seance.getId());
            this.loadSeances();
        }

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
}
