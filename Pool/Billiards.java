
import java.awt.*;
import java.applet.*;
import java.awt.event.MouseEvent;
import java.awt.event.*;
/**
 * Applet that could be seen as the runner and runs the whole game
 * @author Taehwan Kim and Michael Ma
 */
public class Billiards extends Applet implements Runnable,MouseMotionListener,MouseListener
{   
    /**
     * used for loops to draw and move balls
     */
    Thread BallTimer; //used for loops to draw and move balls,
    private final double MASS=1;
    private static final int DRAWSCALE = 10;
    private static final double MINVELOCITY = 0;
    private static final double VELOCITYRANGE = 5;
    private static final double MINSIZE = 2;
    private static final double SIZERANGE = 2;
    private static final int BALLCOUNT = 16;
    private static final int WINDOWWIDTH = 1200; //size of applet and table
    private static final int WINDOWHEIGHT = 600;

    //used for double bufferring
    /**
     * image used for double buffering
     */
    Image offImage;
    /**
     * Graphics variable used for double buffering
     */
    Graphics offGraphics;

    //initiate billiard table, at 0,0 set its size to the constanst given in the globals class
    private BilliardTable T = new BilliardTable(0,0,WINDOWWIDTH/DRAWSCALE, WINDOWHEIGHT /DRAWSCALE);

    //initiate the number of balls in the constant in globals class
    /**
     * represents the balls on the pool table
     */
    static Ball b[] = new Ball[BALLCOUNT];
    /**
     * Graphics variable to mainly draw the balls on the table
     */
    Graphics g = getGraphics();
    //set chosen table color
    /**
     * sets chosen table color
     */
    Color TableColor = new Color(10,108,3);
    //make placeholder point for when the mouse is relesed
    /**
     * makes placeholder point for when mouse is released
     */
    Point lastPoint= new Point(0,0);
    /**
     * makes placeholder point for when mouse is first released
     */
    Point firstPoint=new Point(0,0);
    /**
     * make a boolean that determines wether or not it's time to switch players
     */
    static boolean turnTime=false;
    /**
     * make a boolean that determines whether or not it's scratch time
     */
    static boolean scratch=false;
    /**
     * make a boolean that determines if the player has scrached- VERY DIFFERENT FROM SCRATCH
     */
    static boolean scratched=false;
    /**
     * make a boolean that determines if the player has scrached- VERY DIFFERENT FROM SCRATCH
     */
    static boolean ballIn=false;
    
    private int Mx=0;
    private int My=0;
    private int Dx=0;
    private int Dy=0;
    private static int hits=0;

    /**
     * default, empty constructor
     */
    public Billiards()
    {

    }

    /**
     * emulates the movement of a mouse
     * @param me An event which indicates that a mouse action occurred in a component.
     */
    public void mouseMoved(MouseEvent me)
    {
        if(scratch)
        {
             b[15].changeP(me.getX()/10,me.getY()/10);
        }
        g.setColor(Color.white);
        Mx=me.getX();
        My=me.getY();
        Dx=(int)(b[15].getPx()*DRAWSCALE+20)-me.getX();
        Dy=(int)(b[15].getPy()*DRAWSCALE+20)-me.getY();

    }

    /**
     * performs what would happen when mouse is dragged across screen
     * @param me An event which indicates that a mouse action occurred in a component.
     */
    public void mouseDragged(MouseEvent me)
    {
        Mx=me.getX();
        My=me.getY();

        Dx=(int)(b[15].getPx()*DRAWSCALE+20)-me.getX();
        Dy=(int)(b[15].getPy()*DRAWSCALE+20)-me.getY();
        if(b[15].getVy()==0&&b[15].getVx()==0&&!Current.isMoving())
        {
            g.drawLine(Mx+2*Dx,My+2*Dy,(int)(b[15].getPx()*DRAWSCALE+20),(int)(b[15].getPy()*DRAWSCALE+20));

        }

    }

    /**
     * simulates what happens if mouse has been clicked on
     * @param e An event which indicates that a mouse action occurred in a component.
     */
    public void mouseClicked(MouseEvent e) {
        if(scratch)
        {
             scratch=false;
        }

    }

