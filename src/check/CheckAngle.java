package check;

import map.Line;

public interface CheckAngle
{
	public Line[] getReasonableLine(Line[] allLine, Line trueLine);
}
