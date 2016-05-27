import java.awt.*;
import java.applet.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.*;
/**
 * GUI of the project
 * @author Taehwan Kim and Michael Ma
 */
class GUI extends Thread {
    /**
     * main Graphics variable in order to draw GUI
     */
    Graphics g;
    /**
     * empty, default constructor
     */
    public GUI()
    {

    }

    /**
     * presents each turn for player
     */
    public void run(){
//         Color TableColor = new Color(10,108,3);
//         while(!Current.isStop())
//         {
//             g.setColor(Color.white);
//             if(Current.isPlayerOne())
//             {
//                 g.drawString("Player One's Turn",30,100);
//             }
//             else 
//             {
//                 g.drawString("Player Two's Turn",30,100);
//             }
//             Current.prints(g);
//             g.drawString(java.lang.Boolean.toString(Billiards.scratchCheck()),30,150);
//             
//         }
    }
    /**
     * gets Graphics in order to draw for the GUI
     * @param G Graphics used to draw
     */
    public void getG(Graphics G)
    {
        g=G;
    }
}