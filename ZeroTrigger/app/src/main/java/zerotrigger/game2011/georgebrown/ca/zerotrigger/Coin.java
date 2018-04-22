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

    public Coin( int xPos,int yPos) //Coin class
    {
        coin_PosX=xPos;
        coin_PosY=yPos;
        destroyMe=false;

        BitmapFactory.Options options= new BitmapFactory.Options();
        options.inScaled=false;
        coin_img= BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.coin_img,options); //Sets image of the coin
        player = new RectPlayer(new Rect(100,100,300,300), Color.GREEN);
        coin=new Rect(coin_PosX,coin_PosY,coin_PosX+100,coin_PosY+100);


        coin_anim= new Animation(new Bitmap[]{coin_img}, 2); //Coin animation

        animManager = new AnimationManager(new Animation[]{coin_anim});
        animManager.playAnim(0);
    }
    public boolean CollisionWithCoin(RectPlayer player) //Collision with player
    {
      if( Rect.intersects(coin,player.getRectangle())==true )
      {
          return true;
           }
        return false;
    }
    public void decrementX() { //makes the coin appear to be going left
        coin_PosX -= speed;
        coin.set(coin_PosX,coin_PosY,coin_PosX+100,coin_PosY+100);
    }
    public void DestroyCoin()
    {
        destroyMe=true;
    } //destroys coin
    @Override
    public void draw(Canvas canvas) //draws coin
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
