package it.unicampania.uniapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import it.unicampania.uniapp.datamodel.Studente;

public class DettaglioStudenteActivity extends AppCompatActivity {

    // Costanti
    private final static String EXTRA_STUDENTE = "studente";

    // Widget
    private TextView mMatricola;
    private TextView mCognome;
    private TextView mNome;
    private TextView mCrediti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettaglio_studente);

        // Imposto gli id widget
        mMatricola = (TextView)findViewById(R.id.textMatricola);
        mCognome = (TextView)findViewById(R.id.textCognome);
        mNome = (TextView)findViewById(R.id.textNome);
        mCrediti = (TextView)findViewById(R.id.textCrediti);

        // Ottengo i dati passati ed eventualmente visualizzo lo studente
        Intent intent = getIntent();
        Studente studente = (Studente)intent.getSerializableExtra(EXTRA_STUDENTE);

        if (studente != null) {
            mMatricola.setText(studente.getMatricola());
            mCognome.setText(studente.getCognome());
            mNome.setText(studente.getNome());
            mCrediti.setText(Integer.toString(studente.getCrediti()));
        }
    }
}
