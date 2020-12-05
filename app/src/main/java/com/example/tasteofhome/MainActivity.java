package com.example.tasteofhome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText emailbox, passwordbox;
    private Button loginbutton, registerbutton;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailbox = findViewById(R.id.etEmail);
        passwordbox = findViewById(R.id.etPassword);
        loginbutton = findViewById(R.id.btnlogin);
        registerbutton = findViewById(R.id.btnregister);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user!= null)
        {
            Intent intent = new Intent(MainActivity.this,HomePage.class);
            startActivity(intent);
            finish();
        }
        else
        {
            loginbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String username1 = emailbox.getText().toString().trim();
                    final String password1 = passwordbox.getText().toString().trim();
                    if (emailbox.getText().toString().trim().length() == 0 || passwordbox.getText().toString().trim().length() == 0) {
                        Toast.makeText(MainActivity.this, "Invalid details", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        firebaseAuth.signInWithEmailAndPassword(username1, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Welcome!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, HomePage.class);
                                    startActivity(intent);
                                    finish();

                                }
                                else {
                                    Toast.makeText(MainActivity.this, "Invalid details", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                    }

                }
            });

        }
    }
}