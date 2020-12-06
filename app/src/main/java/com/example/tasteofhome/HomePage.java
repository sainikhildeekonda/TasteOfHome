package com.example.tasteofhome;


import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.database.DatabaseReference;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {


    private ListView listitem;
    private FirebaseFirestore db;
    private ArrayAdapter adapter;
    private final ArrayList<String> list=new ArrayList<>();
    private ArrayList<String> data= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        listitem=findViewById(R.id.list_item);

        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        listitem.setAdapter(adapter);
        db=FirebaseFirestore.getInstance();

        db.collection("recipe").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                ContentRecipeAdd contentRecipeAdd = document.toObject(ContentRecipeAdd.class);
                                if (!list.contains(contentRecipeAdd.getRecipename1())) {
                                    list.add(contentRecipeAdd.getRecipename1());
                                }
                                if (!data.contains(contentRecipeAdd.getRecipepro1())) {
                                    data.add(contentRecipeAdd.getRecipepro1());
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                Toast.makeText(getApplicationContext(), "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        listitem.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent=new Intent(HomePage.this,Description.class);
                String info =data.get(position);
                intent.putExtra("description",info);
                startActivity(intent);
            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.exit: Logout();
                            break;
            case R.id.newrecipe: NewRecipe();
                                    break;
            case R.id.appinfo: info();
                                break;
        }

        return super.onOptionsItemSelected(item);
    }
    private void Logout()
    {
        
    }
    private void info()
    {

      


    }
    private void NewRecipe()
    {
        Intent intent = new Intent(HomePage.this, RecipeAdd.class);
        startActivity(intent);
        finish();
    }
  
}


