import java.io.FileWriter;
import java.io.BufferedWriter;
import java.lang.Math;
import java.io.*;

public class Populate {

	public static void main (String[] args)
	{
		try
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(args[0]));
			for (int i = 0; i < Integer.parseInt(args[1]); i++)
			{
				writer.write(Math.random() * 100 +" "+ Math.random() * 100+"\n");

			}
			writer.close();
		} catch (IOException e) {}

	}
}