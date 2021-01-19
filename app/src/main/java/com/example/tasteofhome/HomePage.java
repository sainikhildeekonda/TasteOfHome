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
import android.widget.TextView;
import android.widget.Toast;

import com.example.tasteofhome.api_interfaces.JsonPlaceHolderApi;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomePage extends AppCompatActivity {


    private ListView listitem;
    private FirebaseFirestore db;
    private FirebaseAuth firebaseAuth;
    private TextView textView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> list=new ArrayList<>();
    private ArrayList<String> data= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        listitem=findViewById(R.id.list_item);
        firebaseAuth=FirebaseAuth.getInstance();

            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:3000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            JsonPlaceHolderApi jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
            Call<List<ContentRecipeAdd>> call=jsonPlaceHolderApi.getPosts();

            call.enqueue(new Callback<List<ContentRecipeAdd>>() {
                @Override
                public void onResponse(Call<List<ContentRecipeAdd>> call, Response<List<ContentRecipeAdd>> response) {

                    List<ContentRecipeAdd> posts=response.body();
                    for (ContentRecipeAdd post:posts)
                    {
                        if (!list.contains(post.getRecipename())) {
                            list.add(post.getRecipename());
                        }
                        if (!data.contains(post.getRecipeprocedure())) {
                            data.add(post.getRecipeprocedure());
                        }
                        adapter.notifyDataSetChanged();
                    }
                }
                @Override
                public void onFailure(Call<List<ContentRecipeAdd>> call, Throwable t) {
                    Toast.makeText(HomePage.this, "Code: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        listitem.setAdapter(adapter);
        /*db=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
          db.collection("recipe").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
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
*/
        listitem.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent=new Intent(HomePage.this,Description.class);
                String info =data.get(position);
                intent.putExtra("description",info);
                startActivity(intent);
            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
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
        firebaseAuth.signOut();
        Intent intent = new Intent(HomePage.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void info()
    {

      Intent intent = new Intent(HomePage.this, AppInfo.class);
        startActivity(intent);
    }
    private void NewRecipe()
    {
        Intent intent = new Intent(HomePage.this, RecipeAdd.class);
        startActivity(intent);
        finish();
    }
  
}


