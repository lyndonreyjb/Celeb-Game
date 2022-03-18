package com.example.guessgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> celebNames = new ArrayList();
    ImageView imgMain, btnAbout;
    Button btnOne, btnTwo, btnThree, btnFour, playAgain;
    HashMap<String, Integer> names = new HashMap<>();
    TextView score;
    Toast myToast;

    int count, index, ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        index = 0;
        count = 0;
        ans = 0;

        playAgain = findViewById(R.id.playagain);
        btnAbout = findViewById(R.id.btnAbout);

        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);

        imgMain = findViewById(R.id.imgMain);
        score = findViewById(R.id.score);

        celebNames.add("Ariana grande");
        celebNames.add("Beyonce");
        celebNames.add("Dwayne Johnson");
        celebNames.add("Ryan Reynolds");
        celebNames.add("Selena Gomez");

        names.put(celebNames.get(0), R.drawable.arianagrande);
        names.put(celebNames.get(1), R.drawable.beyonce);
        names.put(celebNames.get(2), R.drawable.dwaynejohnson);
        names.put(celebNames.get(3), R.drawable.ryanreynolds);
        names.put(celebNames.get(4), R.drawable.selenagomez);

        Collections.shuffle(celebNames); // shuffle the celeb names

        start();
    }


    public void start(){

        btnOne.setBackgroundColor(Color.parseColor("#FF9636"));
        btnTwo.setBackgroundColor(Color.parseColor("#FF9636"));
        btnThree.setBackgroundColor(Color.parseColor("#FF9636"));
        btnFour.setBackgroundColor(Color.parseColor("#FF9636"));
        
        guessCeleb(index);
    }

    public void guessCeleb(int x){

        ArrayList<String> celeb_Names = (ArrayList<String>) celebNames.clone();
        String correctGuess = celebNames.get(x);
        celeb_Names.remove(correctGuess);
        Collections.shuffle(celeb_Names);

        ArrayList<String> celeb = new ArrayList<>();
        celeb.add(celeb_Names.get(0));
        celeb.add(celeb_Names.get(1));
        celeb.add(celeb_Names.get(2));
        celeb.add(correctGuess);

        Collections.shuffle(celeb);

        btnOne.setText(celeb.get(0));
        btnTwo.setText(celeb.get(1));
        btnThree.setText(celeb.get(2));
        btnFour.setText(celeb.get(3));

        imgMain.setImageResource(names.get(celebNames.get(x)));
    }

    public void guessClick(View view){

        String guess = ((Button) view).getText().toString().trim();
        String rightGuess = celebNames.get(index);

        ans++;
        index++;

        if(guess == rightGuess){
            view.setBackgroundColor(Color.parseColor("#00A300"));
            count++;

            score.setText(Integer.toString(count));

            checkWin();

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    start();
                }
            },2000);

        }
        else{
            view.setBackgroundColor(Color.parseColor("#D10000"));

            myToast = Toast.makeText(getApplicationContext(),rightGuess,Toast.LENGTH_SHORT);
            myToast.show();

            checkWin();

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    start();
                }
            },2000);

        }


    }

    public void checkWin() {
        if(count == 5){
            index = 0;
            myToast.cancel();
        playAgain.setVisibility(View.VISIBLE);
        btnOne.setVisibility(View.INVISIBLE);
        btnTwo.setVisibility(View.INVISIBLE);
        btnThree.setVisibility(View.INVISIBLE);
        btnFour.setVisibility(View.INVISIBLE);
        playAgain.setText("Congratulations \n You got 5/5 right! \n\n\n Click anywhere to play again");

        } else if(ans == 5){
            index = 0;
            myToast.cancel();
            playAgain.setVisibility(View.VISIBLE);
            btnOne.setVisibility(View.INVISIBLE);
            btnTwo.setVisibility(View.INVISIBLE);
            btnThree.setVisibility(View.INVISIBLE);
            btnFour.setVisibility(View.INVISIBLE);
            playAgain.setText("Congratulations \n You got " +   count + "/5 right! \n\n\n Click anywhere to play again");
        }
    }


    public void playAgain(View view){

        score.setText("0");
        count = 0;
        ans = 0;
        index = 0;

        playAgain.setVisibility(View.INVISIBLE);
        btnOne.setVisibility(View.VISIBLE);
        btnTwo.setVisibility(View.VISIBLE);
        btnThree.setVisibility(View.VISIBLE);
        btnFour.setVisibility(View.VISIBLE);

        start();
    }

    public void AboutButton(View view) //open about window
    {
        Intent intent = new Intent(this, AboutScreen.class);
        startActivity(intent);
    }

}