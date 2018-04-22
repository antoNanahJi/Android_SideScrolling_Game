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
    private boolean destroyMe=false;

    private AnimationManager animManager;
    public Rect getRectangle()
    {
        return bullet;
    }

    public Bullet( int xPos,int yPos,int st) //Creates Bullet
    {
        bullet_PosX=xPos;
        bullet_PosY=yPos;
        state=st;

        BitmapFactory.Options options= new BitmapFactory.Options();
        options.inScaled=false;
        bullet_img_right= BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.arrow,options);
        bullet_img_left = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.arrow_left,options);
        player = new RectPlayer(new Rect(100,100,300,300), Color.GREEN);
        bullet=new Rect(bullet_PosX,bullet_PosY,100,20); //Sets bullet Position

        bulletR = new Animation(new Bitmap[]{bullet_img_right}, 2); //Creates animation for Bullet going right
        bulletL= new Animation(new Bitmap[]{bullet_img_left}, 2); //Creates animation for Bullet going left

        animManager = new AnimationManager(new Animation[]{bulletR, bulletL});

    }
    public void incrementX()  //makes bullet move right
    {

        bullet_PosX+=speed;
        bullet.set(bullet_PosX,bullet_PosY,bullet_PosX+100,bullet_PosY+50);
        animManager.playAnim(0);
    }
    public void decrementX() //makes bullet move left
    {

        bullet_PosX-=speed;
        bullet.set(bullet_PosX,bullet_PosY,bullet_PosX+100,bullet_PosY+50);
        animManager.playAnim(1);
    }
    public void DestroyBullet()
    {
        destroyMe=true;
    } //Destroys the bullet
    @Override
    public void draw(Canvas canvas) //Draws the bullet
    {
        Paint paint = new Paint();
        if(!destroyMe) {

            //paint.setColor(Color.RED);
            animManager.draw(canvas,bullet);
            //canvas.drawRect(coin, paint);
        }
        if(destroyMe) {
            paint.setColor(Color.TRANSPARENT);
            canvas.drawRect(bullet, paint);
            bullet.set(0,0,0,0);
        }
    }
    @Override
    public void update()
    {
        if(!destroyMe) {
            if (state == 1 || state == 0)
                incrementX();
            if (state == 2)
                decrementX();
            animManager.update();
        }
    }
}
