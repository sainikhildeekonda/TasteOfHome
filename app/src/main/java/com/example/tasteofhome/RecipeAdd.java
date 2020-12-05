package com.example.tasteofhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RecipeAdd extends AppCompatActivity {
    private EditText recipename,recipeprocedure;
    private Button addrecipe;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_add);
        recipename=findViewById(R.id.recipename);
        recipeprocedure=findViewById(R.id.recipepro);
        addrecipe=findViewById(R.id.recipeadd);

        db= FirebaseFirestore.getInstance();


        
    }

}
