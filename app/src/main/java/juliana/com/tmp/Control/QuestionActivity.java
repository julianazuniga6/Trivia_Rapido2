package juliana.com.tmp.Control;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import juliana.com.tmp.Modelo.Pregunta;
import juliana.com.tmp.R;

public class QuestionActivity extends AppCompatActivity {

    private int estado;
    private ArrayList<Pregunta> preguntas;
    private DBHelper db;
    private Pregunta actual;
    private boolean[] aciertos;
    private LoadStatistics statistics;
    private Typeface type;

    private TextView pregunta;
    private Button b1;
    private Button b2;
    private Button b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type=Typeface.createFromAsset(getAssets(),"fonts/unicorn.ttf");
        setContentView(R.layout.activity_loading_data);
        TextView cargando= (TextView)findViewById(R.id.banner1);
        cargando.setTypeface(type);
        aciertos= new boolean[4];
        db=new DBHelper(this);
        db.execute();
    }

    public void mostrarPregunta(int num){
        actual = preguntas.get(num);
        String[] op= actual.getOpciones();
        pregunta.setText(actual.getEnunciado());
        b1.setText(op[0]);
        b2.setText(op[1]);
        b3.setText(op[2]);
    }

    public void refrescarPregunta(){

        switch (estado){
            case 0:
                mostrarPregunta(0);
                estado++;
                break;
            case 1:
                mostrarPregunta(1);
                estado++;
                break;
            case 2:
                mostrarPregunta(2);
                estado++;
                break;
            case 3:
                mostrarPregunta(3);
                estado++;
                break;
        }

    }

    public void finalizarQuiz(){
        statistics= new LoadStatistics(this, aciertos);
        statistics.execute();
        setContentView(R.layout.activity_loading_statistics);
        TextView cargando= (TextView)findViewById(R.id.banner2);
        cargando.setTypeface(type);
    }


    public void onClick(View view) {
        Context context = getApplicationContext();
        CharSequence acerto = "¡Tu respuesta fue correcta!";
        CharSequence mal = "Respuesta incorrecta";
        int duration = Toast.LENGTH_SHORT;

        int id= view.getId();
        boolean t=false;
        switch (id){
            case R.id.op1:
                t= 0==actual.getPosOK();
                aciertos[0]=t;
                break;
            case R.id.op2:
                t= 1==actual.getPosOK();
                aciertos[1]=t;
                break;
            case R.id.op3:
                t= 2==actual.getPosOK();
                aciertos[2]=t;
                break;
        }
        if(t){
            Toast toast = Toast.makeText(context, acerto, duration);
            toast.show();
        }
        else{
            Toast toast = Toast.makeText(context, mal, duration);
            toast.show();
        }

        siguiente();
    }

    public void siguiente(){
        if(estado==4){
            finalizarQuiz();
        }
        else {
            refrescarPregunta();
        }
    }

    public void startNextView(){
        estado=0;
        setContentView(R.layout.activity_question);
        pregunta= (TextView)findViewById(R.id.textPregunta);
        System.out.println(pregunta.toString());
        b1 = (Button)findViewById(R.id.op1);
        b2 = (Button)findViewById(R.id.op2);
        b3 = (Button)findViewById(R.id.op3);
        pregunta.setTypeface(type);
        b1.setTypeface(type);
        b2.setTypeface(type);
        b3.setTypeface(type);
        preguntas= db.getPreguntas();
        refrescarPregunta();
    }

    public void showStatistics(String[][] stats) {
        Intent i= new Intent(this, StatisticsActivity.class);
        i.putExtra("statistics", stats);
        startActivity(i);
        finish();
    }

}
