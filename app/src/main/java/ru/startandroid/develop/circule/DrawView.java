package ru.startandroid.develop.circule;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawView extends SurfaceView implements SurfaceHolder.Callback {
    private View_Thread myThread;




    public DrawView(Context context, AttributeSet attr){
        super(context,attr);
        getHolder().addCallback(this);
        System.out.println("DRAW VIEW CONSTRUCTUR");
        myThread =new View_Thread(getHolder());


    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {


    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {


        myThread.setRunning(true);
        myThread.start();



    }
    public void set_num_point_in_tread(int point){
        myThread.set_point(point);
    }
    public void set_divider_in_tread(float divider){
        myThread.set_divider(divider);
    }
    public void setColorInThread(int color){
        myThread.mColor=color;
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        // завершаем работу потока
        myThread.setRunning(false);
        while (retry) {
            try {
                myThread.join();
                retry = false;
            } catch (InterruptedException e) {
                // если не получилось, то будем пытаться еще и еще
            }
    }
}
}
