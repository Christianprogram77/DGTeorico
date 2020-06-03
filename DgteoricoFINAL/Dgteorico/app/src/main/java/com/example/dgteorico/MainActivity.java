package com.example.dgteorico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import OpenHelper.SQLite_OpenHelper;

public class MainActivity extends AppCompatActivity {

    //Declaramos el textview registrar para que nos lleve a otra pantalla
    TextView tvRegistrar;
    //Declaramos el boton
    Button btnEntrar;

    //Creamos la instancia de la base de datos que nos permitirá trabajar con todos sus métodos
    SQLite_OpenHelper helper = new SQLite_OpenHelper(this,"BD1",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvRegistrar=findViewById(R.id.textViewRegistrese);
        tvRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Registro.class);
                startActivity(i);

            }
        });

        btnEntrar=findViewById(R.id.buttonEntrar);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Leemos el contenido de las cajas de texto
                EditText txtusu = findViewById(R.id.editTextCorreo);
                EditText txtpas = findViewById(R.id.editTextContraseña);

                //Declaramos objeto de tipo cursor
                try {

                    Cursor cursor = helper.ConsultarUsuPas(txtusu.getText().toString(), txtpas.getText().toString());
                    //Vamos a comprobar si existe o no el usuario
                    if(cursor.getCount()>0) {
                        Intent i = new Intent(getApplicationContext(), PantallaHome.class);
                        startActivity(i);
                        //Si no existe
                    }else{
                        Toast.makeText(getApplicationContext(), "Usuario y/o Contraseña incorrectos",Toast.LENGTH_LONG).show();
                    }
                    //Limpiamos las cajas de texto
                    txtusu.setText("");
                    txtpas.setText("");
                    //Focalizamos el cursor
                    txtusu.findFocus();

                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
