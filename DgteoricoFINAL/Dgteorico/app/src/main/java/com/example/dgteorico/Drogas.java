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

public class Drogas extends AppCompatActivity {

    private TextView LabelPregunta;
    private Button Respuesta1;
    private Button Respuesta2;
    private Button Respuesta3;

    private String RespuestaCorrecta;
    private int RespuestaCorrectaCount = 0;
    private int PreguntaCount = 1;
    static final private int QUIZ_COUNT = 10;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();
    String quizData[][] = {
            //Pregunta, Resp Correcta, Respuesta1, Respuesta2
            {"Ante un tratamiento farmacológico, ¿Reaccionan todas las personas igual?", "No, ya que las circunstancias individuales hacen varias los efectos",
            "No, depende únicamente del estado físico","Sí, ya que los medicamentos siempre producen los mismos efectos"},
            {"Entre otros efectos, el consumo de alcohol...", "Aumenta las distracciones al volante", "Ayuda a mantener la atención al volante",
            "Mejora la atención si se toma en pequeñas cantidades"},
            {"¿A partir de qué momento un conductor novel puede conducir con una tasa de alcoholemia de 0,25mg/l en aire espirado?",
            "2 años tras obtener el carnet", "1 año tras obtener el carnet", "6 meses tras obtener el carnet"},
            {"Si el resultado de las pruebas de alcoholemia es positivo, el agente podrá inmovilizar el vehículo, a menos...",
            "Que se pueda hacer cargo de su conducción otra persona debidamente habilitada", "Que en el vehículo viajen niños o ancianos",
            "Que el conductor haga efectiva en el acto la sanción económica impuesta"},
            {"¿Cuanta tasa máxima de alcoholemia puede dar un conductor en general?", "0,5g/l en sangre", "0,3g/l en sangre", "0,15mg/l en aire aspirado"},
            {"¿Qué efectos secundarios tiene el LSD durante la conducción?", "Se pueden sufrir alucinaciones y alteraciones emocionales", "Se sobrevaloran las capacidades del conductor",
            "Aumenta la sensación de fatiga y somnolencia"},
            {"¿Cómo será el comportamiento de un conductor que ha consumido cocaína?", "Competitivo e impulsivo", "Inseguro, ya que el conductor no valora correctamente sus capacidades al volante",
            "Muy relajado, ya que baja su capacidad de concentración"},
            {"¿Qué tanto por ciento de accidentes con muertes están relacionados con el consumo de drogas?", "Un 47%", "Un 15%", "Un 5%"},
            {"Los noveles y profesionales cuanta tasa de alcohol en aire espirado pueden dar?", "0,15mg/l", "0,25mg/l", "0,50mg/l"},
            {"La alcoholemia llega al punto máximo...", "Entre 30 y 90 minutos después de haber ingerido alcohol", "Inmediatamente", "Al cabo de cinco horas de haber ingerido alcohol"},

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drogas1);

        LabelPregunta = findViewById(R.id.textViewDrogPreg1);
        Respuesta1 = findViewById(R.id.buttonDrogRes1);
        Respuesta2 = findViewById(R.id.buttonDrogRes2);
        Respuesta3 = findViewById(R.id.buttonDrogRes3);

        //Create quizArray from quizData
        for(int i = 0; i<quizData.length; i++){

            //Prepare array
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]); //Pregunta
            tmpArray.add(quizData[i][1]); //Resp Correcta
            tmpArray.add(quizData[i][2]); //Respuesta 1
            tmpArray.add(quizData[i][3]); //Respuesta 2

            // Add tmpArray to quizArray
            quizArray.add(tmpArray);
        }
        showNextQuiz();
    }

    public void showNextQuiz(){

        //Generate random number between 0 and 14
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        //Pick one quiz set
        ArrayList<String> quiz = quizArray.get(randomNum);

        //Set question and right answer
        LabelPregunta.setText(quiz.get(0));
        RespuestaCorrecta = quiz.get(1);

        //Remove Pregunta
        quiz.remove(0);
        Collections.shuffle(quiz);

        //Set choices
        Respuesta1.setText(quiz.get(0));
        Respuesta2.setText(quiz.get(1));
        Respuesta3.setText(quiz.get(2));

        //Remove this quiz from quizArray
        quizArray.remove(randomNum);


    }

    public void ComprobarRespuesta(View view){

        //Get pushed button
        Button respuestaBtn = findViewById(view.getId());
        String btnText = respuestaBtn.getText().toString();

        String alerta;

        if(btnText.equals(RespuestaCorrecta)) {
            //Correcto
            alerta = "¡Correcto!";
            RespuestaCorrectaCount++;
        }else{
            //Fallo
            alerta = "¡Error..!";
        }

        //Create dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alerta);
        builder.setMessage("Respuesta : " + RespuestaCorrecta);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(PreguntaCount == QUIZ_COUNT){
                    //Show  result
                    Intent intent = new Intent(getApplicationContext(), ResultActivityDrogas.class);
                    intent.putExtra("CONTADOR_PREGUNTAS_BUENAS", RespuestaCorrectaCount );
                    startActivity(intent);

                }else{
                    PreguntaCount++;
                    showNextQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();

    }
}
