package zerotrigger.game2011.georgebrown.ca.zerotrigger;

import android.graphics.Canvas;
import android.widget.ImageView;

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

        obstacles.add(new Obstacles(obstacleHeight,Constants.SCREEN_WIDTH,0,playerGap));
        obstacles.add(new Obstacles(obstacleHeight,Constants.SCREEN_WIDTH+100,0,playerGap));

}
 public void update()
 {
     int elapsedTime = (int) (System.currentTimeMillis() - startTime);
     startTime = System.currentTimeMillis();
     float speed = Constants.SCREEN_HEIGHT/10000.0f;
     for(Obstacles ob : obstacles)
     {
       // ob.incrementY(speed *elapsedTime);
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
