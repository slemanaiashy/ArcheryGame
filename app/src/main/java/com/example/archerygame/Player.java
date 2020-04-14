package com.example.archerygame;

public class Player {
    protected   String Username;
    protected  String Password;
    protected  String Email;
    protected   int numberOfGames;
    protected  int MostGoldEarnedInSingleGame;
    protected   int longestCombo;
    protected  int currentGold;
    protected  int highScore;
    protected   int equiped;
    protected  boolean bow1;
    protected  boolean bow2;
    protected  boolean bow3;
    protected boolean bow4;
    protected  boolean bow5;
    protected boolean SE;
    protected boolean BK;

    public int getEquiped() {
        return equiped;
    }

    public void setEquiped(int equiped) {
        this.equiped = equiped;
    }

    public boolean isBow1() {
        return bow1;
    }

    public void setBow1(boolean bow1) {
        this.bow1 = bow1;
    }

    public boolean isBow2() {
        return bow2;
    }

    public void setBow2(boolean bow2) {
        this.bow2 = bow2;
    }

    public boolean isBow3() {
        return bow3;
    }

    public void setBow3(boolean bow3) {
        this.bow3 = bow3;
    }

    public boolean isBow4() {
        return bow4;
    }

    public void setBow4(boolean bow4) {
        this.bow4 = bow4;
    }

    public boolean isBow5() {
        return bow5;
    }

    public void setBow5(boolean bow5) {
        this.bow5 = bow5;
    }

    public Player(String username, String password, String email) {
        Username = username;
        Password = password;
        Email = email;
        numberOfGames=0;
        MostGoldEarnedInSingleGame=0;
        longestCombo=0;
        currentGold=0;
        highScore=0;
        BK=true;
        SE=true;
        equiped=0;
        bow1=true;
        bow2=false;
        bow3=false;
        bow4=false;
        bow5=false;
    }
    public Player(String username, String password, String email, int numberOfGames, int currentGold,int highScore) {
        Username = username;
        Password = password;
        Email = email;
        this.numberOfGames = numberOfGames;
        this.currentGold = currentGold;
    }
    public Player(){

    }
    public Player( String username,int numberOfGames, int mostGoldEarnedInSingleGame, int longestCombo, int currentGold,int highScore){
        Username = username;
        this.numberOfGames = numberOfGames;
        MostGoldEarnedInSingleGame = mostGoldEarnedInSingleGame;
        this.longestCombo = longestCombo;
        this.currentGold = currentGold;
        this.highScore=highScore;
    }


    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public boolean isSE() {
        return SE;
    }

    public void setSE(boolean SE) {
        this.SE = SE;
    }

    public boolean isBK() {
        return BK;
    }

    public void setBK(boolean BK) {
        this.BK = BK;
    }

    public Player(String username, String password, String email, int numberOfGames, int mostGoldEarnedInSingleGame, int longestCombo, int currentGold, int highScore, boolean BK, boolean SE,int equiped,boolean bow1,boolean bow2,boolean bow3,boolean bow4,boolean bow5) {
        Username = username;
        Password = password;
        Email = email;
        this.numberOfGames = numberOfGames;
        MostGoldEarnedInSingleGame = mostGoldEarnedInSingleGame;
        this.longestCombo = longestCombo;
        this.currentGold = currentGold;
        this.highScore=highScore;
        this.SE=SE;
        this.BK=BK;
        this.equiped=equiped;
        this.bow1=bow1;
        this.bow2=bow2;
        this.bow3= bow3;
        this.bow4=bow4;
        this.bow5=bow5;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(int numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public void setMostGoldEarnedInSingleGame(int mostGoldEarnedInSingleGame) {
        MostGoldEarnedInSingleGame = mostGoldEarnedInSingleGame;
    }

    public void setLongestCombo(int longestCombo) {
        this.longestCombo = longestCombo;
    }

    public int getCurrentGold() {
        return currentGold;
    }

    public void setCurrentGold(int currentGold) {
        this.currentGold = currentGold;
    }
    public int getMostGoldEarnedInSingleGame() {
        return MostGoldEarnedInSingleGame;
    }

    public int getLongestCombo() {
        return longestCombo;
    }
}
