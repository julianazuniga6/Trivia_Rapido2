package juliana.com.tmp.Control;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import juliana.com.tmp.R;

public class StatisticsActivity extends AppCompatActivity{

    private String[][] statistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/unicorn.ttf");
        TextView tv= (TextView)findViewById(R.id.textest);
        tv.setTypeface(face);

        Intent intent= getIntent();
        statistics= (String[][]) intent.getSerializableExtra("statistics");

        TextView p1b= (TextView)findViewById(R.id.p1b);
        TextView p1m= (TextView)findViewById(R.id.p1m);

        TextView p2b= (TextView)findViewById(R.id.p2b);
        TextView p2m= (TextView)findViewById(R.id.p2m);

        TextView p3b= (TextView)findViewById(R.id.p3b);
        TextView p3m= (TextView)findViewById(R.id.p3m);

        TextView p4b= (TextView)findViewById(R.id.p4b);
        TextView p4m= (TextView)findViewById(R.id.p4m);

        p1b.setText(statistics[0][0]);
        p1m.setText(statistics[0][1]);

        p2b.setText(statistics[1][0]);
        p2m.setText(statistics[1][1]);

        p3b.setText(statistics[2][0]);
        p3m.setText(statistics[2][1]);

        p4b.setText(statistics[3][0]);
        p4m.setText(statistics[3][1]);

    }


    public void restartQuiz(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
