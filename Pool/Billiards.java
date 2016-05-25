import java.awt.*;
import java.applet.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.*;
public class Billiards extends Applet implements Runnable,MouseMotionListener,MouseListener
{   
    Thread BallTimer; //used for loops to draw and move balls,
    

    //used for double bufferring
    Image offImage;
    Graphics offGraphics;

    //initiate billiard table, at 0,0 set its size to the constanst given in the globals class
    private BilliardTable T = new BilliardTable(0,0,globals.WindowWidth / globals.DrawScale, globals.WindowHeight / globals.DrawScale);

    //initiate the number of balls in the constant in globals class
    Ball b[] = new Ball[globals.BallCount];
    Graphics g = getGraphics();
    //set chosen table color
    Color TableColor = new Color(10,108,3);
    //make placeholder point for when the mouse is relesed
    Point lastPoint= new Point(0,0);
    Point firstPoint=new Point(0,0);
    private int Mx=0;
    private int My=0;
    private int Dx=0;
    private int Dy=0;
    public void mouseMoved(MouseEvent me)
    {
        g.setColor(Color.white);
        Mx=me.getX();
        My=me.getY();
        Dx=(int)(b[15].Px*globals.DrawScale+20)-me.getX();
        Dy=(int)(b[15].Py*globals.DrawScale+20)-me.getY();

    }

    public void mouseDragged(MouseEvent me)
    {
        Mx=me.getX();
        My=me.getY();

        Dx=(int)(b[15].Px*globals.DrawScale+20)-me.getX();
        Dy=(int)(b[15].Py*globals.DrawScale+20)-me.getY();
        if(!globals.ballsMoving)
        {
            g.drawLine(Mx+2*Dx,My+2*Dy,(int)(b[15].Px*globals.DrawScale+20),(int)(b[15].Py*globals.DrawScale+20));
        }

    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) { 
        firstPoint=e.getPoint();
    }

    public void mouseReleased(MouseEvent e) {
        if(!(globals.ballsMoving))
        {

            lastPoint=e.getPoint();
            Mx=e.getX();
            My=e.getY();
            b[15].addVelocity(-1*(lastPoint.getX()-firstPoint.getX())/globals.DrawScale,-1*(lastPoint.getY()-firstPoint.getY())/globals.DrawScale);
            globals.playerOne=!globals.playerOne;
           
          

        }

    }
    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
    

    public void run() //used by the Refresh Timer
    {   
        int i = 0; //For loop counters
        int x = 0;
        int y = 0;

        double pX[] = new double[globals.BallCount];
        double pY[] = new double[globals.BallCount];

        System.out.println("Run");
        g = getGraphics();
         GUI myGUI = new GUI();
         myGUI.getG(g);
        myGUI.start();
         

        while (!(globals.stop) )
        {

            offGraphics.setColor(TableColor);
            offGraphics.fillRect((int)T.Left * globals.DrawScale, (int)T.Top * globals.DrawScale, (int)T.Width * globals.DrawScale, (int)T.Height * globals.DrawScale);
            offGraphics.setColor(Color.black);
            globals.ballsMoving=false;

            for(i = 0; i < globals.BallCount; i++)
            {
                pX[i] = b[i].Px;
                pY[i] = b[i].Py;
                b[i].Move(.05); //move the balls a bit
                b[i].Friction();
                if(b[i].Vx !=0 && b[i].Vy !=0)
                {
                    globals.ballsMoving=true;
                }
                Ball.Pocket(b[i]);
            }

            for(i = 0; i < globals.BallCount; i++)
            {
                CheckWallBounce(b[i]);
                //deal with any balls that are hitting the sides
            }

            if (globals.BallCount > 1)
            {
                for(x = 0; x < globals.BallCount; x++)
                {
                    for(y = x + 1; y < globals.BallCount; y++) //check each ball with each other ball
                    {
                        if (Separation(b[x],b[y]) <= RadSum(b[x],b[y])) //if their separationg is small enough to be touching
                        {
                            Collide(b[x],b[y]); //COLLISON!!!!
                        }
                    }
                }
            }

            for(i = 0; i < globals.BallCount; i++)
            {
                b[i].Draw(offGraphics); //Draw every ball
            }

            T.DrawBorder(offGraphics); //draw the border (balls hitting the side can erase part of it
            try { Thread.sleep(15); } //pause for a little while
            catch (InterruptedException e) { }

            g.setColor(Color.white);
            g.drawImage(offImage, 0, 0, null);
            if(!globals.ballsMoving)
            {
                g.drawLine(Mx+2*Dx,My+2*Dy,(int)(b[15].Px*globals.DrawScale+20),(int)(b[15].Py*globals.DrawScale+20));
            }
           
        }
        g.setColor(Color.black);
        g.fillRect(0,0,1200,600);
        g.setColor(Color.yellow);
        if(globals.playerOne)
        {
            g.drawString("Player 2 wins",200,300);
        }
        else
        {
            g.drawString("player 1 wins",200,300);
        }

    }

