/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pool;

/**
 *This class is a Ball object that can be used as a cue ball or a colored ball.
 * @author Taehwan Kim and Michael Ma

 */

public class Ball
{ 
 
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
    
    public int getXV() {
        return xVelocity;
    }

    public int getYV() {
        return yVelocity;
    }

    public void setXV(int newNum) {
        xVelocity = newNum;
    }

    public void setYV(int newNum) {
        yVelocity = newNum;
    }
}

