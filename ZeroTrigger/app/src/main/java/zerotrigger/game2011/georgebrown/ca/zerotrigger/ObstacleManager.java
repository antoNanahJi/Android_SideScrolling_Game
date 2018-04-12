package zerotrigger.game2011.georgebrown.ca.zerotrigger;

import android.graphics.Canvas;

import java.util.ArrayList;

public class ObstacleManager
{
    //higher index = lower on screen = higher y value
    private ArrayList<Obstacles> obstacles;
    private int playerGap;
    private int obstacleGap;
    private int obstacleHeight;
    private int color;

    private long startTime;

public ObstacleManager(int playerGap,int obstacleGap,int obstacleHeight,int color)
{
 this.playerGap = playerGap;
 this.obstacleGap= obstacleGap;
 this.obstacleHeight = obstacleHeight;
 this.color = color;
 obstacles = new ArrayList<>();

 startTime = System.currentTimeMillis();

 populateObstacles();
}
private void populateObstacles()
{
    int currY = -5* Constants.SCREEN_HEIGHT/4;
   while(currY<0)
    {
        //generating gap
        int xStart = (int) (Math.random()*(Constants.SCREEN_WIDTH-playerGap));
        obstacles.add(new Obstacles(obstacleHeight,color,xStart,currY,playerGap));
        currY +=obstacleHeight+obstacleGap;
    }
}
 public void update()
 {
     int elapsedTime = (int) (System.currentTimeMillis() - startTime);
     startTime = System.currentTimeMillis();
     float speed = Constants.SCREEN_HEIGHT/10000.0f;
     for(Obstacles ob : obstacles)
     {
        ob.incrementY(speed *elapsedTime);
     }
     if(obstacles.get(obstacles.size()-1).getRectangle().top>= Constants.SCREEN_HEIGHT)
     {
         int xStart = (int) (Math.random()*(Constants.SCREEN_WIDTH-playerGap));
         obstacles.add(0,new Obstacles(obstacleHeight,color,xStart,obstacles.get(0).getRectangle().top-obstacleHeight-obstacleGap,playerGap));
     }
 }

 public void draw(Canvas canvas)
 {
    for(Obstacles ob : obstacles)
    {
        ob.draw(canvas);
    }
 }

}
