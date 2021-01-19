package com.example.tasteofhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tasteofhome.api_interfaces.JsonPlaceHolderApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeAdd extends AppCompatActivity {
    private EditText recipename,recipeprocedure;
    private Button addrecipe;
 //   private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_add);
        recipename=findViewById(R.id.recipename);
        recipeprocedure=findViewById(R.id.recipepro);
        addrecipe=findViewById(R.id.recipeadd);

   //     db= FirebaseFirestore.getInstance();


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
                    addData();
                    //Toast.makeText(RecipeAdd.this, "Recipe added successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RecipeAdd.this, HomePage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
    void addData()
    {
               /* ContentRecipeAdd contentRecipeAdd=new ContentRecipeAdd(recipename,recipeprocedure);
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
                */
        String name = recipename.getText().toString().trim();
        String procedure = recipeprocedure.getText().toString().trim();

        ContentRecipeAdd post=new ContentRecipeAdd(name,procedure);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
        Call<ContentRecipeAdd> call=jsonPlaceHolderApi.addData(post);
        call.enqueue(new Callback<ContentRecipeAdd>() {
            @Override
            public void onResponse(Call<ContentRecipeAdd> call, Response<ContentRecipeAdd> response) {

                Toast.makeText(RecipeAdd.this, "Recipe Added to the list", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ContentRecipeAdd> call, Throwable t) {
               // Toast.makeText(RecipeAdd.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
