package com.example.cinemafx;

import bdd.ClientManager;
import bdd.SalleManager;
import bdd.SeanceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Client;
import models.Salle;
import models.Seance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private ChoiceBox<Salle> salleComboBox;

    @FXML
    private TextField horaire;

    @FXML
    private TextField date;

    @FXML
    private ListView<Seance> seances_list;

    @FXML
    private TextField salle1;

    @FXML
    private TextField places1;

    @FXML
    private ListView<Salle> salles_list;

    private ObservableList<Client> items = FXCollections.observableArrayList();
    private ObservableList<Seance> items2 = FXCollections.observableArrayList();
    private ObservableList<Salle> items3 = FXCollections.observableArrayList();




    @FXML
    public void initialize(){
        //lier items à la listeView au démarrage
        clients_List.setItems(items);
        seances_list.setItems(items2);
        salles_list.setItems(items3);
        salleComboBox.setItems(items3);
        loadSeances();
        loadClients();
        loadSalle();

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
        String placesStr = this.places.getText();  // Nombre de places demandées
        Salle salle = salleComboBox.getValue();
        String dateString = this.date.getText(); // exemple : "12/05/2024"
        String horaires = this.horaire.getText();

        if (film.isEmpty() || placesStr.isEmpty() || salle == null || dateString.isEmpty() || horaires.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs avec des informations valides");
            alert.showAndWait();
            return;
        }

        try {
            int placesDemandées = Integer.parseInt(placesStr); // Nombre de places demandées
            int placesRestantes = salle.getNbPlaces();  // Nombre de places disponibles dans la salle

            // Afficher les valeurs dans la console pour vérifier
            System.out.println("Salle sélectionnée : " + salle.getNomSalle());  // Affiche le nom de la salle
            System.out.println("Places restantes : " + placesRestantes);  // Affiche le nombre de places restantes
            System.out.println("Places demandées : " + placesDemandées);  // Affiche le nombre de places demandées

            if (placesDemandées > placesRestantes) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Pas assez de places disponibles dans la salle.");
                alert.showAndWait();
                return;
            }

            // Convertir la date FR (dd/MM/yyyy) en format SQL (yyyy-MM-dd)
            SimpleDateFormat frenchFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = frenchFormat.parse(dateString);
            String formattedDate = sqlFormat.format(date);

            // Ajouter la séance
            SeanceManager cm = new SeanceManager();
            cm.addSeance(film, placesStr, String.valueOf(salle.getId()), horaires, formattedDate);

            // Mettre à jour les places restantes dans la salle
            int nouvellesPlaces = placesRestantes - placesDemandées;
            salle.setNbPlaces(nouvellesPlaces);  // Mise à jour dans l'objet salle

            // Log la mise à jour avant de l'envoyer à la BDD
            System.out.println("Mise à jour des places de la salle : " + salle.getNomSalle() + " -> " + nouvellesPlaces + " places restantes");

            // Mettre à jour la salle dans la base de données
            SalleManager sm = new SalleManager();
            sm.updateSalle(salle);

            // Recharger les listes pour afficher les nouvelles données
            this.loadSeances();
            this.loadSalle();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le nombre de places doit être un entier.");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de format");
            alert.setHeaderText(null);
            alert.setContentText("La date doit être au format jj/MM/aaaa");
            alert.showAndWait();
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

    public void add_salle(ActionEvent event){
        String salle1 = this.salle1.getText();
        String places1 = this.places1.getText();


        if (salle1.isEmpty() || places1.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs avec des informations valides");
            alert.showAndWait();
        } else {
            SalleManager sm = new SalleManager();
            sm.addSalle(salle1, places1);
            this.loadSalle();
        }
    }

    public void loadSalle() {
        this.items3.clear();

        try {
            SalleManager sm = new SalleManager();
            ResultSet rs = sm.getSalle();


            while (rs.next()) {
                String salle1 = rs.getString("nom_salle");
                int id = rs.getInt("id");
                String places1 = rs.getString("nb_places_total");

                Salle salle = new Salle(id, salle1, places1);;
                this.items3.add(salle);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

    @FXML
    public void delete_salle(ActionEvent event) {
        Salle salle = (Salle) salles_list.getSelectionModel().getSelectedItem();
        if (salle != null) {
            System.out.println(salle.getId());
            SalleManager sm= new SalleManager();
            sm.deleteSalle(salle.getId());
            this.loadSalle();
        }

    }
}
