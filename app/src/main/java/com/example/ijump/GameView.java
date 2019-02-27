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
    Bitmap cloud1,cloud2,cloud3;
    Bitmap block;
    int [] block_h;
    int manFrame = 0;
    int velocity=0,gravity=8,block_vel=18;
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
        man = new Bitmap[3];
        block_loc = new int[3];
        block_h = new int[3];
//        block_loc[0]=dWidth-rand.nextInt(dWidth);
//        block_loc[1]=dWidth-rand.nextInt(dWidth);
//        block_loc[2]=dWidth-rand.nextInt(dWidth);
        for(int i=0;i<3;i++) {
            block_loc[i] = dWidth - rand.nextInt(dWidth);
            block_h[i] = dHeight - rand.nextInt(dHeight);
        }
        cloud1=BitmapFactory.decodeResource(getResources(),R.drawable.cloud);
//        cloud2=BitmapFactory.decodeResource(getResources(),R.drawable.cloud);
//        cloud3=BitmapFactory.decodeResource(getResources(),R.drawable.cloud);

        man[0]=BitmapFactory.decodeResource(getResources(),R.drawable.rob5);
        man[1]=BitmapFactory.decodeResource(getResources(),R.drawable.rob6);

        man[2]=BitmapFactory.decodeResource(getResources(),R.drawable.rob7);
        manX= dWidth/2-man[0].getWidth()/2;
        manY=dHeight-man[0].getHeight()-man[0].getHeight()/2;
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
                block_h[i]=dHeight-rand.nextInt(dHeight)-block.getHeight();            }
        }
        canvas.drawBitmap(background,null,rect,null);
        int num1=rand.nextInt(4);
        canvas.drawBitmap(cloud1,cloud_vel,200,null);
        canvas.drawBitmap(cloud1,cloud_vel-400,100,null);
        canvas.drawBitmap(cloud1,cloud_vel-1000,300,null);
        canvas.drawBitmap(block,block_loc[0],block_h[0],null);
        canvas.drawBitmap(block,block_loc[1],block_h[1],null);
        canvas.drawBitmap(block,block_loc[2],block_h[2],null);
        if(manFrame==0){
            manFrame=1;
        }
        else if(manFrame==1){
            manFrame=2;
        }
        else{
            manFrame=0;
        }

        if(manY<=dHeight-man[0].getHeight()-man[0].getHeight()/2 || velocity < 0){
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
            velocity=-70;

//            block_vel=-30;
        }
        return true;
    }

}
