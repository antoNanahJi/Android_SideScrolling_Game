package zerotrigger.game2011.georgebrown.ca.zerotrigger;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.media.MediaPlayer;

/**
 * Created by anto2 on 2018-04-20.
 */

public class Coin implements GameObject {
    private Rect coin;
    private RectPlayer player;
    private Bitmap coin_img;
    private int coin_PosX;
    private int coin_PosY;
    private int coin_width=100;
    private int coin_height=21;
    private int speed=7;
    private boolean destroyMe=false;


    private Animation coin_anim;

    private AnimationManager animManager;

    public Coin( int xPos,int yPos)
    {
        coin_PosX=xPos;
        coin_PosY=yPos;
        destroyMe=false;

        BitmapFactory.Options options= new BitmapFactory.Options();
        options.inScaled=false;
        coin_img= BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.coin_img,options);
        player = new RectPlayer(new Rect(100,100,300,300), Color.GREEN);
        coin=new Rect(coin_PosX,coin_PosY,coin_PosX+100,coin_PosY+100);


        coin_anim= new Animation(new Bitmap[]{coin_img}, 2);

        animManager = new AnimationManager(new Animation[]{coin_anim});
        animManager.playAnim(0);
    }
    public boolean CollisionWithCoin(RectPlayer player)
    {
      if( Rect.intersects(coin,player.getRectangle())==true )
      {
          return true;
           }
        return false;
    }
    public void decrementX() {
        coin_PosX -= speed;
        coin.set(coin_PosX,coin_PosY,coin_PosX+100,coin_PosY+100);
    }
    public void DestroyCoin()
    {
        destroyMe=true;
    }
    @Override
    public void draw(Canvas canvas)
    {
        Paint paint = new Paint();
        if(!destroyMe) {

            //paint.setColor(Color.RED);
            animManager.draw(canvas,coin);
            //canvas.drawRect(coin, paint);
        }
        if(destroyMe) {
            paint.setColor(Color.TRANSPARENT);
            canvas.drawRect(coin, paint);
            coin.set(0,0,0,0);
        }
    }
    @Override
    public void update()
    {
        animManager.update();
        if(!destroyMe) {
            decrementX();
        }
    }

}
