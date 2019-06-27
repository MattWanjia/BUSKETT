package com.example.matt.buskett;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private EditText registerEmail, registerPassword;
    private Button registerBtn;
    private TextView registered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (user != null){
                    startActivity(new Intent(getApplication(), MainActivity.class));
                }
            }
        };

        registerEmail = (EditText) findViewById(R.id.registerEmail);
        registerPassword = (EditText) findViewById(R.id.registerPassword);

        registerBtn = (Button) findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(this);

        registered = findViewById(R.id.registeredTV);
        registered.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == registerBtn){
            registerUser();
        }
        if (v == registered){
            startActivity(new Intent(getApplication(), LoginActivity.class));
        }

    }

    private void registerUser(){
        String inputEmail = registerEmail.getText().toString();
        String inputPassword = registerPassword.getText().toString();

        if (inputEmail.isEmpty() || inputPassword.isEmpty()){
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(inputEmail, inputPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isComplete()){
                    Toast.makeText(getApplication(), "REGISTERED", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplication(), MainActivity.class));
                }else if (!task.isComplete()){
                    Toast.makeText(getApplication(), "TRY AGAIN", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }
}
