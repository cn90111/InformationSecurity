package check;

import map.Line;
import map.Point;

public interface CheckTime
{
	public Line[] getReasonableLine(Point[] nowPoint,Point[] nextPoint);
}
