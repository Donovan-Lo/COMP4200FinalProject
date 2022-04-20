package com.example.higherorlower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class gameOver_act extends AppCompatActivity {

    TextView tv_go_desc;
    Button btn_playAgain, btn_cat;
    Intent intent;
    int score;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        tv_go_desc = findViewById(R.id.tv_go_desc);
        btn_cat = findViewById(R.id.btn_go_cat);
        btn_playAgain = findViewById(R.id.btn_go_playAgain);
        intent = getIntent();

        score = intent.getIntExtra("SCORE", 0);
        category = intent.getStringExtra("CATEGORY");

        //Set display msg to user
        tv_go_desc.setText("You finished with a score of " + score + "!");


        //add btn listeners
        btn_playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent selectCat = new Intent(gameOver_act.this, category_act.class);
                startActivity(selectCat);
                finish();
            }
        });


    }
}