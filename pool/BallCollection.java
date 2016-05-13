/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

import java.awt.List;
import java.util.ArrayList;

/**
 * Represents all the ball on the table (cue ball and colored balls)
 * @author tkim8
 */
public class BallCollection
{
   
    ArrayList<Ball> balls= new ArrayList<>(); 
    public BallCollection()
    {
        
        Ball cue= new Ball(0,20,100);
        balls.add(cue);
        Ball one=new Ball(1,100,100);
        balls.add(one);
        Ball two=new Ball(2,120,80);
        balls.add(two);
        Ball three=new Ball(3,120,120);
        balls.add(three);
        Ball four=new Ball(4,140, 60);
        balls.add(four);
        Ball five=new Ball(5,140, 100);
        balls.add(five);
        Ball six=new Ball(6, 140, 140);
        balls.add(six);
        Ball seven=new Ball(7,160,40);
        balls.add(seven);
        Ball eight=new Ball(8,160,80);
        balls.add(eight);
        Ball nine=new Ball(9,160,120);
        balls.add(nine);
        Ball ten=new Ball(10,160,160);
        balls.add(ten);
        Ball eleven=new Ball(11,180,20);
        balls.add(eleven);
        Ball twelve=new Ball(12,180,60);
        balls.add(twelve);
        Ball thirteen=new Ball(13,180,100);
        balls.add(thirteen);
        Ball fourteen=new Ball(14,180,140);
        balls.add(fourteen);
        Ball fifteen=new Ball(15,180,160);
        balls.add(fifteen);
    
    }
    //returns a ball from BallCollection
    public Ball getBalls(int index)
    {
        return balls.get(index);
    }
    //removes ball if went into a pocket
    public void inHole(int index)
    {
        balls.remove(index);
    }
}