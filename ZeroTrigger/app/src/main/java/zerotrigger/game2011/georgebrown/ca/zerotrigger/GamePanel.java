package zerotrigger.game2011.georgebrown.ca.zerotrigger;

import android.content.Context;
import android.graphics.Canvas;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{
    private MainThread thread;
    private RectPlayer player;
    private Point playerPoint;
    private ObstacleManager obstacleManager;

    public GamePanel(Context context)
    {
        super(context);

        getHolder().addCallback(this);
        Constants.CURRENT_CONTEXT = context;

        thread = new MainThread(getHolder(),this);
        player = new RectPlayer(new Rect(100,100,200,200), Color.GREEN);
        playerPoint= new Point(150,150);

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
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            playerPoint.set((int)event.getX(),(int)event.getY());
        }
        return true;
        //return super.onTouchEvent(event);
    }
    public void update()
    {
        player.update(playerPoint);
        obstacleManager.update();
    }
    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        //drawing background
        //drawing Map
        obstacleManager.draw(canvas);
        //drawing player
        player.draw(canvas);
        //drawing enemy
    }
}
