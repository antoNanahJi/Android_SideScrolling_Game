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



public class Wolf implements GameObject {
    private Rect wolf;
    private RectPlayer player;
    private Bullet bullet;
    private Bitmap wolf_img;
    private int color;

    private int wolf_PosX ;
    private int wolf_PosY ;
    private int wolf_spawnX = 250;
    private int wolf_spawnY = 900;

    private int state=0;
    private int speed = 7;

    private Animation wolfidle_anim;
    private Animation wolfattack_anim;
    private Animation wolfrecover_anim;

    private AnimationManager animManager;

    private boolean destroyMe=false;



    /////////////////////////////////////////
    // Wolf constructor
    /////////////////////////////////////////

    public Wolf(int xPos, int yPos) //wolf iniatilization
    {
        wolf_PosX = xPos;
        wolf_PosY = yPos;


        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        BitmapFactory bf = new BitmapFactory();

        player = new RectPlayer(new Rect(125, 100, 300, 300), Color.GREEN);
        bullet=new Bullet(player.player_sPosX + 50, player.player_sPosY + 70, 2);
        wolf = new Rect(wolf_PosX, wolf_PosY, wolf_PosX+200, wolf_PosY+200);
        /////////////////////////////////////////
        // Wolf idle images
        /////////////////////////////////////////

        /*Bitmap[] wolf_idle = new Bitmap[]{bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.Wolf_idle1),
                bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.Wolf_idle2),
                bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.Wolf_idle3)};*/

        Bitmap wolf_idle1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.wolf_idle1,options);
        Bitmap wolf_idle2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.wolf_idle2,options);
        Bitmap wolf_idle3 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.wolf_idle3,options);

        /////////////////////////////////////////
        // Wolf attack images
        /////////////////////////////////////////

        /*Bitmap[] wolf_atk = new Bitmap[]{bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.Wolf_attack1),
                bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.Wolf_attack2),
                bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.Wolf_attack3),
                bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.Wolf_attack4),
                bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.Wolf_attack5),
                bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.Wolf_attack6)};*/

        Bitmap wolf_atk1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.wolf_attack1,options);
        Bitmap wolf_atk2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.wolf_attack2,options);
        Bitmap wolf_atk3 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.wolf_attack3,options);
        Bitmap wolf_atk4 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.wolf_attack4,options);
        Bitmap wolf_atk5 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.wolf_attack5,options);
        Bitmap wolf_atk6 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.wolf_attack6,options);

        /////////////////////////////////////////
        // Wolf recover images
        /////////////////////////////////////////

        /*Bitmap[] wolfRec = new Bitmap[]{bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.Wolf_Recover1),
                bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.Wolf_Recover2),
                bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.Wolf_Recover3)};*/

        Bitmap wolfRec1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.wolf_recover1,options);
        Bitmap wolfRec2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.wolf_recover2,options);
        Bitmap wolfRec3 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.wolf_recover3,options);



        Matrix matrix = new Matrix();
        matrix.preScale(-1, 1);

        wolf_idle1 = Bitmap.createBitmap(wolf_idle1, 0, 0, wolf_idle1.getWidth(), wolf_idle1.getHeight(), matrix, false);
        wolf_idle2 = Bitmap.createBitmap(wolf_idle2, 0, 0, wolf_idle2.getWidth(), wolf_idle2.getHeight(), matrix, false);
        wolf_idle3 = Bitmap.createBitmap(wolf_idle3, 0, 0, wolf_idle3.getWidth(), wolf_idle3.getHeight(), matrix, false);
        wolf_atk1 = Bitmap.createBitmap(wolf_atk1, 0, 0, wolf_atk1.getWidth(), wolf_atk1.getHeight(), matrix, false);
        wolf_atk2 = Bitmap.createBitmap(wolf_atk2, 0, 0, wolf_atk2.getWidth(), wolf_atk2.getHeight(), matrix, false);
        wolf_atk3 = Bitmap.createBitmap(wolf_atk3, 0, 0, wolf_atk3.getWidth(), wolf_atk3.getHeight(), matrix, false);
        wolf_atk4 = Bitmap.createBitmap(wolf_atk4, 0, 0, wolf_atk4.getWidth(), wolf_atk4.getHeight(), matrix, false);
        wolf_atk5 = Bitmap.createBitmap(wolf_atk5, 0, 0, wolf_atk5.getWidth(), wolf_atk5.getHeight(), matrix, false);
        wolf_atk6 = Bitmap.createBitmap(wolf_atk6, 0, 0, wolf_atk6.getWidth(), wolf_atk6.getHeight(), matrix, false);
        wolfRec1 = Bitmap.createBitmap(wolfRec1, 0, 0, wolfRec1.getWidth(), wolfRec1.getHeight(), matrix, false);
        wolfRec2 = Bitmap.createBitmap(wolfRec2, 0, 0, wolfRec2.getWidth(), wolfRec2.getHeight(), matrix, false);
        wolfRec3 = Bitmap.createBitmap(wolfRec3, 0, 0, wolfRec3.getWidth(), wolfRec3.getHeight(), matrix, false);
        /////////////////////////////////////////
        // Creating animation object
        /////////////////////////////////////////
        wolfidle_anim = new Animation(new Bitmap[]{wolf_idle1, wolf_idle2, wolf_idle3, wolf_atk1, wolf_atk2,
                wolf_atk3, wolf_atk4, wolf_atk5, wolf_atk6, wolfRec1, wolfRec2, wolfRec3}, 3.0f);
        //wolfattack_anim = new Animation(new Bitmap[]{wolf_atk1, wolf_atk2, wolf_atk3, wolf_atk4, wolf_atk5, wolf_atk6}, 0.5f);
        //wolfrecover_anim = new Animation(new Bitmap[]{wolfRec1, wolfRec2, wolfRec3}, 0.5f);

        animManager = new AnimationManager(new Animation[]{wolfidle_anim});

    }

    public boolean CollisionPlayerWolf(RectPlayer player)
    {
        if(Rect.intersects(wolf, player.getRectangle()) == true)
        {
            return true;
        }
        return false;
    }
    public boolean CollisionBulletWolf(Bullet bullet)
    {
        if(Rect.intersects(wolf, bullet.getRectangle()) == true)
        {
            return true;
        }
        return false;
    }
    public void decrementX()
    {
        if(!destroyMe) {
            wolf_PosX -= speed;
            wolf.set(wolf_PosX, wolf_PosY, wolf_PosX + 200, wolf_PosY + 200);
        }
    }

    public void DestroyWolf()
    {
        destroyMe=true;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        if(!destroyMe) {

            //paint.setColor(Color.RED);
            animManager.draw(canvas,wolf);
            //canvas.drawRect(coin, paint);
        }
        if(destroyMe) {
            paint.setColor(Color.TRANSPARENT);
            canvas.drawRect(wolf, paint);
            wolf.set(0,0,0,0);
        }
    }

    @Override
    public void update() {
        if(!destroyMe) {
        animManager.playAnim(0);
        animManager.update();
        }
    }


}
