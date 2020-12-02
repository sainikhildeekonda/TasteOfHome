package com.example.tasteofhome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class RegistrationPage extends AppCompatActivity {
    private TextView display;
    private EditText emailbox,passwordbox;
    private Button create;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        display= findViewById(R.id.tvregistertext);
        emailbox= findViewById(R.id.etregisteremail);
        passwordbox= findViewById(R.id.etregisterpass);
        create= findViewById(R.id.btncreate);
        firebaseAuth =FirebaseAuth.getInstance();


        create.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                final String username=emailbox.getText().toString().trim();
                final String password=passwordbox.getText().toString().trim();
                if (emailbox.getText().toString().trim().length() == 0 || passwordbox.getText().toString().trim().length() ==0)
                {
                    Toast.makeText(RegistrationPage.this, "please fill correct details", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    firebaseAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(RegistrationPage.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                finish();
                                Intent intent = new Intent(RegistrationPage.this, MainActivity.class);
                                startActivity(intent);

                            }
                            else {
                                Toast.makeText(RegistrationPage.this, "please fill correct details", Toast.LENGTH_SHORT).show();

                            }


                        }
                    });
                }
            }
        });

    }

}