    public Color RandColor()//returns a random color in full color range
    {
        int r,g,b;
        r = (int)(Math.random() * 256);
        g = (int)(Math.random() * 256);
        b = (int)(Math.random() * 256);
        return new Color(r,g,b);
    }

    public void init() //start balltimer thread, and initiate all balls
    {   
        //setup applet size
        setSize(globals.WindowWidth, globals.WindowHeight);

        //Setup the invisible image to be used for double buffering, set the offGraphics to be the graphics
        //which draw on on that image
        offImage = createImage((int)(T.Width + T.Left) * globals.DrawScale,(int) (T.Height + T.Top) * globals.DrawScale);
        offGraphics = offImage.getGraphics();

        double tmpRad; //stores temporary radius, (we need it to check for ball overlap)
        double tmpX, tmpY; //to store random X and Y coords for a ball before we check if it will overlap
        boolean overlap; //used in loop for ball overlap check
        int x; //counter for overlap loop

        double dX, dY, Rads; //calculating distance between balls

        for(int i = 0; i < globals.BallCount; i++)
        { //individually initiate every ball and give random position, radius and velocities

            tmpRad = Math.random() * globals.SizeRange + globals.MinSize;

            //NOTE: code should be added later to break the loop with an error
            //      if there is nowhere the ball can be placed
            do
            {
                overlap = false;

                //random x and y coords inside of the table
                tmpX = Math.random() * T.Width + T.Left;
                tmpY = Math.random() * T.Height + T.Top;

                for(x = 0; x < i; x++) //check it with every ball already initiated to find a suitable position
                {
                    dX = b[x].Px - tmpX;
                    dY = b[x].Py - tmpY;
                    Rads = b[x].Radius + tmpRad;
                    if (Math.sqrt(dX * dX + dY * dY) < Rads) //if their seperation is less than it would be if they were touching
                        overlap = true; //they are overlaping
                }
            } while (overlap);

            b[i] = new Ball(tmpX, tmpY, i+1);
            b[i].Vx = (Math.random() * globals.VelocityRange + globals.MinVelocity) * PosNegRand();
            b[i].Vy = (Math.random() * globals.VelocityRange + globals.MinVelocity) * PosNegRand();
            b[i].BallColor = RandColor();
        }

        b[15].BallColor = Color.white;

        repaint(); //just make sure that the table gets painted

        BallTimer = new Thread(this); //set the ball timer to be a new thread, and start it!
        addMouseMotionListener(this);
        addMouseListener(this);
        
       

        BallTimer.start(); //after this happens, the Run() method is called and our moving loops start
    }

    public int PosNegRand()//randomly returns + or - 1, to make velocity random direction
    {
        double a = Math.random() * 2;
        if (a >= 1)
            a = 1;
        if (a < 1)
            a = -1;

        return (int)a;
    }

    public void CheckWallBounce(Ball B)//checks if the ball is touching any of the sides
    {
        double Xleft = B.Px;
        double Ytop = B.Py;
        double Ybottom = B.Py + 2 * B.Radius; //Py measures the top left coord of ball, so we add two radii
        double Xright = B.Px + 2 * B.Radius;  //to get the bottom and right y and x coords.
        if(Ytop!=133700)
        {
            if (Xleft < T.minX())
            {
                B.Px = T.minX();
                B.WallBounceX();
            }

            if (Ytop < T.minY())
            {
                B.Py = T.minY();
                B.WallBounceY();
            }

            if (Xright > T.maxX())
            {
                B.Px = T.maxX() - 2 * B.Radius;
                B.WallBounceX();
            }

            if (Ybottom > T.maxY())
            {
                B.Py = T.maxY() - 2 * B.Radius;
                B.WallBounceY();
            }
        }
    }

    public void paint(Graphics g) //redraws the entire table (not balls)
    {
        T.Draw(g, TableColor);

    }

    public double Separation(Ball B1, Ball B2) //Gives the distance between two balls
    {
        double X1, X2, Y1, Y2;
        X1 = B1.Px + B1.Radius;
        X2 = B2.Px + B2.Radius;
        Y1 = B1.Py + B1.Radius;
        Y2 = B2.Py + B2.Radius;

        return Math.sqrt((X2 - X1) * (X2 - X1) + (Y2 - Y1) * (Y2 - Y1));

    }

    public double RadSum(Ball B1, Ball B2) //Sum of the radii of two balls
    {
        return (B1.Radius + B2.Radius);

    }

