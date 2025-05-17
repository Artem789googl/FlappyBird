package com.example.flappybird;

import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameThread extends Thread{

    SurfaceHolder surfaceHolder;
    //Surfacefolder object reference

    boolean isRunning;
    //Flag to detect whether the thread is running or not

    long startTime, loopTime;
    //Loop start time and loop duration

    long DELAY  = 33;

    public GameThread (SurfaceHolder surfaceHolder){
        this.surfaceHolder = surfaceHolder;
        isRunning = true;

    }

    @Override
    public void run() {
        //looping until the boolean is false
        while (isRunning) {
            startTime = SystemClock.uptimeMillis();
            Canvas canvas = surfaceHolder.lockCanvas(null);
            if (canvas != null) {
                synchronized (surfaceHolder){
                    AppConstants.getGameEngine().updateAndDrawableBackgroundImage(canvas);
                    AppConstants.getGameEngine().updateAndDrawBird(canvas);
                    AppConstants.getGameEngine().updateAndDrawTubes(canvas);
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            loopTime = SystemClock.uptimeMillis() - startTime;
            if (loopTime < DELAY){
                try{
                    Thread.sleep(DELAY - loopTime);
                }catch (InterruptedException e){
                    Log.e("Interrumped", " Interrumped while sleeping");

                }
            }
        }
    }

    public boolean isRunning(){
        return isRunning;
    }

    public void setRunning(boolean state){
        isRunning = state;
    }
}
