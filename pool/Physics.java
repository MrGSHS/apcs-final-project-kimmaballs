/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class determines the physics that the balls will use. For example, how each ball will collide with another and friction.
 * @author Taehwan Kim and Michael Ma
 */
public class Physics 
{
    /**
     * a constant that represents the radius of every ball
     */
    
    final int RADIUS = 20;
    /**
     * does not construct anything. has default constructor.
     */
    public Physics()
    {

    }

    /**
     * returns if a ball is in contact with another
     * @param a pool ball
     * @param b another pool ball
     * @return true if the distance between their centers is less than or equal to the sum of their radii
     */

    public boolean inContact(Ball a, Ball b)
    {

        double xa = a.getX() + RADIUS;
        double xb = b.getX() + RADIUS;
        double ya = a.getY() + RADIUS;
        double yb = b.getY() + RADIUS;
        if(Math.sqrt(((xa - xb) * (xa - xb)) + ((ya - yb) * (ya - yb)))<=40)
        {
            return true;
        }
        return false;
    }
    //should set new velocities of Balls a and b after a  has come in contact with b
    /**
     * sets new velocities of Balls a and b after a  has come in contact with b
     * @param a pool ball
     * @param b another pool ball
     */
    public void newVelocity(Ball a, Ball b)
    {
        double xa = a.getX() + RADIUS;
        double xb = b.getX() + RADIUS;
        double ya = a.getY() + RADIUS;
        double yb = b.getY() + RADIUS;
        double vHypo=Math.sqrt(((a.getXV()) * (a.getXV())) + ((a.getYV()) * (a.getYV())));
        double hypo=Math.sqrt(((xa - xb) * (xa - xb)) + ((ya - yb) * (ya - yb)));
        if(inContact(a,b))
        {
            double theta= Math.acos((xb-xa)/hypo);
            b.setXV(vHypo*Math.cos(theta));
            b.setYV(vHypo*Math.sin(theta));
            a.setXV(0);
            a.setYV(0);
        }
    }
    
    public void hitVertWall(Ball a)
    {
        a.setXV(-a.getXV());
    }

    public void hitHorWall(Ball a)
    {
        a.setYV(-a.getYV());
    }
}