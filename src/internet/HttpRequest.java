package internet;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import map.Point;

public class HttpRequest
{
	public static String getRequestContent(URL url, String requestMode) throws IOException
	{
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		http.setRequestMethod(requestMode);
		InputStream input = http.getInputStream();
		Scanner temp = new Scanner(input);
		Scanner reader = temp.useDelimiter("\\Z");
		String content = reader.next();
		reader.close();
		temp.close();
		return content;
	}

	public static URL urlString(Point start, Point end) throws MalformedURLException
	{
		URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + start.getY() + ",%20"
				+ start.getX() + "&destinations=" + end.getY() + ",%20" + end.getX()
				+ "&mode=walking&key=AIzaSyDXJwstHbmg5EQLxZEoMk38Duax1bBcTlY");
		return url;
	}
}
