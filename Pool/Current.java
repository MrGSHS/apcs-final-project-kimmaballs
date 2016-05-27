import java.awt.*;
import java.applet.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.*;
/**
 * global values that are important each turn and to determine if a turn is over or not
 * @author Taehwan Kim
 */
public class Current //values which may be needed by several classes
{
    private static final int DRAWSCALE = 10;
    private static final double MINVELOCITY = 0;
    private static final double VELOCITYRANGE = 5;
    private static final double MINSIZE = 2;
    private static final double SIZERANGE = 2;
    private static final int BALLCOUNT = 16;
    private static final int WINDOWWIDTH = 1200; //size of applet and table
    private static final int WINDOWHEIGHT = 600;
    private static boolean ballsMoving = false;
    private static boolean playerOne=true;
    private static boolean stop=false;
    private static int solid=0;
    private static boolean itWentIn=false;

    /**
     * default, empty constructor
     */
    public Current()
    {

    }

    /**
     * returns the max velocity of a ball considering its velocity range
     * @return Max velocity
     */
    public static double MaxV()
    {
        return MINVELOCITY + VELOCITYRANGE;
    }

    /**
     * returns if a ball is in motion or not
     * @return true if a ball is stopped
     */
    public static boolean isStop()
    {
        return stop;
    }

    /**
     * sets if ball is stopped or not
     * @param b boolean value to change stop variable
     */
    public static void setStop(boolean b)
    {
        stop=b;
    }

    /**
     * returns true if current player is player 1
     * @return true if currently the player playing is player one
     */
    public static boolean isPlayerOne()
    {
        return playerOne;
    }

    /**
     * sets if the current player is playerOne
     * @param b sets the player to be player One by affectin playerOne variable
     */

    public static void setPlayerOne(boolean b)
    {
        playerOne=b;
    }

    /**
     * returns true if the ball is in motion
     * @return true if the ball is moving
     */
    public static boolean isMoving()
    {
        return ballsMoving;
    }

    /**
     * sets if the ball is in motion
     * @param b value to set if the ball is in motion
     */
    public static void setMoving(boolean b)
    {
        ballsMoving=b;
    }

    /**
     * sees if the ball that went in is solid in order to determine teams
     */
    public static void setSolid(int b)
    {
        solid=b;
    }

    /**
     * returns 1 if solid 0 if no team -1 if striped
     * @return gets number to determine teams
     */
    public static int isSolid()
    {
        return solid;
    }
    /**
     * changes if a ball went into a hole
     */
    public static void wentIn(boolean a)
    {
        itWentIn=a;
    }
    /**
     * returns if a ball went in a pocket
     * @return true if ball went in
     */
    public static boolean getWentIn()
    {
        return itWentIn;
    }
    /**
     * prints team name

     */
    public static void prints(Graphics g)
    {
//         if(solid==1)
//         {
//             g.drawString("SOLID",30,120);
//         }
//         if(solid==-1)
//         {
//             g.drawString("STRIPE",30,120);
//         }

    }
}