package com.dochi;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;

public class BoilerRunner extends TimerTask {
	public static boolean isRunning;

	public int mPeriodTime;
//	private String m_hh_dong;
//	private String m_hkey;
//	private String m_hh_ho;
//	private String m_requestURL;

	public BoilerRunner() {
		//mPeriodTime = periodTime;
		isRunning = false;

	}

	public void setTempWithoutMin(String temp) {
		
		PropertyReader prop = new PropertyReader();
		MyLogger.logger.info("Called settempWithoutMin Method -> Hoping Temp : " + temp);
		try {
			HttpRequestWithBody httpRequest = Unirest.post(prop.getRequestURL());
			for (int i = 1; i < 7; i++) {
				if (i == 5)
					continue;
				HttpResponse<InputStream> tempRequest = httpRequest.queryString("hkey", prop.getHkey())
						.queryString("hh_dong", prop.getHh_dong()).queryString("hh_ho", prop.getHh_ho())
						.queryString("no", Integer.toString(i)).queryString("onoff", "Y")
						.queryString("settemp", temp)
						.asBinary();
				MyLogger.logger.info("Request Result : " + tempRequest.getStatusText());
				// = tempRequest.queryString("no", "1").asBinary();

				// 0 -> maybe bathroom?
				// 1 -> 거실
				// 2 -> 안방
				// 3 -> 큰방
				// 4 -> 작은방
			}
		} catch (Exception e) {
			MyLogger.logger.error("Requesting URL has Error");
			MyLogger.logger.error(e.getMessage());
		}
	}

	public void runBoiler(String temp) {
		// String maxTemp = "30.0";
		MyLogger.logger.info("Called runBoiler Method -> Hoping Temp : " + temp);

		MyLogger.logger.info("Called settempWithoutMin Method -> Hoping Temp : " + temp);
		try {
			PropertyReader prop = new PropertyReader();
			HttpRequestWithBody httpRequest = Unirest.post(prop.getRequestURL());
			for (int i = 1; i < 7; i++) {
				if (i == 5)
					continue;
				HttpResponse<InputStream> tempRequest = httpRequest.queryString("hkey", prop.getHkey())
						.queryString("hh_dong", prop.getHh_dong()).queryString("hh_ho", prop.getHh_ho())
						.queryString("no", Integer.toString(i)).queryString("onoff", "Y")
						.queryString("settemp", temp)
						.asBinary();
				MyLogger.logger.info("Request Result : " + tempRequest.getStatusText());
				// = tempRequest.queryString("no", "1").asBinary();

				// 0 -> maybe bathroom?
				// 1 -> 거실
				// 2 -> 안방
				// 3 -> 큰방
				// 4 -> 작은방
			}
			// Run the task
			isRunning = true;
			Timer timer = new Timer();
			int runningMinTmp = Integer.parseInt(prop.getRunningMin());
			timer.schedule(this, runningMinTmp * 1000 * 60);
		} catch (Exception e) {
			MyLogger.logger.error("Requesting URL has Error");
			MyLogger.logger.error(e.getMessage());
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		PropertyReader prop = new PropertyReader();

		MyLogger.logger.info("in " + prop.getRunningMin() + "minutes");
		runBoiler(prop.getMinTemp());
		isRunning = false;
	}

}
