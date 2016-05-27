import java.awt.*;
import java.applet.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.*;
/**
 * Draws the Billiard Table
 * @author Taehwan Kim and Michael Ma
 */
class BilliardTable
{
   /**
    * variable to be the leftmost point of the billiard table
    */
    double left;
    /**
    * variable to be the topmost point of the billiard table
    */
    double top;
    /**
    * variable to be the width of the billiard table
    */
    double width;
    /**
    * variable to be the height of the billiard table
    */
    double height; 
    private static final int DRAWSCALE = 10;
    private static final double MINVELOCITY = 0;
    private static final double VELOCITYRANGE = 5;
    private static final double MINSIZE = 2;
    private static final double SIZERANGE = 2;
    private static final int BALLCOUNT = 16;
    private static final int WINDOWWIDTH = 1200; //size of applet and table
    private static final int WINDOWHEIGHT = 600;
    /**
     * constructs the table with given size
     * @param L leftmost coordinate
     * @param T top coordinate
     * @param W width of table
     * @param H height of table
     */
    public BilliardTable(double L, double T, double W, double H)
    {
        left = L;
        top = T;
        width = W;
        height = H;
    }

    /**
     * gets minimum x value for a ball
     * @return leftmost coordinate, which is the minimum x value for a ball
     */
    public double minX()//minimum X value for a ball
    {
        return left;
    }

    /**
     * gets minimum y value for a ball
     * @return topmost coordinate, which is the minimum y value for a ball
     */
    public double minY()//minimum Y value for a ball
    {
        return top;
    }

    /**
     * gets max X value for a ball (NOTE: ball coords not measured on right side, must add radius)
     * @return rightmost coordinate, which is max x value
     */
    public double maxX()//max X value for a ball (NOTE: ball coords not measured on right side, must add radius
    {
        return left + width;
    }
    
    /**
     * gets max y value for a ball (NOTE: ball coords not measured on bottom side, must add radius)
     * @return bottommost coordinate, which is max y value
     */
    public double maxY()//max Y value, see above
    {
        return top + height;
    }
/**
 * draws the table 
 * @param g Graphics variable to draw table
 * @param TableColor color of Billiard table
 */
    public void draw(Graphics g, Color TableColor)//draw the table and the border
    {
        g.setColor(TableColor); //draw all the color part of table
        g.fillRect((int)(left * DRAWSCALE), (int)(top * DRAWSCALE), (int)(width * DRAWSCALE), (int)(height * DRAWSCALE));
        g.setColor(Color.white); 
        drawBorder(g);

    }
/**
 * draws the border
 * @param g Graphics variable to draw table and border
 */
    public void drawBorder(Graphics g)//draws the border of the table
    {
        g.setColor(new Color(255,255,255)); //white for now, can change easy
        g.drawRect((int)(left * DRAWSCALE), (int)(top * DRAWSCALE), (int)(width * DRAWSCALE), (int)(height * DRAWSCALE));
        g.setColor(Color.white);
        g.fillArc(-25,-25,50,50,270,360);
        g.fillArc(575,-25,50,50,180,360);
        g.fillArc(1175,-25,50,50,180,270);
        g.fillArc(-25,575,50,50,0,90);
        g.fillArc(575,575,50,50,0,180);
        g.fillArc(1175,575,50,50,90,180);
    }
}