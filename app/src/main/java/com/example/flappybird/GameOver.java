package com.example.flappybird;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameOver extends AppCompatActivity {

    ImageView PlayAgain;
    TextView Score, BestScore;
    Button Btn_Exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_over);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        PlayAgain = findViewById(R.id.PlayAgain);
        Score = findViewById(R.id.Score);
        BestScore = findViewById(R.id.BestScore);
        Btn_Exit = findViewById(R.id.Btn_Exit);

        int score = getIntent().getExtras().getInt("Score");
        SharedPreferences sharedPreferences = getSharedPreferences("Preference", 0);
        int scoreSP = sharedPreferences.getInt("scoreSP", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (score > scoreSP){
            scoreSP = score;
            editor.putInt("scoreSP", scoreSP);
            editor.commit();
        }

        Score.setText(""+ score);
        BestScore .setText(""+ scoreSP);

        PlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOver.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Btn_Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }
}