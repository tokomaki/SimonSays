package com.example.simonsays;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Define variables
        Random random = new Random();
        final int rand = random.nextInt(4);
        String[] fourColors = {"Green", "Red", "Yellow", "Blue"};
        final ArrayList<String> allColors = new ArrayList<>();
        allColors.add(fourColors[rand]);
        startBtn = findViewById(R.id.startBtn);
        final Class[] activities = {Green.class, Red.class, Yellow.class, Blue.class};

        // Generate Simon Says colors
        for (int i = 0; i < 4; i++) {
            int randColor = random.nextInt(4);
            allColors.add(fourColors[randColor]);
        }

        // Start button begins game
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activities[rand]);
                intent.putStringArrayListExtra("colors", allColors);
                intent.putExtra("count", 0);
                intent.putExtra("score", 0);
                startActivity(intent);
            }
        });
    }
}
