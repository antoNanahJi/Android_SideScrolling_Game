package zerotrigger.game2011.georgebrown.ca.zerotrigger;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Point;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainGameActivity extends Activity {

    //private BackgroundView backgroundView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main_game);

        ///////////////Background Scrolling///////////////////
        // Get a display object to access screen details
       /* Display display = getWindowManager().getDefaultDisplay();

        // Load the resolution into a point object
        Point resolution = new Point();
        display.getSize(resolution);

        // Set view of game
        backgroundView = new BackgroundView(this, resolution.x, resolution.y);

        // Make our bgview thew view for the activity
        setContentView(backgroundView);*/

        ////////////////////////////////////////////////////////////////

       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        Display dm =getWindowManager().getDefaultDisplay();
        Point size = new Point();
        dm.getSize(size);
        Constants.SCREEN_WIDTH = size.x;
        Constants.SCREEN_HEIGHT= size.y;


        setContentView(new GamePanel(MainGameActivity.this));


    }

   /* @Override
    protected void onPause()
    {
        super.onPause();
        backgroundView.pause();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        backgroundView.resume();
    }*/

}

