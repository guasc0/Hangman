package com.example.gualbertoscolari.hangman;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A clas that plays the game, and prints everything to the player.
 */
public class PlayGame extends AppCompatActivity {

    Hangman hangman;
    TextView randomWord, guessesLeft, usedLetters;
    String winOrLose, yourWord;
    boolean hasWon, hasLost;
    int nrOfGuesses, imgNr;
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        hangman = new Hangman();
        hangman.startGame();

        randomWord = (TextView) findViewById(R.id.current_word);
        randomWord.setText(hangman.getHiddenWord());

        guessesLeft = (TextView) findViewById(R.id.guesses_left);
        guessesLeft.setText("Guesses left: " + hangman.getGuessesLeft());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_huvudmeny, menu);
        menu.getItem(0).setVisible(false);
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
        return super.onOptionsItemSelected(item);
    }

    /**
     * Handles what happens when you make your guess, and handles the error handling.
     * Changes the images depending on how many tries you have left.
     * @param view The view that was clicked
     */
    public void clickGuessButton(View view) {

        MediaPlayer mp = MediaPlayer.create(this, R.raw.pling);
        mp.start();

        EditText input = (EditText) findViewById(R.id.your_guess);

        if (input.length() > 1 ){
            Toast.makeText(this, "You can ony luse one letter", Toast.LENGTH_SHORT).show();

        } else if (input.length() == 0 ){
            Toast.makeText(this, "You need to enter a letter", Toast.LENGTH_SHORT).show();
        } else {

            char guess = input.getText().toString().toUpperCase().charAt(0);

            if (hangman.isLetterUsed(guess)) {
                Toast.makeText(this, "You have already used this letter", Toast.LENGTH_SHORT).show();
            }

            hangman.guess(guess);
            guessesLeft.setText("Guesses left: " + hangman.getGuessesLeft());

            hangman.showHiddenWord(hangman.getCurrentWord());
            randomWord.setText(hangman.getShowWord());

            usedLetters = (TextView) findViewById(R.id.wrong_letters);
            usedLetters.setText(hangman.getUsedLetters());

            imageView = (ImageView) findViewById(R.id.image_view);
            imgNr = getResources().getIdentifier("hang" + hangman.getGuessesLeft(), "drawable", getPackageName());
            imageView.setImageResource(imgNr);

            hasLost = hangman.isHasLost();
            hasWon = hangman.isHasWon();
            winOrLose = hangman.winOrLose();
            nrOfGuesses = hangman.getGuessesLeft();
            yourWord = hangman.getCurrentWord();

            if (hasLost || hasWon) {
                result();
            }
            input.setText("");
        }
    }

    /**
     * Takes the player to the result activity, and sends different information if you
     * lose or win
     */
    public void result(){
        Intent result = new Intent(this, Result.class);
        result.putExtra("resultMessage", winOrLose);
        result.putExtra("resultGuesses", nrOfGuesses);
        result.putExtra("rigthWord", yourWord);
        startActivity(result);
    }


}



