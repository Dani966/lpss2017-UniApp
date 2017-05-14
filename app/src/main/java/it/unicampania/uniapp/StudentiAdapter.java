package it.unicampania.uniapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.List;

import it.unicampania.uniapp.datamodel.Studente;
/**
 * Adapter per la gestione degli studenti
 * Created by pasquale on 20/04/17.
 */

class StudentiAdapter extends BaseAdapter {

    private List<Studente> studenti = Collections.emptyList();
    private Context context;

    /**
     * Costruttore
     * @param context contesto da utilizzare
     */
    public StudentiAdapter(Context context) {
        this.context = context;
    }

    public void update(List<Studente> newList) {
        studenti = newList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return studenti.size();
    }

    @Override
    public Studente getItem(int position) {
        return studenti.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.riga_studente, parent, false);

        // Ottengo gli id correnti
        TextView textMatricola = (TextView)convertView.findViewById(R.id.textMatricola);
        TextView textCognome = (TextView)convertView.findViewById(R.id.textCognome);
        TextView textNome = (TextView)convertView.findViewById(R.id.textNome);

        // Imposto i valori da visualizzare
        Studente studente = studenti.get(position);
        textMatricola.setText(studente.getMatricola());
        textCognome.setText(studente.getCognome());
        textNome.setText(studente.getNome());

        return convertView;
    }
}
