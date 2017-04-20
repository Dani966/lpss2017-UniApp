package it.unicampania.uniapp.datamodel;

/**
 * Generica classe
 * Created by pasquale on 20/04/17.
 */

public class Studente {

    private String matricola;
    private String cognome;
    private String nome;
    private int crediti;

    /**
     * Costruttore
     */
    public Studente() {
    }

    /**
     * Costruttore
     * @param matricola matricola dello studente
     * @param cognome cognome
     * @param nome nome di battesimo
     * @param crediti maturati
     */
    public Studente(String matricola, String cognome, String nome, int crediti) {
        this.matricola = matricola;
        this.cognome = cognome;
        this.nome = nome;
        this.crediti = crediti;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCrediti() {
        return crediti;
    }

    public void setCrediti(int crediti) {
        this.crediti = crediti;
    }
}
