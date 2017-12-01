import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import map.Line;
import map.Map;
import map.Point;

public class Launch
{
	public static final int REAL_MODE = 1;
	public static final int VIRTUAL_MODE = 2;

	private int mode;
	private Point upperLeft;
	private Point lowerRight;
	private int quantityK;

	private Point[] allPoint = null;
	private Point beforeTruePoint = null;
	private Point nowTruePoint = null;

	private int runCount = 0;

	public static void main(String[] arg) throws IOException
	{
//		Launch launch = new Launch();
//
//		launch.parameterPassing(arg);
//
//		Algorithm algorithm = new Algorithm(launch.mode, new Map(launch.upperLeft, launch.lowerRight),
//				launch.quantityK);
//
//		while (true)
//		{
//			Line trueLine = null;
//			if (launch.beforeTruePoint == null)
//			{
//				launch.initialization();
//			}
//			else
//			{
//				launch.nowTruePoint = launch.getNextTruePoint();
//				trueLine = new Line(launch.beforeTruePoint, launch.nowTruePoint);
//				launch.allPoint = algorithm.createDummies(launch.allPoint, trueLine);
//			}
//
//			launch.outputFile(launch.allPoint, trueLine);
//
//			System.out.println("Press any key to next iter");
//			Scanner input = new Scanner(System.in);
//			input.next();
//			input.close();
//
//			launch.beforeTruePoint = launch.nowTruePoint;
//		}
		System.out.print("...");
	}

	private void parameterPassing(String[] arg)
	{
		mode = Integer.parseInt(arg[0]);
		upperLeft = new Point(Double.parseDouble(arg[1]), Double.parseDouble(arg[2]));
		lowerRight = new Point(Double.parseDouble(arg[3]), Double.parseDouble(arg[4]));
		quantityK = Integer.parseInt(arg[5]);
	}

	private void initialization()
	{
		beforeTruePoint = initTruePoint();
		allPoint = initDummies();
	}

	private Point initTruePoint()
	{
		Point point = null;
		// need implementation
		return point;
	}

	private Point[] initDummies()
	{
		Point[] pointArray = null;
		// need implementation
		return pointArray;
	}

	private Point getNextTruePoint()
	{
		Point point = null;
		// need implementation
		return point;
	}

	private void outputFile(Point[] pointArray, Line trueLine) throws IOException
	{
		FileWriter writer = new FileWriter("dummies.txt", true);
		writer.write(String.format("\n======= the %d iter ======= ", runCount));
		for (Point point : pointArray)
		{
			writer.write(String.format("%s\n", point.toString()));
		}
		if (trueLine == null)
		{
			writer.write(String.format("\n%s", "null"));
		}
		else
		{
			writer.write(String.format("\n%s", trueLine.toString()));
		}
		writer.close();
		runCount++;
	}
}
