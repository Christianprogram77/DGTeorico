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

public class Simulacro extends AppCompatActivity {

    private TextView LabelPreguntaSimu;
    private Button Respuesta1Simu;
    private Button Respuesta2Simu;
    private Button Respuesta3Simu;

    private String RespuestaCorrectaSimu;
    private int RespuestaCorrectaCountSimu = 0;
    private int PreguntaCountSimu = 1;
    static final private int QUIZ_COUNT_SIMU = 10;

    ArrayList<ArrayList<String>> quizArray4 = new ArrayList<>();
    String quizData4[][] = {
            //Pregunta, Resp Correcta, Respuesta1, Respuesta2
            {"¿Se podrá cambiar de carril cuando se está situado en un carril delimitado por lineas continuas?", "No", "Sí", "Únicamente los camiones"},
            {"¿Qué se debe hacer ante una linea discontinua a lo ancho de uno o varios carriles?", "Ceder el paso cuando sea necesario", "Detener el vehiculo", "Girar obligatoriamente a la derecha"},
            {"¿Puede estar obligado un peatón a realizar una prueba de alcoholemia?", "Sí, cuando esté implicado directamente como posible responsable de un accidente", "No, en ningún caso", "Sí, en cualquier control preventivo"},
            {"La luz antiniebla trasera deberá utilizarse en caso de...", "Niebla espesa", "Circular al anochecer por una vía sin arcén", "circular de noche por una vía sin asfaltar"},
            {"¿Está permitido estacionar en doble fila?", "No está permitido en ningún caso", "Sí, cuando no haya espacio libre en las proximidades", "Sí, en las vías urbanas, por un tiempo inferior a dos minutos"},
            {"¿Qué medida deben adoptar los conductores para mejorar la visibilidad y adherencia con niebla?", "Aumentar la distancia de separación", "Incrementar la velocidad", "No prestar demasiada atención a las marcas viales"},
            {"Los accidentes de tráfico generan...", "Daños materiales y costes sanitarios, administrativos y humanos", "Un enorme impacto  económico, solamente", "Daños materiales y costes humanos, únicamente"},
            {"La distancia de reacción es:", "La distancia que recorre el vehículo durante el tiempo de reacción", "La distancia que recorre el vehículo desde que empieza a frenar hasta que se detiene", "La distancia que debe guardarse con el vehículo que va delante"},
            {"¿Qué es el ángulo muerto?", "Es una zona alrededor del vehículo que no se ve con los retrovisores", "Es una parte de los coches que puede producir accidentes", "Es la zona sin visibilidad que tiene el conductor al coger una curva"},
            {"¿Qué indica una linea discontinua, cuyos trazos están más próximos entre si?", "La proximidad de un tramo peligroso", "Un carril especial", "La proximidad de un carril para tráfico lento"},

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulacro);

        LabelPreguntaSimu = findViewById(R.id.textViewSimuPreg1);
        Respuesta1Simu = findViewById(R.id.buttonSimuRes1);
        Respuesta2Simu = findViewById(R.id.buttonSimuRes2);
        Respuesta3Simu = findViewById(R.id.buttonSimuRes3);

        //Create quizArray from quizData
        for(int i = 0; i<quizData4.length; i++){

            //Prepare array
            ArrayList<String> tmpArray4 = new ArrayList<>();
            tmpArray4.add(quizData4[i][0]); //Pregunta
            tmpArray4.add(quizData4[i][1]); //Resp Correcta
            tmpArray4.add(quizData4[i][2]); //Respuesta 1
            tmpArray4.add(quizData4[i][3]); //Respuesta 2

            // Add tmpArray to quizArray
            quizArray4.add(tmpArray4);
        }
        showNextQuiz();
    }

    public void showNextQuiz(){

        //Generate random number between 0 and 14
        Random random = new Random();
        int randomNum = random.nextInt(quizArray4.size());

        //Pick one quiz set
        ArrayList<String> quiz = quizArray4.get(randomNum);

        //Set question and right answer
        LabelPreguntaSimu.setText(quiz.get(0));
        RespuestaCorrectaSimu = quiz.get(1);

        //Remove Pregunta
        quiz.remove(0);
        Collections.shuffle(quiz);

        //Set choices
        Respuesta1Simu.setText(quiz.get(0));
        Respuesta2Simu.setText(quiz.get(1));
        Respuesta3Simu.setText(quiz.get(2));

        //Remove this quiz from quizArray
        quizArray4.remove(randomNum);


    }

    public void ComprobarRespuestaSimu(View view){

        //Get pushed button
        Button respuestaBtn = findViewById(view.getId());
        String btnText = respuestaBtn.getText().toString();

        String alertaSimu;

        if(btnText.equals(RespuestaCorrectaSimu)) {
            //Correcto
            alertaSimu = "¡Correcto!";
            RespuestaCorrectaCountSimu++;
        }else{
            //Fallo
            alertaSimu = "¡Error..!";
        }

        //Create dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertaSimu);
        builder.setMessage("Respuesta : " + RespuestaCorrectaSimu);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(PreguntaCountSimu == QUIZ_COUNT_SIMU){
                    //Show  result
                    Intent intent = new Intent(getApplicationContext(), ResultActivitySimulacro.class);
                    intent.putExtra("CONTADOR_PREGUNTAS_BUENAS_SIMU", RespuestaCorrectaCountSimu );
                    startActivity(intent);

                }else{
                    PreguntaCountSimu++;
                    showNextQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();


    }
}
