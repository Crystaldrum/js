package com.smee.e;

import java.io.BufferedInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class get {

	public final static String GET_URL = "http://apis.data.go.kr/1611000/AptListService/getRoadnameAptList";
    public final static String GET_KEY = "bkomOZtMIiCFnE5738mjIbiMLRTAWDxcAF7hQrAhB9UNIB3%2FfjfVqA6IcLmTaTx3grGZgYc3bAavc3BubwFGXQ%3D%3D";

    public get() {
    	try {
			api();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void api() throws Exception{
    	URL url = new URL(getURL(null));
    	
    	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
    	factory.setNamespaceAware(true);
    	XmlPullParser xpp = factory.newPullParser();
    	BufferedInputStream bis = new BufferedInputStream(url.openStream());
    	xpp.setInput(bis,"utf-8");
    	
    	String tag = null;
    	int event_type = xpp.getEventType();
    	
    	ArrayList<String> list = new ArrayList<String>();
    	
    	String addr = null;
    	while(event_type != XmlPullParser.END_DOCUMENT){
    		if(event_type == XmlPullParser.START_TAG){
    			tag = xpp.getName();
    		}else if(event_type == XmlPullParser.TEXT){
    			
    			if(tag.equals("kaptName")){
    				addr = xpp.getName();
    			
    			}
    		}else if (event_type == XmlPullParser.END_TAG){
    			tag = xpp.getName();
    			if(tag.equals("item")){
    				list.add(addr);
    			}
    		}
    		event_type = xpp.next();
    	}
    	System.out.println(list);
    }
    
    
    
    
    public String getURL(String search){
    	String url = GET_URL+"?"+search+"&ServiceKey="+GET_KEY;
    	search = "loadCode=113504130298";
    	System.out.println("22222222222222");
    	System.out.println(url);
    	
    	return url;
    
    }
    
}
