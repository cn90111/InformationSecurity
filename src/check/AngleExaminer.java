package check;

import java.util.ArrayList;

import map.Line;

public class AngleExaminer implements CheckAngle{

	public Line[] getReasonableLine(Line[] allLine, Line trueLine) {
		double angle = 45; //0~180
		ArrayList<Line> reasonLine = new ArrayList<Line>();
		
		double x = trueLine.getEnd().getX()-trueLine.getStart().getX();
		double y = trueLine.getEnd().getY()-trueLine.getStart().getY();
		double sinAngle = y/Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
		double cosAngle = x/Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
		
		for( int i=0; i<allLine.length; i++ )
		{
			Line line = allLine[i];
			x = line.getEnd().getX()-line.getStart().getX();
			y = line.getEnd().getY()-line.getStart().getY();
			double lineX = x*cosAngle + y*sinAngle;
			double lineY = -x*sinAngle + y*cosAngle;
			double lineAngle = Math.toDegrees(Math.atan(lineY/lineX));
			if( lineX<0 ) //2.3象限
				lineAngle += 180;
			else if( lineY<0 ) //4象限
				lineAngle += 360;
			if( lineAngle>180 )
				lineAngle = -(lineAngle-360);
			if( lineAngle<=angle )
				reasonLine.add(allLine[i]);
		}
		return reasonLine.toArray(new Line[0]);
	}
}