    /**
     *  simulates what happens if the mouse was pressed on by the player
     * @param e An event which indicates that a mouse action occurred in a component.
     */
    public void mousePressed(MouseEvent e) { 
        firstPoint=e.getPoint();

    }
    /**
     *  simulates what happens  if the mouse was released by player and method to changes player
     * @param e An event which indicates that a mouse action occurred in a component.
     */
    public void mouseReleased(MouseEvent e) {
        if(!(Current.isMoving())&&!scratch)
        {

            lastPoint=e.getPoint();
            Mx=e.getX();
            My=e.getY();
            b[15].addVelocity((-1*(lastPoint.getX()-firstPoint.getX())/DRAWSCALE)*2,(-1*(lastPoint.getY()-firstPoint.getY())/DRAWSCALE)*2);

            turnTime=true;
        }

    }
    /**
     *  simulates what happens if mouse has entered
     * @param e An event which indicates that a mouse action occurred in a component.
     */
    public void mouseEntered(MouseEvent e) {
    }

    /**
     *  simulates what happens if mouse has exited
     * @param e An event which indicates that a mouse action occurred in a component.
     */
    public void mouseExited(MouseEvent e) {
    }

    /**
     * puts the balls in correct position and repaints the screen continuously in a loop to actually roll the game
     */
    public void run() //used by the Refresh Timer
    {   
        int i = 0; //For loop counters
        int x = 0;
        int y = 0;

        double pX[] = new double[BALLCOUNT];
        double pY[] = new double[BALLCOUNT];

        System.out.println("Run");
        g = getGraphics();
        GUI myGUI = new GUI();
        myGUI.getG(g);
        myGUI.start();
        //makes color for each ball
        Color yellow = new Color(238,238,37);
        Color blue= new Color(20,20,200);
        Color red= new Color(255,50,25);
        Color purple = new Color(60,20,100);
        Color orange= new Color(255,200,30);
        Color green = new Color(2,141,2);
        Color darkRed = new Color(141,2,2);
        Color black = new Color (0,0,0);   
        Color[] colors = {yellow, blue, red, purple, orange, green, darkRed, black};
        //changes colors of each ball
        for(int i2 =0;i2<15;i2++)
        {

            b[i2].setBallCol(colors[i2%8]);
        }
        b[15].setBallCol(Color.white);
        //sets to initial position

        b[0].changeP(84.4,28.2);
        b[1].changeP(87.5,27);
        b[2].changeP(87.5,30.1);
        b[3].changeP(90.5,25.9);
        b[4].changeP(90.5,28.7);
        b[5].changeP(90.5,31.8);
        b[6].changeP(94.7,24);
        b[7].changeP(94.7,27);
        b[8].changeP(94.7,30.1);
        b[9].changeP(94.7,33);
        b[10].changeP(99.4,21.6);
        b[11].changeP(99.4,25.3);
        b[12].changeP(99.4,28.4);
        b[13].changeP(99.4,30.9);
        b[14].changeP(99.4,36.8);
        b[15].changeP(30,28.2);
        while (!(Current.isStop()) )
        {

            offGraphics.setColor(TableColor);
            offGraphics.fillRect((int)T.left * DRAWSCALE, (int)T.top * DRAWSCALE, (int)T.width * DRAWSCALE, (int)T.height * DRAWSCALE);
            offGraphics.setColor(Color.black);
            Current.setMoving(false);

            for(i = 0; i < BALLCOUNT; i++)
            {
                pX[i] = b[i].getPx();
                pY[i] = b[i].getPy();
                b[i].move(.05); //move the balls a bit
                b[i].friction();
                if(b[i].getVx() !=0 && b[i].getVy() !=0)
                {
                    Current.setMoving(true);
                }
                Ball.pocket(b[i]);
            }

            for(i = 0; i < BALLCOUNT; i++)
            {
                checkWallBounce(b[i]);
                //deal with any balls that are hitting the sides
            }

            if (BALLCOUNT > 1)
            {
                for(x = 0; x < BALLCOUNT; x++)
                {
                    for(y = x + 1; y < BALLCOUNT; y++) //check each ball with each other ball
                    {
                        if (separation(b[x],b[y]) <= radSum(b[x],b[y])) //if their separationg is small enough to be touching
                        {
                            if((b[x].getNumber()==16||b[y].getNumber()==16)&&hits==0)
                            {
                                hits=b[x].getNumber()+b[y].getNumber()-15;
                            }

                            collide(b[x],b[y]); //COLLISON!!!!
                        }
                    }
                }
            }
             g.setColor(Color.white);
            if(Current.isSolid()!=0)
            {
                if(Current.isPlayerOne())
                {
                    g.drawString("Player One's Turn",30,100);
                }
                else

                {
                    g.drawString("Player Two's Turn",30,100);
                }
                
            }
            else
            {
                g.drawString("Get Some Balls In",30,100);
            }
            

            if(Current.isSolid()==1)
            {
                g.drawString("YOU ARE HITTING: SOLIDS",30,120);
            }
            if(Current.isSolid()==-1)
            {
                g.drawString("YOU ARE HITTING: STRIPES",30,120);
            }
            turn();

            for(i = 0; i < BALLCOUNT; i++)
            {
                b[i].draw(offGraphics); //Draw every ball
            }

            T.drawBorder(offGraphics); //draw the border (balls hitting the side can erase part of it
            try { Thread.sleep(15); } //pause for a little while
            catch (InterruptedException e) { }

            g.setColor(Color.white);
            g.drawImage(offImage, 0, 0, null);
            if(!Current.isMoving())
            {
                g.drawLine(Mx+2*Dx,My+2*Dy,(int)(b[15].getPx()*DRAWSCALE+20),(int)(b[15].getPy()*DRAWSCALE+20));
            }
           

           

            

        }
        g.setColor(Color.black);
        g.fillRect(0,0,1200,600);
        g.setColor(Color.yellow);
        if(Current.isPlayerOne())
        {
            g.drawString("Player 1 wins",200,300);
        }
        else
        {
            g.drawString("Player 2 wins",200,300);
        }

    }
    /**
     * There was no other way. A complicated method that determines the turn system
     * 
     */
    public static void turn()
    {

        if(!Current.isMoving()&&Current.isSolid()!=0&& turnTime==true)
        {
            if(scratchCheck())
            {
                scratch();
            }
            Current.setPlayerOne(!Current.isPlayerOne());
            Current.setSolid(-1*Current.isSolid());
            hits=0;
            turnTime=false;
            if(!scratched&&ballIn)
            {
                 Current.setPlayerOne(!Current.isPlayerOne());
            Current.setSolid(-1*Current.isSolid());
            ballIn=false;
            scratched=false;
                }
        }

    }

