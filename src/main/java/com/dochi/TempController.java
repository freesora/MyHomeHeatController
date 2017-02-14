package com.dochi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Future;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;

public class TempController extends TimerTask {
	final static Logger logger = Logger.getLogger(TempController.class);

	private String mResponseURL;
	// private String mRequestURL;
	// private XPath xpath;
//	private String m_hh_dong;
//	private String m_hkey;
//	private String m_hh_ho;
//	private String m_wannaTemp;

	private BoilerRunner boilerRunner;

	public TempController(String responseURL) {
		boilerRunner = new BoilerRunner();
		mResponseURL = responseURL;
		try {

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Reading Property File Error");
			logger.error(e.getMessage());
			// e.printStackTrace();
		}

	}

	public void watchTemparature(String temp, int period) {

		String tempStr = temp;
		// String tempPeriod = Integer.toString(period);

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(this, 0, period);

		// http://m.ezville.net/ezvillehn/mobile/service/temSetControlCall.php?hkey=0008911&&hh_dong=101&hh_ho=705&no=1&onoff=Y&settemp=23.0
		// Access the m.ezville.net
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		PropertyReader prop = new PropertyReader();
		if (BoilerRunner.isRunning != true) {
			try {
				// Number 1 : wanna focus
				logger.info("Watching Temperature");

				HttpResponse<InputStream> response = Unirest.get(prop.getResponseURL())
						.queryString("hkey", prop.getHkey()).queryString("hh_dong", prop.getHh_dong())
						.queryString("hh_ho", prop.getHh_ho()).queryString("no", "1").asBinary();

				// if(response)
				Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(response.getBody());
				XPath xpath = XPathFactory.newInstance().newXPath();
				String expression = "//boiler//boilerinfo";
				NodeList nodeList = (NodeList) xpath.compile(expression).evaluate(doc, XPathConstants.NODESET);
				String curTemp = null;
				String setTemp = null;
				if (nodeList != null) {
					for (int i = 0; nodeList != null && i < nodeList.getLength(); i++) {
						NodeList childNodes = nodeList.item(i).getChildNodes();
						for (int j = 0; j < childNodes.getLength(); j++) {
							Node tmpNode = childNodes.item(j);
							if (tmpNode.getNodeName().equals("curtemp")) {
								curTemp = tmpNode.getFirstChild().getNodeValue();
								logger.info("Current Temp : " + curTemp);
							} else if (tmpNode.getNodeName().equals("settemp")) {
								setTemp = tmpNode.getFirstChild().getNodeValue();
								logger.info("Setting Temp : " + setTemp);
							}
						}

					}
				}
				double convertedCurTemp = Double.parseDouble(curTemp);
				double convertedWannaTemp = Double.parseDouble(prop.getWannaTemp());
				if (convertedCurTemp < convertedWannaTemp && BoilerRunner.isRunning == false) {
					logger.info("Running Boiler : " + prop.getHighTemp());
					boilerRunner.runBoiler(prop.getHighTemp());
				}
			} catch (Exception e) {
				logger.error("Error!");
				logger.error(e.getMessage());
			}
		}

	}
//
//	public class TempWatcherTask extends TimerTask {
//
//		private String mBaseURL;
//
//		TempWatcherTask(String baseURL) {
//			mBaseURL = baseURL;
//		}
//
//		@Override
//		public void run() {
//			// TODO Auto-generated method stub
//			try {
//
//				InputStream input = new FileInputStream("config.properties");
//				Properties prop = new Properties();
//				prop.load(input);
//
//				String hh_dong = prop.getProperty("hh_dong");
//				String hkey = prop.getProperty("hkey");
//				String hh_ho = prop.getProperty("hh_ho");
//
//				HttpResponse<InputStream> response = Unirest.get(this.mBaseURL).queryString("hkey", hkey)
//						.queryString("hh_dong", hh_dong).queryString("hh_ho", hh_ho).queryString("no", "1").asBinary();
//
//				// if(response)
//				Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(response.getBody());
//				XPath xpath = XPathFactory.newInstance().newXPath();
//				String expression = "//boiler//boilerinfo";
//				NodeList nodeList = (NodeList) xpath.compile(expression).evaluate(doc, XPathConstants.NODESET);
//				String curTemp;
//				String setTemp;
//				if (nodeList != null) {
//					for (int i = 0; nodeList != null && i < nodeList.getLength(); i++) {
//						NodeList childNodes = nodeList.item(i).getChildNodes();
//						for (int j = 0; j < childNodes.getLength(); j++) {
//							Node tmpNode = childNodes.item(j);
//							if (tmpNode.getNodeName().equals("curtemp")) {
//								curTemp = tmpNode.getFirstChild().getNodeValue();
//								MyLogger.logger.info("Current Temp : " + curTemp);
//							} else if (tmpNode.getNodeName().equals("settemp")) {
//								setTemp = tmpNode.getFirstChild().getNodeValue();
//								MyLogger.logger.info("Setting Temp : " + setTemp);
//							}
//						}
//
//					}
//				}
//			}
//
//			catch (Exception e) {
//				MyLogger.logger.error("Error!");
//				MyLogger.logger.error(e.getMessage());
//
//			}
//
//			//
//			// Future<HttpResponse<InputStream>> future =
//			// Unirest.get(this.mBaseURL).queryString("hkey", hkey)
//			// .queryString("hh_dong", hh_dong).queryString("hh_ho",
//			// hh_ho).queryString("no", "1")
//			// .asBinaryAsync(new Callback<InputStream>() {
//			//
//			// @Override
//			// public void completed(HttpResponse<InputStream> response) {
//			// // TODO Auto-generated method stub
//			// try {
//			// Document doc =
//			// DocumentBuilderFactory.newInstance().newDocumentBuilder()
//			// .parse(response.getBody());
//			// XPath xpath = XPathFactory.newInstance().newXPath();
//			// String expression = "//boiler//boilerinfo";
//			// NodeList nodeList = (NodeList)
//			// xpath.compile(expression).evaluate(doc, XPathConstants.NODESET);
//			// String curTemp;
//			// String setTemp;
//			// if (nodeList != null) {
//			// for(int i=0;nodeList != null && i<nodeList.getLength();i++)
//			// {
//			// NodeList childNodes = nodeList.item(i).getChildNodes();
//			// for(int j=0;j<childNodes.getLength();j++)
//			// {
//			// Node tmpNode = childNodes.item(j);
//			// if(tmpNode.getNodeName().equals("curtemp"))
//			// {
//			// curTemp = tmpNode.getFirstChild().getNodeValue();
//			// MyLogger.logger.info("Current Temp : " + curTemp);
//			// }
//			// else if(tmpNode.getNodeName().equals("settemp"))
//			// {
//			// setTemp = tmpNode.getFirstChild().getNodeValue();
//			// MyLogger.logger.info("Setting Temp : " + setTemp);
//			// }
//			// }
//			//
//			// }
//			// }
//			//
//			// } catch (Exception e) {
//			// MyLogger.logger.error("Document Reading FAIL");
//			// MyLogger.logger.error(e.getMessage());
//			// }
//			// }
//			//
//			// @Override
//			// public void failed(UnirestException e) {
//			// // TODO Auto-generated method stub
//			// MyLogger.logger.error("URL Connection Failes");
//			// MyLogger.logger.error(e.getMessage());
//			// }
//			//
//			// @Override
//			// public void cancelled() {
//			// // TODO Auto-generated method stub
//			// MyLogger.logger.error("URL Connection cancelled");
//			// }
//			//
//			// });
//
//		}
//	}

}
