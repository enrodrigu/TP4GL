package fr.erodrigu.demo;

import java.util.Objects;

public class Comptes {
    private final int numero;
    private final Personne titulaire;
    private float solde;
    private float decouvert;
    private float decouvertMax;
    private float debitMax;

    public Comptes(int numero, Personne titulaire) throws IllegalArgumentException{
        this.titulaire = titulaire;
        this.numero = numero;
        solde = 0.0F;
        decouvert = .0F;
        decouvertMax = 800.0F;
        debitMax = 1000.0F;
    }

    public Comptes(int numero, Personne titulaire, float solde) throws IllegalArgumentException{
        if (solde<0) throw new IllegalArgumentException();
        this.numero = numero;
        this.titulaire = titulaire;
        this.solde = solde;
        decouvertMax = 800.0F;
        debitMax = 1000.0F;

    }

    public Comptes(int numero, Personne titulaire, float solde, float decouvertMax, float debitMax) throws IllegalArgumentException{
        if (solde<0 || decouvertMax<0 || debitMax<0) throw new IllegalArgumentException();
        this.numero = numero;
        this.titulaire = titulaire;
        this.solde = solde;
        this.decouvertMax = decouvertMax;
        this.debitMax = debitMax;
    }

    public void crediter(float amount) throws IllegalArgumentException{
        if(amount<0) throw new IllegalArgumentException();
        solde += amount;
        if(this.situation().equals("à découvert")){
            decouvert += amount;
            if(decouvert>0){
                decouvert=0;
            }
        }
    }

    public void debiter(float amount) throws IllegalArgumentException{
        if(amount<0) throw new IllegalArgumentException();
        if(decouvert+amount>decouvertMax){
            System.out.println("Trop de découvert");
        }
        else {
            solde-=amount;
            if(solde<0){
                decouvert = -solde;
            }
        }
    }

    public void virer(float amount, Comptes destinataire) throws IllegalArgumentException{
        if(amount<0) throw new IllegalArgumentException();
        if(!(decouvert+amount>decouvertMax)){
            this.debiter(amount);
            destinataire.crediter(amount);
        }
    }

    public String situation(){
        if(decouvert>0){
            return "à découvert";
        }
        else return "Sans découvert";
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    public void setDecouvert(float decouvert) {
        this.decouvert = decouvert;
    }

    public void setDecouvertMax(float decouvertMax) {
        this.decouvertMax = decouvertMax;
    }

    public void setDebitMax(float debitMax) {
        this.debitMax = debitMax;
    }

    public int getNumero() {
        return numero;
    }

    public Personne getTitulaire() {
        return titulaire;
    }

    public float getSolde() {
        return solde;
    }

    public float getDecouvert() {
        return decouvert;
    }

    public float getDecouvertMax() {
        return decouvertMax;
    }

    public float getDebitMax() {
        return debitMax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comptes comptes = (Comptes) o;
        return getNumero() == comptes.getNumero() && Float.compare(getSolde(), comptes.getSolde()) == 0 && Float.compare(getDecouvert(), comptes.getDecouvert()) == 0 && Float.compare(getDecouvertMax(), comptes.getDecouvertMax()) == 0 && Float.compare(getDebitMax(), comptes.getDebitMax()) == 0 && Objects.equals(getTitulaire(), comptes.getTitulaire());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumero(), getTitulaire(), getSolde(), getDecouvert(), getDecouvertMax(), getDebitMax());
    }
}
