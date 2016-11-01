package juliana.com.tmp.Control;

import android.os.AsyncTask;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import juliana.com.tmp.Modelo.Pregunta;

/**
 * Created by Juliana on 30/10/2016.
 */

public class LoadStatistics extends AsyncTask<View, String, Object> {

    private QuestionActivity q;
    private boolean[] stats;
    private String[][] statistics;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    public LoadStatistics(QuestionActivity q, boolean[] stats){
        this.stats= stats;
        this.q=q;
        statistics= new String[4][2];

        for(int i =0; i<stats.length;i++){
            System.out.println(stats[i]);
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(View... params) {
        submit(stats);
        statistics= getStats();
        return null;
    }

    public void submit(boolean[] stats){
        DatabaseReference ref= null;
        for(int i=0; i<stats.length; i++){
            Listener l= new Listener();
            if(stats[i]){
                ref= FirebaseDatabase.getInstance().getReference().child("respuestas").child("p"+(i+1)).child("buenas");
                System.out.println("entra si esta correcta");
                System.out.println(ref.toString());
            }
            else{
                ref= FirebaseDatabase.getInstance().getReference().child("respuestas").child("p"+(i+1)).child("malas");
                System.out.println("entra si no esta correcta");
                System.out.println(ref.toString());
            }

            ref.addListenerForSingleValueEvent(l);
            while(!l.isReady()){}
            System.out.println("el dato: " + l.getData());
            Long val= Long.parseLong((String)l.getData());
            val=val+1;
            String valueToSend = "" + val;
            System.out.println("valor a mandar: " + valueToSend);
            ref.setValue(valueToSend);

        }
    }

    public String[][] getStats(){

        DatabaseReference b= null;
        DatabaseReference m= null;

        for (int i=1; i<=4; i++){
            b= FirebaseDatabase.getInstance().getReference().child("respuestas").child("p"+i).child("buenas");
            m= FirebaseDatabase.getInstance().getReference().child("respuestas").child("p"+i).child("malas");
            Listener l= new Listener();
            b.addListenerForSingleValueEvent(l);
            while (!l.isReady()){}
            String buenas= l.getData();
            l=new Listener();
            m.addListenerForSingleValueEvent(l);
            while (!l.isReady()){}
            String malas= l.getData();
            statistics[i-1][0]=buenas;
            statistics[i-1][1]=malas;
        }

        return statistics;
    }

    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        q.showStatistics(statistics);
    }

}
