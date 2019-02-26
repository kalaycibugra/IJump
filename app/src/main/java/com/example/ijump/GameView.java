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

import java.util.Random;

public class GameView extends View {
    Handler handler;
    Runnable runnable;
    final int UPDATE_MILLIS=50;
    Bitmap background;
    Display display;
    Point point;
    int dWidth,dHeight; //device width and height
    Rect rect;
    int manX,manY;
    Bitmap [] man;
    int [] block_loc;
    Bitmap cloud1,cloud2;
    Bitmap block;
    int manFrame = 0;
    int velocity=0,gravity=7,block_vel=13;
    int cloud_vel=0;
    Random rand = new Random();
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
        block=BitmapFactory.decodeResource(getResources(),R.drawable.block);
        display=((Activity)getContext()).getWindowManager().getDefaultDisplay();
        point=new Point();
        display.getSize(point);
        dHeight=point.y;
        dWidth=point.x;
        rect = new Rect(0,0,dWidth,dHeight);
        man = new Bitmap[2];
        block_loc = new int[3];
        block_loc[0]=dWidth/2+100;
        block_loc[1]=dWidth/2+300;
        block_loc[2]=dWidth/2-100;
        cloud1=BitmapFactory.decodeResource(getResources(),R.drawable.cloud);
        cloud2=BitmapFactory.decodeResource(getResources(),R.drawable.cloud);
        man[0]=BitmapFactory.decodeResource(getResources(),R.drawable.as);
        man[1]=BitmapFactory.decodeResource(getResources(),R.drawable.asdd);
        manX= dWidth/2-man[0].getWidth()/2;
        manY=dHeight-man[0].getHeight()/2-300;
        cloud_vel=dWidth-1;


    }
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        for(int i=0;i<3;i++){
            block_loc[i]=block_loc[i]-block_vel;
            if(block_loc[i]<=0){
                int num=rand.nextInt(100);
                block_loc[i]=dWidth-num;
            }
        }
        canvas.drawBitmap(background,null,rect,null);
        canvas.drawBitmap(block,block_loc[0],dHeight-180,null);
        canvas.drawBitmap(block,block_loc[1],dHeight-180,null);
        canvas.drawBitmap(block,block_loc[2],dHeight-180,null);
        canvas.drawBitmap(cloud1,cloud_vel,200,null);
        canvas.drawBitmap(cloud1,cloud_vel-400,100,null);
        if(manFrame==0){
            manFrame=1;
        }
        else{
            manFrame=0;
        }

        if(manY<=dHeight-man[0].getHeight()-150 || velocity < 0){
            velocity+=gravity;
            manY+=velocity;

        }

        if(cloud_vel>0){
            cloud_vel-=block_vel;
        }
        else{
            cloud_vel=dWidth-1;
        }
        canvas.drawBitmap(man[manFrame],manX,manY,null);
        handler.postDelayed(runnable,UPDATE_MILLIS);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int action = event.getAction();
        if(action==MotionEvent.ACTION_DOWN){
//            cloud_vel=cloud_vel-20;
            velocity=-50;

//            block_vel=-30;
        }
        return true;
    }

}
