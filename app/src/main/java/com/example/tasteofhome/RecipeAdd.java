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


        addrecipe.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if (recipename.getText().toString().trim().length() == 0 || recipeprocedure.getText().toString().trim().length() ==0)
                {
                    Toast.makeText(RecipeAdd.this, "please fill correct details", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    addData(recipename.getText().toString(),recipeprocedure.getText().toString());
                    Toast.makeText(RecipeAdd.this, "Recipe added successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RecipeAdd.this, HomePage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            }


            public void addData(String recipename,String recipeprocedure)
            {
                ContentRecipeAdd contentRecipeAdd=new ContentRecipeAdd(recipename,recipeprocedure);
                db.collection("recipe").add(contentRecipeAdd).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Recipe Added successfully", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "please fill correct details!", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

    }

}
