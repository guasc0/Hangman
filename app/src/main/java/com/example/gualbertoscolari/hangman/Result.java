package com.example.gualbertoscolari.hangman;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Method that prints the result with different text, if you lose or win
 */
public class Result extends AppCompatActivity {

    TextView playerMessage;
    String winOrLose;
    int nrOfGuesses;
    TextView tries;
    String word;
    TextView rightWord;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_huvudmeny, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent intentAbout = new Intent(this, About.class);
            startActivity(intentAbout);
            return true;
        }
        if (id == R.id.action_play) {
            Intent intent = new Intent(this, PlayGame.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        winOrLose = intent.getStringExtra("resultMessage");
        playerMessage = (TextView) findViewById(R.id.win_loose);
        playerMessage.setText(winOrLose);


        nrOfGuesses = intent.getIntExtra("resultGuesses", 0);
        tries = (TextView) findViewById(R.id.result_guesses);
        tries.setText("Guesses left: " + nrOfGuesses);

        word = intent.getStringExtra("rigthWord");
        rightWord = (TextView) findViewById(R.id.result_word);
        rightWord.setText("The word was: " + word);

    }

    /**
     * Method that takes you back to the main menu when the button is clicked
     *
     * @param view The view that was clicked
     */
    public void backToMain(View view) {
        startActivity(new Intent(this, MainMenu.class));
    }
}
