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
    int FPS=30;
    int num;
    int x;
    int y;
    int xVelocity, yVelocity;

    /**
     * Constructs a ball with a number from 0 to 15.
     * 
     * @param num integer from 0 to 15 that represents the pool ball's number. 0 is the cue ball, while 1 - 15 represents the 15 colored balls.
     * @param x x coordinate of ball
     * @param y y coordinate of ball
     * 
     */
    public Ball(int num, int x, int y)
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
    public int getX()
    {
        return x;
    }

    /**
     * 
     * @return y coordinate of ball 
     */
    public int getY()
    {
        return y;
    }

    public void setX(int newNum) {
        x = newNum;
    }

    public void setY(int newNum) {
        y = newNum;
    }
    
    //gets x velocity
    public int getXV() {
        return xVelocity;
    }
    
    //gets y velocity
    public int getYV() {
        return yVelocity;
    }

    //sets new x velocity
    public void setXV(int newNum) {
        xVelocity = newNum;
    }
    
    //sets new y velocity
    public void setYV(int newNum) {
        yVelocity = newNum;
    }
   
    //moves the ball
    public void move()
    {
        x+=xVelocity*FPS;
        y+=yVelocity*FPS;
    }
}

