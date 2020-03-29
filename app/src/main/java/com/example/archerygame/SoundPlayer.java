package com.example.archerygame;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundPlayer {
    private static SoundPool soundPool;
    private int startSound;
    private int hitSound;
    private int gameOver;

    public SoundPlayer(Context context){
        soundPool= new SoundPool(2, AudioManager.STREAM_MUSIC,0);
        hitSound=soundPool.load(context,R.raw.boopfinal,1);
        gameOver=soundPool.load(context,R.raw.awwfinal,1);
        startSound=soundPool.load(context,R.raw.nature,1);
    }
    public void playHitSound(){
        soundPool.play(hitSound,1.0f,1.0f,1,0,1.0f);
    }
    public void playGameOver(){
        soundPool.play(gameOver,1.0f,1.0f,1,0,1.0f);
    }
}
