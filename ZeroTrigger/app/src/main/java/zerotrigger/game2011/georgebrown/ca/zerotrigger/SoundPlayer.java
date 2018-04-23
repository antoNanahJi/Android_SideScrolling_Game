package zerotrigger.game2011.georgebrown.ca.zerotrigger;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundPlayer {
    private static SoundPool soundPool;
    private static int hitSound;
    private static int coinSound;
    private static int wolfSound;
    private static int gameOverSound;
    private static int arrowSound;
    private static int gameSound;


    public SoundPlayer(Context context)
    {
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC,0);
        hitSound = soundPool.load(context,R.raw.zero_trigger_bonk,1);
        coinSound = soundPool.load(context,R.raw.zero_trigger_coin,1);
        wolfSound = soundPool.load(context,R.raw.zero_trigger_wolf_death,1);
        gameOverSound = soundPool.load(context,R.raw.zero_trigger_gameover,1);
        arrowSound = soundPool.load(context,R.raw.zero_trigger_arrow,1);
        gameSound = soundPool.load(context,R.raw.zero_trigger_music,1);
    }
    public void playHitSound()
    {
        soundPool.play(hitSound,1.0f,1.0f,1,0,1.0f);
    }
    public void playCoinSound()
    {
        soundPool.play(coinSound,1.0f,1.0f,1,0,1.0f);
    }
    public void playWolfSound()
    {
        soundPool.play(wolfSound,1.0f,1.0f,1,0,1.0f);
    }
    public void playGameOverSound()
    {
        soundPool.play(gameOverSound,1.0f,1.0f,1,0,1.0f);
    }
    public void playArrowSound()
    {
        soundPool.play(arrowSound,1.0f,1.0f,1,0,1.0f);
    }
    public void playGameSound()
    {
        soundPool.play(gameSound,1.0f,1.0f,1,0,1.0f);
    }
}
