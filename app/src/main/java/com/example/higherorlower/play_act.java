package com.example.higherorlower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class play_act extends AppCompatActivity {

    Button btn_higher, btn_lower;
    TextView tv_score, tv_sectTitle1, tv_sectDesc1, tv_sectTitle2, tv_sectDesc2, tv_correct;
    static int score = 0;
    //highscore
    String db_table, category;
    Country randomCount1, randomCount2;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        score = 0;

        btn_higher = findViewById(R.id.btn_higher);
        btn_lower = findViewById(R.id.btn_lower);
        tv_score = findViewById(R.id.tv_score);
        tv_sectTitle1 = findViewById(R.id.tv_sectTitle1);
        tv_sectTitle2 = findViewById(R.id.tv_sectTitle2);
        tv_sectDesc1 = findViewById(R.id.tv_sectDesc1);
        tv_sectDesc2 = findViewById(R.id.tv_sectDesc2);
        tv_correct = findViewById(R.id.tv_correct);
        Intent intent = getIntent();

        //On create set Score text
        tv_score.setText("Score: " + score);

        dbHelper = new DBHelper(this);
        dbHelper.getReadableDatabase();

        //Get which category user selected from extra
        category = intent.getStringExtra("CATEGORY");

        switch (category){
            case "pop":
                generatePopQuestions();
                break;

            case "size":
                generateSizeQuestions();
                break;

        }


        //add higher and lower button listeners
        btn_higher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(higherCompare()){
                    score++;
                    tv_correct.setText("Correct!");
                    tv_score.setText("Score: " + score);

                    //Display the answer to user
                    switch(category){
                        case "pop":
                            tv_sectDesc2.setText("has a Population of: " + randomCount2.getCountryPop());
                            //Sleep for 3 seconds then display new question
                            new CountDownTimer(3250, 1000) {

                                public void onTick(long millisUntilFinished) {
                                }

                                public void onFinish() {
                                    generatePopQuestions();
                                }

                            }.start();
                            break;
                        case "size":
                            tv_sectDesc2.setText("has the size in squared miles: " + randomCount2.getCountrySize());
                            //Sleep for 3 seconds then display new question
                            new CountDownTimer(3250, 1000) {

                                public void onTick(long millisUntilFinished) {
                                }

                                public void onFinish() {
                                    generateSizeQuestions();
                                }

                            }.start();
                            break;
                    }

                }
                else{
                    Intent gameover_intent = new Intent(play_act.this, gameOver_act.class);
                    gameover_intent.putExtra("SCORE", score);
                    gameover_intent.putExtra("CATEGORY", category);
                    startActivity(gameover_intent);
                    score = 0;
                    tv_score.setText("Score: " + score);
                    switch(category){
                        case "pop":
                            generatePopQuestions();
                            break;
                        case "size":
                            generateSizeQuestions();
                            break;
                    }
                }
            }
        });

        btn_lower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lowerCompare()){
                    score++;
                    tv_correct.setText("Correct!");
                    tv_score.setText("Score: " + score);

                    //Display the answer to user
                    switch(category){
                        case "pop":
                            tv_sectDesc2.setText("has a Population of: " + randomCount2.getCountryPop());
                            //Sleep for 3 seconds then display new question
                            new CountDownTimer(3250, 1000) {

                                public void onTick(long millisUntilFinished) {
                                }

                                public void onFinish() {
                                    generatePopQuestions();
                                }

                            }.start();
                            break;
                        case "size":
                            tv_sectDesc2.setText("has the size in squared miles: " + randomCount2.getCountrySize());
                            //Sleep for 3 seconds then display new question
                            new CountDownTimer(3250, 1000) {

                                public void onTick(long millisUntilFinished) {
                                }

                                public void onFinish() {
                                    generateSizeQuestions();
                                }

                            }.start();
                            break;
                    }

                }
                else{
                    Intent gameover_intent = new Intent(play_act.this, gameOver_act.class);
                    gameover_intent.putExtra("SCORE", score);
                    gameover_intent.putExtra("CATEGORY", category);
                    startActivity(gameover_intent);
                    score = 0;
                    tv_score.setText("Score: " + score);
                    switch(category){
                        case "pop":
                            generatePopQuestions();
                            break;
                        case "size":
                            generateSizeQuestions();
                            break;
                    }
                }
            }

        });




    }

    //Function to compare the values based of category selected
    boolean higherCompare(){
        switch(category){
            case "pop":
                if(randomCount1.getCountryPop() <= randomCount2.getCountryPop()){
                    return true;
                }
                else{
                    return false;
                }

            case "size":
                if(randomCount1.getCountrySize() <= randomCount2.getCountrySize()){
                    return true;
                }
                else{
                    return false;
                }
        }
        return true;
    }

    //Function to compare the values based of category selected
    boolean lowerCompare(){
        switch(category){
            case "pop":
                if(randomCount1.getCountryPop() >= randomCount2.getCountryPop()){
                    return true;
                }
                else{
                    return false;
                }

            case "size":
                if(randomCount1.getCountrySize() >= randomCount2.getCountrySize()){
                    return true;
                }
                else{
                    return false;
                }
        }
        return true;
    }

    public void generatePopQuestions(){
        tv_correct.setText("");
        randomCount1 = dbHelper.selectRandomCountry();
        randomCount2 = dbHelper.selectRandomCountry();
        tv_sectTitle1.setText(randomCount1.getCountryName());
        tv_sectDesc1.setText("has a Population of: " + randomCount1.getCountryPop());
        tv_sectTitle2.setText(randomCount2.getCountryName());
        tv_sectDesc2.setText("has a higher or lower Population: ?");
    }

    public void generateSizeQuestions(){
        tv_correct.setText("");
        randomCount1 = dbHelper.selectRandomCountry();
        randomCount2 = dbHelper.selectRandomCountry();
        tv_sectTitle1.setText(randomCount1.getCountryName());
        tv_sectDesc1.setText("has the Size of: " + randomCount1.getCountrySize() + " in squared miles");
        tv_sectTitle2.setText(randomCount2.getCountryName());
        tv_sectDesc2.setText("has a higher or lower size: ?");
    }
}