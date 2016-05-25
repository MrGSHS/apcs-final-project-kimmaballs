import java.awt.*;
import java.applet.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.*;
class BilliardTable
{
    double Left, Top, Width, Height;

    public BilliardTable(double L, double T, double W, double H)
    {
        Left = L;
        Top = T;
        Width = W;
        Height = H;
    }
// 
//     public static void Turn(Graphics g)
//     {
//         Graphics2D g2 = (Graphics2D)g;
// 
//         Font font = new Font("Serif", Font.PLAIN, 20);
//         g2.setFont(font);
// 
//         if(globals.playerOne)
//         {
//             g2.drawString("PLAYER ONE TURN", 40, 120); 
//         }
//         else
//         {
//             g2.drawString("PLAYER TWO TURN", 40, 120); 
//         }
// 
//     }

    public double minX()//minimum X value for a ball
    {
        return Left;
    }

    public double minY()//minimum Y value for a ball
    {
        return Top;
    }

    public double maxX()//max X value for a ball (NOTE: ball coords not measured on right side, must add radius
    {
        return Left + Width;
    }

    public double maxY()//max Y value, see above
    {
        return Top + Height;
    }

    public void Draw(Graphics g, Color TableColor)//draw the table and the border
    {
        g.setColor(TableColor); //draw all the color part of table
        g.fillRect((int)(Left * globals.DrawScale), (int)(Top * globals.DrawScale), (int)(Width * globals.DrawScale), (int)(Height * globals.DrawScale));
        g.setColor(Color.white); 
        DrawBorder(g);

    }

    public void DrawBorder(Graphics g)//draws the border of the table
    {
        g.setColor(new Color(255,255,255)); //white for now, can change easy
        g.drawRect((int)(Left * globals.DrawScale), (int)(Top * globals.DrawScale), (int)(Width * globals.DrawScale), (int)(Height * globals.DrawScale));
                g.setColor(Color.white);
        g.fillArc(-25,-25,50,50,270,360);
        g.fillArc(575,-25,50,50,180,360);
        g.fillArc(1175,-25,50,50,180,270);
        g.fillArc(-25,575,50,50,0,90);
        g.fillArc(575,575,50,50,0,180);
        g.fillArc(1175,575,50,50,90,180);
    }
}