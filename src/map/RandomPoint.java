package map;

import java.io.IOException;

public interface RandomPoint
{
	public void loadMap(Map map) throws IOException;;

	public Point[] getCandidatesPoint(Point truePoint, int quantityK ,int failTime) ;

}
