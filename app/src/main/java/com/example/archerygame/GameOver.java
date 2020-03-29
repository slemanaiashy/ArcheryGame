package com.example.archerygame;

public class GameOver {
    protected int TimePlayed;
    protected int GoldEarned;
    protected int HighScore;
    protected int Score;



    public GameOver(int timePlayed, int goldEarned, int highScore, int score) {
        TimePlayed = timePlayed;
        GoldEarned = goldEarned;
        HighScore = highScore;
        Score = score;
    }

    public int getTimePlayed() {
        return TimePlayed;
    }

    public void setTimePlayed(int timePlayed) {
        TimePlayed = timePlayed;
    }

    public int getGoldEarned() {
        return GoldEarned;
    }

    public void setGoldEarned(int goldEarned) {
        GoldEarned = goldEarned;
    }

    public int getHighScore() {
        return HighScore;
    }

    public void setHighScore(int highScore) {
        HighScore = highScore;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }
}
