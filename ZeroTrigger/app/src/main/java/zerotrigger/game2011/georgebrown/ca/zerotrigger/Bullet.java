package zerotrigger.game2011.georgebrown.ca.zerotrigger;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by anto2 on 2018-04-19.
 */

public class Bullet implements GameObject {
    private Rect bullet;
    private RectPlayer player;
    private Bitmap bullet_img_left;
    private Bitmap bullet_img_right;
    private int bullet_PosX;
    private int bullet_PosY;
    private int bullet_width=100;
    private int bullet_height=21;
    private int speed=20;
    private int state=0;
    private Animation bulletR;
    private Animation bulletL;

    private AnimationManager animManager;

    public Bullet( int xPos,int yPos,int st)
    {
        bullet_PosX=xPos;
        bullet_PosY=yPos;
        state=st;

        BitmapFactory.Options options= new BitmapFactory.Options();
        options.inScaled=false;
        bullet_img_right= BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.arrow,options);
        bullet_img_left = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.arrow_left,options);
        player = new RectPlayer(new Rect(100,100,300,300), Color.GREEN);
        bullet=new Rect(bullet_PosX,bullet_PosY,100,20);

        bulletR = new Animation(new Bitmap[]{bullet_img_right}, 2);
        bulletL= new Animation(new Bitmap[]{bullet_img_left}, 2);

        animManager = new AnimationManager(new Animation[]{bulletR, bulletL});

    }
    public void incrementX()
    {

        bullet_PosX+=speed;
        bullet.set(bullet_PosX,bullet_PosY,bullet_PosX+100,bullet_PosY+50);
        animManager.playAnim(0);
    }
    public void decrementX()
    {

        bullet_PosX-=speed;
        bullet.set(bullet_PosX,bullet_PosY,bullet_PosX+100,bullet_PosY+50);
        animManager.playAnim(1);
    }
    @Override
    public void draw(Canvas canvas)
    {
        animManager.draw(canvas,bullet);
    }
    @Override
    public void update()
    {
        if(state==1 || state==0)
            incrementX();
        if(state==2)
            decrementX();
        animManager.update();
    }
}
