package com.example.cinemafx;

import bdd.ClientManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientController {

    // Déclaration des éléments de l'interface
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




    // Liste observable des clients
    private ObservableList<Client> items = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        //lier items à la listeView au démarrage
        clients_List.setItems(items);
        loadClients();
    }
    // Charger des données clients (exemple de clients)


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
    void delete_client(ActionEvent event) {
//copier coller recepter
    }

}
