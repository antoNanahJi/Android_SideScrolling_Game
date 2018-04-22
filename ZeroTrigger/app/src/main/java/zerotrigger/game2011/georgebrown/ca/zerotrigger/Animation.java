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
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Animation {
    private Bitmap[] frames;
    private int frameIndex;

    private boolean isPlaying =false;
    public boolean isPlaying()
    {
        return isPlaying;
    }
    public void play()
    {
        isPlaying=true;
        frameIndex = 0;
        lastFrame = System.currentTimeMillis();
    }
    public void stop()
    {
        isPlaying = false;

    }
    private float frameTime;
    private long lastFrame;

    public Animation(Bitmap[] frames,float animTime)
    {
        this.frames = frames;
        frameIndex = 0;
        frameTime = animTime/frames.length;
        lastFrame = System.currentTimeMillis();
    }
    public void draw(Canvas canvas, Rect destination)
    {
        if(!isPlaying)
            return;
        canvas.drawBitmap(frames[frameIndex],null,destination, new Paint());
    }
    public void update()
    {
        if(!isPlaying)
            return;
        if(System.currentTimeMillis()-lastFrame > frameTime *1000)
        {
            frameIndex++;
            frameIndex=frameIndex>= frames.length ? 0: frameIndex;
            lastFrame = System.currentTimeMillis();

        }
    }
}
