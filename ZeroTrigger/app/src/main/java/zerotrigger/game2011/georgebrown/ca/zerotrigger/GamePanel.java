package zerotrigger.game2011.georgebrown.ca.zerotrigger;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.LinearLayout;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{
    private MainGameActivity mainGameActivity;
    private MainThread thread;
    private RectPlayer player;
    private Point playerPoint;
    private ObstacleManager obstacleManager;
    private Bitmap left_Btn;
    private int right_Btn_PosX=0;
    private int right_Btn_PosY=Constants.SCREEN_HEIGHT - 200;
    private int right_Btn_SIZE=200;
    private Bitmap right_Btn;
    private int left_Btn_PosX=250;
    private int left_Btn_PosY=Constants.SCREEN_HEIGHT - 200;
    private int left_Btn_SIZE=200;
    private boolean moveMap=false;
    private boolean movePlayerR=false;
    private boolean movePlayerL=false;
    private Bitmap background;
    private Bitmap pauseImg;
    private int pause_Btn_PosX=Constants.SCREEN_WIDTH - 200;
    private int pause_Btn_PosY=0;
    private int pause_Btn_SIZE=200;

    public GamePanel(Context context)
    {
        super(context);

        getHolder().addCallback(this);
        Constants.CURRENT_CONTEXT = context;

        thread = new MainThread(getHolder(),this);
        player = new RectPlayer(new Rect(100,100,300,300), Color.GREEN);
        playerPoint= new Point(250,830);
        BitmapFactory.Options options= new BitmapFactory.Options();
        options.inScaled=false;
        right_Btn= BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.right_btn,options);
        left_Btn= BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.left_btn,options);
        background=BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.back_img,options);
        pauseImg=BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.pause_btn,options);
        obstacleManager = new ObstacleManager(200);
        setFocusable(true);
    }
    @Override
    public void onSizeChanged (int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Constants.SCREEN_WIDTH = w;
       // Constants.SCREEN_HEIGHT = h;

    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }
    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        thread = new MainThread(getHolder(),this);
        thread.setRunning(true);
        thread.start();
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true;
        while (true)
        {
            try{
                thread.setRunning(false);
                thread.join();
            }catch (Exception e){e.printStackTrace();}
            retry = false;
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

       int x=(int)event.getX();
        int y=(int)event.getY();
        if(event.getAction()==MotionEvent.ACTION_DOWN)//&&event.getAction()==MotionEvent.ACTION_MOVE) {
        {
            if ((x > left_Btn_PosX && x < left_Btn_PosX + left_Btn_SIZE)) {
                if (y > left_Btn_PosY && y < left_Btn_PosY + left_Btn_SIZE) {
                    player.setState(1);
                    moveMap = true;
                    movePlayerL=true;
                }
            }
            if ((x > right_Btn_PosX && x < right_Btn_PosX + right_Btn_SIZE)) {
                if (y > right_Btn_PosY && y < right_Btn_PosY + right_Btn_SIZE) {
                    player.setState(2);
                    movePlayerR=true;
                }
            }
            if ((x > pause_Btn_PosX && x < pause_Btn_PosX + pause_Btn_SIZE)) {
                if (y > pause_Btn_PosY && y < pause_Btn_PosY + pause_Btn_SIZE) {
                    //Intent intentInstance = new Intent(Constants.CURRENT_CONTEXT, MainActivity.class);
                   // mainGameActivity.startActivity(intentInstance);
                }
            }
        }
        if(event.getAction()==MotionEvent.ACTION_UP)
        {
            moveMap = false;
            player.setState(0);
            movePlayerL=false;
            movePlayerR=false;
        }
        return true;
        //return super.onTouchEvent(event);

    }
    public void update()
    {
        if(movePlayerR)
        {
            player.decrementPlayerX();

        }
        if(movePlayerL)
        {
            player.incrementPlayerX();
        }
        player.setPlayerPos();
        player.update();
         if(moveMap)
            obstacleManager.update();
    }
    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        //drawing background
        canvas.drawBitmap(background,0,0,null);
        canvas.drawBitmap(pauseImg,Constants.SCREEN_WIDTH-200,0,null);

        //drawing Map
        obstacleManager.draw(canvas);
        canvas.drawBitmap(right_Btn,250,Constants.SCREEN_HEIGHT-200,null);
        canvas.drawBitmap(left_Btn,0,Constants.SCREEN_HEIGHT-200,null);
        //drawing player
        player.draw(canvas);
        //drawing enemy
    }
}
