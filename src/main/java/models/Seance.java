package models;

public class Seance {

    private String film;
    private String salle;

    private String places;

    private String date;

    private String horaire;

    private int id;



    public Seance(int id, String film, String salle, String places) {
        this.id = id;
        this.film = film;
        this.salle = salle;
        this.places = places;

    }

    public String getFilm() {return film;}

    public String getSalle() {
        return salle;
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