package com.dochi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputStream input;
		BasicConfigurator.configure();
		try {
			input = new FileInputStream("config.properties");
			Properties prop = new Properties();
			prop.load(input);
			String responseURL = prop.getProperty("responseURL");
			TempController tmp = new TempController(responseURL);
			tmp.watchTemparature("22.0", 10*1000*60);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
