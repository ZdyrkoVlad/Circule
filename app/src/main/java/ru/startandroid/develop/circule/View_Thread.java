package ru.startandroid.develop.circule;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.SurfaceHolder;

public class View_Thread extends Thread{

    private boolean running = false;
    private SurfaceHolder surfaceHolder;
    public Handler mHandler;
    private long prevTime;
    private Paint mPaint = new Paint();
    private  int point_number=400;
    private float divider=2;
    public int mColor;
    public View_Thread(SurfaceHolder surfaceHolder){
        this.surfaceHolder = surfaceHolder;
        mColor=Color.BLUE;

    }

    public void setRunning(boolean run) {
        running = run;

    }

    public void set_point(int point_num){
        point_number=point_num;

    }
    public void set_divider(float divider_num){
        divider=divider_num;
    }


    @Override
    public void run() {
        Canvas canvas=null;

        while (running) {

            try {
                canvas = surfaceHolder.lockCanvas(null);
                if (canvas == null)
                    continue;

                canvas.drawColor(Color.WHITE);
                int point=point_number;
                int radius=540;
                mPaint.setColor(mColor);
//                mPaint.setColor();


                int [][] array_cordinate=calculet_circule(point,radius);
//	        System.out.println(Arrays.deepToString(array_cordinate));
                for(int i=0;i<=point-1;i++){
                    if (i==0){
                        canvas.drawLine(radius,0,array_cordinate[i][0],array_cordinate[i][1],mPaint);
                    }else{
                        canvas.drawLine(array_cordinate[i-1][0],array_cordinate[i-1][1],array_cordinate[i][0],array_cordinate[i][1],mPaint);
                        if(i==point){
//	        			g.drawLine(array_cordinate[i-1][0],array_cordinate[i-1][1],array_cordinate[0][0],array_cordinate[0][1]);
//	        			System.out.println("x= "+array_cordinate[i][0]+"y= "+array_cordinate[i][1]);
                        }
                    }

                }

                for (int j=0;j<10;j++){
                    float n= divider;

                    for (int i=0;i<=point-1;i++){
                        float a=i*n;
                        if (a>point-1){
                            while (a>point-1){
                                a=a-point;
                            }
                        }
                        canvas.drawLine(array_cordinate[i][0],array_cordinate[i][1],array_cordinate[(int)a][0],array_cordinate[(int)a][1],mPaint);

                    }

                }

            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
    public int[][] calculet_circule(float num_point, int radius){
        float gra=360/(num_point-1);
        float gra_step=gra;
        //System.out.print(gra);
        int [][] a=new int[(int)num_point][2];
        //System.out.println(Arrays.deepToString(a));
        int point_steap=(int)num_point/4;
        for (int i=0;i<=num_point/4-1;i++){

            //System.out.println("GRAD "+gra);
            float cordinate_x=(float) (radius*(Math.sin( Math.toRadians(gra))));
            //System.out.println("Cordinate x "+cordinate_x);
            float cordinate_y=(float)Math.sqrt(radius*radius-(cordinate_x*cordinate_x));
//    			System.out.println("Cordinate y "+cordinate_y);
            a[i][0]=(int) cordinate_x+radius;
            a[i][1]=(int) radius-(int)cordinate_y;

            a[i+point_steap][0]=(int)cordinate_y+radius;
            a[i+point_steap][1]=(int)cordinate_x+radius;

            a[i+point_steap*2][0]=(int)radius- (int)cordinate_x;
            a[i+point_steap*2][1]=(int) cordinate_y+radius;
//



//
            a[i+point_steap*3][0]=(int)radius-(int)cordinate_y;
            a[i+point_steap*3][1]=(int)radius-(int)cordinate_x;


            gra=gra+gra_step;
        }
//	    	System.out.println(Arrays.deepToString(a));
        return a;
    }
}
