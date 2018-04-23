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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.SurfaceView;



public class Dragon implements GameObject {
    private Bullet bullet;
    private Rect dragon;
    private RectPlayer player;

    private Bitmap dragon_img;
    private int color;

    private int dragon_PosX ;
    private int dragon_PosY ;
    private int wolf_spawnX = 250;
    private int wolf_spawnY = 900;

    private int state=0;
    private int speed = 7;

    private Animation dragonAnim;
    private AnimationManager animManager;
    private boolean destroyMe = false;


    /////////////////////////////////////////
    // dragon constructor
    /////////////////////////////////////////

    public Dragon(int xPos, int yPos) //dragon iniatilization
    {
        dragon_PosX = xPos;
        dragon_PosY = yPos;


        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        BitmapFactory bf = new BitmapFactory();

        player = new RectPlayer(new Rect(180, 200, 300, 300), Color.GREEN);
        bullet=new Bullet(player.player_sPosX + 50, player.player_sPosY + 70, 2);
        dragon = new Rect(dragon_PosX, dragon_PosY, dragon_PosX+200, dragon_PosY+200);
        /////////////////////////////////////////
        // dragon images
        /////////////////////////////////////////

        Bitmap dragonatk1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.dragon1,options);
        Bitmap dragonatk2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.dragon2,options);
        Bitmap dragonatk3 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.dragon3,options);
        Bitmap dragonatk4 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.dragon4,options);
        Bitmap dragonatk5 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.dragon5,options);
        Bitmap dragonatk6 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.dragon6,options);
        Bitmap dragonatk7 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.dragon7,options);
        Bitmap dragonatk8 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.dragon8,options);
        Bitmap dragonatk9 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.dragon9,options);
        Bitmap dragonatk10 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.dragon10,options);



        Matrix matrix = new Matrix();
        matrix.preScale(-2, 2);

        dragonatk1 = Bitmap.createBitmap(dragonatk1, 0, 0, dragonatk1.getWidth(), dragonatk1.getHeight(), matrix, false);
        dragonatk2 = Bitmap.createBitmap(dragonatk2, 0, 0, dragonatk2.getWidth(), dragonatk2.getHeight(), matrix, false);
        dragonatk3 = Bitmap.createBitmap(dragonatk3, 0, 0, dragonatk3.getWidth(), dragonatk3.getHeight(), matrix, false);
        dragonatk4 = Bitmap.createBitmap(dragonatk4, 0, 0, dragonatk4.getWidth(), dragonatk4.getHeight(), matrix, false);
        dragonatk5 = Bitmap.createBitmap(dragonatk5, 0, 0, dragonatk5.getWidth(), dragonatk5.getHeight(), matrix, false);
        dragonatk6 = Bitmap.createBitmap(dragonatk6, 0, 0, dragonatk6.getWidth(), dragonatk6.getHeight(), matrix, false);
        dragonatk7 = Bitmap.createBitmap(dragonatk7, 0, 0, dragonatk7.getWidth(), dragonatk7.getHeight(), matrix, false);
        dragonatk8 = Bitmap.createBitmap(dragonatk8, 0, 0, dragonatk8.getWidth(), dragonatk8.getHeight(), matrix, false);
        dragonatk9 = Bitmap.createBitmap(dragonatk9, 0, 0, dragonatk9.getWidth(), dragonatk9.getHeight(), matrix, false);
        dragonatk10 = Bitmap.createBitmap(dragonatk10, 0, 0, dragonatk10.getWidth(), dragonatk10.getHeight(), matrix, false);

        /////////////////////////////////////////
        // Creating animation object
        /////////////////////////////////////////
        dragonAnim = new Animation(new Bitmap[]{dragonatk1, dragonatk2, dragonatk3, dragonatk4, dragonatk5,
                dragonatk6, dragonatk7, dragonatk8, dragonatk9, dragonatk10}, 3.0f);


        animManager = new AnimationManager(new Animation[]{dragonAnim});

    }

    public boolean CollisionPlayerDragon(RectPlayer player)
    {
        if(Rect.intersects(dragon, player.getRectangle()) == true)
        {
            return true;
        }
        return false;
    }
    public boolean CollisionBulletDragon(Bullet bullet)
    {
        if(Rect.intersects(dragon, bullet.getRectangle()) == true)
        {
            return true;
        }
        return false;
    }
    public void decrementX()
    {
        if(!destroyMe) {
            dragon_PosX -= speed;
            dragon.set(dragon_PosX, dragon_PosY, dragon_PosX + 300, dragon_PosY + 400);
        }
    }

    public void DestroyDragon()
    {
        destroyMe=true;
    }

    @Override
    public void draw(Canvas canvas) {

        animManager.draw(canvas,dragon);
    }

    @Override
    public void update() {
        if(!destroyMe) {
            animManager.playAnim(0);
            animManager.update();
        }
    }


}
