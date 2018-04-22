package zerotrigger.game2011.georgebrown.ca.zerotrigger;

/**
 * Created by andre on 4/20/2018.
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
    private Bitmap wolf_img;
    private int color;

    private int wolf_PosX = 100;
    private int wolf_PosY = 730;
    private int wolf_spawnX = 250;
    private int wolf_spawnY = 900;

    private int state=0;
    private int speed = 5;

    private Animation wolfidle_anim;
    private Animation wolfattack_anim;
    private Animation wolfrecover_anim;

    private AnimationManager animManager;



    /////////////////////////////////////////
    // Wolf constructor
    /////////////////////////////////////////

    public Wolf(int xPos, int yPos)
    {
        wolf_PosX = xPos;
        wolf_PosY = yPos;


        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        BitmapFactory bf = new BitmapFactory();

        player = new RectPlayer(new Rect(125, 100, 300, 300), Color.GREEN);
        wolf = new Rect(wolf_PosX, wolf_PosY, wolf_PosX+100, wolf_PosY+100);
        /////////////////////////////////////////
        // Wolf idle images
        /////////////////////////////////////////

        /*Bitmap[] wolf_idle = new Bitmap[]{bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.Wolf_idle1),
                bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.Wolf_idle2),
                bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.Wolf_idle3)};*/

        Bitmap wolf_idle1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.wolf_idle1);
        Bitmap wolf_idle2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.wolf_idle2);
        Bitmap wolf_idle3 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.wolf_idle3);

        /////////////////////////////////////////
        // Wolf attack images
        /////////////////////////////////////////

        /*Bitmap[] wolf_atk = new Bitmap[]{bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.Wolf_attack1),
                bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.Wolf_attack2),
                bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.Wolf_attack3),
                bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.Wolf_attack4),
                bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.Wolf_attack5),
                bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.Wolf_attack6)};*/

        Bitmap wolf_atk1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.wolf_attack1);
        Bitmap wolf_atk2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.wolf_attack2);
        Bitmap wolf_atk3 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.wolf_attack3);
        Bitmap wolf_atk4 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.wolf_attack4);
        Bitmap wolf_atk5 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.wolf_attack5);
        Bitmap wolf_atk6 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.wolf_attack6);

        /////////////////////////////////////////
        // Wolf recover images
        /////////////////////////////////////////

        /*Bitmap[] wolfRec = new Bitmap[]{bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.Wolf_Recover1),
                bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.Wolf_Recover2),
                bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.Wolf_Recover3)};*/

        Bitmap wolfRec1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.wolf_recover1);
        Bitmap wolfRec2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.wolf_recover2);
        Bitmap wolfRec3 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.wolf_recover3);

        /////////////////////////////////////////
        // Creating animation object
        /////////////////////////////////////////
        wolfidle_anim = new Animation(new Bitmap[]{wolf_idle1, wolf_idle2, wolf_idle3}, 2);
        wolfattack_anim = new Animation(new Bitmap[]{wolf_atk1, wolf_atk2, wolf_atk3, wolf_atk4, wolf_atk5, wolf_atk6}, 2);
        wolfrecover_anim = new Animation(new Bitmap[]{wolfRec1, wolfRec2, wolfRec3}, 2);

        Matrix matrix = new Matrix();
        matrix.preScale(-1, 1);

        wolf_idle1 = Bitmap.createBitmap(wolf_idle1, 0, 0, wolf_idle1.getWidth(), wolf_idle1.getHeight(), matrix, false);
        wolf_idle2 = Bitmap.createBitmap(wolf_idle2, 0, 0, wolf_idle2.getWidth(), wolf_idle2.getHeight(), matrix, false);
        wolf_idle3 = Bitmap.createBitmap(wolf_idle3, 0, 0, wolf_idle3.getWidth(), wolf_idle3.getHeight(), matrix, false);

        animManager = new AnimationManager(new Animation[]{wolfidle_anim, wolfattack_anim, wolfrecover_anim});
        animManager.playAnim(0);
    }

    public boolean CollisionWithWolf(RectPlayer player)
    {
        if(Rect.intersects(wolf, player.getRectangle()) == true)
        {
            return true;
        }
        return false;
    }

    public void decrementX()
    {
        wolf_PosX -= speed;
        wolf.set(wolf_PosX, wolf_PosY, wolf_PosX+100, wolf_PosY+100);
    }

    @Override
    public void draw(Canvas canvas) {
        animManager.draw(canvas, wolf);
    }

    @Override
    public void update() {
        animManager.update();
    }


}
