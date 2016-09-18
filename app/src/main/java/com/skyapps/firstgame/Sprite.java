package com.skyapps.firstgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class Sprite {

    float x,y;
    float runx, runy;
    Bitmap bitmap;
    boolean isLife ;
    BoardGame boardGame;
    boolean stopJump;
    Paint paint;

    boolean Left;

    public Sprite(float x, float y, float runx, float runy, Bitmap bitmap, boolean isLife, BoardGame boardGame, boolean stopJump) {
        this.x = x;
        this.y = y;
        this.runx = runx;
        this.runy = runy;
        this.bitmap = bitmap;
        this.isLife = isLife;
        this.boardGame = boardGame;
        this.stopJump = stopJump;
        paint = new Paint();
        paint.setAlpha(255);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRunx() {
        return runx;
    }

    public void setRunx(float runx) {
        this.runx = runx;
    }

    public float getRuny() {
        return runy;
    }

    public void setRuny(float runy) {
        this.runy = runy;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public boolean isLife() {
        return isLife;
    }

    public void setLife(boolean life) {
        isLife = life;
    }

    public void setLeft (boolean left) {
        Left = left;
    }

    public BoardGame getBoardGame() {
        return boardGame;
    }

    public void setBoardGame(BoardGame boardGame) {
        this.boardGame = boardGame;
    }

    public boolean isStopJump() {
        return stopJump;
    }

    public void setStopJump(boolean stopJump) {
        this.stopJump = stopJump;
    }

    public synchronized void drawBitmap(Canvas canvas){
        canvas.drawBitmap(bitmap,x,y,paint);
    }

    public void jumpIt() {

        if (isLife) {

            if (!stopJump) {

                if (this.x + 10 < boardGame.getWidth() - 100) {
                    if (Left)
                        this.x = this.x + 20;
                    else
                        this.x = this.x - 20;

                }
                else {
                    this.x = this.x - 50;
                }

                y += runy;
                runy += 10;


            }
            if (this.x <0){
                this.x = this.x + 100;
            }
            /*if (this.x > boardGame.getWidth()){
                this.x = this.x - 50;
            }*/
            if (y - 100 >= boardGame.getHeight() /(float)1.3) {
                stopJump = true;
                runy = -runy;

            }

        }
    }

    public void moveFruit(){
        this.x=this.x-750;

        if (this.x<0){
            this.x = boardGame.getWidth()+100;
        }

    }

    public boolean isCollision(Fruit fruit){

        if (x>fruit.getX() && x<fruit.getX() + fruit.getBitmap().getWidth() && y>=fruit.getY() && y<fruit.getY() + fruit.getBitmap().getHeight()  ){
            return true;
        }


        /*if (x + 5 >fruit.getX()  && x - 5<fruit.getX() + fruit.getBitmap().getWidth() && y + 5>=fruit.getY()  && y - 5<fruit.getY() + fruit.getBitmap().getHeight()  )
            return true;*/

        return false;

    }


}
