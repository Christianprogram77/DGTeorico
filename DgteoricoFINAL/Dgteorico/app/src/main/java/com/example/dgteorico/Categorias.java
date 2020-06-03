package com.example.dgteorico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Categorias extends AppCompatActivity implements View.OnClickListener {

    ImageButton drogas, carreteras, seniales, simulacro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

       drogas=findViewById(R.id.imageButtonDrogas);
       drogas.setOnClickListener(this);

       carreteras=findViewById(R.id.imageButtonCarretera);

       seniales=findViewById(R.id.imageButtonSenal);

       simulacro=findViewById(R.id.imageButtonExamen);



    }

    @Override
    public void onClick(View v) {
        Intent intent1 = new Intent(this,Drogas.class);
        startActivity(intent1);
    }

    public void clickCarreteras(View v){
        Intent intent2 = new Intent(this,Carreteras.class);
        startActivity(intent2);
    }

    public void clickSeniales (View v){
        Intent intent3 = new Intent(this,Seniales.class);
        startActivity(intent3);
    }

    public void clickSimulacro (View v){
        Intent intent4 = new Intent(this,Simulacro.class);
        startActivity(intent4);
    }
}
