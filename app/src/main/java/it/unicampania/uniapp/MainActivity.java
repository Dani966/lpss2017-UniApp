package it.unicampania.uniapp;

import android.content.ClipData;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import it.unicampania.uniapp.datamodel.DataStore;
import it.unicampania.uniapp.datamodel.Studente;

public class MainActivity extends AppCompatActivity {

    // Costanti
    private final static String EXTRA_STUDENTE = "studente";
    private final static String TAG = "UniApp";

    // Widget
    private ListView listaStudenti;

    // Adapter
    private StudentiAdapter adapter;

    // Data Store
    private DataStore archivio = new DataStore();

    // Autenticazione Firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Autenticazione Firebase
        mAuth = FirebaseAuth.getInstance();

        // Comportamento differenziato
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            // Utente non autenticato, voglio impedire l'accesso
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else {
            // Utente autenticato.
            // Nessuna operazione richiesta
        }

        listaStudenti = (ListView)findViewById(R.id.listaStudenti);
        adapter = new StudentiAdapter(this);

        archivio.iniziaOsservazioneStudenti(new DataStore.UpdateListener() {
            @Override
            public void studentiAggiornati() {
                adapter.update(archivio.elencoStudenti());
            }
        });

        listaStudenti.setAdapter(adapter);
        listaStudenti.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Studente studente = adapter.getItem(position);
                Intent intent = new Intent(view.getContext(), DettaglioStudenteActivity.class);
                intent.putExtra(EXTRA_STUDENTE, studente);
                startActivity(intent);
            }
        });

        Studente s = new Studente();
        s.setMatricola("A13008888");
        s.setCognome("Filini");
        s.setNome("Ragioner");
        s.setCrediti(1);
        archivio.aggiungiStudente(s);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        archivio.terminaOsservazioneStudenti();
    }
}
