package com.example.archerygame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Instructions extends AppCompatActivity {
    ImageButton exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions);
        exit=findViewById(R.id.imageButton3);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent i = getIntent();
                Intent s = new Intent(getApplicationContext(), StartGame.class);
                s.putExtra("login", false);
                s.putExtra("password", i.getExtras().getString("password"));//i.getExtras().getString("username"),i.getExtras().getString("password"),i.getExtras().getString("email")
                s.putExtra("email", i.getExtras().getString("email"));
                s.putExtra("username", i.getExtras().getString("username"));
                startActivity(s);
            }
        });
    }
}
