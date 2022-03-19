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
    HashMap<String, Integer> names = new HashMap<>(); // use hashmap to store key/values
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

        // add Celeb Names to array
        celebNames.add("Ariana grande");
        celebNames.add("Beyonce");
        celebNames.add("Dwayne Johnson");
        celebNames.add("Ryan Reynolds");
        celebNames.add("Selena Gomez");
        // put the values in hashmap
        names.put(celebNames.get(0), R.drawable.arianagrande);
        names.put(celebNames.get(1), R.drawable.beyonce);
        names.put(celebNames.get(2), R.drawable.dwaynejohnson);
        names.put(celebNames.get(3), R.drawable.ryanreynolds);
        names.put(celebNames.get(4), R.drawable.selenagomez);
        // will shuffle the array
        Collections.shuffle(celebNames); // shuffle the celeb names
        // start the game logic
        start();
    }


    public void start(){
        // we set the background colour to #FF9636
        btnOne.setBackgroundColor(Color.parseColor("#FF9636"));
        btnTwo.setBackgroundColor(Color.parseColor("#FF9636"));
        btnThree.setBackgroundColor(Color.parseColor("#FF9636"));
        btnFour.setBackgroundColor(Color.parseColor("#FF9636"));
        // then pass the index to guessCeleb method
        guessCeleb(index);
    }

    // using the index we pass to
    public void guessCeleb(int x){
        // clone the original array to celeb_Names array
        ArrayList<String> celeb_Names = (ArrayList<String>) celebNames.clone();
        String correctGuess = celebNames.get(x); // to get the celeb index
        celeb_Names.remove(correctGuess);
        Collections.shuffle(celeb_Names); // then shuffle the array again

        ArrayList<String> celeb = new ArrayList<>(); // new array to store the right and wrong guesses
        celeb.add(celeb_Names.get(0));
        celeb.add(celeb_Names.get(1));
        celeb.add(celeb_Names.get(2));
        celeb.add(correctGuess);

        Collections.shuffle(celeb); // then shuffle the array

        btnOne.setText(celeb.get(0)); // to match the text and celeb name
        btnTwo.setText(celeb.get(1));
        btnThree.setText(celeb.get(2));
        btnFour.setText(celeb.get(3));

        imgMain.setImageResource(names.get(celebNames.get(x))); // display the celeb image
    }

    // onclick method
    public void guessClick(View view){
        // get the text(celeb name) on the button
        String guess = ((Button) view).getText().toString().trim();
        String rightGuess = celebNames.get(index);
        // we increment the index, count and ans every on click
        ans++;
        index++;
        // if the button text matches the celeb name then it is right
        if(guess == rightGuess){
            view.setBackgroundColor(Color.parseColor("#00A300"));
            count++;

            score.setText(Integer.toString(count)); // set the score base on the correct guess
            // to display win
            checkWin();
            // using handler to delay function call
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    start(); // start the game again
                }
            },2000);

        }
        else{ // if not right
            view.setBackgroundColor(Color.parseColor("#D10000"));

            myToast = Toast.makeText(getApplicationContext(),rightGuess,Toast.LENGTH_SHORT);
            myToast.show();
            // we check for win
            checkWin();
            // delay start method call
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    start();
                }
            },2000);

        }


    }
    // check count and ans
    public void checkWin() {
        if(count == 5){ // the count is 5 then you win(5 consecutive right)
            index = 0;
            myToast.cancel();
        playAgain.setVisibility(View.VISIBLE);
        btnOne.setVisibility(View.INVISIBLE);
        btnTwo.setVisibility(View.INVISIBLE);
        btnThree.setVisibility(View.INVISIBLE);
        btnFour.setVisibility(View.INVISIBLE);
        playAgain.setText("Congratulations \n You got 5/5 right! \n\n\n Click anywhere to play again");

        } else if(ans == 5){ // will display the correct guess out of 5
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

    // playAgain would reset everything
    public void playAgain(View view){

        // set all to 0
        score.setText("0");
        count = 0;
        ans = 0;
        index = 0;
        // pop up will be invisible and buttons be visible
        playAgain.setVisibility(View.INVISIBLE);
        btnOne.setVisibility(View.VISIBLE);
        btnTwo.setVisibility(View.VISIBLE);
        btnThree.setVisibility(View.VISIBLE);
        btnFour.setVisibility(View.VISIBLE);
        // start the game again
        start();
    }

    // to display the game instruction to a new page activity
    public void AboutButton(View view) //open about window
    {
        Intent intent = new Intent(this, AboutScreen.class);
        startActivity(intent);
    }

}