package it.unicampania.uniapp.datamodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta il datamodel dell'applicazione.
 * Si dovrà interfacciare con il database
 * Prima versione: simulazione con dati locali
 * Created by pasquale on 20/04/17.
 */

public class DataStore {

    // Lista locale degli studenti
    // Todo: da eliminare
    private ArrayList<Studente> studenti;

    /**
     * Costruttore
     */
    public DataStore() {
        studenti = new ArrayList<>();

        // Dati fittizi per effettuare le prove
        // Todo: da eliminare
        Studente a = new Studente("A1800032", "Rossi", "Mario", 28);
        Studente b = new Studente("A1800099", "Bianchi", "Luigi", 28);
        Studente c = new Studente("A1800127", "Verdi", "Giovanni", 28);
        studenti.add(a);
        studenti.add(b);
        studenti.add(c);
    }

    /**
     * Aggiunge uno studente al database
     * @param studente studente da aggiungere
     */
    public void aggiungiStudente(Studente studente) {
        studenti.add(studente);
    }

    /**
     * Aggiorna i dati dello studente utilizzando la matricola come riferimento
     * @param studente dati da aggiornare
     */
    public void aggiornaStudente(Studente studente) {
        int posizione = getStudenteIndex(studente.getMatricola());
        if (posizione == -1)
            aggiungiStudente(studente);
        else
            studenti.set(posizione, studente);
    }

    /**
     * Elimina uno studente
     * @param matricola matricola dello studente da eliminare
     */
    public void eliminaStudente(String matricola) {
        int posizione = getStudenteIndex(matricola);
        if (posizione != -1)
            studenti.remove(posizione);
    }

    /**
     * Legge lo studente individuato dalla matricola passata
     * @param matricola matricola da cercare
     * @return Studente letto, oppure null nel caso non venga trovato
     */
    public Studente leggiStudente(String matricola) {
        int posizione = getStudenteIndex(matricola);
        if (posizione == -1)
            return null;
        else
            return studenti.get(posizione);
    }

    /**
     * Ottiene l'elenco di tutti gli studenti
     * Todo: Attenzione il metodo è potenzialmente pericoloso. Potrebbe restituire troppi dati!
     * @return Lista di studenti
     */
    public List<Studente> elencoStudenti() {
        return studenti;
    }

    /**
     * Restituisce il numero di studenti presenti nel database
     * @return numero di studenti
     */
    public int numeroStudenti() {
        return studenti.size();
    }

    /**
     * Restituisce l'indice di uno studente nell'array partendo dalla matricola
     * @param matricola matricola da cercare
     * @return indice dello studente. -1 se non trovato
     */
    private int getStudenteIndex(String matricola) {
        boolean trovato = false;
        int index = 0;
        while (!trovato && index < studenti.size()) {
            if (studenti.get(index).getMatricola().equals(matricola)) {
                return index;
            }
            ++index;
        }
        return -1;
    }
}
