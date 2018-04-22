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
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{
    private MainGameActivity mainGameActivity;
    private MainThread thread;
    private RectPlayer player;
    private ArrayList<Bullet> bullets;
    private Obstacles obstacles;
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
    private boolean moveBullet=false;
    private Bitmap background;
    private Bitmap attackBtnImg;
    private int attack_Btn_PosX=Constants.SCREEN_WIDTH - 250;
    private int attack_Btn_PosY=Constants.SCREEN_HEIGHT-300;
    private int attack_Btn_SIZE=200;

    //Timer variables
    float mTimer=300.0f; //sets timer
    long startTime, endTime;
    double diff;
    String minutes=String.valueOf(mTimer);
    String seconds="0";
    Paint paint = new Paint();
    //score variables
    private int mScore=0; //initial score
    private ArrayList<Coin> coins; //array holding coins
    private int coinX=0;
    private Bitmap coin_img; //coin bitmap
    //health variables
    private int mHealth=30; //starting health
    private Bitmap health_img;   //Health heart Bitmaps
    private Bitmap health_img_1;
    private Bitmap health_img_2;
    //game over variables
    private boolean isGameOver=false;
    private Bitmap gameOver_img;


    ///////////////////////////
    // Wolf variables
    private ArrayList<Wolf> wolves; //Array holdiing wolves
    public Wolf wolf;
    public int wolfX = 0;
    private boolean isWolfAlive = true;


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

        right_Btn= BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.right_btn,options); //right touch button bitmap
        left_Btn= BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.left_btn,options);//left touch button bitmap
        background=BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.back_img,options);//back touch button bitmap

        attackBtnImg=BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.attack_btn,options);//attack button bitmap


        obstacleManager = new ObstacleManager(200);
        obstacles=new Obstacles(3);
        setFocusable(true);

        //Bullets List
        bullets = new ArrayList<>(); //array holding bullets

        //Coin object
        coins=new ArrayList<>();
        coin_img=BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.coin_bar_img,options);

        //Health bar
        health_img = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.health_bar_img,options);
        health_img_1=BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.health_bar_img,options);
        health_img_2=BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.health_bar_img,options);

        //Game over
        gameOver_img=BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.game_over_img,options);

        // Wolf object
        wolves = new ArrayList<>();
        //wolf = new Wolf(400, 730);
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
    void updateTimerText()
    {
        minutes = String.valueOf((int)mTimer / 60);
        seconds = String.valueOf(((int)mTimer % 60));
        mTimer --;
    }
    boolean timerIsDone()
    {
        if(mTimer<=0.0f)
        {
            return true;
        }
        return false;
    }
    void GeneratingStuff() //generates wolves, coins ,update timer
    {
        endTime = System.nanoTime();
        diff = (endTime - startTime)/1e6;
        if (diff > 1000) {
           //Generating Coins
            generatingCoins();
            //Generating Wolves
            spawnWolf();
            //Update Timer
            updateTimerText();
            startTime = endTime;

        }
    }
    void generatingCoins()
    {
       if(coinX<5000) {
           coins.add(new Coin(550 + coinX, 730));
           coinX+=1250;
       }
    }

    void  spawnWolf()
    {
        if (wolfX < 5000) {
            wolves.add(new Wolf(650 + wolfX, 680));
            wolfX += 1000;
        }
    }

    void setHealthBar(int value) //sets health according to heart image amount.
    {
       mHealth=value;
        BitmapFactory.Options options= new BitmapFactory.Options();
        options.inScaled=false;
        paint.setColor(Color.TRANSPARENT);
        if(mHealth==20)
            health_img_2=BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.empty,options);
        if(mHealth==10)
            health_img_1=BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.empty,options);
        if(mHealth==0) {
            health_img = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.empty, options);
            isGameOver=true;
        }
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
            }/*
            if ((x > pause_Btn_PosX && x < pause_Btn_PosX + pause_Btn_SIZE)) {
                if (y > pause_Btn_PosY && y < pause_Btn_PosY + pause_Btn_SIZE) {

                    this.clearFocus();

                }
            }*/
            if ((x > attack_Btn_PosX && x < attack_Btn_PosX + attack_Btn_SIZE)) {
                if (y > attack_Btn_PosY && y < attack_Btn_PosY + attack_Btn_SIZE) {
                    if(player.getState()==0||player.getState()==1) {
                        player.setState(4);
                        bullets.add(new Bullet(player.player_sPosX + 50, player.player_sPosY + 70, 1));
                    }
                    if(player.getState()==2||player.getState()==3) {
                        player.setState(5);
                        bullets.add(new Bullet(player.player_sPosX + 50, player.player_sPosY + 70, 2));
                    }

                    moveBullet=true;
                }
            }
        }
        if(event.getAction()==MotionEvent.ACTION_UP)
        {
            moveMap = false;
            if(movePlayerL || player.getState()==4)
            {
                movePlayerL=false;
                player.setState(0);
            }
            if(movePlayerR || player.getState()==5)
            {
                movePlayerR=false;
                player.setState(3);
            }

        }
        return true;
        //return super.onTouchEvent(event);

    }
    public void update()
    {
        if(!isGameOver) {
            //Generating Coins ,Wolves
            GeneratingStuff();
            //Moving Player
            if (movePlayerR) {
                player.decrementPlayerX();

            }
            if ((movePlayerL && player.getEPX()<Constants.SCREEN_WIDTH/2)||(movePlayerL&&obstacles.end)) {
                player.incrementPlayerX();
            }
            player.setPlayerPos();
            player.update();
            if (moveMap) {
                obstacles.update();
                for (Coin ob : coins) {
                    ob.update();
                }

                for (Wolf ob : wolves)
                {
                    ob.decrementX();
                }
            }

            if (moveBullet) {
                for (Bullet ob : bullets) {
                    ob.update();
                }
            }

            if (isWolfAlive) {


                //wolves.update();
                //when player and enemy collides set health bar
                // mHealth-=10;
                //setHealthBar(mHealth);
            }
            for (Wolf ob : wolves) {
                ob.update();
                if (ob.CollisionPlayerWolf(player)) {
                    ob.DestroyWolf();
                    mHealth-=10;
                    setHealthBar(mHealth);
                }
                for (Bullet bu : bullets) {
                    if (ob.CollisionBulletWolf(bu)) {
                    ob.DestroyWolf();
                    bu.DestroyBullet();
                    mScore++;
                }
                }
            }
            //Coin collision
            for (Coin ob : coins) {
                //ob.update();
                if (ob.CollisionWithCoin(player)) {
                    ob.DestroyCoin();
                    mScore++;
                  }
            }


            //If timer is done
            if(timerIsDone())
                isGameOver=true;
        }
    }
    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        if(!isGameOver) {
            //drawing background
            canvas.drawBitmap(background, 0, 0, null);

            //drawing Map
            // obstacleManager.draw(canvas);
            obstacles.draw(canvas);
            //Buttons
            canvas.drawBitmap(right_Btn, 250, Constants.SCREEN_HEIGHT - 200, null);
            canvas.drawBitmap(left_Btn, 0, Constants.SCREEN_HEIGHT - 200, null);
            canvas.drawBitmap(attackBtnImg, attack_Btn_PosX, attack_Btn_PosY, null);
            //Drawing timer , score ,health
            paint.setColor(Color.argb(255, 255, 255, 255));
            paint.setTextSize(45);
            canvas.drawText(minutes + " : " + seconds, Constants.SCREEN_WIDTH / 2, 50, paint);
            canvas.drawBitmap(coin_img, Constants.SCREEN_WIDTH - 200, 0, null);
            paint.setColor(Color.argb(255, 255, 255, 0));
            canvas.drawText(String.valueOf(mScore), Constants.SCREEN_WIDTH - 80, 50, paint);
            canvas.drawBitmap(health_img, 0, 0, null);
            canvas.drawBitmap(health_img_1, 100, 0, null);
            canvas.drawBitmap(health_img_2, 200, 0, null);


            //drawing player
            player.draw(canvas);
            //drawing bullets
            if (moveBullet) {
                for (Bullet ob : bullets) {
                    ob.draw(canvas);
                }
            }

            //drawing coins
            for (Coin ob : coins) {
                ob.draw(canvas);
            }
            //drawing enemy
            for(Wolf ob : wolves)
            {
                ob.draw(canvas);
            }

        }
        //drawing game over screen
        if(isGameOver)
            canvas.drawBitmap(gameOver_img,0,0,null);
    }
}
