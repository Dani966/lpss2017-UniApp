package it.unicampania.uniapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    // Costanti
    private final static String TAG = "Login";

    // Widget
    private EditText mUsername;
    private EditText mPassword;
    private Button btnLogin;
    private ProgressBar mProgress;

    // Firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Imposto gli attributi relativi ai widget
        mUsername = (EditText)findViewById(R.id.editEmail);
        mPassword = (EditText)findViewById(R.id.editPassword);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        mProgress = (ProgressBar)findViewById(R.id.progressBar);

        // Impostazione Firebase
        mAuth = FirebaseAuth.getInstance();

        // Imposto il listener per il pulsante
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mUsername.getText().toString();
                String password = mPassword.getText().toString();
                if (email.isEmpty())
                    mUsername.setError(getString(R.string.obbligatorio));
                else if (password.isEmpty())
                    mPassword.setError(getString(R.string.obbligatorio));
                else
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

        mProgress.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mProgress.setVisibility(View.INVISIBLE);
                        Log.d(TAG, "Task completato: successo = " + task.isSuccessful());
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, R.string.errore_login, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
