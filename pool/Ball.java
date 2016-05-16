/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *This class is a Ball object that can be used as a cue ball or a colored ball.
 * @author Taehwan Kim and Michael Ma

 */

public class Ball
{ 
    final double FPS=30 ;
    private int num;
    private double x;
    private double y;
    private double xVelocity, yVelocity;

    /**
     * Constructs a ball with a number from 0 to 15.
     * 
     * @param num doubleeger from 0 to 15 that represents the pool ball's number. 0 is the cue ball, while 1 - 15 represents the 15 colored balls.
     * @param x x coordinate of ball
     * @param y y coordinate of ball
     * 
     */
    public Ball(int num, double x, double y)
    {

        this.num=num;
        this.x=x;
        this.y=y;
        xVelocity=0;
        yVelocity=0;

    }

    /**
     * Returns the number of the ball. If it is the cue ball, it will return 0.
     * @return the number of the ball.
     */
    public int getNumber()
    {
        return num;   
    }

    /**
     * 
     * @return x coordinate of ball 
     */
    public double getX()
    {
        return x;
    }

    /**
     * 
     * @return y coordinate of ball 
     */
    public double getY()
    {
        return y;
    }

    public void setX(double newNum) {
        x = newNum;
    }

    public void setY(double newNum) {
        y = newNum;
    }
    
    //gets x velocity
    public double getXV() {
        return xVelocity;
    }
    
    //gets y velocity
    public double getYV() {
        return yVelocity;
    }

    //sets new x velocity
    public void setXV(double newNum) {
        xVelocity = newNum;
    }
    
    //sets new y velocity
    public void setYV(double newNum) {
        yVelocity = newNum;
    }
   
    //moves the ball
    public void move()
    {
        x+=xVelocity*FPS;
        y+=yVelocity*FPS;
    }
}

