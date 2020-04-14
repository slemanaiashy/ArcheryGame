package com.example.archerygame;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class Arrow {
    protected int Time;
    protected double ySpeed;
    protected double xSpeed,Angle;
    protected  float x;
    protected  float y;
    protected int arrowHeight;
    protected int arrowWidth;
    protected float canvasHeight;
    protected float canvasWidth;
    private boolean a,b,ab;

    public void setA(boolean a) {
        this.a = a;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public void setAb(boolean ab) {
        this.ab = ab;
    }
    public boolean getAb() {
        return ab;
    }

    public void setTime(int time) {
        Time = time;
    }

    public void setCanvasHeight(float canvasHeight) {
        this.canvasHeight = canvasHeight;
    }

    public void setCanvasWidth(float canvasWidth) {
        this.canvasWidth = canvasWidth;
    }

    public Arrow(int time, double ySpeed, double xSpeed, Bitmap arrow, int arrowHeight, int arrowWidth, float x1, float y1, boolean ab, boolean a , boolean b) {
        Time = time;
        this.ySpeed = ySpeed;
        this.xSpeed = xSpeed;
        this.x=x1;
        this.y=y1;
        this.a =a ;
        this.b=b;
        this.ab=ab;
        this.arrowHeight = arrowHeight;
        this.arrowWidth = arrowWidth;
    }
    public void Setx (float x )
    {
        this.x=x;
    }
    public void Sety (float y ){
        this.y=y;
    }
    public void setAngle(double Angle){
        this.Angle=Angle;
    }
    public double getAngle(){//-angle
        return Math.atan((ySpeed) / xSpeed) * -57.2958 ;
    }
    public double getySpeed(){
        return (-ySpeed*Time+Time*Time/10);
    }
    public boolean getX(){
        return x +Math.abs(xSpeed * (Time))<=(canvasWidth);
    }
    public boolean getY(){
        return y+getySpeed()<=(canvasHeight);
    }
    public Matrix getArrow(){
        System.out.println("T: "+Time);
        Matrix matrix = new Matrix();//-110,-90
        if(ab){
            matrix.postRotate((int)getAngle(), arrowWidth/2, arrowHeight / 2);
            x =x+(int) Math.abs(xSpeed * Time);
            y = y+(int)getySpeed();
            matrix.postTranslate(x,y );
            return matrix;
        }
        if (a&&!ab) {

            matrix.postRotate((int)Angle, arrowWidth/2, arrowHeight / 2);
            matrix.postTranslate(x,canvasHeight );

            return matrix;
        }
        if(b&&!ab){
            matrix.postRotate((int)Angle, arrowWidth/2, arrowHeight / 2);
            matrix.postTranslate(canvasWidth,y );

            return matrix;

        }
        if(!a&&!b&&!ab){
            matrix.postRotate((int)Angle, arrowWidth/2, arrowHeight / 2);
            matrix.postTranslate(x,y );
            return matrix;
        }
        return matrix;
    }
}
