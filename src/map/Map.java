package map;

public class Map
{
	private Point upperLeft;
	private Point lowerRight;

	public Map(Point upperLeft, Point lowerRight)
	{
		this.upperLeft = upperLeft;
		this.lowerRight = lowerRight;
	}
	
	public Point getUpperLeft()
	{
		return upperLeft;
	}

	public Point getLowerRight()
	{
		return lowerRight;
	}
}
