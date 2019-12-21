package com.example.simonsays;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Blue extends AppCompatActivity {
    private TextView title;
    private TextView scoreCount;
    private Button green;
    private Button red;
    private Button yellow;
    private Button blue;
    private Button restart;
    private Class[] activities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green);

        // Retrieve mobile elements
        title = findViewById(R.id.appTitle);
        scoreCount = findViewById(R.id.scoreCount);
        green = findViewById(R.id.greenBtn);
        red = findViewById(R.id.redBtn);
        yellow = findViewById(R.id.yellowBtn);
        blue = findViewById(R.id.blueBtn);
        restart = findViewById(R.id.restartBtn);

        // Gather data using intent
        final int score = getIntent().getIntExtra("score", -2);
        final int count = getIntent().getIntExtra("count", -3);
        final ArrayList<String> colorsList = getIntent().getStringArrayListExtra("colors");

        // Update score
        scoreCount.setText(score);

        // Update title text
        if (score != count) {
            String temp = String.format("Color: %d", count + 1);
            title.setText(temp);
        } else {
            String temp = String.format("Simon says %s", colorsList.get(count));
            title.setText(temp);
        }

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCorrect("Green", 0, colorsList, count, score);
            }
        });

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCorrect("Red", 1, colorsList, count, score);
            }
        });

        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCorrect("Yellow", 2, colorsList, count, score);
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCorrect("Blue", 3, colorsList, count, score);
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Blue.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void gameOver(String newTitle, ArrayList<String> colorsList, int count) {
        colorsList.set(count, newTitle);
        title.setText(newTitle);
        restart.setVisibility(View.VISIBLE);
        red.setText(newTitle);
        yellow.setText(newTitle);
        green.setText(newTitle);
    }

    private void onCorrect(String answer, int classNum, ArrayList<String> colorsList, int count, int score) {
        if (colorsList.get(count).equals(answer)) {
            Intent intent = new Intent(Blue.this, activities[classNum]);
            if ((count + 1) == colorsList.size()) {
                gameOver("You Win!", colorsList, count);
            } else {
                if (count == score) {
                    count = -1;
                    score++;
                }
                count++;
                intent.putStringArrayListExtra("colors", colorsList);
                intent.putExtra("count", count);
                intent.putExtra("score", score);
                startActivity(intent);
            }
        }
        else if (restart.getVisibility() != View.VISIBLE) {
            gameOver("gameOver", colorsList, count);
        }
    }
}
