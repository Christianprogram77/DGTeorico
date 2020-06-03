package com.example.dgteorico;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Carreteras extends AppCompatActivity {

    private TextView LabelPreguntaCarre;
    private Button Respuesta1Carre;
    private Button Respuesta2Carre;
    private Button Respuesta3Carre;

    private String RespuestaCorrectaCarre;
    private int RespuestaCorrectaCountCarre = 0;
    private int PreguntaCountCarre = 1;
    static final private int QUIZ_COUNT_Carre = 10;

    ArrayList<ArrayList<String>> quizArray2 = new ArrayList<>();
    String quizData2[][] = {
            //Pregunta, Resp Correcta, Respuesta1, Respuesta2
            {"¿Puede circular un conductor novel con su turismo por un carril VAO?", "Sí, cuando en el vehículo viajen el número de personas fijado", "No, porque no puede superar los 80km/h",
            "No, no puede circular durante el primer año"},
            {"¿Cómo es la circulación por autopista y autovía en comparación con la de las vías urbanas?", "Más rápida", "Más lenta", "Se dan a la misma velocidad"},
            {"Para adelantar en carretera con un turismo, ¿se puede superar la velocidad máxima permitida?", "Sí, en 20 km/h, en carreteras convencionales", "No, porque está prohibido", "Sí, en todas las vías"},
            {"En vía urbana, ¿cuál es la velocidad máxima permitida para motocicletas?", "50km/h", "60km/h", "45km/h"},
            {"Un turismo que arrastra un remolque ligero, ¿a qué velocidad máxima puede circular por una autopista o una autovía?", "A 90km/h", "A 80km/h", "A 100km/h"},
            {"En una vía urbana con tres carriles para cada sentido, ¿cuál es la velocidad máxima permitida?", "50km/h porque no hay otras señales", "60km/h porque tiene tres carriles", "40km/h porque tiene pasos de peatones"},
            {"Para entrar en una autopista, ¿a qué velocidad debe circular?", "A la velocidad adecuada que permita incorporarse con seguridad", "Siempre muy despacio para entrar con seguridad", "A la máxima velocidad que permita la autopista"},
            {"¿Puede ser superada la velocidad máxima en las autopistas fuera del poblado?", "No, está totalmente prohibido","Sí, aun rebasando los límites genéricos para vías fuera de poblado",
            "Sí, pero siempre sin rebasar los límites genéricos para vías fuera de poblado"},
            {"Se prohibe circular por autopistas a los...", "Vehículos que no sean capaces de alcanzar en llano 60km/h", "Vehículos con remolque","Vehículos que transporten mercancías peligrosas"},
            {"En una autopista que transcurre por una zona urbana, ¿a qué velocidad máxima puede circular un turismo si no existe señalización?", "A 80km/h", "A 120km/h", "A 100km/h"},

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carreteras);

        LabelPreguntaCarre = findViewById(R.id.textViewCarrePreg1);
        Respuesta1Carre = findViewById(R.id.buttonCarreRes1);
        Respuesta2Carre = findViewById(R.id.buttonCarreRes2);
        Respuesta3Carre = findViewById(R.id.buttonCarreRes3);

        //Create quizArray from quizData
        for(int i = 0; i<quizData2.length; i++){

            //Prepare array
            ArrayList<String> tmpArray2 = new ArrayList<>();
            tmpArray2.add(quizData2[i][0]); //Pregunta
            tmpArray2.add(quizData2[i][1]); //Resp Correcta
            tmpArray2.add(quizData2[i][2]); //Respuesta 1
            tmpArray2.add(quizData2[i][3]); //Respuesta 2

            // Add tmpArray to quizArray
            quizArray2.add(tmpArray2);
        }
        showNextQuiz();
    }

    public void showNextQuiz(){

        //Generate random number between 0 and 14
        Random random = new Random();
        int randomNum = random.nextInt(quizArray2.size());

        //Pick one quiz set
        ArrayList<String> quiz = quizArray2.get(randomNum);

        //Set question and right answer
        LabelPreguntaCarre.setText(quiz.get(0));
        RespuestaCorrectaCarre = quiz.get(1);

        //Remove Pregunta
        quiz.remove(0);
        Collections.shuffle(quiz);

        //Set choices
        Respuesta1Carre.setText(quiz.get(0));
        Respuesta2Carre.setText(quiz.get(1));
        Respuesta3Carre.setText(quiz.get(2));

        //Remove this quiz from quizArray
        quizArray2.remove(randomNum);


    }

    public void ComprobarRespuestaCarre(View view){

        //Get pushed button
        Button respuestaBtn = findViewById(view.getId());
        String btnText = respuestaBtn.getText().toString();

        String alertaCarre;

        if(btnText.equals(RespuestaCorrectaCarre)) {
            //Correcto
            alertaCarre = "¡Correcto!";
            RespuestaCorrectaCountCarre++;
        }else{
            //Fallo
            alertaCarre = "¡Error..!";
        }

        //Create dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertaCarre);
        builder.setMessage("Respuesta : " + RespuestaCorrectaCarre);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(PreguntaCountCarre == QUIZ_COUNT_Carre){
                    //Show  result
                    Intent intent = new Intent(getApplicationContext(), ResultActivityCarreteras.class);
                    intent.putExtra("CONTADOR_PREGUNTAS_BUENAS_CARRE", RespuestaCorrectaCountCarre );
                    startActivity(intent);

                }else{
                    PreguntaCountCarre++;
                    showNextQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();

    }
}


