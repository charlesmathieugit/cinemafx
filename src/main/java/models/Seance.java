package models;

public class Seance {

    private String film;

    private String client;

    private String salle;

    private String places;

    private String date;

    private String horaire;

    private int id;



    public Seance(int id, String film, String client, String salle, String places) {
        this.id = id;
        this.film = film;
        this.client = client;
        this.salle = salle;
        this.places = places;

    }

    public String getFilm() {return film;}

    public String getSalle() {
        return salle;
    }

    public String getPlaces() {
        return places;
    }

        @Override
        public String toString() {
            return "Film : " + film +
                    " | Salle : " + salle +
                    " | Places : " + places +
                    " | Date : " + date + " - " + horaire;
        }

    public int getId(){
        return this.id;
    }

    }