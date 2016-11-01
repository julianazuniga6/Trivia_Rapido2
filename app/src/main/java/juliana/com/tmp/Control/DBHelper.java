package juliana.com.tmp.Control;

import android.os.AsyncTask;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import juliana.com.tmp.Modelo.Pregunta;

/**
 * Created by Juliana on 29/10/2016.
 */

public class DBHelper extends AsyncTask<View, String, Object> {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    private Listener l;
    private ArrayList<Pregunta> preguntas;
    private QuestionActivity qa;

    public DBHelper(QuestionActivity lda){
        preguntas= new ArrayList<Pregunta>();
        this.qa=lda;
    }
    @Override
    protected Object doInBackground(View... params) {
        cargarPreguntas();
        actualizarEstadisticas(true,1);
        return null;
    }
    private void cargarPreguntas(){

        for(int i=1; i<=4; i++){
            DatabaseReference p= FirebaseDatabase.getInstance().getReference().child("preguntas").child("p"+i);
            Listener l= new Listener();
            p.addListenerForSingleValueEvent(l);
            while(!l.isReady()){

            }
            String pregunta = (String) l.getData();
            //opciones
            //buena
            DatabaseReference ok= FirebaseDatabase.getInstance().getReference().child("opciones").child("p"+i).child("buena");
            l= new Listener();
            ok.addListenerForSingleValueEvent(l);
            while(!l.isReady()){}
            String buena= l.getData();
            //malas
            DatabaseReference w= FirebaseDatabase.getInstance().getReference().child("opciones").child("p"+i).child("malas");
            l= new Listener();
            w.addListenerForSingleValueEvent(l);
            while(!l.isReady()){}
            String m= l.getData();
            String[] malas= m.split(";");
            Pregunta q= new Pregunta(malas,buena,pregunta);
            preguntas.add(q);
            q.printPregunta();

        }
    }

    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        qa.startNextView();
    }

    public ArrayList<Pregunta> getPreguntas(){
        return preguntas;
    }

    public void validarRespuesta(int pregunta, boolean b){
        if(b){
            actualizarEstadisticas(b, pregunta++);
        }
        else{
            actualizarEstadisticas(b, pregunta++);
        }
    }

    private void actualizarEstadisticas(boolean acerto, int pregunta){

        }

    }

