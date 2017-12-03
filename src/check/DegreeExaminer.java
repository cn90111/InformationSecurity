package check;

import java.util.ArrayList;

import map.Line;
import map.Point;

public class DegreeExaminer implements CheckDegree{

	public boolean isReasonableDegree(Line[] allLine, Line trueLine, int quantityK) {
		ArrayList<Point> outDegreePoint = new ArrayList<Point>();
		ArrayList<Point> inDegreePoint = new ArrayList<Point>();
		int[] outDegreeCount = new int[allLine.length];
		int[] inDegreeCount = new int[allLine.length];
		
		outDegreePoint.add(trueLine.getStart());
		outDegreeCount[0]++;
		inDegreePoint.add(trueLine.getEnd());
		inDegreeCount[0]++;
		for( int i=0; i<allLine.length; i++ )
		{
			// out degree
			boolean hasPoint = false;
			for( int j=0; j<outDegreePoint.size(); j++ )
				if( allLine[i].getStart().equals( outDegreePoint.get(j)) )
				{
					outDegreeCount[j]++;
					hasPoint = true;
					break;
				}
			if( !hasPoint )
			{
				outDegreePoint.add( allLine[i].getStart() );
				outDegreeCount[outDegreePoint.size()-1]++;
			}
			// in degree
			hasPoint = false;
			for( int j=0; j<inDegreePoint.size(); j++ )
				if( allLine[i].getEnd().equals( inDegreePoint.get(j)) )
				{
					inDegreeCount[j]++;
					hasPoint = true;
					break;
				}
			if( !hasPoint )
			{
				inDegreePoint.add( allLine[i].getEnd() );
				inDegreeCount[inDegreePoint.size()-1]++;
			}
		}
		
		boolean outDegree = false;
		for( int i=1; i<outDegreeCount.length; i++ )
			if( outDegreeCount[i] > outDegreeCount[0] )
				outDegree = true;
		boolean inDegree = false;
		for( int i=1; i<inDegreeCount.length; i++ )
			if( inDegreeCount[i] > inDegreeCount[0] )
				inDegree = true;
		if( quantityK >= inDegreeCount.length && outDegree && inDegree )
			return true;
		return false;
	}

}












