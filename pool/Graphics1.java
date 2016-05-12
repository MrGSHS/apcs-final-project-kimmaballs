/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.Line2D;
import javax.swing.*;

/**
 * this is a program to test MouseMotionListener
 * @author mma8
 */
public class Graphics1 extends JPanel {
     // instance variables - replace the example below with your own
     private int x;
     private Point lastPoint;
    /**
     * Constructor for objects of class ball drawer
     */
    public Graphics1()
    {
        
        // initialise instance variables
        x = 0;
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                Graphics g = getGraphics();
                 g.setColor (Color.gray);
                 Graphics2D g2 = (Graphics2D) g;
                 g2.setStroke(new BasicStroke(7));
                g.clearRect(0, 0, getWidth(), getHeight());
                lastPoint = new Point(e.getX(), e.getY());
                g2.draw(new Line2D.Float(lastPoint.x, lastPoint.y, 80, 90));
                lastPoint = new Point(e.getX(), e.getY());               
                g.dispose();
            }
        });
    }
    //runs the program that we made as a test
    public static void main(String[] args) {
        JFrame frame = new JFrame("Makes huge balls");
        frame.getContentPane().add(new Graphics1(), BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setVisible(true);
    }
    
}
