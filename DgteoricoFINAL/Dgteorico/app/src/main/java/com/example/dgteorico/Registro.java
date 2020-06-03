package com.example.dgteorico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import OpenHelper.SQLite_OpenHelper;

public class Registro extends AppCompatActivity {

    //Creamos los componentes de la pantalla registro
    Button btnGrabarUsu;
    EditText txtNomUsu, txtEdaUsu, txtCorUsu, txtContraUsu;

    //Creamos una instancia de nuestra base de datos del paquete OpenHelper
    //Tenemos que introducir los 4 parametros del constructor
    //Version 1 para poder actualizar las estructuras de la tabla con respecto a la base de datos
    SQLite_OpenHelper helper = new SQLite_OpenHelper(this,"BD1",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //Enlazamos los controles con las variables
        btnGrabarUsu=findViewById(R.id.buttonRegistrarUsuario);
        txtNomUsu=findViewById(R.id.RegistroNombre);
        txtEdaUsu=findViewById(R.id.RegistroEdad);
        txtCorUsu=findViewById(R.id.RegistroCorreo);
        txtContraUsu=findViewById(R.id.RegistroContraseña);

        //Implementamos el click del botón
        btnGrabarUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Abrimos la base de datos para poder insertar los registros
                helper.abrir();
                //Para poder escribir en ella
                //Introducimos los datos de la clase InsertarReg
                helper.insertarReg(String.valueOf(txtNomUsu.getText()),
                        String.valueOf(txtEdaUsu.getText()),
                        String.valueOf(txtCorUsu.getText()),
                        String.valueOf(txtContraUsu.getText()));
                helper.cerrar();

                //Mostrar mensaje
                Toast.makeText(getApplicationContext(),"Registro almacenado con éxito", Toast.LENGTH_LONG).show();

                //Volvemos a la pantalla MainActivity
                Intent i = new Intent(getApplicationContext(),MainActivity.class);

                //Lanzamos la actividad
                startActivity(i);
            }
        });
    }
}
