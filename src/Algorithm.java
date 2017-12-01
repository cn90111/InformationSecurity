import check.CheckAngle;
import check.CheckDegree;
import check.CheckTime;
import map.Line;
import map.Map;
import map.Point;
import map.RandomPoint;

public class Algorithm
{
	RandomPoint randomMaker;
	CheckTime timeExaminer;
	CheckAngle angleExaminer;
	CheckDegree degreeExaminer;
	int quantityK;

	public Algorithm(int mode, Map map, int quantityK)
	{
		randomMaker = null; // need implementation
		randomMaker.loadMap(map);
		switch (mode)
		{
			case Launch.REAL_MODE:
				timeExaminer = null; // need implementation
				break;
			case Launch.VIRTUAL_MODE:
				timeExaminer = null; // need implementation
				break;
			default:
				throw new UnsupportedOperationException("This mode not support");
		}
		angleExaminer = null; // need implementation
		degreeExaminer = null; // need implementation
	}

	public Point[] createDummies(Point[] nowPoint, Line trueLine)
	{
		Point[] candidatesPoint = null;
		Line[] allLine = null;
		while (candidatesPoint == null || degreeExaminer.isReasonableDegree(allLine, trueLine, quantityK) == false)
		{
			candidatesPoint = randomMaker.getCandidatesPoint(trueLine, quantityK);
			allLine = timeExaminer.getReasonableLine(nowPoint, candidatesPoint);
			allLine = angleExaminer.getReasonableLine(allLine, trueLine);
		}
		candidatesPoint = decideSol(allLine);
		return candidatesPoint;
	}

	private Point[] decideSol(Line[] allLine)
	{
		Point[] dummies = new Point[quantityK];
		int randomChange;
		Line temp;
		for (int i = 0; i < allLine.length; i++)
		{
			randomChange = (int) (Math.random() * allLine.length);
			temp = allLine[i];
			allLine[i] = allLine[randomChange];
			allLine[randomChange] = temp;
		}
		int nowIndex = 0;
		for (int i = 0; i < allLine.length && nowIndex < quantityK; i++)
		{
			Point point = allLine[i].getEnd();
			if (nowIndex == 0 || havePointRepeat(point, dummies) == false)
			{
				dummies[nowIndex] = point;
				nowIndex++;
			}
		}
		return dummies;
	}

	private boolean havePointRepeat(Point checkPoint, Point[] pointArray)
	{
		for (int i = 0; i < pointArray.length; i++)
		{
			if (checkPoint.equals(pointArray[i]))
			{
				return true;
			}
		}
		return false;
	}
}
