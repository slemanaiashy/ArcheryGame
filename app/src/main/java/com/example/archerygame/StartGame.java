package com.example.archerygame;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class StartGame extends Activity {
    GameView gameView;
    ImageButton easy,med,hard,shop,logout;
    private static final String PREFS_NAME="PrefsFile";
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE );
        easy = findViewById(R.id.easy);
        med = findViewById(R.id.med);
        hard = findViewById(R.id.hard);
        shop = findViewById(R.id.shop);
        logout = findViewById(R.id.logout);
        final Intent i = getIntent();

        final Context context=this;
        readData(new FirebaseCallback() {
            @Override
            public void onCallback(int max,int max1,int max2,int max3) {
                final GameData gameData = new GameData(max,max1,max2,max3);

                easy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(i.getExtras().getBoolean("login")){
                            Player player=new Player(i.getExtras().getString("username"),i.getExtras().getString("password"),i.getExtras().getString("email"),i.getExtras().getInt("gamenum"),i.getExtras().getInt("longestgame"),i.getExtras().getInt("mostgoldinasinglegame"),i.getExtras().getInt("longestcombo"),i.getExtras().getInt("currentgold"),i.getExtras().getInt("highscore"),i.getExtras().getBoolean("BK"),i.getExtras().getBoolean("SE"),i.getExtras().getInt("equiped"),i.getExtras().getBoolean("bow1"),i.getExtras().getBoolean("bow2"),i.getExtras().getBoolean("bow3"),i.getExtras().getBoolean("bow4"),i.getExtras().getBoolean("bow5"));
                            gameView = new GameView(context,player,1,gameData);}
                        else{

                            gameView=new GameView(context,new Player(i.getExtras().getString("username"),i.getExtras().getString("password"),i.getExtras().getString("email")),1,gameData);
                        }
                        setContent(gameView);
                    }
                });
                med.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(i.getExtras().getBoolean("login")){
                            Player player=new Player(i.getExtras().getString("username"),i.getExtras().getString("password"),i.getExtras().getString("email"),i.getExtras().getInt("gamenum"),i.getExtras().getInt("longestgame"),i.getExtras().getInt("mostgoldinasinglegame"),i.getExtras().getInt("longestcombo"),i.getExtras().getInt("currentgold"),i.getExtras().getInt("highscore"),i.getExtras().getBoolean("BK"),i.getExtras().getBoolean("SE"),i.getExtras().getInt("equiped"),i.getExtras().getBoolean("bow1"),i.getExtras().getBoolean("bow2"),i.getExtras().getBoolean("bow3"),i.getExtras().getBoolean("bow4"),i.getExtras().getBoolean("bow5"));
                            gameView = new GameView(context,player,2,gameData);}
                        else{
                            gameView=new GameView(context,new Player(i.getExtras().getString("username"),i.getExtras().getString("password"),i.getExtras().getString("email")),2,gameData);
                        }
                        setContent(gameView);
                    }
                });
                hard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(i.getExtras().getBoolean("login")){
                            Player player=new Player(i.getExtras().getString("username"),i.getExtras().getString("password"),i.getExtras().getString("email"),i.getExtras().getInt("gamenum"),i.getExtras().getInt("longestgame"),i.getExtras().getInt("mostgoldinasinglegame"),i.getExtras().getInt("longestcombo"),i.getExtras().getInt("currentgold"),i.getExtras().getInt("highscore"),i.getExtras().getBoolean("BK"),i.getExtras().getBoolean("SE"),i.getExtras().getInt("equiped"),i.getExtras().getBoolean("bow1"),i.getExtras().getBoolean("bow2"),i.getExtras().getBoolean("bow3"),i.getExtras().getBoolean("bow4"),i.getExtras().getBoolean("bow5"));
                            gameView = new GameView(context,player,3,gameData);}
                        else{
                            gameView=new GameView(context,new Player(i.getExtras().getString("username"),i.getExtras().getString("password"),i.getExtras().getString("email")),3,gameData);
                        }
                        setContent(gameView);
                    }
                });
                shop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(i.getExtras().getBoolean("login")){
                            Player player=new Player(i.getExtras().getString("username"),i.getExtras().getString("password"),i.getExtras().getString("email"),i.getExtras().getInt("gamenum"),i.getExtras().getInt("longestgame"),i.getExtras().getInt("mostgoldinasinglegame"),i.getExtras().getInt("longestcombo"),i.getExtras().getInt("currentgold"),i.getExtras().getInt("highscore"),i.getExtras().getBoolean("BK"),i.getExtras().getBoolean("SE"),i.getExtras().getInt("equiped"),i.getExtras().getBoolean("bow1"),i.getExtras().getBoolean("bow2"),i.getExtras().getBoolean("bow3"),i.getExtras().getBoolean("bow4"),i.getExtras().getBoolean("bow5"));
                            gameView = new GameView(context,player,4,gameData);}
                        else{
                            gameView=new GameView(context,new Player(i.getExtras().getString("username"),i.getExtras().getString("password"),i.getExtras().getString("email")),4,gameData);
                        }
                        setContent(gameView);
                    }
                });
                logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(),Login.class);
                        intent.putExtra("check",false);
                        startActivity(intent);
                    }
                });

            }
        });
        //  numberOfGames=0;
        //        logestGame=0;
        //        MostGoldEarnedInSingleGame=0;
        //        longestCombo=0;
        //        currentGold=0;
        //     s.putExtra("gamenum",player1.getNumberOfGames());
        //                           s.putExtra("longestgame",player1.getLogestGame());
        //                           s.putExtra("mostgoldinasinglegame",player1.getMostGoldEarnedInSingleGame());
        //                           s.putExtra("longestcombo",player1.getLongestCombo());
        //                           s.putExtra("currentgold",player1.getCurrentGold());

    }
    private void readData(final FirebaseCallback firebaseCallback){
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("PlayersInfo");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int min = 0,min1=0,min2=0,min3=0;
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    String value = String.valueOf(dataSnapshot1.child("mostGoldEarnedInSingleGame").getValue());
                    if(min<Integer.parseInt(value))
                        min=Integer.parseInt(value);
                    String value1 = String.valueOf(dataSnapshot1.child("longestCombo").getValue());
                    if(min1<Integer.parseInt(value1))
                        min1=Integer.parseInt(value1);
                    String value2 = String.valueOf(dataSnapshot1.child("currentGold").getValue());
                    if(min2<Integer.parseInt(value2))
                        min2=Integer.parseInt(value2);
                    String value3 = String.valueOf(dataSnapshot1.child("highScore").getValue());
                    if(min3<Integer.parseInt(value3))
                        min3=Integer.parseInt(value3);
                }
                firebaseCallback.onCallback(min,min1,min2,min3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private interface FirebaseCallback{
        void onCallback(int maxSingle,int maxCombo,int maxScore,int maxGold);
    }
    public void setContent(GameView gameView){
        setContentView(gameView);
    }
}

