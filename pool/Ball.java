import java.awt.*;
import java.applet.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.*;
class Ball
{
    double Vx = 0; //x velocity
    double Vy = 0; //y velocity
    double Px = 0; //x position
    double Py = 0; //y position

    double Radius=2; //set by constructor
    double Mass=1; //calculated in constructor
    int Number;

    Color BallColor = new Color(0,0,0); //balls initiate to white (who knows why) smile emoticon
    public Ball(double x, double y, int num) //Constructor: Initiates values
    {

        Px = x;
        Py = y;
        Number=num;
    }

    public Ball(double x, double y, int num, Color c) //Constructor: Takes a color too
    {

        Px = x;
        Py = y;
        Number=num;
        BallColor=c;
    }

    public void Friction()
    {
        Vx=Vx+(Vx*(-1))/75;
        Vy=Vy+(Vy*(-1))/75;
        if(Math.abs(Vx) < 0.1 || Math.abs(Vy) <0.1)
        {
            Vx=0;
            Vy=0;
        }
    }

    public static void Pocket(Ball b)
    {
        double y=b.Py;
        double x=b.Px;

        if(y<(double)50/10||y>(double)550/10)
        {
            if(x<(double)50/10||((double)550/10<x&&(double)650/10>x)||x>(double)1150/10)
            {
                if(b.Number!=8)
                {
                    b.Py=133700;
                    b.Vx=0;
                    b.Vy=0;
                }
                else
                {
                    globals.stop=true;
                }
            }
        }

    }

    public void addVelocity(double x, double y)//increase the velocity by some amount (used to start movement)
    {
        Vx+=x;
        Vy+=y;

    }

    public double Velocity() //returns the total velocity of the ball
    {
        return Math.sqrt(Vx * Vx + Vy * Vy);

    }

    public double Momentum() //returns total momentum of ball
    { //p = mv
        return Mass * Vx + Mass * Vy;

    }

    public double Kinetic() //returns total kinetic energy of ball
    {// ke = 1/2 m v^2
        return .5 * Mass * Velocity() * Velocity();

    }

    public void WallBounceX() //the ball hits a side wall
    {
        Vx = -Vx; //ball switches x velocity
    }

    public void WallBounceY() //the ball hits a top or bottom wall
    {
        Vy = -Vy; //ball switches y velocity
    }

    public void Move(double t) //Moves the ball by one cycle
    {
        Px = Px + Vx * t;
        Py = Py + Vy * t;
    }

    public void Draw(Graphics g) //Draws the ball
    {
        int top, left, size;
        left = (int)(Px * globals.DrawScale);
        top = (int)(Py * globals.DrawScale);
        size = (int)(2 * Radius * globals.DrawScale);

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

        g.drawString(Integer.toString(Number),left+20, top+25);
        //g.setColor(Color.black); //Draw the outline of the ball
        //g.drawOval(left, top, size, size);
    }
}