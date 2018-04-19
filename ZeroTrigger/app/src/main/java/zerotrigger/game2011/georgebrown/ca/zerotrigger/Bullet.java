package zerotrigger.game2011.georgebrown.ca.zerotrigger;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;

/**
 * Created by anto2 on 2018-04-19.
 */

public class Bullet implements GameObject {
    private RectPlayer player;
    private Bitmap bullet_img_left;
    private Bitmap bullet_img_right;
    private int bullet_PosX;
    private int bullet_PosY;
    private int bullet_width=100;
    private int bullet_height=21;
    private int speed=20;
    private int state=0;

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


    }
    public void incrementX()
    {
        bullet_PosX+=speed;
    }
    public void decrementX()
    {
        bullet_PosX-=speed;
    }
    @Override
    public void draw(Canvas canvas)
    {
        if(state==1 || state==0)
            canvas.drawBitmap(bullet_img_right,bullet_PosX,bullet_PosY,null);
        if(state==2)
            canvas.drawBitmap(bullet_img_left,bullet_PosX,bullet_PosY,null);
    }
    @Override
    public void update()
    {
        if(state==1 || state==0)
            incrementX();
        if(state==2)
            decrementX();
    }
}
