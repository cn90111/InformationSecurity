package map;

public interface RandomPoint
{
	public void loadMap(Map map);

	public Point[] getCandidatesPoint(Line trueLine, int quantityK);
}
