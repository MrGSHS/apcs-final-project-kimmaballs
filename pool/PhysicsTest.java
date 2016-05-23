/**
 * tests if Physics class works
 * 
 * @author Taehwan Kim and Michael Ma
 */
public class PhysicsTest
{
    public static void main(String [] args)
    {
        Physics p=new Physics();
        Ball a= new Ball(1,100,0);
        Ball b= new Ball(2,0,20);
        System.out.println("X COORDINATE OF A: " + a.getX());
        System.out.println("Y COORDINATE OF A: " + a.getY());
        System.out.println("X COORDINATE OF B: " + b.getX());
        System.out.println("Y COORDINATE OF B: " + b.getY());
        System.out.println("in contact? - " + p.inContact(a,b));
        System.out.println();

        a.setX(0);
        System.out.println("X COORDINATE OF A: " + a.getX());
        System.out.println("Y COORDINATE OF A: " + a.getY());
        System.out.println("X COORDINATE OF B: " + b.getX());
        System.out.println("Y COORDINATE OF B: " + b.getY());
        System.out.println("in contact? - " + p.inContact(a,b));
        System.out.println();

        a.setXV(10);
        a.setYV(10);
        System.out.println("X VELOCITY OF A: " + a.getXV());
        System.out.println("Y VELOCITY OF A: " + a.getYV());
        System.out.println("X VELOCITY OF B: " + b.getXV());
        System.out.println("Y VELOCITY OF B: " + b.getYV());
        System.out.println();

        p.newVelocity(a,b);
        System.out.println("NEW X VELOCITY OF A: " + a.getXV());
        System.out.println("NEW Y VELOCITY OF A: " + a.getYV());
        System.out.println("NEW X VELOCITY OF B: " + b.getXV());
        System.out.println("NEW Y VELOCITY OF B: " + b.getYV());
    }
}