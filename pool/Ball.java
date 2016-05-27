import java.awt.*;
import java.applet.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.*;
/**
 * Represents each pool ball
 * @author Taehwan Kim
 */
public class Ball
{
    private static final int DRAWSCALE = 10;
    private static final double MINVELOCITY = 0;
    private static final double VELOCITYRANGE = 5;
    private static final double MINSIZE = 2;
    private static final double SIZERANGE = 2;
    private static final int BALLCOUNT = 16;
    private static final int WINDOWWIDTH = 1200; //size of applet and table
    private static final int WINDOWHEIGHT = 600;
    private static  int count=0; //used to determine teams
    private double Vx = 0; //x velocity
    private double Vy = 0; //y velocity
    private double Px = 0; //x position
    private double Py = 0; //y position

    private double Radius=2; //set by constructor
    private int Number;

    private Color BallColor = new Color(0,0,0); //balls initiate to white (who knows why) smile emoticon
    /**
     * initiates the values and position of a ball
     * @param x horizontal coordinate of ball
     * @param y vertical coordinate of ball
     * @param num number of ball
     */
    public Ball(double x, double y, int num) //Constructor: Initiates values
    {

        Px = x;
        Py = y;
        Number=num;
    }

    /**
     * initiates the values and position of a ball AND the color
     * @param x horizontal coordinate of ball
     * @param y vertical coordinate of ball
     * @param num number of ball
     * @param c Color of the ball
     */
    public Ball(double x, double y, int num, Color c) //Constructor: Takes a color too
    {

        Px = x;
        Py = y;
        Number=num;
        BallColor=c;
    }

    /**
     * simulates friction by slowing the ball down every frame
     */
    public void friction()
    {
        Vx=Vx+(Vx*(-1))/75;
        Vy=Vy+(Vy*(-1))/75;
        if(Math.abs(Vx) < 0.1 || Math.abs(Vy) <0.1)
        {
            Vx=0;
            Vy=0;
        }
    }

    /**
     * takes out the ball when it reaches a pocket, sets teams, and algorithm to decide when to switch players
     * @param b a Ball
     */
    public static void pocket(Ball b)
    {
        double y=b.Py+2;
        double x=b.Px+2;
        int aNum=b.getNumber();

        if(y<(double)60/10||y>(double)560/10)
        {
            if(x<(double)60/10||((double)560/10<x&&(double)660/10>x)||x>(double)1160/10)
            {
                if(b.Number!=8)
                {
                    b.Py=133700;
                    b.Vx=0;
                    b.Vy=0;
                }
                else
                {
                    Current.setStop(true);
                }
                if (count==0)
                {
                   count++;
                    if(aNum<8)
                    {
                        Current.setSolid(1);//sets teams
                    }
                    else if(aNum==16)
                    {
                        count=0;
                        Billiards.scratch();
                    }
                    else  
                    {
                        Current.setSolid(-1);
                    }
                    
                
                 
                    
                }
                Billiards.ballIn=true;
                
            }
        }
        

    }

    /**
     * returns the number of the pool ball
     */
    public int getNumber()
    {
        return Number;
    }

    /**
     * increases the velocity by some amount (used to start movement)
     * @param x how much horizontal velocity you want to increase
     * @param y how much vertical velocity you want to increase
     */
    public void addVelocity(double x, double y)//increase the velocity by some amount (used to start movement)
    {
        Vx+=x;
        Vy+=y;

    }

    /**
     * returns the total velocity of the ball
     * @return total velocity of ball using pythagorean theorem 
     */
    public double velocity() //returns the total velocity of the ball
    {
        return Math.sqrt(Vx * Vx + Vy * Vy);

    }

    /**
     * simulates what happens when a ball hits a side wall
     */
    public void wallBounceX() //the ball hits a side wall
    {
        Vx = -Vx; //ball switches x velocity
    }

    /**
     * simulates what happens when a ball hits a bottom or a top wall
     */
    public void wallBounceY() //the ball hits a top or bottom wall
    {
        Vy = -Vy; //ball switches y velocity
    }

    /**
     * moves the ball by one cycle
     * @param t time cycle
     */
    public void move(double t) //Moves the ball by one cycle
    {
        Px = Px + Vx * t;
        Py = Py + Vy * t;
    }

    /**
     * returns the x coordinate of the Pool ball 
     * @return x coordinate
     */
    public double getPx()
    {
        return  Px;
    }

    /**
     * sets x coordinate to a value
     * @param a value to set x coordinate
     */
    public void setPx(double a)
    {
        Px=a;
    }

    public void changeP(double x,double y)
    {
        Px=x;
        Py=y;
    }

    /**
     * returns the y coordinate of the Pool ball 
     * @return y coordinate
     */
    public double getPy()
    {
        return  Py;
    }

    /**
     * sets y coordinate to a value
     * @param a value to set y coordinate
     */
    public void setPy(double a)
    {
        Py=a;
    }

    /**
     * returns horizontal component of velocity
     * @return horizontal velocity
     */
    public double getVx()
    {
        return Vx;
    }

    /**
     * sets the horizontal velocity
     * @param a sets horizontal velocity to a value
     */
    public void setVx(double a)
    {
        Vx=a;
    }

    /**
     * returns vertical component of velocity
     * @return vertical velocity
     */
    public double getVy()
    {
        return Vy;
    }

    /**
     * sets the vertical velocity
     * @param a sets horizontal velocity to a value
     */
    public void setVy(double a)
    {
        Vy=a;
    }

    /**
     * sets ball color
     * @param c color to change ball to
     */
    public void setBallCol(Color c)
    {
        BallColor=c;   
    }

    /**
     * draws each individual ball
     * @param g Graphics variable used to draw the ball
     */
    public void draw(Graphics g) //Draws the ball
    {
        int top, left, size;
        left = (int)(Px * DRAWSCALE);
        top = (int)(Py *DRAWSCALE);
        size = (int)(2 * Radius *DRAWSCALE);

        g.setColor(BallColor); //Draw the ball in its color
        g.fillOval(left, top, size, size);
        g.setColor(Color.white);
        if(Number !=16)
        {
            if(Number>8) //striped
            {
                g.fillRect(left+3,top+15,37,10);
                g.setColor(Color.black);
            }
        }

        //exception for cue ball
        if(Number!=16)
            g.drawString(Integer.toString(Number),left+20, top+25);
        //g.setColor(Color.black); //Draw the outline of the ball
        //g.drawOval(left, top, size, size);
    }
}