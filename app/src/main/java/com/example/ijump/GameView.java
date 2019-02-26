package com.example.ijump;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import android.os.Handler;

public class GameView extends View {
    Handler handler;
    Runnable runnable;
    final int UPDATE_MILLIS=100;
    Bitmap background;
    Display display;
    Point point;
    int dWidth,dHeight; //device width and height
    Rect rect;
    int manX,manY;
    Bitmap [] man;
    int manFrame = 0;
    int velocity=0,gravity=3;
    public GameView(Context context) {

        super(context);
//    init();
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
        background= BitmapFactory.decodeResource(getResources(),R.drawable.background);
        display=((Activity)getContext()).getWindowManager().getDefaultDisplay();
        point=new Point();
        display.getSize(point);
        dHeight=point.y;
        dWidth=point.x;
        rect = new Rect(0,0,dWidth,dHeight);
        man = new Bitmap[2];
        man[0]=BitmapFactory.decodeResource(getResources(),R.drawable.as);
        man[1]=BitmapFactory.decodeResource(getResources(),R.drawable.asdd);
        manX= dWidth/2-man[0].getWidth()/2;
        manY=dHeight/2-man[0].getHeight()/2;


    }
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawBitmap(background,null,rect,null);
        if(manFrame==0){
            manFrame=1;
        }
        else{
            manFrame=0;
        }

        if(manY<dHeight-man[0].getHeight()-300 || velocity < 0){
            velocity+=gravity;
            manY+=velocity;

        }
        canvas.drawBitmap(man[manFrame],manX,manY,null);
        handler.postDelayed(runnable,UPDATE_MILLIS);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int action = event.getAction();
        if(action==MotionEvent.ACTION_DOWN){
            velocity=-30;
        }
        return true;
    }

}
