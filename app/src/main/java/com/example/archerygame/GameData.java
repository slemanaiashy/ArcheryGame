package com.example.archerygame;

public class GameData {
    protected int MostGoldEarnedInSingleGame;
    protected int longestCombo;
    protected  int HighestGold;
    protected int HighestScore;

    public GameData(int  MostGoldEarnedInSingleGame,int longestCombo,int HighestGold,int HighestScore){
        this.MostGoldEarnedInSingleGame=MostGoldEarnedInSingleGame;
        this.longestCombo=longestCombo;
        this.HighestGold=HighestGold;
        this.HighestScore=HighestScore;
    }

    public int getMostGoldEarnedInSingleGame() {
        return MostGoldEarnedInSingleGame;
    }

    public int getLongestCombo() {
        return longestCombo;
    }

    public int getHighestGold() {
        return HighestGold;
    }

    public int getHighestScore() {
        return HighestScore;
    }

    public void setMostGoldEarnedInSingleGame(int mostGoldEarnedInSingleGame) {
        MostGoldEarnedInSingleGame = mostGoldEarnedInSingleGame;
    }

    public void setLongestCombo(int longestCombo) {
        this.longestCombo = longestCombo;
    }

    public void setHighestGold(int highestGold) {
        HighestGold = highestGold;
    }

    public void setHighestScore(int highestScore) {
        HighestScore = highestScore;
    }
}



