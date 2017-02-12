package com.dochi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Logger.getLogger("org.apache.http").setLevel(org.apache.log4j.Level.OFF);

		InputStream input;
		//BasicConfigurator.configure();
		PropertyConfigurator.configure("log4j.properties");

		try {
//			input = new FileInputStream("config.properties");
//			Properties prop = new Properties();
//			prop.load(input);
//			String responseURL = prop.getProperty("responseURL");
//			int runningMin = Integer.parseInt(prop.getProperty("runningMin"));
			PropertyReader prop = new PropertyReader();
			int runningMin = Integer.parseInt(prop.getRunningMin());

			BoilerRunner boilerRunner = new BoilerRunner();
			boilerRunner.setTempWithoutMin(prop.getMinTemp());
			
			TempController tmp = new TempController(prop.getResponseURL());
			tmp.watchTemparature(prop.getWannaTemp(), 10*1000*60);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
