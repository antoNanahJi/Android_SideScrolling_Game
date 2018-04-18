package zerotrigger.game2011.georgebrown.ca.zerotrigger;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.widget.ImageView;

public class Obstacles implements GameObject
{
    int xClip;
    int startY;
    int endY;
    int SIZE=200;
    int scrollingSpeed;
    //Bitmap width and height
    int bWidth;
    int bHeight;
    long startTime;

    boolean reversedFirst;



    private int numRow=4;
    private int numCol=12;
    private int startPosX=0;
    private int startPosY=Constants.SCREEN_HEIGHT-800;
    private int mapLayout[][]=
            {
                    {0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,5,0,5,0,0,6,7,8,0,9},
                    {1,2,2,2,2,2,2,2,2,2,2,4}
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
            //startPosX = 0;
            scrollingSpeed=0;
            //reversedFirst = !reversedFirst;
        }
            if (startPosX <= 0) {
            //startPosX = bWidth;
           // reversedFirst = !reversedFirst;

        }

    }
    public Obstacles( int playerGap)
    {

        for(int i=0;i<numRow;i++) {
            for (int j = 0; j < numCol; j++) {
                 switch (mapLayout[i][j])
                    {
                        case 0:
                            mapLayout[i][j]=platformImg[0];
                            break;
                        case 1:
                            mapLayout[i][j]=platformImg[1];
                            break;
                        case 2:
                             mapLayout[i][j]=platformImg[2];
                             break;
                        case 3:
                            mapLayout[i][j]=platformImg[3];
                            break;
                        case 4:
                            mapLayout[i][j]=platformImg[4];
                            break;
                        case 5:
                            mapLayout[i][j]=platformImg[5];
                            break;
                        case 6:
                            mapLayout[i][j]=platformImg[6];
                            break;
                        case 7:
                            mapLayout[i][j]=platformImg[7];
                            break;
                        case 8:
                            mapLayout[i][j]=platformImg[8];
                            break;
                        case 9:
                            mapLayout[i][j]=platformImg[9];
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


        scrollingSpeed =5;

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
        for(int i=0;i<numRow;i++) {
            for (int j = 0; j < numCol; j++) {
                canvas.drawBitmap(bitmapArr[i][j],(j*SIZE)+startPosX,(i*SIZE)+startPosY,null);
            }
        }
        System.out.println(startPosX+" "+bWidth);
    }

    @Override
    public void update() {
       // incrementX();
    }
}
