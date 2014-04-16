import java.lang.Math;
import java.util.*;

public class Graham {
    
    public boolean left_turn(Point a, Point b, Point c)
    {
        return true;
    }
	
	public static void point_swap(Point a, Point b)
	{
		Point temp = new Point();
		temp = a;
		a = b;
		b = temp;		
	}
	
	public static Point[] sort_polar_angle(Point[] points)
	{
				
		//find index of p0
		int index_of_lowest = Point.return_lowest_point_index(points);
		
		//put p0 in the first index of new_points
		point_swap(points[0],points[index_of_lowest]);
		
		//keep track of minimum polar angle and calculate polar angle for each point
		double min_angle = Point.calculate_polar_angle(points[0],points[1]);
		for (int i = 1; i < points.length; i++)
		{
			points[i].polar_angle = Point.calculate_polar_angle(points[0], points[i]);
			if (points[i].polar_angle < min_angle)
			{
				min_angle = points[i].polar_angle;
			}
		}
		
		// make sure p0 is always the first element and sort the rest
		points[0].polar_angle = min_angle - 1.00;		
		Arrays.sort(points);
		
		return points;
	}
	
	public static void grahams_scan(Point[] pts)
	{
        Point[] points = new Point[pts.length];
		Stack<Point> hull_stack = new Stack<Point>();
		
		//sort the points by polar angle with the bottom most point
		points = sort_polar_angle(points);
		
		//init
		hull_stack.push(points[0]);
		hull_stack.push(points[1]);
		hull_stack.push(points[2]);

        for (int i = 3; i < points.length; i++)
        {
            System.out.println(i);
        }

	}
	
	
	public static void main(String[] args)
	{
		Point points[] = new Point[5];
				
		//populating with random numbers for the sake of it
		for (int i = 0; i < 5; i++)
		{
			points[i] = new Point(Math.random() * 100, Math.random() * 100);
			System.out.println("(x,y):  (" + points[i].x + ", " + points[i].y + ")");

		}
        grahams_scan(points);
				
	}
	

}
