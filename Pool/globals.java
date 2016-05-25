import java.awt.*;
import java.applet.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.*;
class globals //values which may be needed by several classes
{
    public static int DrawScale = 10;
    public static double MinVelocity = 0;
    public static double VelocityRange = 5;
    public static double MinSize = 2;
    public static double SizeRange = 2;
    public static final int BallCount = 16;
    public static final int WindowWidth = 1200; //size of applet and table
    public static final int WindowHeight = 600;
    public static boolean ballsMoving = false;
    public static boolean playerOne=true;
    public static boolean stop=false;
    public static double MaxV()
    {
        return MinVelocity + VelocityRange;
    }
}