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

    //comments by Sai Nikhil
    /*declaring variable used in the MainActivity XML file in resource layout*/
    private EditText emailbox, passwordbox;
    private Button loginbutton, registerbutton;
    //as we are using firebase authentication we have declared an instance of FirebaseAuth
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailbox = findViewById(R.id.etEmail);
        passwordbox = findViewById(R.id.etPassword);
        loginbutton = findViewById(R.id.btnlogin);
        registerbutton = findViewById(R.id.btnregister);
        //here we have initialized the FirebaseAuth instance
        firebaseAuth = FirebaseAuth.getInstance();

        //now we have to check if user is currently signed in or not. if signed in then every time we open the app we dont see the login page.
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user!= null)
        {
            Intent intent = new Intent(MainActivity.this,HomePage.class);
            startActivity(intent);
            finish();
        }
        else //when user is not signed in
        {
            loginbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String username1 = emailbox.getText().toString().trim();// gets username entered as string. trim method is used to remove spaces if user enters by mistake.
                    final String password1 = passwordbox.getText().toString().trim();//gets password entered
                    if (emailbox.getText().toString().trim().length() == 0 || passwordbox.getText().toString().trim().length() == 0) //if edit text fields edit box and pass box are empty
                         {
                        Toast.makeText(MainActivity.this, "Invalid details", Toast.LENGTH_SHORT).show();//toast a message which looks but like a popup message
                    }
                    else {
                        firebaseAuth.signInWithEmailAndPassword(username1, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Welcome!", Toast.LENGTH_SHORT).show();//toast a message that login was successful
                                    Intent intent = new Intent(MainActivity.this, HomePage.class);//move from this activity to HomePage using the concept intents
                                    startActivity(intent);
                                    finish();// this method should only be used when user doesnt want to come back to this page again. its like killing the acticity and when user goes back this page wont showup

                                }
                                else {
                                    Toast.makeText(MainActivity.this, "Invalid details", Toast.LENGTH_SHORT).show();//when user login credentials are wrong
                                }

                            }
                        });

                    }

                }
            });

        }
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrationPage.class);
                startActivity(intent);
            }
        });
    }
}