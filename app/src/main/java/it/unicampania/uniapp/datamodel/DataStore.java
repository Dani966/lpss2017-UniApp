package it.unicampania.uniapp.datamodel;

import android.provider.ContactsContract;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta il datamodel dell'applicazione.
 * Interfacciata con Firebase
 * Created by pasquale on 20/04/17.
 */

public class DataStore {

    // Costanti
    private final static String TAG = "DataStore";
    private final static String DB_STUDENTI = "studenti";
    private final static String KEY_COGNOME = "cognome";
    private final static String KEY_NOME = "nome";
    private final static String KEY_CREDITI = "crediti";

    private ValueEventListener listenerStudenti;

    // Lista locale degli studenti
    // Todo: da eliminare
    private ArrayList<Studente> studenti;

    /**
     * Costruttore
     */
    public DataStore() {
        studenti = new ArrayList<>();
    }

    public interface UpdateListener {
        void studentiAggiornati();
    }

    public void iniziaOsservazioneStudenti(final UpdateListener notifica) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(DB_STUDENTI);

        listenerStudenti = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                studenti.clear();
                for (DataSnapshot elemento:dataSnapshot.getChildren()) {
                    Studente studente = new Studente();
                    studente.setMatricola(elemento.getKey());
                    studente.setCognome(elemento.child(KEY_COGNOME).getValue(String.class));
                    studente.setNome(elemento.child(KEY_NOME).getValue(String.class));
                    studente.setCrediti(elemento.child(KEY_CREDITI).getValue(Integer.class));
                    studenti.add(studente);
                }
                notifica.studentiAggiornati();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        ref.addValueEventListener(listenerStudenti);
    }

    public void terminaOsservazioneStudenti() {
        if (listenerStudenti != null)
            FirebaseDatabase.getInstance().getReference(DB_STUDENTI).removeEventListener(listenerStudenti);
    }



    /**
     * Aggiunge uno studente al database
     * @param studente studente da aggiungere
     */
    public void aggiungiStudente(Studente studente) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(DB_STUDENTI).child(studente.getMatricola());
        ref.child(KEY_COGNOME).setValue(studente.getCognome());
        ref.child(KEY_NOME).setValue(studente.getNome());
        ref.child(KEY_CREDITI).setValue(studente.getCrediti());
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
