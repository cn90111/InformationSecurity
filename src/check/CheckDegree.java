package check;

import map.Line;

public interface CheckDegree
{
	public boolean isReasonableDegree(Line[] allLine, Line trueLine, int quantityK);
}
