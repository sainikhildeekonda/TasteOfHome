package com.example.tasteofhome;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Description extends AppCompatActivity {

    private TextView description;

    private int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        description=findViewById(R.id.description1);

       String nikhil=getIntent().getStringExtra("description");
        description.setText(nikhil);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
