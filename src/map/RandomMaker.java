package map;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RandomMaker implements RandomPoint
{
	double x, y;
	int m = 20, size = 80;
	Point initPoint, endPoint;
	public double[][] probabilities = new double[size][size];

	@Override
	public void loadMap(Map map) throws IOException
	{
		// TODO Auto-generated method stub
		initPoint = map.getUpperLeft();
		endPoint = map.getLowerRight();

		// initPoint = new Point(-123.019869,36.929451);
		// endPoint = new Point(-121.287728,38.275117);

		x = endPoint.getX() - initPoint.getX();
		y = endPoint.getY() - initPoint.getY();

		x = x / 80;
		y = y / 80;

		FileReader fr = new FileReader("output.txt");
		BufferedReader br = new BufferedReader(fr);
		int q = 0;
		while (br.ready())
		{
			String line = br.readLine();
			String[] split_line = line.split(",");
			String longitude = split_line[0];
			String latitude = split_line[1];
			probabilities[changePointToIndex(Double.parseDouble(longitude), 'x')][changePointToIndex(
					Double.parseDouble(latitude), 'y')]++;
		}
		fr.close();
	}

	@Override
	public Point[] getCandidatesPoint(Point truePoint, int quantityK,int failTime)
	{
		// TODO Auto-generated method stub
		ArrayList<Point> dummyLocation = new ArrayList<>();
		double randonPoint1, randonPoint2;

		int i = 0;
		int[][] set = new int[size][size];
		int setX;
		int setY;

		setX = changePointToIndex(truePoint.getX(), 'x');
		setY = changePointToIndex(truePoint.getY(), 'y');
		set[setX][setY] = 1;

		for (int j = 0; j < size; j++)
		{
			for (int w = 0; w < size; w++)
			{
				if (probabilities[setX][setY] == probabilities[j][w])
				{
					randonPoint1 = Math.random() * x + x * j + initPoint.getX();
					randonPoint2 = Math.random() * y + y * w + initPoint.getY();
					Point point = new Point(randonPoint1, randonPoint2);
					dummyLocation.add(point);
					set[j][w] = 1;
					i++;
				}
			}
		}

		while (i < (8 * Math.pow(2, failTime) * quantityK))
		{
			randonPoint1 = Math.random() * (endPoint.getX() - initPoint.getX()) + initPoint.getX();
			randonPoint2 = Math.random() * (endPoint.getY() - initPoint.getY()) + initPoint.getY();
			setX = changePointToIndex(randonPoint1, 'x');
			setY = changePointToIndex(randonPoint2, 'y');

			if (set[setX][setY] == 0)
			{
				Point point = new Point(randonPoint1, randonPoint2);
				dummyLocation.add(point);
				set[setX][setY] = 1;
				i++;
			}

		}

		for (int j = 0; j < set[0].length; j++)
		{
			for (int w = 0; w < set[j].length; w++)
			{
				set[j][w] = 0;
			}
		}

		double maxH1 = 0, H1, b, a;
		Point[] testDummyLocation = new Point[(int) (4 * Math.pow(2, failTime) * quantityK)];
		Point[] dummyLocation1 = null;
		setX = changePointToIndex(truePoint.getX(), 'x');
		setY = changePointToIndex(truePoint.getY(), 'y');
		a = probabilities[setX][setY];
		for (int j = 0; j < m; j++)
		{
			H1 = 0;
			i = 0;
			testDummyLocation[i] = truePoint;
			H1 = a * (Math.log(a) / Math.log(2));
			i++;
			while (i < (4 * Math.pow(2, failTime) * quantityK))
			{
				randonPoint1 = Math.random() * dummyLocation.size();
				setX = changePointToIndex((dummyLocation.get((int) randonPoint1).getX()), 'x');
				setY = changePointToIndex((dummyLocation.get((int) randonPoint1).getY()), 'y');
				if (set[setX][setY] == 0)
				{
					testDummyLocation[i] = dummyLocation.get((int) randonPoint1);
					set[setX][setY] = 1;
					i++;
					a = probabilities[setX][setY];
					H1 = H1 + a * (Math.log(a) / Math.log(2));
				}
			}

			H1 = -1 * H1;
			if (maxH1 == 0 || maxH1 < H1)
			{
				maxH1 = H1;
				dummyLocation1 = Arrays.copyOf(testDummyLocation, testDummyLocation.length);
			}

			for (int c = 0; c < set.length; c++)
			{
				for (int w = 0; w < set[c].length; w++)
				{
					set[c][w] = 0;
				}
			}
		}

		i = 0;
		Point[] finallyDummyLocation = new Point[(int) (2 * Math.pow(2, failTime) * quantityK)];
		double[] distance1 = new double[(int) (4 * Math.pow(2, failTime) * quantityK)];
		finallyDummyLocation[0] = truePoint;
		int u = 0;
		i++;
		for (int j = 0; j < distance1.length; j++)
		{
			distance1[j] = 1;
		}

		for (int j = 1; j < finallyDummyLocation.length; j++)
		{
			for (int w = 0; w < dummyLocation1.length - u; w++)
			{
				for (int g = 0; g < finallyDummyLocation.length; g++)
				{
					if (finallyDummyLocation[g] != null)
						distance1[w] = distance1[w] * distance(dummyLocation1[w], finallyDummyLocation[g]);
					else
						break;
				}
			}
			double total = 0;
			for (int w = 0; w < dummyLocation1.length - u; w++)
				total = total + distance1[w];
			for (int w = 0; w < dummyLocation1.length - u; w++)
				distance1[w] = distance1[w] / total;

			double k = Math.random();
			total = 0;

			for (int w = 0; w < dummyLocation1.length - u; w++)
			{
				total += distance1[w]; 
				if (k < total)
				{
					finallyDummyLocation[i] = dummyLocation1[w];
					i++;
					double temp;
					Point point;
					point = dummyLocation1[w];
					dummyLocation1[w] = dummyLocation1[dummyLocation1.length - 1 -u];
					dummyLocation1[dummyLocation1.length - 1 - u] = point;

					temp = distance1[w];
					distance1[w] = distance1[distance1.length - 1 - u];
					distance1[distance1.length - 1 - u] = temp;
					u++;
					break;
				}
			}
		}

		return finallyDummyLocation;
	}

	private double distance(Point a1, Point a2)
	{
		double x;
		x = Math.sqrt(Math.pow((a1.getX() - a2.getX()), 2) + Math.pow((a1.getY() - a2.getY()), 2));
		return x;
	}

	private int changePointToIndex(double value, char mode)
	{
		switch (mode)
		{
			case 'x':
				return (int) ((value - initPoint.getX()) / x);
			case 'y':
				return (int) ((value - initPoint.getY()) / y);
			default:
				throw new UnsupportedOperationException();
		}
	}

}
