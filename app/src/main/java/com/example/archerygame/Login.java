package com.example.archerygame;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    private static final String TAG ="Login" ;
    private static final String PREFS_NAME="PrefsFile";
    private SharedPreferences sharedPreferences;
    FirebaseDatabase database;

    Player user1;
    DatabaseReference PlayerInfo;
    CheckBox checkBox;
    ImageButton RegisterButton,LoginButton;
    EditText UsernameEdit, PasswordEdit;
    Player player1;
    boolean prizee;
    Intent s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        s= new Intent(getApplicationContext(),StartGame.class);
        final Intent check = getIntent();
        database = FirebaseDatabase.getInstance();
        PlayerInfo= database.getReference("PlayersInfo");
        Context context;
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE );
        RegisterButton = findViewById(R.id.imageButton);
        LoginButton = findViewById(R.id.loginbut);
        UsernameEdit = findViewById(R.id.editTextuser);
        PasswordEdit = findViewById(R.id.editTextpass);
        checkBox = findViewById(R.id.checkBox);
        prizee=check.getExtras().getBoolean("prize");
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //       System.out.println(UsernameEdit.getText().toString()+ "  b  "+PasswordEdit.getText().toString());
                //  Player player = new Player(UsernameEdit.getText().toString(),PasswordEdit.getText().toString(),"a",0,0,0,0,0);
                // System.out.println("this is  "+player.getUsername() );
                signInCheck(UsernameEdit.getText().toString(), PasswordEdit.getText().toString());

            }


        });
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s = new Intent(getApplicationContext(),Register.class);
                startActivity(s);
            }
        });
        //Toast.makeText(getApplicationContext(),"workplease",Toast.LENGTH_SHORT).show();
        if(check.getExtras().getBoolean("check")){
            getData();
        }

        else{
            SharedPreferences.Editor editor =sharedPreferences.edit();
            editor.putString("username","");
            editor.putString("password","");
            editor.putBoolean("checkbox",false);
            editor.apply();
        }
    }
    private void getData() {
        Context context;
        SharedPreferences sp = getSharedPreferences(PREFS_NAME,MODE_PRIVATE );
        if(sp.contains("username")){
            UsernameEdit.setText(sp.getString("username",""));
        }
        if(sp.contains("password")){
            PasswordEdit.setText(sp.getString("password",""));
        }
        if(sp.contains("checkbox")){
            checkBox.setChecked(sp.getBoolean("checkbox",false));
        }
    }
    private void signInCheck(final String Username,final String Password) {

        PlayerInfo.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(Username).exists()) {
                    if (!Username.isEmpty()) {
                        player1 = dataSnapshot.child(Username).getValue(Player.class);
                        Random random = new Random();
                        if(prizee)
                        player1.setCurrentGold(player1.getCurrentGold()+random.nextInt(15)+5);
                        if(player1.getPassword().equals(Password)){
                            if(checkBox.isChecked()){
                                boolean checked = checkBox.isChecked();
                                SharedPreferences.Editor editor =sharedPreferences.edit();
                                editor.putString("username",Username);
                                editor.putString("password",Password);
                                editor.putBoolean("checkbox",checked);
                                editor.apply();
                                System.out.println(Username+" ? "+Password+" ?? "+checked);
                            }
                            else{
                                sharedPreferences.edit().clear().apply();
                            }
                            //  numberOfGames=0;
                            //        logestGame=0;
                            //        MostGoldEarnedInSingleGame=0;
                            //        longestCombo=0;
                            //        currentGold=0;
                            PlayerInfo.child(Username).child("currentGold").setValue(player1.getCurrentGold());
                            s.putExtra("password",player1.getPassword());
                            s.putExtra("email",player1.getEmail());
                            s.putExtra("username",player1.getUsername());
                            s.putExtra("gamenum",player1.getNumberOfGames());
                            s.putExtra("longestgame",player1.getLogestGame());
                            s.putExtra("mostgoldinasinglegame",player1.getMostGoldEarnedInSingleGame());
                            s.putExtra("longestcombo",player1.getLongestCombo());
                            s.putExtra("currentgold",player1.getCurrentGold());
                            s.putExtra("highscore",player1.getHighScore());
                            s.putExtra("BK",player1.isBK());
                            s.putExtra("SE",player1.isSE());
                            s.putExtra("login",true);
                            s.putExtra("equiped",player1.equiped);
                            s.putExtra("bow1",player1.bow1);
                            s.putExtra("bow2",player1.bow2);
                            s.putExtra("bow3",player1.bow3);
                            s.putExtra("bow4",player1.bow4);
                            s.putExtra("bow5",player1.bow5);
                            System.out.println("king: "+player1.bow3);
                            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(Calendar.HOUR,23);
                            calendar.set(Calendar.MINUTE,59);
                            calendar.set(Calendar.SECOND,59);
                            PendingIntent pendingIntent=  PendingIntent.getBroadcast(getApplicationContext(),100,new Intent(getApplicationContext(),AlarmReceiver.class),PendingIntent.FLAG_UPDATE_CURRENT);
                            alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
                            startActivity(s);
                            UsernameEdit.getText().clear();
                            PasswordEdit.getText().clear();
                        }
                        else{

                            Toast.makeText(Login.this,"Wrong Password",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else{
                    Toast.makeText(Login.this,"Username doesn't exist",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }
}
