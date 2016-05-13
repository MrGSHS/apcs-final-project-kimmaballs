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
    /*
     * does not construct anything. Has default constructor.
     */
    public Physics()
    {

    }

    /**
     * 
     * @param a
     * @param b
     * @return true if the distance between their centers is less than or equal to the sum of their radii
     */

    public boolean inContact(Ball a, Ball b)
    {

        int xa = a.getX() + 20;
        int xb = b.getX() + 20;
        int ya = a.getY() + 20;
        int yb = b.getY() + 20;
        if(Math.sqrt(((xa - xb) * (xa - xb)) + ((ya - yb) * (ya - yb)))<=40)
        {
            return true;
        }
        return false;
    }
    //should return new velocity after a ball has come in contact with another
    public int newVelocity(Ball a, Ball b)
    {
        int xa = a.getX() + 20;
        int xb = b.getX() + 20;
        int ya = a.getY() + 20;
        int yb = b.getY() + 20;
        if(inContact(a,b))
        {
            
        }
    }
}
