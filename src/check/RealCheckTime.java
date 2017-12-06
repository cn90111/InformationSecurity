package check;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONObject;

import internet.HttpRequest;
import map.Line;
import map.Point;

public class RealCheckTime implements CheckTime
{
	public Line[] getReasonableLine(Point[] nowPoint, Point[] nextPoint, Line trueLine, double thresholdTime)
	{
		ArrayList<Line> reasonableLine = new ArrayList<Line>();

		double trueTime = -1;

		try
		{
			trueTime = getTwoPointTime(trueLine.getStart(), trueLine.getEnd());
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.exit(-1);
		}

		double twoPointTime;

		for (int i = 0; i < nowPoint.length; i++)
		{
			for (int j = 0; j < nextPoint.length; j++)
			{
				try
				{
					twoPointTime = getTwoPointTime(nowPoint[i], nextPoint[j]);

					if (isLegalTime(twoPointTime, trueTime, thresholdTime) == true)
					{
						reasonableLine.add(new Line(nowPoint[i], nextPoint[j]));
					}
				}
				catch (IOException e)
				{
					j = j - 1; // Resend
					e.printStackTrace();
				}
			}
		}

		return reasonableLine.toArray(new Line[0]);
	}

	private double splitTimeFromData(String content)
	{
		JSONObject jsonObject = new JSONObject(content);
		double needTime = jsonObject.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0)
				.getJSONObject("duration").getDouble("value");
		return needTime;
	}

	private double getTwoPointTime(Point start, Point end) throws IOException
	{
		URL url = HttpRequest.urlString(start, end);
		String content = HttpRequest.getRequestContent(url, "GET");
		return splitTimeFromData(content);
	}

	private boolean isLegalTime(double time, double trueTime, double thresholdTime)
	{
		if (time - trueTime <= trueTime * thresholdTime)
		{
			return true;
		}
		return false;
	}
}
