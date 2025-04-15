package models;

public class Salle {

    private int id;
    private String nomSalle;
    private int nbPlaces;

    // Constructeur
    public Salle(int id, String nomSalle, String nbPlaces) {
        this.id = id;
        this.nomSalle = nomSalle;
        try {
            this.nbPlaces = Integer.parseInt(nbPlaces.trim());
        } catch (NumberFormatException e) {
            this.nbPlaces = 0;
        }
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNomSalle() {
        return nomSalle;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    // Setter
    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    @Override
    public String toString() {
        return nomSalle + " - " + nbPlaces; // Affiche uniquement le nom dans la ChoiceBox
    }
}