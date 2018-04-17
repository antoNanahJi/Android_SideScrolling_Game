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
    private Animation walkLeft;
    private Animation walkRight;

    private AnimationManager animManager;

    public Rect getRectangle()
    {
        return rectangle;
    }


    public RectPlayer(Rect rectangle, int color)
    {
     this.rectangle = rectangle;
     this.color = color;
        BitmapFactory bf= new BitmapFactory();

        Bitmap idleImg =bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.character);
        Bitmap walk1=bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.character);
        Bitmap walk2=bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.character);

        idle = new Animation(new Bitmap[]{idleImg}, 2);
        walkRight = new Animation(new Bitmap[]{walk1, walk2}, 0.5f);

        Matrix m = new Matrix();
        m.preScale(-1, 1);
        walk1 = Bitmap.createBitmap(walk1, 0, 0, walk1.getWidth(), walk1.getHeight(), m, false);
        walk2 = Bitmap.createBitmap(walk2, 0, 0, walk2.getWidth(), walk2.getHeight(), m, false);

        walkLeft = new Animation(new Bitmap[]{walk1, walk2}, 0.5f);

        animManager = new AnimationManager(new Animation[]{idle, walkRight, walkLeft});
    }
    @Override
    public void draw(Canvas canvas) {
       animManager.draw(canvas,rectangle);
    }

    @Override
    public void update() {
        animManager.update();
    }
    public void update(Point point)
    {
        //l,t,r,b
       //rectangle.set(point.x-rectangle.width()/2,point.y-rectangle.height()/2,point.x+rectangle.width()/2,point.y+rectangle.height()/2);
        float oldLeft = rectangle.left;

        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2, point.x + rectangle.width()/2, point.y + rectangle.height()/2);

        int state = 0;
        if(rectangle.left - oldLeft > 5)
            state = 1;
        else if(rectangle.left - oldLeft < -5)
            state = 2;

        animManager.playAnim(state);
        animManager.update();
    }

}