    public void Collide(Ball B1, Ball B2) //Collides two balls and changes their velocities accordingly
    {

        /* These collisions should all be perfectly (of very close to it) elastic.
         * Final velocities are calculated on the premise that the impulse from a
         * collision acts along a line through the center of the ball and through the
         * collision point, therefore the component of velocity perpendicular to the
         * collision cannot be changed by it, therefore the component of velocity headed
         * straight into the collision can be treated as a single dimensional collison*/

        double Dx1, Dx2, Dy1, Dy2; //Distances to the collision point
        double X1, X2, Y1, Y2; //distances to bewteen centers and collision
        double DxR, DyR; //real distances (collision overlap)
        double Dx, Dy; //imaginary distances (no overlap)
        double Vp1, Vp2, Vs1, Vs2; //normal and perpendicular velocities to collision
        double newVs1, newVs2; //for storing new straigth velocites during calculations
        double distance; //real distance between ball centers

        X1 = B1.Px + B1.Radius;
        X2 = B2.Px + B2.Radius;
        Y1 = B1.Py + B1.Radius;
        Y2 = B2.Py + B2.Radius;

        //the first thing that we need to do is move one of the balls to make it a perfect collision on the edges
        //since there should always be at least a very slight overlap from non actual smooth movement
        //(NOTE: This probably should later be replaced by a method inside of the move method to prevent overlap instead of correcting it)
        DxR = (X2 - X1);
        DyR = (Y2 - Y1);

        distance = Math.sqrt(DxR * DxR + DyR * DyR); //gets the ACTUAL distance between the two balls

        //RadSum adds radii, this gives the ideal distance
        Dx = RadSum(B1,B2) * DxR / distance; //this is how much we much change X and Y coords to get a perfect collision
        Dy = RadSum(B1,B2) * DyR / distance;

        //so that things dont look screwy when we have a fast tiny ball collide with a slow big one
        //we want it to be the small ball that will have its position adjusted

        
        
            X2 = (X2 + (Dx - DxR));
            Y2 = (Y2 + (Dy - DyR));

            B2.Px = X2 - B2.Radius;
            B2.Py = Y2 - B2.Radius;
               

        //Find the x and y distances from the centers of each ball to the collision point
        Dx1 = (B1.Radius / RadSum(B1,B2)) * (X2 - X1);
        Dx2 = (B2.Radius / RadSum(B1,B2)) * (X2 - X1);

        Dy1 = (B1.Radius / RadSum(B1,B2)) * (Y2 - Y1);
        Dy2 = (B2.Radius / RadSum(B1,B2)) * (Y2 - Y1);

        //calculate the components of velocity of each ball headed towards the collision point and perpendicular to it
        Vs1 = StraightVelocity(B1.Vx, B1.Vy, Dx1, Dy1, B1.Radius);
        Vp1 = PerpendicularVelocity(B1.Vx, B1.Vy, Dx1, Dy1, B1.Radius);

        Vs2 = StraightVelocity(B2.Vx, B2.Vy, Dx2, Dy2, B2.Radius);
        Vp2 = PerpendicularVelocity(B2.Vx, B2.Vy, Dx2, Dy2, B2.Radius);

        //use the formulas in the method to find new straight velocities for each ball
        newVs1 = CollisionVelocity(Vs1, Vs2, B1.Mass, B2.Mass);
        newVs2 = CollisionVelocity(Vs2, Vs1, B2.Mass, B1.Mass);

        //now we get new X and Y velocities for each, using the new straight velocity and the
        //unaffected perpendicular velocity component

        B1.Vx = XVelocity(newVs1, Vp1, Dx1, Dy1, B1.Radius);
        B1.Vy = YVelocity(newVs1, Vp1, Dx1, Dy1, B1.Radius);

        B2.Vx = XVelocity(newVs2, Vp2, Dx2, Dy2, B2.Radius);
        B2.Vy = YVelocity(newVs2, Vp2, Dx2, Dy2, B2.Radius);

    }

    public double StraightVelocity(double Vx, double Vy, double Dx, double Dy, double R)
    {
        return Vx * Dx / R + Vy * Dy / R;
    } //velocity directed towards collision
    public double PerpendicularVelocity(double Vx, double Vy, double Dx, double Dy, double R)
    {
        return Vy * Dx / R - Vx * Dy / R;
    }//velocity perpendicular to collision
    public double XVelocity(double Vs, double Vp, double Dx, double Dy, double R)
    {
        return Vs * Dx / R - Vp * Dy / R;
    } //x velocity from S and P
    public double YVelocity(double Vs, double Vp, double Dx, double Dy, double R)
    {
        return Vs * Dy / R + Vp * Dx / R;
    } //y velocity from S and P
    public double CollisionVelocity(double V1, double V2, double m1, double m2) //Returns velocity of a ball after collision
    {
        return V1 * (m1-m2) / (m1+m2) + V2 * (2 * m2) / (m1 + m2);
    }
}