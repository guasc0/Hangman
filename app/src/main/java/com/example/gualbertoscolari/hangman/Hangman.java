package com.example.gualbertoscolari.hangman;

import android.media.MediaPlayer;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by gualbertoscolari on 2016-10-24.
 * The class that handles the game logic
 */

public class Hangman {

    private Random rand = new Random();
    private String[] words = {"BIKE", "CAR", "HOUSE", "BREAD", "CANDLE", "SWORD", "CHRISTMAS", "FLOWER", "FRIEND", "BIKE", "FISHERMAN"};
    private boolean[] visibleWords;
    private ArrayList<Character> wrongGuesses = new ArrayList<>();
    private int guessesLeft = 10;
    private String hiddenWord = "", winOrLose, showWord = "", letters = "", currentWord = "";
    private boolean hasWon, hasLost;

    /**
     * Method that starts the game, generates a word and the hidden word. Creates a boolean array
     * with the length of the current word.
     */
    public void startGame() {

        currentWord = generateWord(words);
        hiddenWord = generateHiddenWord(currentWord);
        visibleWords = new boolean[currentWord.length()];

        for (int i = 0; i < currentWord.length(); i++) {
            visibleWords[i] = false;
        }
    }

    /**
     * Method that checks if your guess matches a letter in the generated word.
     * If so, that letter is printed out otherwise the setTriesLeft method is called.
     *
     * @param guess The letter that the player guessed on.
     */
    public void guess(char guess) {

        boolean foundMatch = false;
        for (int i = 0; i < currentWord.length(); i++) {

            if (currentWord.charAt(i) == guess) {
                visibleWords[i] = true;
                foundMatch = true;
            }
        }
        if (!foundMatch) {
            setGuessesLeft();
            printUsedLetters(guess);

        }

        areAllTrue(visibleWords);
        winOrLose = winOrLose();
    }

    /**
     * Prints the letters that the user has guessed on.
     *
     * @param guess The letter the player guessed on.
     */
    public void printUsedLetters(char guess) {

        letters = "";

        if (!wrongGuesses.contains(guess)) {
            wrongGuesses.add(guess);
        } else if (wrongGuesses.contains(guess)) {

        }

        for (int i = 0; i < wrongGuesses.size(); i++) {
            letters += wrongGuesses.get(i) + "  ";
        }

    }

    /**
     * Method that is called when the player makes a wrong guess and decreases the tries
     * left. Checks if the player has lost the game if the tries left is equal to 0.
     */
    public void setGuessesLeft() {
        guessesLeft--;

        if (guessesLeft == 0) {
            hasLost = true;
        }
    }

    /**
     * @return the tries that the player has left.
     */
    public int getGuessesLeft() {
        return guessesLeft;
    }

    /**
     * Method that makes a string of the generated word, for example the word CAT will be shown
     * as - - - before the player makes a right guess.
     *
     * @param currentWord The word that has been randomized.
     */
    public void showHiddenWord(String currentWord) {

        showWord = "";

        for (int i = 0; i < visibleWords.length; i++) {
            if (visibleWords[i]) {

                showWord += " " + currentWord.charAt(i) + " ";
            } else {

                showWord += " - ";
            }
        }
    }

    /**
     * @return the hidden word
     */
    public String getHiddenWord() {

        return hiddenWord;
    }

    /**
     * Sets the string with specific text if you win or lose.
     *
     * @returns the string
     */
    public String winOrLose() {

        if (hasWon) {
            winOrLose = "Congratulations you won!!!";

        } else if (hasLost){
            winOrLose = "You lost, try again!!!";
        }

        return winOrLose;
    }

    /**
     * Method that generates the hidden word, that changes when you get a guess right.
     *
     * @param currentWord The word that has been randomized
     * @return the current word
     */
    public String generateHiddenWord(String currentWord) {
        hiddenWord = "";

        for (int i = 0; i < currentWord.length(); i++) {

            hiddenWord += " - ";
        }
        return hiddenWord;
    }

    /**
     * Method that generates a word from the word array that contains all words.
     *
     * @param words string array that contains words
     * @return a word from the word array
     */
    public String generateWord(String[] words) {

        int index = rand.nextInt(words.length);
        return words[index];
    }

    /**
     * @return the showWord string that contains the letters of the generated word
     */
    public String getShowWord() {
        return showWord;
    }

    /**
     * Returns the Current word that the player have to guess.
     *
     * @return current word
     */
    public String getCurrentWord() {
        return currentWord;
    }

    /**
     *@return Returns the letters the user has guessed on.
     */
    public String getUsedLetters() {
        return letters;
    }

    /**
     * @return true if the player has lost
     */
    public boolean isHasLost() {
        return hasLost;
    }

    /**
     *@return true if the player has won
     */
    public boolean isHasWon() {
        return hasWon;
    }

    /**
     * Checks if all the letters in the word have been guessed on,
     * if so it sets hasWon to true and the player wins the game
     * @param array
     * @return true or false
     */
    public boolean areAllTrue(boolean[] array) {

        for (int i = 0; i < array.length; i++) {

            if (!array[i]) {
                return false;
            }
        }
        return hasWon = true;
    }

    /**
     * Checks if the guessed letter has already been guessed on.
     * @param guess
     * @return true or false
     */
    public boolean isLetterUsed(char guess) {

        if (showWord.indexOf(guess) >= 0 || wrongGuesses.contains(guess)) {
            return true;
        }
        return false;
    }
}
