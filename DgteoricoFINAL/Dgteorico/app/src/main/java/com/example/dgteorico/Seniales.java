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

public class Seniales extends AppCompatActivity {

    private TextView LabelPreguntaSeni;
    private Button Respuesta1Seni;
    private Button Respuesta2Seni;
    private Button Respuesta3Seni;

    private String RespuestaCorrectaSeni;
    private int RespuestaCorrectaCountSeni = 0;
    private int PreguntaCountSeni = 1;
    static final private int QUIZ_COUNT_SENI = 10;

    ArrayList<ArrayList<String>> quizArray3 = new ArrayList<>();
    String quizData3[][] = {
            //Pregunta, Resp Correcta, Respuesta1, Respuesta2
            {"¿Qué significado tienen las señales verticales de tráfico de forma circular y de color rojo?", "Prohibición o restricción", "Obligación", "Peligro"},
            {"En una intersección hay una señal vertical de Stop y una marca vial de Ceda al Paso, ¿a cuál de las dos debemos obedecer?", "La señal vertical de Stop", "La marca vial de ceda al paso",
            "Ninguna de las dos, se aplicará la norma de prioridad"},
            {"Si nos encontramos una señal de VADO permanente...", "Estará prohibido estacionar delante del VADO", "Estará prohibido parar delante del VADO", "Estará permitido estacionar con duración limitada"},
            {"Cuando en una carretera hay dos señales de tráfico que se contradicen aparentemente, ¿cuál de las dos tiene prioridad?", "Solo la señal más restrictiva", "Ninguna de las dos señales",
            "Las dos señales porque son del mismo tipo"},
            {"Si nos encontramos una señal de Stop y a su misma vez hay un semáforo en verde, ¿qué deberé hacer?", "Obedecer al semáforo ya que tiene prioridad sobre la señal vertical de Stop", "Obedecer al semáforo cediendo el paso al resto de vehículos",
            "Obedecer la señal vertical de Stop"},
            {"La luz verde de un semáforo circular para vehículos significa que...", "Puede pasar y tiene preferencia de paso", "No puede pasar hasta que no se apague", "Debe dejar pasar al resto de vehículos"},
            {"Una señal azul de forma cuadrada con un 50 en medio, ¿qué indica?", "Aconsejan no circular a más de 50km/hora", "Obligan a circular a 50km/h en las curvas", "Prohiben circular a más de 50 km/h"},
            {"Una señal circular con el borde de color rojo en la que aparece una motocicleta, ¿qué significa?", "Prohibe la entrada de ciclomotores", "Prohibe la entrada de velocípedos", "Prohibe la entrada a cualquier vehículo de dos ruedas"},
            {"Si nos encontramos una señal circular de color azul con una flecha con dos direcciones indica...", "Las únicas direcciones y sentidos en que puede ir", "La prohibición de ir en las direcciones indicadas",
            "Las únicas direcciones y sentidos en que se puede ir, y que puede cambiar el sentido de la marcha"},
            {"Una señal de forma rectangular, de color azul y con un coche blanco en medio, ¿qué significa?", "Vía reservada para automóviles", "Calzada obligatoria para automóviles, excepto motocicletas sin sidecar", "Calzada obligatoria para automóviles"},

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seniales);

        LabelPreguntaSeni = findViewById(R.id.textViewSeniPreg1);
        Respuesta1Seni = findViewById(R.id.buttonSeniRes1);
        Respuesta2Seni = findViewById(R.id.buttonSeniRes2);
        Respuesta3Seni = findViewById(R.id.buttonSeniRes3);

        //Create quizArray from quizData
        for(int i = 0; i<quizData3.length; i++){

            //Prepare array
            ArrayList<String> tmpArray3 = new ArrayList<>();
            tmpArray3.add(quizData3[i][0]); //Pregunta
            tmpArray3.add(quizData3[i][1]); //Resp Correcta
            tmpArray3.add(quizData3[i][2]); //Respuesta 1
            tmpArray3.add(quizData3[i][3]); //Respuesta 2

            // Add tmpArray to quizArray
            quizArray3.add(tmpArray3);
        }
        showNextQuiz();
    }

    public void showNextQuiz(){

        //Generate random number between 0 and 14
        Random random = new Random();
        int randomNum = random.nextInt(quizArray3.size());

        //Pick one quiz set
        ArrayList<String> quiz = quizArray3.get(randomNum);

        //Set question and right answer
        LabelPreguntaSeni.setText(quiz.get(0));
        RespuestaCorrectaSeni = quiz.get(1);

        //Remove Pregunta
        quiz.remove(0);
        Collections.shuffle(quiz);

        //Set choices
        Respuesta1Seni.setText(quiz.get(0));
        Respuesta2Seni.setText(quiz.get(1));
        Respuesta3Seni.setText(quiz.get(2));

        //Remove this quiz from quizArray
        quizArray3.remove(randomNum);


    }

    public void ComprobarRespuestaSeni(View view){

        //Get pushed button
        Button respuestaBtn = findViewById(view.getId());
        String btnText = respuestaBtn.getText().toString();

        String alertaSeni;

        if(btnText.equals(RespuestaCorrectaSeni)) {
            //Correcto
            alertaSeni = "¡Correcto!";
            RespuestaCorrectaCountSeni++;
        }else{
            //Fallo
            alertaSeni = "¡Error..!";
        }

        //Create dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertaSeni);
        builder.setMessage("Respuesta : " + RespuestaCorrectaSeni);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(PreguntaCountSeni == QUIZ_COUNT_SENI){
                    //Show  result
                    Intent intent = new Intent(getApplicationContext(), ResultActivitySeniales.class);
                    intent.putExtra("CONTADOR_PREGUNTAS_BUENAS_SENI", RespuestaCorrectaCountSeni );
                    startActivity(intent);

                }else{
                    PreguntaCountSeni++;
                    showNextQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();

    }
}





