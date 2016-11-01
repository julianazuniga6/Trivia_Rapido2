package juliana.com.tmp.Control;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import juliana.com.tmp.Modelo.Pregunta;
import juliana.com.tmp.R;

public class MainActivity extends AppCompatActivity{

    private ArrayList<Pregunta> pregs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/unicorn.ttf");
        TextView tv= (TextView)findViewById(R.id.title);
        tv.setTypeface(face);
        TextView m= (TextView)findViewById(R.id.mensaje);
        m.setTypeface(face);


    }
    public void startQuiz(View view){
        Intent i= new Intent(this, QuestionActivity.class);
        startActivity(i);
        finish();
    }

}
