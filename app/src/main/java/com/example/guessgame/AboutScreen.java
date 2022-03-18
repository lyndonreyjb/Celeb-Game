package com.example.guessgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AboutScreen extends AppCompatActivity {

    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_screen);

        btnBack = findViewById(R.id.btnBack);
    }

    public void backButton(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}