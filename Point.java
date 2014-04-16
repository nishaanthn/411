import java.lang.Math;
import java.io.*;

public class Point {

	public double x,y;
	public double polar_angle;

	public Point() { x = 0.0; y = 0.0; polar_angle = 0.0; }

	public Point(float a, float b) { x = a; y = b; polar_angle = 0.0; }

	public Point(double a, double b) { x = a; y = b; polar_angle = 0.0; }

	public Point (Point a) { this.x = a.x; this.y = a.y; polar_angle = 0.0; }

    public String toString()
    {
        return  ("( "+x+", "+y+")");
    }
	
	static public int return_lowest_point_index(Point[] points)
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
		return index_of_lowest;
	}
	
	//Comparable implementation
	public int compareTo(Point a)
	{
		if (polar_angle < a.polar_angle) return -1;
		else if (polar_angle > a.polar_angle) return 1;
		else return 0;
	}
	
	static public double calculate_polar_angle(Point a, Point b)
	{
		double angle, delta_x, delta_y;
		// calculate the slope of the line between the two points
		delta_x = b.x - a.x;
		delta_y = b.y - a.y;
		
		// calculate counter-clockwise angle between the line and the horizontal axis, in radians, and then convert to degrees
		angle = (Math.atan2(delta_y, delta_x)) * 180/Math.PI;
		
		return angle;
	}
}

