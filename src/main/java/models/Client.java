package models;

public class Client {

    private String name;
    private String birthdate;

    private String adresse;


    private String email;

    private int id;


    public Client(int id, String name, String email, String adresse, String birthdate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.adresse = adresse;
        this.birthdate = birthdate;

    }

    public String getName() {return name;}

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return this.name + " - " + this.email ;
    }

    public int getId(){
        return this.id;
    }
}
