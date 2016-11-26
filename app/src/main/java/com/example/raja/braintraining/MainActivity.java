package com.example.raja.braintraining;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView sumText;
    TextView resultDisplay;
    TextView scoreText;
    TextView timerText;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button startButton;
    Button playButton;
    RelativeLayout gameLayout;
    ArrayList<Integer> options = new ArrayList<Integer>();
    Random rand;
    int correctLocation;
    int score = 0;
    int totalQuestions = 0;
    Boolean finish = false;

    public  void  playAgain(View view){
        generateQuestion();
        score = 0;
        totalQuestions = 0;
        finish = false;
        scoreText.setText(Integer.toString(score)+"/"+Integer.toString(totalQuestions));
        timerText.setText("30s");
        playButton.setVisibility(View.INVISIBLE);
        resultDisplay.setText("");
        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long l) {
                timerText.setText(Integer.toString( (int) l/1000));
            }

            @Override
            public void onFinish() {
                timerText.setText("0s");
                finish = true;
                resultDisplay.setText("your Score is: "+Integer.toString(score)+"/"+Integer.toString(totalQuestions));
                playButton.setVisibility(View.VISIBLE);
            }
        }.start();

    }

    public  void  buttonClick(View view){
        if(finish == false) {
            Log.i("staus", view.getTag().toString() + " " + Integer.toString(correctLocation));
            if (view.getTag().toString().equals(Integer.toString(correctLocation))) {
                score++;
                resultDisplay.setText("Correct!");
            } else {
                resultDisplay.setText("Wrong!");
            }
            totalQuestions++;
            scoreText.setText(Integer.toString(score) + "/" + Integer.toString(totalQuestions));
            options.clear();
            generateQuestion();
        }
    }



    public void  generateQuestion(){
        if(finish == false) {
            int a = rand.nextInt(21);
            int b = rand.nextInt(21);

            sumText.setText(Integer.toString(a) + '+' + Integer.toString(b));


            correctLocation = rand.nextInt(4);
            for (int i = 0; i < 4; i++) {
                if (i == correctLocation) {
                    options.add(a + b);
                } else {
                    int newoption = rand.nextInt(41);
                    options.add(newoption);
                }
            }

            button0.setText(Integer.toString(options.get(0)));
            button1.setText(Integer.toString(options.get(1)));
            button2.setText(Integer.toString(options.get(2)));
            button3.setText(Integer.toString(options.get(3)));
        }
    }

    public void startGo(View view){
        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(view.VISIBLE);
        playAgain(findViewById(R.id.playAgain));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameLayout = (RelativeLayout) findViewById(R.id.game);
        sumText = (TextView) findViewById(R.id.sumText);
        timerText = (TextView) findViewById(R.id.timerText);
        resultDisplay = (TextView) findViewById(R.id.resultDisplay);
        scoreText = (TextView) findViewById(R.id.scoreText);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        startButton = (Button) findViewById(R.id.startButton);
        playButton = (Button) findViewById(R.id.playAgain);

        rand = new Random();






    }
}
