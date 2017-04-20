package it.unicampania.uniapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import it.unicampania.uniapp.datamodel.DataStore;

public class MainActivity extends AppCompatActivity {

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
    }
}
