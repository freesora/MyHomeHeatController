package com.dochi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
	private String requestURL;
	private String responseURL;
	private String hkey;
	private String highTemp;
	public String getRequestURL() {
		return requestURL;
	}

	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}

	public String getResponseURL() {
		return responseURL;
	}

	public void setResponseURL(String responseURL) {
		this.responseURL = responseURL;
	}

	public String getHkey() {
		return hkey;
	}

	public void setHkey(String hkey) {
		this.hkey = hkey;
	}

	public String getHh_dong() {
		return hh_dong;
	}

	public void setHh_dong(String hh_dong) {
		this.hh_dong = hh_dong;
	}

	public String getHh_ho() {
		return hh_ho;
	}

	public void setHh_ho(String hh_ho) {
		this.hh_ho = hh_ho;
	}

	public String getWannaTemp() {
		return wannaTemp;
	}

	public void setWannaTemp(String wannaTemp) {
		this.wannaTemp = wannaTemp;
	}

	public String getRunningMin() {
		return runningMin;
	}

	public void setRunningMin(String runningMin) {
		this.runningMin = runningMin;
	}

	public String getMinTemp() {
		return minTemp;
	}

	public void setMinTemp(String minTemp) {
		this.minTemp = minTemp;
	}

	private String hh_dong;
	private String hh_ho;
	private String wannaTemp;
	private String runningMin;
	private String minTemp;
	
//	responseURL=http://m.ezville.net/ezvillehn/mobile/service/temSetSearchCall.php
//		requestURL=http://m.ezville.net/ezvillehn/mobile/service/temSetControlCall.php
//		hkey=0008911
//		hh_dong=101
//		hh_ho=705
//		wannaTemp=22.0
//		runningMin=30
//		minTemp=11.0
	
	public PropertyReader()
	{
		InputStream input;
		try {
			Properties prop = new Properties();
			input = new FileInputStream("config.properties");
			prop.load(input);
			requestURL = prop.getProperty("requestURL");
			responseURL = prop.getProperty("responseURL");
			hh_dong = prop.getProperty("hh_dong");
			hkey = prop.getProperty("hkey");
			hh_ho = prop.getProperty("hh_ho");
			wannaTemp = prop.getProperty("wannaTemp");
			runningMin = prop.getProperty("runningMin");
			minTemp = prop.getProperty("minTemp");
			highTemp = prop.getProperty("highTemp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			MyLogger.logger.error("Reading Property Error");
			MyLogger.logger.error(e.getMessage());
			//e.printStackTrace();
		}
	}

	public String getHighTemp() {
		return highTemp;
	}

	public void setHighTemp(String highTemp) {
		this.highTemp = highTemp;
	}
}
