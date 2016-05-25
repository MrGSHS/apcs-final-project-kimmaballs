import java.awt.*;
import java.applet.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.*;
class GUI extends Thread {
    Graphics g;

    public void run(){
        while(!globals.stop)
        {
            if(globals.playerOne)
            {
                g.drawString("Player One's Turn",30,100);
            }
            else 
            {
                g.drawString("Player Two's Turn",30,100);
            }
        }

    }

    public void getG(Graphics G)
    {
        g=G;
    }
}