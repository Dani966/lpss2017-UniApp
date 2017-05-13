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
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Autenteciazione Firebase
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "login effettuato: " + user.getUid());
                } else {
                    Log.d(TAG, "Logout effettuato");
                }
            }
        };

        listaStudenti = (ListView)findViewById(R.id.listaStudenti);
        adapter = new StudentiAdapter(this);
        adapter.update(archivio.elencoStudenti());
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
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null)
            mAuth.removeAuthStateListener(mAuthListener);
    }
}
