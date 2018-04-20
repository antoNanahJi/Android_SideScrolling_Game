package zerotrigger.game2011.georgebrown.ca.zerotrigger;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class RectPlayer implements GameObject{
    private Rect rectangle;
    private int color;
    private Animation idle;
    private Animation idleL;
    private Animation walkLeft;
    private Animation walkRight;
    private Animation attackRight;
    private Animation attackLeft;
    public int player_sPosX=50;
    public int player_sPosY=700;
    public int player_ePosX=250;
    public int player_ePosY=900;
    private int state=0;
    private int speed=10;

    private AnimationManager animManager;
    public void setSpeed(int s)
    {
        speed=s;
    }
    public int getSPX()
    {
        return player_sPosX;
    }
    public int getSPY()
    {
        return player_sPosY;
    }
    public int getEPX()
    {
        return player_ePosX;
    }
    public int getEPY()
    {
        return player_ePosY;
    }
    public void setSPX(int n)
    {
        player_sPosX=n;
    }
    public void setSPY(int n)
    {
        player_sPosY=n;
    }
    public void decrementPlayerX()
    {
        player_sPosX-=speed;
        player_ePosX-=speed;
        if ( player_sPosX <= 0) {
            speed=0;
        }
        if ( player_sPosX >= Constants.SCREEN_WIDTH-200) {
            speed=10;
        }
    }
    public void incrementPlayerX()
    {
        player_sPosX+=speed;
        player_ePosX+=speed;
        if ( player_sPosX >= Constants.SCREEN_WIDTH-200) {
            speed=0;
        }
        if ( player_sPosX <= 0) {
            speed=10;
        }
    }
    public Rect getRectangle()
    {
        return rectangle;
    }
    public void setPlayerPos()
    {
        rectangle.set(player_sPosX, player_sPosY , player_ePosX,  player_ePosY);
    }
    public void setState(int s)
    {
        state = s;
        animManager.playAnim(state);
    }
    public int getState()
    {
        return state;
    }
    public RectPlayer(Rect rectangle, int color)
    {
        this.rectangle = rectangle;
        this.color = color;
        BitmapFactory bf= new BitmapFactory();

        Bitmap idleImg =bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.character_idle_r);
        Bitmap walk1=bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.character_walk_1);
        Bitmap walk2=bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.character_walk_2);
        Bitmap idleImgL =bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.character_idle_l);
        Bitmap attackImgR =bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.character_attack_r);
        Bitmap attackImgL =bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.character_attack_l);

        idle = new Animation(new Bitmap[]{idleImg}, 2);
        idleL = new Animation(new Bitmap[]{idleImgL}, 2);
        attackRight = new Animation(new Bitmap[]{attackImgR}, 2);
        attackLeft = new Animation(new Bitmap[]{attackImgL}, 2);
        walkRight = new Animation(new Bitmap[]{walk1, walk2}, 0.5f);

        Matrix m = new Matrix();
        m.preScale(-1, 1);
        walk1 = Bitmap.createBitmap(walk1, 0, 0, walk1.getWidth(), walk1.getHeight(), m, false);
        walk2 = Bitmap.createBitmap(walk2, 0, 0, walk2.getWidth(), walk2.getHeight(), m, false);

        walkLeft = new Animation(new Bitmap[]{walk1, walk2}, 0.5f);

        animManager = new AnimationManager(new Animation[]{idle, walkRight, walkLeft,idleL,attackRight,attackLeft});
        setState(0);
    }
    @Override
    public void draw(Canvas canvas) {

        animManager.draw(canvas,rectangle);

    }


    @Override
    public void update() {
        animManager.update();
    }

    public void update(int s)
    {
        animManager.update();
    }

}
