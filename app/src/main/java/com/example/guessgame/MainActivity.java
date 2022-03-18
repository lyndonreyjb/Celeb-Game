package com.example.guessgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> celebNames = new ArrayList();
    ImageView imgMain;
    Button btnOne, btnTwo, btnThree, btnFour;
    HashMap<String, Integer> names = new HashMap<>();
    TextView score;

    int num, index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        index = 0;
        num = 0;

        celebNames.add("Ariana Grande");
        celebNames.add("Beyonce");
        celebNames.add("Dwayne Johnson");
        celebNames.add("Ryan Reynolds");
        celebNames.add("Selena Gomez");

        names.put(celebNames.get(0), R.drawable.ArianaGrande);
        names.put(celebNames.get(1), R.drawable.Beyonce);
        names.put(celebNames.get(2), R.drawable.DwayneJohnson);
        names.put(celebNames.get(3), R.drawable.RyanReynolds);
        names.put(celebNames.get(4), R.drawable.SelenaGomez);

        Collections.shuffle(celebNames); // shuffle the celeb names

        score = findViewById(R.id.score);
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);

        imgMain = findViewById(R.id.imgMain);

        guessCeleb(index);
    }

    public void guessCeleb(int x){
        score.setText(num);
        ArrayList<String> celeb_Names = (ArrayList<String>) celebNames.clone();
        String correctGuess = celebNames.get(index);
        celeb_Names.remove(correctGuess);
        Collections.shuffle(celeb_Names);
        ArrayList<String> celeb = new ArrayList<>();


    }
}