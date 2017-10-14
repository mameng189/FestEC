package com.matt.latter.util.timer;

import java.util.TimerTask;

/**
 * Created by Administrator on 2017/8/7.
 */

public class BaseTimerTask extends TimerTask {
    private ITimerListener listener = null;

    public BaseTimerTask(ITimerListener listener) {
        this.listener = listener;
    }
    @Override
    public void run() {
        if (listener!=null){
            listener.onTimer();
        }
    }
}
