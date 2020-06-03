package com.example.dgteorico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivityDrogas extends AppCompatActivity implements View.OnClickListener {

    Button volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_drogas);

        volver = findViewById(R.id.buttonVolver);
        volver.setOnClickListener(this);



        TextView resultLabel = findViewById(R.id.resultLabel);
        TextView totalScoreLabel = findViewById(R.id.totalScoreLabel);

        int score = getIntent().getIntExtra("CONTADOR_PREGUNTAS_BUENAS", 0);

        SharedPreferences settings = getSharedPreferences("quizApp", Context.MODE_PRIVATE);
        int totalScore = settings.getInt("totalScore", 0);
        totalScore += score;



        resultLabel.setText(score + " / 10");
        totalScoreLabel.setText("Puntuación Total: " + totalScore);

        //Update total score
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("PuntuacionTotal", totalScore);
        editor.commit();
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(getApplicationContext(),Categorias.class);
        startActivity(i);
    }
}
