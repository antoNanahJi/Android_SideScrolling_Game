package zerotrigger.game2011.georgebrown.ca.zerotrigger;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.widget.ImageView;

public class Obstacles implements GameObject
{
    RectPlayer player;

    boolean aRock=false;
    private int numRow=4;
    private int numCol=30;
    private int startPosX=0;
    private int startPosY=Constants.SCREEN_HEIGHT-800;
    int xClip;
    int startY;
    int endY;
    int SIZE=200;
    int obstacleX[][]=new int[numRow][numCol];
    int obstacleY[][]=new int[numRow][numCol];
    int scrollingSpeed;
    //Bitmap width and height
    int bWidth;
    int bHeight;
    long startTime;
    public boolean end=false;

    boolean reversedFirst;
    private int mapLayout[][]=
            {

                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,5,0,5,0,0,6,7,8,0,5,0,0,5,0,0,6,7,8,0,0,0,5,0,0,5,0,0,0},
                    {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3}
            };
    //Platform images....
    int platformImg[]={R.drawable.empty
            ,R.drawable.top_left
            ,R.drawable.top_middle
            ,R.drawable.top_right
            ,R.drawable.middle
            ,R.drawable.single_platform
            ,R.drawable.long_platform_left
            ,R.drawable.long_platform_middle
            ,R.drawable.long_platform_right
            ,R.drawable.top_middle_2
    };
    private Bitmap bitmapArr[][]=new Bitmap[numRow][numCol];


    public void incrementX()
    {
        startPosX-=scrollingSpeed;
        bWidth-=scrollingSpeed;
        if (bWidth <= Constants.SCREEN_WIDTH) {
            end=true;
            scrollingSpeed=0;
        }
    }

    public Obstacles( int playerGap)
    {
        player = new RectPlayer(new Rect(100,100,300,300), Color.GREEN);
         for(int i=0;i<numRow;i++) {
            for (int j = 0; j < numCol; j++) {
                 switch (mapLayout[i][j])
                    {
                        case 0:
                            mapLayout[i][j]=platformImg[0];
                            break;
                        case 1:
                            mapLayout[i][j]=platformImg[1];
                            aRock=true;
                            break;
                        case 2:
                             mapLayout[i][j]=platformImg[2];
                             aRock=true;
                             break;
                        case 3:
                            mapLayout[i][j]=platformImg[3];
                            aRock=true;
                            break;
                        case 4:
                            mapLayout[i][j]=platformImg[4];
                            aRock=true;
                            break;
                        case 5:
                            mapLayout[i][j]=platformImg[5];
                            aRock=true;
                            break;
                        case 6:
                            mapLayout[i][j]=platformImg[6];
                            aRock=true;
                            break;
                        case 7:
                            mapLayout[i][j]=platformImg[7];
                            aRock=true;
                            break;
                        case 8:
                            mapLayout[i][j]=platformImg[8];
                            aRock=true;
                            break;
                        case 9:
                            mapLayout[i][j]=platformImg[9];
                            aRock=true;
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


        scrollingSpeed =7;

        bWidth=SIZE*numCol;
        bHeight=SIZE*numRow;

        reversedFirst=false;
        // l , t , r , b
      //  rectangle = new Rect(0, startY, endY, startY+ rectHeight);
      //  rectangle2 = new Rect(endY + 50, startY,Constants.SCREEN_WIDTH,startY+rectHeight);

    }


    @Override
    public void draw(Canvas canvas) {

        Paint paint= new Paint();
        paint.setColor(Color.GREEN);

        for(int i=0;i<numRow;i++) {
            for (int j = 0; j < numCol; j++) {
                canvas.drawBitmap(bitmapArr[i][j],(j*SIZE)+startPosX,(i*SIZE)+startPosY,null);
            }
        }


    }

    @Override
    public void update() {
       incrementX();
    }
    public boolean MapCollision(RectPlayer player)
    {
        /*
        for(int i=0;i<numRow;i++) {
            for (int j = 0; j < numCol; j++) {
                if( Rect.intersects(rect[i][j],player.getRectangle())==true )
                    return true;
            }
        }

        */
        return false;
    }
}
