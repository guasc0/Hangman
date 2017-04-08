package com.example.gualbertoscolari.hangman;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Class with information about the app creator
 */
public class About extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    /**
     * Takes the player back to the main menu
     * @param view The view that was clicked
     */
    public void backToMain(View view){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);

    }
}
