import java.io.*;
import java.lang.Math;
import java.util.*;

/** Class point **/
class Point implements Comparable<Point>
{
    public float x, y;

    public float polar_angle;

    public void Point() { x = 0; y = 0; polar_angle = 0;}

    public String toString()
    {
    	return ("( "+x+", "+y+")");
    }

    

    //Comparable implementation
	public int compareTo(Point a)
	{
		if (polar_angle < a.polar_angle) return -1;
		else if (polar_angle > a.polar_angle) return 1;
		else return 0;
	}

}

public class Graham {

	public float calculate_polar_angle(Point a, Point b)
	{
		double angle, delta_x, delta_y;
		// calculate the slope of the line between the two points
		delta_x = b.x - a.x;
		delta_y = b.y - a.y;
		
		// calculate counter-clockwise angle between the line and the horizontal axis, in radians, and then convert to degrees
		angle = (Math.atan2(delta_y, delta_x)) * 180/Math.PI;
		
		return (float) angle;
	}
    
    private float ccw(Point p, Point q, Point r)
    {
        float val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        System.out.println(val);
        return val;
    }
	
	public Point[] point_swap(Point[] pts, int a, int b)
	{
		Point temp = new Point();
		temp = pts[a];
		pts[a] = pts[b];
		pts[b] = temp;		

		return pts;
	}

	public int return_lowest_point_index(Point[] points)
	{
		// Node to return
		Point lowest = points[0];
		int index_of_lowest = 0;
		
		//finding the lowest point among the point list
		for (int i = 0; i < points.length; i ++)
		{
			if (points[i].y < lowest.y)
			{
				lowest = points[i];
				index_of_lowest = i;
			}
		}
		//return the lowest point's index
		//System.out.println(points[index_of_lowest]);
		return index_of_lowest;
	}
	
	public Point[] sort_polar_angle(Point[] points)
	{
				
		//find index of p0
		int index_of_lowest = return_lowest_point_index(points);

		System.out.println(points[index_of_lowest]);
		
		//put p0 in the first index of new_points
		points = point_swap(points, 0, index_of_lowest);

		display(points);
		
		//keep track of minimum polar angle and calculate polar angle for each point
		float min_angle = calculate_polar_angle(points[0],points[1]);
		for (int i = 1; i < points.length; i++)
		{
			points[i].polar_angle = calculate_polar_angle(points[0], points[i]);
			if (points[i].polar_angle < min_angle)
			{
				min_angle = points[i].polar_angle;
			}
		}
		
		// make sure p0 is always the first element and sort the rest
		points[0].polar_angle = min_angle - 1;		
		System.out.println(points);
		Arrays.sort(points);
		
		return points;
	}
	
	public void grahams_scan(Point[] pts)
	{
        Point[] points = new Point[pts.length];
		Stack<Point> hull_stack = new Stack<Point>();

		// local copy
		for (int i = 0; i < pts.length; i++)
			points[i] = pts[i];
		
		//sort the points by polar angle with the bottom most point
		points = sort_polar_angle(points);

		display(points);
		
		//init
		hull_stack.push(points[0]);

		// push first non-p0
		int t1, t;
		for (t1 = 1; t1 < points.length; t1++)
		{
			if(!(points[t1].x == points[0].x && points[t1].y == points[0].y))
				break;
		}
		hull_stack.push(points[t1]);

		// push first non-collinear		
		for (t = t1+1; t < points.length; t++)
		{
			if (ccw(points[0], points[t1], points[t]) != 0) break;
		}
		hull_stack.push(points[t]);

		System.out.println(hull_stack);
        for (int i = t+1; i < points.length; i++)
        {
            Point top = hull_stack.pop();
            // TODO verify accuracy
            while(ccw(hull_stack.peek(), top, points[i]) > 0)
            {
            	System.out.println("Popping");
            	top = hull_stack.pop();
            }
            hull_stack.push(top);
            hull_stack.push(points[i]);
        }

        System.out.println(hull_stack);

	}

	public void display(Point[] points)
    {
        System.out.println("\n points : ");
        for (int i = 0; i < points.length; i++)
            System.out.println(points[i]);

    }	
	
	public static void main(String[] args)
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
		    Graham g = new Graham();
		    long startTime = System.nanoTime();
        	g.grahams_scan(points);
        	long endTime = System.nanoTime();
        	long duration = endTime - startTime;
        	System.out.println("DURATION: "+duration);

	    } catch(IOException e) {}
		
				
	}
	

}
