package com.example.higherorlower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class category_act extends AppCompatActivity {

    private final String INTENT_KEY = "CATEGORY";
    Button btn_countryPop, btn_countrySize, btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        btn_back = findViewById(R.id.btn_backCat);
        btn_countryPop = findViewById(R.id.btn_countryPop);
        btn_countrySize = findViewById(R.id.btn_countrySize);


        //Go back to home if back btn pressed
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Start play activity with the extra of country pop selected
        btn_countryPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent play_intent = new Intent(category_act.this, play_act.class);

                //Add extra so we know which category was selected
                String category = "pop";
                play_intent.putExtra(INTENT_KEY, category);
                startActivity(play_intent);
                finish();
            }
        });

        btn_countrySize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent play_intent = new Intent(category_act.this, play_act.class);

                //Add extra so we know which category was selected
                String category = "size";
                play_intent.putExtra("CATEGORY", category);
                startActivity(play_intent);
                finish();
            }
        });


    }
}