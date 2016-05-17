import java.util.*;
public class BallTest
{
    public static void main (String [] args)
    {
        Scanner sc= new Scanner(System.in);
        Ball a= new Ball(1,0,0);
        System.out.println("NUMBER: " + a.getNumber());
        System.out.println();
        //gets and changes x and y VELOCITYs
        System.out.println("X COORDINATE: " + a.getX());
        System.out.println("SET NEW X COORDINATE: ");
        a.setX(sc.nextDouble());
        System.out.println("NEW X COORDINATE: " + a.getX());
        System.out.println("Y COORDINATE: " + a.getY());
        System.out.println("SET NEW Y COORDINATE: ");
        a.setY(sc.nextDouble());
        System.out.println("NEW Y COORDINATE: " + a.getY());
        System.out.println();
        //gets and changes x and y velocities
        System.out.println("X VELOCITY: " + a.getXV());
        System.out.println("SET NEW X VELOCITY: ");
        a.setXV(sc.nextDouble());
        System.out.println("NEW X VELOCITY: " + a.getXV());
        System.out.println("Y VELOCITY: " + a.getYV());
        System.out.println("SET NEW Y VELOCITY: ");
        a.setYV(sc.nextDouble());
        System.out.println("NEW Y VELOCITY: " + a.getYV());
        System.out.println();
        //prints position after chosen amount of seconds.
        System.out.println("X COORDINATE: " + a.getX());
        System.out.println("Y COORDINATE: " + a.getY());
        System.out.println("HOW MANY SECONDS: ");
        int x=sc.nextInt();
        int i=0;
        while(i<x)
        {
            a.move();
            i++;
        }
        System.out.println("NEW X COORDINATE: " + a.getX());
        System.out.println("NEW Y COORDINATE: " + a.getY());
    }
}