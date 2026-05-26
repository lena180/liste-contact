package com.example.listecontact;

import jakarta.persistence.*;

@Entity
@Table(name = "contacts") // nom de la table

public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prenom;
    private String nom;
    private String email;
    private String telephone;

    public Contact () {} // constructeur sans parametre

    public Contact (String prenom, String nom, String email, String telephone) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
    }

    //les getters et les setters
    public Long getId() { return id;}
    public String getPrenom() {return prenom;}
    public void setPrenom(String prenom){ this.prenom = prenom;}

    public String getNom() { return nom;}
    public void setNom(String nom){ this.nom = nom;}

    public String getEmail() {return email;}
    public void setEmail(String email) { this.email = email;}

    public String getTelephone() { return telephone;}
    public void setTelephone(String telephone) { this.telephone = telephone;}

}
