package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import map.Map;
import map.Point;
import map.RandomMaker;

public class RandomMakerTest
{
	RandomMaker randomMaker;
	Point upperLeft = new Point(-123.019869, 38.275117);
	Point lowerRight = new Point(-121.287728 * 0.999, 36.929451 * 0.999);
	private static final int K = 20;
	
	@Before
	public void setUp() throws Exception
	{
		randomMaker = new RandomMaker();
		randomMaker.loadMap(new Map(upperLeft, lowerRight));
	}

	@After
	public void tearDown() throws Exception
	{
		randomMaker = null;
	}

	@Test
	public void getCandidatesPointTest()
	{
		double randonPoint1 = Math.random() * (lowerRight.getX() - upperLeft.getX()) + upperLeft.getX();
		double randonPoint2 = Math.random() * (lowerRight.getY() - upperLeft.getY()) + upperLeft.getY();
		Point[] allPoint = randomMaker.getCandidatesPoint(new Point(randonPoint1, randonPoint2), K ,0);

		for (int i = 0; i < K; i++)
		{
			if(outBound(allPoint[i]) == true)
			{
				fail(String.format("%d out bound", i));
			}
		}
	}

	private boolean outBound(Point point)
	{
		if(point.getX() > upperLeft.getX() && point.getX() < lowerRight.getX() && point.getY() < upperLeft.getY() && point.getY() > lowerRight.getY())
			return false;
		else
			return true;
	}
}
