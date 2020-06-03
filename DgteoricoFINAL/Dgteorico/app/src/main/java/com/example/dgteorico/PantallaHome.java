package com.example.dgteorico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PantallaHome extends AppCompatActivity implements View.OnClickListener {

    Button iniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_home);

        iniciar=findViewById(R.id.buttonIniciar);
        iniciar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent1 = new Intent(this,Categorias.class);
        startActivity(intent1);
    }
}
