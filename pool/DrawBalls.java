/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
import java.awt.Color;
import java.awt.Graphics;
/**
 *
 * @author tkim8
 */
//attempting to draw all the balls and assign each circle to a Ball object
public class DrawBalls {
    public void DrawBalls(Graphics g)
    {
        BallCollection balls=new BallCollection();
        for(int i = 0; i<16;i++)
        {        
          g.fillOval(balls.getBalls(i).getX(), balls.getBalls(i).getY(),25,25 );
          
        }
        
    }
    public static void main(String[] args)
    {
        DrawBalls balling= new DrawBalls();
        
    }
}
