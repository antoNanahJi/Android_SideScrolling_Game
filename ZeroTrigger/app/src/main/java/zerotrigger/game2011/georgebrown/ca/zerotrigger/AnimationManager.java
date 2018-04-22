package zerotrigger.game2011.georgebrown.ca.zerotrigger;

import android.graphics.Canvas;
import android.graphics.Rect;
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
public class AnimationManager {

    private Animation[] animations;
    private int animationIndex = 0;

    public AnimationManager(Animation[] animations) {
        this.animations = animations;
    }

    public void playAnim(int index) {
        for(int i = 0; i < animations.length; i++) {
            if(i == index) {
                if(!animations[index].isPlaying())
                    animations[i].play();
            } else
                animations[i].stop();
        }
        animationIndex = index;
    }

    public void draw(Canvas canvas, Rect rect) {
        if(animations[animationIndex].isPlaying())
            animations[animationIndex].draw(canvas, rect);
    }

    public void update() {
        if(animations[animationIndex].isPlaying())
            animations[animationIndex].update();
    }
}
