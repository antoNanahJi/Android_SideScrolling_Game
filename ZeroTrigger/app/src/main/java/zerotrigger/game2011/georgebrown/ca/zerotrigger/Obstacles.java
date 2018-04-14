package zerotrigger.game2011.georgebrown.ca.zerotrigger;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.widget.ImageView;

public class Obstacles implements GameObject
{
    private Rect rectangle;
    private Rect rectangle2;
    private int color;
    private int numRow=4;
    private int numCol=3;
    private int startPosX=0;
    private int startPosY=Constants.SCREEN_HEIGHT-800;
    private int mapLayout[][]=
            {
                    {0,1,2},
                    {0,1,2},
                    {0,1,2},
                    {0,1,2}
            };
    //Platform images....
    int platformImg[]={R.drawable.left_floor,R.drawable.middle_floor,R.drawable.right_floor,R.drawable.character};
    private Bitmap bitmapArr[][]=new Bitmap[numRow][numCol];

    public Rect getRectangle()
    {
        return rectangle;
    }
    public void incrementY(float y)
    {
        rectangle.top+=y;
        rectangle.bottom+=y;
        rectangle2.top+=y;
        rectangle2.bottom+=y;
    }

    public Obstacles(int rectHeight, int startX, int startY, int playerGap)
    {
        //this.color = color;
        for(int i=0;i<numRow;i++) {
            for (int j = 0; j < numCol; j++) {
                 switch (j)
                    {
                        case 0:
                            mapLayout[i][j]=platformImg[0];
                            break;
                        case 1:
                            mapLayout[i][j]=platformImg[1];
                            break;
                        case 2:
                            mapLayout[i][j]=platformImg[3];
                            break;
                    }

            }
        }
        BitmapFactory.Options options= new BitmapFactory.Options();
        options.inScaled=false;
        for(int i=0;i<numRow;i++) {
            for (int j = 0; j < numCol; j++) {

                //bitmapArr[i][j]=BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), mapLayout[i][j]);
                bitmapArr[i][j]=BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(),mapLayout[i][j],options);
            }
        }
        // l , t , r , b
        rectangle = new Rect(0, startY, startX, startY+ rectHeight);
        rectangle2 = new Rect(startX + 50, startY,Constants.SCREEN_WIDTH,startY+rectHeight);
    }

    public boolean playerCollide(RectPlayer player)
    {
        if(rectangle.contains(player.getRectangle().left,player.getRectangle().top)||
                rectangle.contains(player.getRectangle().right,player.getRectangle().top)||
                rectangle.contains(player.getRectangle().left,player.getRectangle().bottom)||
                rectangle.contains(player.getRectangle().right,player.getRectangle().bottom))
        {
            return true;
        }
        return false;
    }



    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle,paint);
        canvas.drawRect(rectangle2,paint);

        for(int i=0;i<1;i++) {

            for (int j = 0; j < numCol; j++) {

                canvas.drawBitmap(bitmapArr[i][j],(j*400)+startPosX,(i*400)+startPosY,null);

            }

            }
    }

    @Override
    public void update() {

    }
}
