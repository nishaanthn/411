import java.io.*;
import java.util.Scanner;
import java.util.Arrays;


/** Class point **/
class Point
{
    public float x, y;

    public void Point() { x = 0; y = 0;}

    public String toString()
    {
    	return ("( "+x+", "+y+")");
    }

}
 
/** Class Jarvis **/
public class Jarvis
{
    private boolean check_turn(Point p, Point q, Point r)
    {
        float val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
         if (val >= 0)
             return false;
		return true;

    }

    public void convexHull(Point[] points)

    {
        int n = points.length;
        /** if less than 3 points return **/        
        if (n < 3) 
            return;     

        int[] next = new int[n];
        Arrays.fill(next, -1);
        /** find the leftmost point **/
        int leftMost = 0;
        for (int i = 1; i < n; i++)

            if (points[i].x < points[leftMost].x)

                leftMost = i;

        int p = leftMost, q;

        /** iterate till p becomes leftMost **/
        do
	    {
            /** wrapping **/
            q = (p + 1) % n;
            for (int i = 0; i < n; i++)
              if (check_turn(points[p], points[i], points[q]))
                 q = i;

            next[p] = q;  
            p = q; 

        } while (p != leftMost);

        // Display result 
        display(points, next);
    }

    public void display(Point[] points, int[] next)
    {
        System.out.println("\nConvex Hull points : ");
        for (int i = 0; i < next.length; i++)
            if (next[i] != -1)
               System.out.println(points[i]);

    }

    /** Main function **/

    public static void main (String[] args) 
    {
	    try
	    {
	        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
	        Point[] points = new Point[Integer.parseInt(args[1])];
	        String line = null;
	        String delims = "[ ]+";
	        String[] numbers;
	        float x,y;
	        int i = 0;
	        while((line = reader.readLine()) != null)
	        {
	        	numbers = line.split(delims);
	        	x = Float.parseFloat(numbers[0]);
	        	y = Float.parseFloat(numbers[1]);
	        	points[i] = new Point();
	        	points[i].x = x;
	        	points[i].y = y;
	        	i++;
	        }
		    Jarvis j = new Jarvis();
            long startTime = System.nanoTime();
	        j.convexHull(points);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            System.out.println("DURATION: "+duration);

	    } catch(IOException e) {}
                
    }
}
