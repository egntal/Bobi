package com.skyapps.firstgame;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Random;

public class BoardGame extends View {
    private int counter=0;
    private Bitmap back, bR, bL, bob;
    private float x,y;
    private Context context;
    private int first;
    private Handler gameHandler;
    private GameThread gameTread;
    private boolean right;
    private Sprite bobi;
    private int point;

    boolean play = true;

    private int life = 3;

    private Fruit[] fruits;



    public BoardGame(Context context) {
        super(context);
        this.context = context;
        x = 20;
        y = 20;
        gameTread = new GameThread();
        gameTread.start();
        gameHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                invalidate();
                return true;
            }
        });

        back = BitmapFactory.decodeResource(getResources(), R.raw.wallpaper);

        bR = BitmapFactory.decodeResource(getResources(),R.drawable.bobi_l);
        bL = BitmapFactory.decodeResource(getResources(),R.drawable.bobi_r);
        bob = Bitmap.createScaledBitmap(bL,150,150,false);

        bobi = new Sprite(200,200,20,20,bob,true,this,true);
        //bobi.jumpIt();

        fruits = new Fruit[5];

        play = true;
        //loadFruits();


    }

    public void loadFruits(){

        fruits[0] = new Fruit(context,this.getWidth(), 450, BitmapFactory.decodeResource(getResources(),R.drawable.fruit_1),this,false,10,1,
                BitmapFactory.decodeResource(getResources(),R.drawable.bomb),true);

        fruits[1] = new Fruit(context,this.getWidth(), 770, BitmapFactory.decodeResource(getResources(),R.drawable.fruit_2),this,false,10,1,
                BitmapFactory.decodeResource(getResources(),R.drawable.bomb),true);

        fruits[2] = new Fruit(context,this.getWidth(), 510, BitmapFactory.decodeResource(getResources(),R.drawable.bomba),this,false,-100,1,
                BitmapFactory.decodeResource(getResources(),R.drawable.bomb),true);

        fruits[3] = new Fruit(context,this.getWidth(), 800, BitmapFactory.decodeResource(getResources(),R.drawable.fruit_4),this,false,10,1,
                BitmapFactory.decodeResource(getResources(),R.drawable.bomb),true);

        fruits[4] = new Fruit(context,this.getWidth(), 320, BitmapFactory.decodeResource(getResources(),R.drawable.fruit_5),this,false,10,1,
                BitmapFactory.decodeResource(getResources(),R.drawable.bomb),true);

      // fruits[5] = new Fruit(context,this.getWidth(), 850, BitmapFactory.decodeResource(getResources(),R.drawable.fruit_6),this,false,1,1,
      //          BitmapFactory.decodeResource(getResources(),R.drawable.bomb),true);

        /*fruits[6] = new Fruit(this.getWidth(), 350, BitmapFactory.decodeResource(getResources(),R.drawable.fruit_1),this,false,10,1,
                BitmapFactory.decodeResource(getResources(),R.drawable.bomb),true);

        fruits[7] = new Fruit(this.getWidth(), 510, BitmapFactory.decodeResource(getResources(),R.drawable.fruit_4),this,false,10,1,
                BitmapFactory.decodeResource(getResources(),R.drawable.bomb),true);

        fruits[8] = new Fruit(this.getWidth(), 70, BitmapFactory.decodeResource(getResources(),R.drawable.bomba),this,false,-100,1,
                BitmapFactory.decodeResource(getResources(),R.drawable.bomb),true);

        fruits[9] = new Fruit(this.getWidth(), 720, BitmapFactory.decodeResource(getResources(),R.drawable.fruit_3),this,false,10,1,
                BitmapFactory.decodeResource(getResources(),R.drawable.bomb),true);*/




    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
           // x = event.getX();
           // y = event.getY();

        int _x = (int) event.getX();
        int _y = (int) event.getY();

        //Log.e("_Y : " + this.y , "_Y BOBY : " + bobi.getY());

        if (_x >= bobi.getX()){
            bobi.setStopJump(false);
            bobi.setRuny(-46);
            bobi.setLeft(true);
            bobi.jumpIt();
        }

        if (_x < bobi.getX()){
            bobi.setStopJump(false);
            bobi.setRuny(-46);
            bobi.setLeft(false);
            bobi.jumpIt();



        }

        if (this.y > bobi.getY()){
            bobi.setY(50);
        }

        if (this.x < 0){
            bobi.setX(200);
        }





        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        this.setBackgroundColor(Color.BLACK);
        if (first==0){
            bobi.setX(getWidth()*10 / 100);
            bobi.setY(getHeight()/(float)1.3);

            bobi.Left = true;

            loadFruits();




            for(int i=0;i<fruits.length;i++){
                Random x1 = new Random();
                //int x = x1.nextInt();
               // int xR = x1.nextInt(this.getHeight() - (this.getHeight() / (int) 1.33) + (this.getHeight() / (int)1.23));

                fruits[0].setX(x1.nextInt(1150-800)+800);
                fruits[1].setX(x1.nextInt(800-600)+600);
                fruits[2].setX(x1.nextInt(600-400)+400);
                fruits[3].setX(x1.nextInt(400-300)+300);
                fruits[4].setX(x1.nextInt(300-200)+200);

                //Log.e("x1 :" , x1.toString());

                /*fruits[0].setY(x1.nextInt(1150-800)+800);
                fruits[1].setY(x1.nextInt(500-200)+200);
                fruits[2].setY(x1.nextInt(550-300)+300);
                fruits[3].setY(x1.nextInt(700-200)+200);
                fruits[4].setY(x1.nextInt(600-400)+400);*/
            }
        }


        first++;


        for (int i = 0; i<10; i++){
            drawBitmap(canvas,back,counter++,0,getWidth(),getHeight());
            drawBitmap(canvas, back, counter-getWidth(), 0, getWidth(), getHeight());
            counter = counter % getWidth();
            counter = counter + 30;

        }

        /*drawBitmap(canvas,back,-counter,0,getWidth(),getHeight());
        drawBitmap(canvas, back, -counter+getWidth(), 0, getWidth(), getHeight());
        counter = counter % getWidth();
        counter = counter + 100;*/






        for(int i=0;i<fruits.length;i++){

            new Thread(new Runnable() {
                @Override
                public void run() {

                    //Bitmap fruit5 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.fruit_6),70,70,false);  fruits[5].setBitmap(fruit5);
                }
            }).start();



            Bitmap fruit0 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.fruit_1),220,220,false);  fruits[0].setBitmap(fruit0);
            Bitmap fruit1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.fruit_2),150,200,false);  fruits[1].setBitmap(fruit1);
            Bitmap fruit2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.bomba),200,200,false);  fruits[2].setBitmap(fruit2);
            Bitmap fruit3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.fruit_4),200,200,false);  fruits[3].setBitmap(fruit3);
            Bitmap fruit4 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.fruit_5),200,250,false);  fruits[4].setBitmap(fruit4);

            /*Bitmap fruit6 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.fruit_1),180,180,false);  fruits[6].setBitmap(fruit6);
            Bitmap fruit7 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.fruit_4),250,250,false);  fruits[7].setBitmap(fruit7);
            Bitmap fruit8 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.bomba),150,150,false);  fruits[8].setBitmap(fruit8);
            Bitmap fruit9 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.fruit_3),190,230,false);  fruits[9].setBitmap(fruit9);*/

            Random x1 = new Random();
            //int xR = x1.nextInt(this.getHeight() - (this.getHeight() / (int) 1.45) + (this.getHeight() / (int)1.45));

            fruits[i].drawBitmap(canvas);
            fruits[i].moveFruit(35 , x1.nextInt(2000));
            //Log.e("The i :" , i + "");

            Typeface custom = Typeface.createFromAsset(context.getAssets(),"fonts/abc.ttf");

            //draw Text
            Paint paint = new Paint();
            paint.setTextSize(75);
            paint.setColor(Color.BLACK);
            //paint.setUnderlineText(true);
            paint.isFakeBoldText();
            paint.setTypeface(custom);
            canvas.drawText("Your Points: " + context.getSharedPreferences("TAL", Context.MODE_APPEND).getInt("points",0), 100, 100 , paint );
            canvas.drawText("Your Top Record: " + context.getSharedPreferences("TAL", Context.MODE_APPEND).getInt("TopPoints",0), 100, 200 , paint );

            if (context.getSharedPreferences("TAL", Context.MODE_APPEND).getInt("points",0) > context.getSharedPreferences("TAL", Context.MODE_APPEND).getInt("TopPoints",0)){
                context.getSharedPreferences("TAL", Context.MODE_APPEND).edit().putInt("TopPoints", context.getSharedPreferences("TAL", Context.MODE_APPEND).getInt("points",0)).commit();
                //Toast.makeText(context, "New Record !!", Toast.LENGTH_SHORT).show();
            }


        }

        if (bobi.Left){
            bob = Bitmap.createScaledBitmap(bL,150,150,false);
        }

        else{
            bob = Bitmap.createScaledBitmap(bR,150,150,false);
        }

        bobi.setBitmap(bob);
        bobi.drawBitmap(canvas);
        bobi.jumpIt();

        for (int i = 0; i<fruits.length;i++){

            if (bobi.isCollision(fruits[i])){

                point = point + fruits[i].getPoint();
                fruits[i].getPaint().setAlpha(0);

                fruits[i].fireNow(canvas);

            }
        }


    }

    public static synchronized void drawBitmap(Canvas canvas, Bitmap bitmap, int x, int y, int width, int height){
        Rect source = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
        Rect target = new Rect(x,y,x+width,y+height);
        canvas.drawBitmap(bitmap,source,target,null);
    }

    public class GameThread extends Thread{
        @Override
        public void run() {
            super.run();

            while (true){
                try {

                    Thread.sleep(10);
                    if (play==true)
                        gameHandler.sendEmptyMessage(0);


                } catch (Exception e){
                    e.printStackTrace();
                }
            }

        }
    }



}
