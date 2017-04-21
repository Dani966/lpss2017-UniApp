package it.unicampania.uniapp;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import it.unicampania.uniapp.datamodel.DataStore;
import it.unicampania.uniapp.datamodel.Studente;

public class MainActivity extends AppCompatActivity {

    // Costanti
    private final static String EXTRA_STUDENTE = "studente";

    // Widget
    private ListView listaStudenti;

    // Adapter
    private StudentiAdapter adapter;

    // Data Store
    private DataStore archivio = new DataStore();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
