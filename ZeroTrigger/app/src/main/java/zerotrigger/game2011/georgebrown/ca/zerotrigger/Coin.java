package zerotrigger.game2011.georgebrown.ca.zerotrigger;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

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


    private Animation coin_anim;

    private AnimationManager animManager;

    public Coin( int xPos,int yPos)
    {
        coin_PosX=xPos;
        coin_PosY=yPos;


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
      {   return true;
           }
        return false;
    }
    @Override
    public void draw(Canvas canvas)
    {

        animManager.draw(canvas,coin);
    }
    @Override
    public void update()
    {
        animManager.update();
    }

}
