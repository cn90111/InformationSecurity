package check;

import java.util.ArrayList;

import map.Line;
import map.Point;

public class DegreeExaminer implements CheckDegree
{
	public boolean isReasonableDegree(Line[] allLine, Line trueLine, int quantityK)
	{
		ArrayList<Point> outDegreePoint = new ArrayList<Point>();
		ArrayList<Point> inDegreePoint = new ArrayList<Point>();
		int[] outDegreeCount = new int[allLine.length];
		int[] inDegreeCount = new int[allLine.length];

		outDegreePoint.add(trueLine.getStart());
		inDegreePoint.add(trueLine.getEnd());
		for (Line line : allLine)
		{
			// out degree
			boolean hasPoint = false;
			for (int j = 0; j < outDegreePoint.size(); j++)
				if (line.getStart().equals(outDegreePoint.get(j)))
				{
					outDegreeCount[j]++;
					hasPoint = true;
					break;
				}
			if (!hasPoint)
			{
				outDegreePoint.add(line.getStart());
				outDegreeCount[outDegreePoint.size() - 1]++;
			}
			// in degree
			hasPoint = false;
			for (int j = 0; j < inDegreePoint.size(); j++)
				if (line.getEnd().equals(inDegreePoint.get(j)))
				{
					inDegreeCount[j]++;
					hasPoint = true;
					break;
				}
			if (!hasPoint)
			{
				inDegreePoint.add(line.getEnd());
				inDegreeCount[inDegreePoint.size() - 1]++;
			}
		}

		boolean outDegree = false;
		for (int i = 1; i < outDegreePoint.size(); i++)
			if (outDegreeCount[i] > outDegreeCount[0])
			{
				outDegree = true;
				break;
			}
		boolean inDegree = false;
		for (int i = 1; i < inDegreePoint.size(); i++)
			if (inDegreeCount[i] > inDegreeCount[0])
			{
				inDegree = true;
				break;
			}
		if (quantityK <= inDegreePoint.size() && outDegree && inDegree)
			return true;
		return false;
	}
}