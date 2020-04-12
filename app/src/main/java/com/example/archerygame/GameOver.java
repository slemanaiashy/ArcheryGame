package com.example.archerygame;

public class GameOver {
    protected int GoldEarned;
    protected int HighScore;
    protected int Score;



    public GameOver( int goldEarned, int highScore, int score) {
        GoldEarned = goldEarned;
        HighScore = highScore;
        Score = score;
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
