import java.util.Arrays;

public class Graham
{
    public Stack<Point> hull = new Stack<Point>();

    public Graham(Point pts)
    {
        Point points = new Point(pts.length);
        for(int i = 0; i < pts.length; i++)
        {
            points[i] = pts[i];
        }


    }
