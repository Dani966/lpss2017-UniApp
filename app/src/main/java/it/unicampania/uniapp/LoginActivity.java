package it.unicampania.uniapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    // Costanti
    private final static String TAG = "Login";

    // Widget
    private EditText mUsername;
    private EditText mPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Imposto gli attributi relativi ai widget
        mUsername = (EditText)findViewById(R.id.editEmail);
        mPassword = (EditText)findViewById(R.id.editPassword);
        btnLogin = (Button)findViewById(R.id.btnLogin);

        // Imposto il listener per il pulsante
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mUsername.getText().toString();
                String password = mPassword.getText().toString();
                effettuaLogin(email, password);
            }
        });
    }

    /**
     * Funzione che prova ed effettuare il login
     * @param email indirizzo di email
     * @param password password
     */
    private void effettuaLogin(String email, String password) {

    }

}
