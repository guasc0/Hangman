package com.example.gualbertoscolari.hangman;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Class for the main menu view, that makes you choose to start a game or read about the creator
 */
public class MainMenu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MediaPlayer mp = MediaPlayer.create(this, R.raw.welcome3);
        mp.start();

    }

    /**
     * Starts a new game
     *
     * @param view The view that was clicked
     */
    public void playGame(View view) {
        Intent intent = new Intent(this, PlayGame.class);
        startActivity(intent);

    }

    /**
     * Takes the player to the about activity with info about the creator
     * @param view The view that was clicked
     */
    public void about(View view) {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);

    }

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
        if (id == R.id.action_play) {
            Intent intent = new Intent(this, PlayGame.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_about) {
            Intent intentAbout = new Intent(this, About.class);
            startActivity(intentAbout);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
