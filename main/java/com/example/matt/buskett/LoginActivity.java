package com.example.matt.buskett;

import android.arch.core.executor.TaskExecutor;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private EditText loginEmail, loginPassword;
    private Button loginBtn;
    private TextView notRegistered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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

        loginEmail = (EditText) findViewById(R.id.loginEmail);
        loginPassword = (EditText) findViewById(R.id.loginPassword);

        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);

        notRegistered = findViewById(R.id.notRegisteredTV);
        notRegistered.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == loginBtn){
            loginUser();
        }
        if (v == notRegistered){
            startActivity(new Intent(getApplication(), RegisterActivity.class));
        }
    }

    private void loginUser(){
        String inputEmail = loginEmail.getText().toString();
        String inputPassword = loginPassword.getText().toString();

        if (inputEmail.isEmpty() || inputPassword.isEmpty()){
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(inputEmail, inputPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplication(), "WELCOME", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplication(), MainActivity.class));
                }else if (!task.isSuccessful()){
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
