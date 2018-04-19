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
    private int numCol=12;
    private int startPosX=0;
    private int startPosY=Constants.SCREEN_HEIGHT-800;
    int xClip;
    int startY;
    int endY;
    int SIZE=200;
    int obstacleX[]=new int[numRow*numCol];
    int obstacleY[]=new int[numRow*numCol];
    int scrollingSpeed;
    //Bitmap width and height
    int bWidth;
    int bHeight;
    long startTime;

    boolean reversedFirst;




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
                obstacleX[j]=(j*SIZE)+startPosX;
                obstacleY[j]=(j*SIZE)+startPosY;

            }
        }

    }

    @Override
    public void update() {
       incrementX();
    }
    public void MapCollision()
    {

        for (int r =0; r < numRow ; r++) {
            for (int c = 0; c < numCol; c++) {
                if (aRock) {
                    //Get the distance from the center of the player to the center of a rock
                    int vectorX = (player.getSPX() +SIZE/2 ) - (obstacleX[c] + (SIZE / 2));
                    int vectorY = (player.getSPY() + (player.getEPY() / 2)) - (obstacleY[c] + (SIZE / 2));

                    //var vectorX = (player.x + (player.width/2) - 10) - (map[r][c].x + (SIZE/2));
                    //var vectorY = (player.y + (player.height/2)) - (map[r][c].y + (SIZE/2));

                    int boxWidth = ((player.getEPX() / 2) - 10) + SIZE / 2;
                    if(Math.abs(vectorX) < boxWidth && Math.abs(vectorY) < SIZE) {
                        int cX = boxWidth - Math.abs(vectorX);
                        int cY = SIZE - Math.abs(vectorY);
                        if( cX >= cY)
                        {
                            //We are on ground
                            if ( vectorY <= 0 && ((((player.getSPX() + SIZE - 30) >= (obstacleX[c] )) || ( (player.getSPX() + 30) >= (obstacleX[c] ) )) && ((player.getSPX() + 30) <= (obstacleX[c] + SIZE))))
                            {



                                player.setSPY(obstacleY[c] - player.getSPY());

                            }

                        }else {
                            if (vectorX >= 0 && vectorX <= 170) {

                                //player.img = images[1];
                                player.setSPX(obstacleX[c] + SIZE - 20);
                                System.out.println("Left");
                                player.setSpeed(0);

                            }
                            //Right side collision
                            else if (((player.getSPX() + SIZE - 20) >= obstacleX[c]) && (player.getEPX() - 20) <= (obstacleX[c] + SIZE / 2)) {
                                System.out.println("Right");
                                //player.img = images[0];
                                player.setSPX(obstacleX[c] - (player.getEPX() - 20));
                                player.setSpeed(0);
                            }
                        }
                    }
                }
            }
        }
    }
}
