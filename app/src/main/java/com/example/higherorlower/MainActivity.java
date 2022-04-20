package com.example.higherorlower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button btn_play, btn_instruct, btn_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_play = findViewById(R.id.btn_play);
        btn_instruct = findViewById(R.id.btn_instruct);
        btn_exit = findViewById(R.id.btn_exit);


        //Set onclick listener to instructions button to navigate to new instructions activity
        btn_instruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_instruct = new Intent(MainActivity.this, instruct_activity.class);
                startActivity(intent_instruct);
            }
        });

        //Will close the app if clicked
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //Play button will start category activity where user will select which category to play
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_play = new Intent(MainActivity.this, category_act.class);
                startActivity(intent_play);
            }
        });

    }
}