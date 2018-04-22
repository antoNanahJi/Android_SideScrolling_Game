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
    private int speed=15;

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


    public void decrementPlayerX() //moves player left
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
    public void incrementPlayerX() //moves player right
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

    public RectPlayer(Rect rectangle, int color) //animations
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
