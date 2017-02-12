//package com.dochi;
//
//import java.io.InputStream;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.xpath.XPath;
//import javax.xml.xpath.XPathConstants;
//import javax.xml.xpath.XPathFactory;
//
//import org.apache.log4j.Logger;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//
//import com.mashape.unirest.http.HttpResponse;
//import com.mashape.unirest.http.Unirest;
//
//public class TempWatcher {
//
//	final static Logger logger = Logger.getLogger(TempWatcher.class);
//	private String baseURL;
//
//	TempWatcher() {
//		baseURL = "http://m.ezville.net/ezvillehn/mobile/service/temSetSearchCall.php";
//	}
//
//	public void test() {
//		Timer timer = new Timer();
//		TimerTask timerTask = new TimerTask() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				try {
//					
//					HttpResponse<InputStream> response = Unirest.get(baseURL).queryString("hkey", "0008911")
//							.queryString("hh_dong", "101").queryString("hh_ho", "705").queryString("no", "1").asBinary();
//					
////					
////					for(int i=0;i<cols.getLength();i++)
////					{
////							String temp = cols.item(i).getTextContent();
////							System.out.println(temp);
////							
////							logger.info("Temp : " + temp);
////						//
////					}
////					
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		
//			}
//		};
//		timer.schedule(timerTask, 0, 2000);
//	}
//}
