package zerotrigger.game2011.georgebrown.ca.zerotrigger;

import android.os.CountDownTimer;

/**
 * Created by andre on 4/21/2018.
 */

class MyCountdownTimer {

    long mCurrentMilisLeft;
    long mInterval;
    CountdownTimerWrapper mCountdownTimer;

    class CountdownTimerWrapper extends CountDownTimer {
        public CountdownTimerWrapper(long millisInFuture,long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {

        }

        @Override
        public void onTick(long millisUntilFinished) {
            mCurrentMilisLeft = millisUntilFinished;
        }

    }

    public MyCountdownTimer(long millisInFuture, long countDownInterval) {
        set(millisInFuture,countDownInterval);
    }

    public void pause(){
        mCountdownTimer.cancel();
    }

    public void resume(){
        mCountdownTimer = new CountdownTimerWrapper(mCurrentMilisLeft, mInterval);
        mCountdownTimer.start();
    }

    public void start(){
        mCountdownTimer.start();
    }

    public void set(long millisInFuture, long countDownInterval){
        mInterval = countDownInterval;
        mCurrentMilisLeft = millisInFuture;
        mCountdownTimer = new CountdownTimerWrapper(millisInFuture, countDownInterval);
    }

}
