package ru.startandroid.develop.circule;

import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import yuku.ambilwarna.AmbilWarnaDialog;


public class MainActivity extends AppCompatActivity {


    public DrawView myView;
    int mDefaultColor;
    public Handler mHandler ;
    Button mButton;
    public SurfaceHolder holder;
    ConstraintLayout mLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(getActivityLayout());
        myView=(DrawView) findViewById(R.id.drawView);


        mDefaultColor = ContextCompat.getColor(MainActivity.this, R.color.colorPrimary);mLayout = (ConstraintLayout) findViewById(R.id.layout);
        mDefaultColor = ContextCompat.getColor(MainActivity.this, R.color.colorPrimary);
        mButton = (Button) findViewById(R.id.button3);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker();
            }
        });



    }

    protected int getActivityLayout(){
        return R.layout.activity_main;
    }
    public void PointClick (View view)
    {
        // выводим сообщение


    }
    public void openColorPicker() {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(this, mDefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                mDefaultColor = color;
                myView.setColorInThread(mDefaultColor);
            }
        });
        colorPicker.show();
    }


    public void StartAnimation_draw(View view){
        int max_point_num=800;
        int min_point_num=200;

//        for (int i =min_point_num;i<max_point_num;i++){
//            try {
//                //set time in mili
//                Thread.sleep(100);
//
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            myView.set_num_point_in_tread(i);
//        }

        for(double j=100;j<301;j=j+0.04){
            try {
                //set time in mili
                Thread.sleep(37);

            }catch (Exception e){
                e.printStackTrace();
            }
            myView.set_divider_in_tread((float)j);
        }
    }


}


