package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import check.RealCheckTime;
import junit.framework.Assert;
import map.Point;

public class RealCheckTimeTest
{
	RealCheckTime timeExaminer;
	String testJSONContent = "{" + "\"destination_addresses\" : [\"542台灣南投縣草屯鎮水沙連高速公路\" ],"
			+ "\"origin_addresses\" : [ \"Unnamed Road, 復興區桃園市台灣 336\" ]," + "\"rows\" :[" + "{" + "\"elements\" : ["
			+ "{" + "\"distance\" : {" + "\"text\" : \"141 公里\"," + "\"value\" : 141313" + "}," + "\"duration\" : {"
			+ "\"text\" : \"1 天 6 小時\"," + "\"value\" : 108478" + "}," + "\"status\" : \"OK\"" + "}" + "]" + "}" + "],"
			+ "\"status\" : \"OK\"" + "}";

	@Before
	public void setUp() throws Exception
	{
		timeExaminer = new RealCheckTime();
	}

	@After
	public void tearDown() throws Exception
	{
		timeExaminer = null;
	}

	// @Test
	// public void splitTimeFromDataTest()
	// {
	// double time;
	// // before test need set splitTimeFromData public
	// time = timeExaminer.splitTimeFromData(testJSONContent);
	// Assert.assertEquals(108478, time, 0.001);
	// }
	//
	// @Test
	// public void requestTest() throws IOException
	// {
	// double time;
	// // just test funtion, unit test can't test E2E
	// time = timeExaminer.getTwoPointTime(new Point(121.26343, 24.790293), new
	// Point(120.718261, 24.006317));
	// Assert.assertEquals(108478, time, 0.001);
	// }

	// @Test
	// public void isLegalTimeTest()
	// {
	// // before test need set isLegalTime public
	// Assert.assertEquals(true, timeExaminer.isLegalTime(45, 30, 0.5));
	// Assert.assertEquals(true, timeExaminer.isLegalTime(15, 30, 0.5));
	// Assert.assertEquals(false, timeExaminer.isLegalTime(60, 30, 0.5));
	// }
}
