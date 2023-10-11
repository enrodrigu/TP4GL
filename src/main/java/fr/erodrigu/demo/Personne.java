package fr.erodrigu.demo;

import java.util.Objects;

public class Personne {
    private String nom;
    private String prenom;
    private String adresse;

    public Personne(String nom, String prenom){
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = "";
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = (Personne) o;
        return Objects.equals(getNom(), personne.getNom()) && Objects.equals(getPrenom(), personne.getPrenom()) && Objects.equals(getAdresse(), personne.getAdresse());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNom(), getPrenom(), getAdresse());
    }
}
