package zerotrigger.game2011.georgebrown.ca.zerotrigger;

import android.graphics.Canvas;
import android.widget.ImageView;

import java.util.ArrayList;

public class ObstacleManager
{
    //higher index = lower on screen = higher y value
    private ArrayList<Obstacles> obstacles;
    private int playerGap;
    private Obstacles obs;

public ObstacleManager(int playerGap)
{
 this.playerGap=playerGap;
 obstacles = new ArrayList<>();
 obs=new Obstacles(playerGap);
 populateObstacles();
}
private void populateObstacles()
{
    obstacles.add(new Obstacles(playerGap));
}
 public void update()
 {
     for(Obstacles ob : obstacles)
     {
       ob.incrementX();
     }
 }

 public void draw(Canvas canvas)
 {
    for(Obstacles ob : obstacles)
    {
        ob.draw(canvas);
    }//

 }
}
