package zerotrigger.game2011.georgebrown.ca.zerotrigger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private Button playBtn;
    private Button instructionBtn;
    private Button quitBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MediaPlayer menuSound = MediaPlayer.create(MainActivity.this, R.raw.zero_trigger_music);
        menuSound.start();


        Button playBtn= (Button) findViewById(R.id.PlayBtn);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intentInstance = new Intent(MainActivity.this, MainGameActivity.class);
               startActivity(intentInstance);
            }
        });

        Button instructionBtn= (Button) findViewById(R.id.InstructionBtn);
        instructionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentInstance = new Intent(MainActivity.this, InstructionActivity.class);
                startActivity(intentInstance);
            }
        });

        Button quitBtn= (Button) findViewById(R.id.QuitBtn);
        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
