package tn.esprit.kochbatiyakine.pidev;

/**
 * Created by Kochbati Yakine on 27/11/2017.
 */

public class User {
  private  int id;
   private String nom;
   private String mail;

    public User() {
    }

    public User(String nom, String mail) {
        this.nom = nom;
        this.mail = mail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
