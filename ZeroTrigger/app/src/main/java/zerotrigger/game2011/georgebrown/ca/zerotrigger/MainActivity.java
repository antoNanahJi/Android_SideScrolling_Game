package zerotrigger.game2011.georgebrown.ca.zerotrigger;
//Zero Trigger
//Andre Reano 101075780
//Anto  Nanah Ji 101103788
//Logan King 101100360
//Created March 28th 2018
//Side Scrolling Android Game
     /*  Revision History
        Fixing Enemy/Player movement April 21th,2018
        added Menu sound April 21th,2018
        added Instruction Screen April 21th,2018
        updates to wolf anim April 21th,2018
        Adding Score/HealthBar/Timer/GameOver April 21th,2018
        Working on timer April 20th,2018
        Wolf enemy April 20th,2018
        Commits on Apr 20, 2018
        Added Coins April 20th,2018
        Adding Character Animations April 19,2018
        Added Bullets April 19th,2018
        Added Buttons April 18th, 2018
        New Map Tiles April 17th,2018
        Bg scroll code April 16th,2018
        Setting Map Array April 14th, 2018
        Changed from portrait to landscape April 13,2018
        LogoDesign April 13,2018
        heiarchy setup April 12,2018
        Character/Forest Sprites April 11, 2018
        Drew an example of level Apr 9,2018
        Setting Scenes Mar 29,2018
        Updated External Document March 28
        Interface idea 25 days ago
        External Game Document committed 25 days ago
        Initial commit committed 25 days ago
*/
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
                setContentView(R.layout.activity_main);
                MediaPlayer gameSound = MediaPlayer.create(MainActivity.this, R.raw.zero_trigger_music);
                gameSound.start();
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
