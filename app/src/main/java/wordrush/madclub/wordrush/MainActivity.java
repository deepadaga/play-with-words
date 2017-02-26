package wordrush.madclub.wordrush;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button play;
    private TextView howPlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = (Button) findViewById(R.id.play);
        howPlay =(TextView)findViewById(R.id.how_play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playIntent = new Intent(getApplicationContext(),GameActivity.class);
                startActivity(playIntent);
            }
        });
        howPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent howPlay = new Intent(getApplicationContext(),Help.class);
                startActivity(howPlay);
            }
        });
    }
}
