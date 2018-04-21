package zerotrigger.game2011.georgebrown.ca.zerotrigger;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.test.ActivityUnitTestCase;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by andre on 4/21/2018.
 */

public class TimerCountdown extends Activity{
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);


        textView = (TextView) findViewById(R.id.timer);


        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                textView.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                textView.setText("done!");
            }
        }.start();
    }
}
