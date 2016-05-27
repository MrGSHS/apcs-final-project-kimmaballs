
/**
 * Write a description of class BallTest here.
 * 
 * @author Taehwan Kim and Michael Ma
 * @version 5-27-16
 */
public class BallTest
{
    public static void main(String[] args)
    {
        Ball a=new Ball(0,0,1);
        System.out.println(a.getNumber());
        a.addVelocity(100,100);
        System.out.println("x velocity: " + a.getVx());
        System.out.println("y velocity: " + a.getVy());
        System.out.println(" x position: " + a.getPx());
        System.out.println(" y position: " + a.getPy());
        a.setVx(10);
        a.setVy(10);
        a.setPx(40);
        a.setPy(40);
        System.out.println("new x velocity: " + a.getVx());
        System.out.println("new y velocity: " + a.getVy());
        System.out.println("new x position: " + a.getPx());
        System.out.println("new y position: " + a.getPy());
        for(int i=0;i<20;i++)
        {
            a.move(1);
            a.friction();
        }
        System.out.println("new x velocity: " + a.getVx());
        System.out.println("new y velocity: " + a.getVy());
    }
}