    /**
     * checks if it is valid for a player to hit the 8 ball
     * @return as you would expect  
     */
    public static boolean allIn()
    {
        boolean all=true;
        if(Current.isSolid()==-1)
        {
            for(int i = 8; i <= 14; i++)
            {
                if(b[i].getPy()!= 133700)
                {
                    return false;
                }
            }
        }
        else if(Current.isSolid()==-1)
        {
            for(int i = 0; i <= 7; i++)
            {
                if(b[i].getPy()!= 133700)
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
        return true;
    }

    /**
     * checks if the player's move was a scratch
     * @return self explanatory
     */
    public static boolean scratchCheck()
    {
        if((hits==9&&!allIn())||(hits==0&&Current.isSolid()!=0))
        {  
            return true;}
        if(Current.isSolid()==1)
        {
            if(hits>9)
            {

                return true;
            }
        }
        else if (Current.isSolid()==-1)
        {
            if(hits<9)
            { 
                return true;}
        }
        else
        {
        }

        return false;
    }

    public static void scratch()
    {
        scratch=true;
        scratched=true;

    }

    /**
     * Only for testing purposes, but returns a random color when called
     * @return random color with a random rgb code
     */
    public Color randColor()//returns a random color in full color range
    {
        int r,g,b;
        r = (int)(Math.random() * 256);
        g = (int)(Math.random() * 256);
        b = (int)(Math.random() * 256);
        return new Color(r,g,b);
    }

    /**
     * initial graphics, such as the border and the table. Creates the balls first.
     */
    public void init() //start balltimer thread, and initiate all balls
    {   

        //setup applet size
        setSize(WINDOWWIDTH, WINDOWHEIGHT);

        //Setup the invisible image to be used for double buffering, set the offGraphics to be the graphics
        //which draw on on that image
        offImage = createImage((int)(T.width + T.left) * DRAWSCALE,(int) (T.height + T.top) * DRAWSCALE);
        offGraphics = offImage.getGraphics();

        double tmpRad; //stores temporary radius, (we need it to check for ball overlap)
        double tmpX, tmpY; //to store random X and Y coords for a ball before we check if it will overlap
        boolean overlap; //used in loop for ball overlap check
        int x; //counter for overlap loop

        double dX, dY, Rads; //calculating distance between balls

        for(int i = 0; i < BALLCOUNT; i++)
        { //individually initiate every ball and give random position, radius and velocities

            tmpRad = Math.random() * SIZERANGE + MINSIZE;

            //NOTE: code should be added later to break the loop with an error
            //      if there is nowhere the ball can be placed
            do
            {
                overlap = false;

                //random x and y coords inside of the table
                tmpX = Math.random() * T.width + T.left;
                tmpY = Math.random() * T.height + T.top;

                for(x = 0; x < i; x++) //check it with every ball already initiated to find a suitable position
                {
                    dX = b[x].getPx() - tmpX;
                    dY = b[x].getPy() - tmpY;
                    Rads =2 + tmpRad;
                    if (Math.sqrt(dX * dX + dY * dY) < Rads) //if their seperation is less than it would be if they were touching
                        overlap = true; //they are overlaping
                }
            } while (overlap);

            //will delete
            b[i] = new Ball(tmpX, tmpY, i+1);
            //             b[i].setVx( (Math.random() * VELOCITYRANGE + MINVELOCITY) * posNegRand());
            //             b[i].setVy ( (Math.random() * VELOCITYRANGE + MINVELOCITY) * posNegRand());
            b[i].setBallCol(randColor());
        }        // //         Color yellow = new Color(238,238,37);
        // //         Color blue= new Color(20,20,200);
        // //         Color red= new Color(255,50,25);
        // //         Color purple = new Color(60,20,100);
        // //         Color orange= new Color(255,200,30);
        // //         Color green = new Color(2,141,2);
        // //         Color darkRed = new Color(141,2,2);
        // //         Color black = new Color (0,0,0);        // //         Color yellow = new Color(238,238,37);

        //just make sure that the table gets painted
        BallTimer = new Thread(this); //set the ball timer to be a new thread, and start it
        addMouseMotionListener(this);
        addMouseListener(this);

        BallTimer.start(); //after this happens, the Run() method is called and our moving loops start
    }

    /**
     * soley for testing purposes. randomly returns + or - 1, to make velocity random direction
     * @return + or -1
     */
    public int posNegRand()//randomly returns + or - 1, to make velocity random direction
    {
        double a = Math.random() * 2;
        if (a >= 1)
            a = 1;
        if (a < 1)
            a = -1;

        return (int)a;
    }

    /**
     * //checks if the ball is touching any of the sides
     * @param B a ball
     */
    public void checkWallBounce(Ball B)//checks if the ball is touching any of the sides
    {
        double Xleft = B.getPx();
        double Ytop = B.getPy();
        double Ybottom = B.getPy() + 2 * 2; //Py measures the top left coord of ball, so we add two radii
        double Xright = B.getPx() + 2 * 2;  //to get the bottom and right y and x coords.
        if(Ytop!=133700)
        {
            if (Xleft < T.minX())
            {
                B.setPx(T.minX());
                B.wallBounceX();
            }

            if (Ytop < T.minY())
            {
                B.setPy(T.minY());
                B.wallBounceY();
            }

            if (Xright > T.maxX())
            {
                B.setPx(T.maxX() - 2 * 2);
                B.wallBounceX();
            }

            if (Ybottom > T.maxY())
            {
                B.setPy(T.maxY() - 2 * 2);
                B.wallBounceY();
            }
        }
    }

    /**
     * redraws the entire table (not balls)
     * @param g Graphics variable
     */
    public void paint(Graphics g) //redraws the entire table (not balls)
    {
        T.draw(g, TableColor);

    }

    /**
     * gives the distance between two balls 
     * @param B1 a ball
     * @param B2 another ball
     * @return the distance between two balls
     */
    public double separation(Ball B1, Ball B2) //Gives the distance between two balls
    {
        double X1, X2, Y1, Y2;
        X1 = B1.getPx() + 2;
        X2 = B2.getPx() +2;
        Y1 =B1.getPy() +2;
        Y2 = B2.getPy() +2;

        return Math.sqrt((X2 - X1) * (X2 - X1) + (Y2 - Y1) * (Y2 - Y1));

    }

    /**
     * gives the sum of the two radii 
     * @param B1 a ball
     * @param B2 another ball
     * @return Sum of the radii of two balls
     */
    public double radSum(Ball B1, Ball B2) //Sum of the radii of two balls
    {
        return (2+2);

    }

    /**
     * simulates collision with a developed physics engine. considers momentum and energy
     * @param B1 a Ball
     * @param B2 another ball
     */
    public void collide(Ball B1, Ball B2) //Collides two balls and changes their velocities accordingly
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

        X1 = B1.getPx() + 2;
        X2 = B2.getPx() + 2;
        Y1 = B1.getPy() + 2;
        Y2 = B2.getPy() + 2;

        //the first thing that we need to do is move one of the balls to make it a perfect collision on the edges
        //since there should always be at least a very slight overlap from non actual smooth movement
        //(NOTE: This probably should later be replaced by a method inside of the move method to prevent overlap instead of correcting it)
        DxR = (X2 - X1);
        DyR = (Y2 - Y1);

        distance = Math.sqrt(DxR * DxR + DyR * DyR); //gets the ACTUAL distance between the two balls

        //RadSum adds radii, this gives the ideal distance
        Dx = radSum(B1,B2) * DxR / distance; //this is how much we much change X and Y coords to get a perfect collision
        Dy = radSum(B1,B2) * DyR / distance;

        //so that things dont look screwy when we have a fast tiny ball collide with a slow big one
        //we want it to be the small ball that will have its position adjusted

        X2 = (X2 + (Dx - DxR));
        Y2 = (Y2 + (Dy - DyR));

        B2.setPx(X2 - 2);
        B2.setPy( Y2 - 2);

        //Find the x and y distances from the centers of each ball to the collision point
        Dx1 = (2 / radSum(B1,B2)) * (X2 - X1);
        Dx2 = (2 / radSum(B1,B2)) * (X2 - X1);

        Dy1 = (2 / radSum(B1,B2)) * (Y2 - Y1);
        Dy2 = (2 / radSum(B1,B2)) * (Y2 - Y1);

        //calculate the components of velocity of each ball headed towards the collision point and perpendicular to it
        Vs1 = straightVelocity(B1.getVx(), B1.getVy(), Dx1, Dy1, 2);
        Vp1 = perpendicularVelocity(B1.getVx(), B1.getVy(), Dx1, Dy1, 2);

        Vs2 = straightVelocity(B2.getVx(), B2.getVy(), Dx2, Dy2, 2);
        Vp2 = perpendicularVelocity(B2.getVx(), B2.getVy(), Dx2, Dy2, 2);

        //use the formulas in the method to find new straight velocities for each ball
        newVs1 = collisionVelocity(Vs1, Vs2);
        newVs2 = collisionVelocity(Vs2, Vs1);

        //now we get new X and Y velocities for each, using the new straight velocity and the
        //unaffected perpendicular velocity component

        B1.setVx( xVelocity(newVs1, Vp1, Dx1, Dy1, 2));
        B1.setVy( yVelocity(newVs1, Vp1, Dx1, Dy1, 2));

        B2.setVx( xVelocity(newVs2, Vp2, Dx2, Dy2, 2));
        B2.setVy( yVelocity(newVs2, Vp2, Dx2, Dy2, 2));

    }

    /**
     * calculates horizontal component of velocity headed towards the collision point
     * @param Vx horizontal velocity
     * @param Vy vertical velocity
     * @param Dx difference in x distance
     * @param Dy difference in y distance
     * @param R radius of ball (always 2)
     * @return x-direction component of velocity when headed towards the collision point
     */
    public double straightVelocity(double Vx, double Vy, double Dx, double Dy, double R)
    {
        return Vx * Dx / R + Vy * Dy / R;
    } //velocity directed towards collision
    /**
     * calculates vertical component of velocity headed towards the collision point
     * @param Vx horizontal velocity
     * @param Vy vertical velocity
     * @param Dx difference in x distance
     * @param Dy difference in y distance
     * @param R radius of ball (always 2)
     * @return y-direction component of velocity when headed towards the collision point
     */
    public double perpendicularVelocity(double Vx, double Vy, double Dx, double Dy, double R)
    {
        return Vy * Dx / R - Vx * Dy / R;
    }//velocity perpendicular to collision
    /**
     * calculates new x velocity from calculated straight and perpendicular velocity
     * @param Vs value from straightVelocity() method
     * @param Vp value from perpendicularVelocity() method
     * @param Dx  difference in x distance
     * @param Dy difference in y distance
     * @param R radius of ball (always 2)
     * @return new horizontal velocity
     */
    public double xVelocity(double Vs, double Vp, double Dx, double Dy, double R)
    {
        return Vs * Dx / R - Vp * Dy / R;
    } //x velocity from S and P

    /**
     * calculates new y velocity from calculated straight and perpendicular velocity
     * @param Vs value from straightVelocity() method
     * @param Vp value from perpendicularVelocity() method
     * @param Dx  difference in x distance
     * @param Dy difference in y distance
     * @param R radius of ball (always 2)
     * @return new vertical velocity
     */
    public double yVelocity(double Vs, double Vp, double Dx, double Dy, double R)
    {
        return Vs * Dy / R + Vp * Dx / R;
    } //y velocity from S and P
    /**
     * Returns velocity of a ball after collision
     * @param V1 velocity of one ball
     * @param V2 velocity of another ball
     * @return  velocity of a ball after collision
     */
    public double collisionVelocity(double V1, double V2) //Returns velocity of a ball after collision
    {
        return V1 * (MASS-MASS) / (MASS+MASS) + V2 * (2 * MASS) / (MASS + MASS);
    }
}