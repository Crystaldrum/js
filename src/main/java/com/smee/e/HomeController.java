package com.smee.e;

import java.io.BufferedInputStream;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.smee.e.dao.TestDAO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	// 공동주택 단지 목록제공 서비스 URL
	public final static String LTH_GET_URL = "http://apis.data.go.kr/1611000/AptListService/getRoadnameAptList?loadCode=";
	
	// 공동주택 기본 정보제공 서비스 URL
	public final static String LTH_GET_URL2 = "http://apis.data.go.kr/1611000/AptBasisInfoService/getAphusBassInfo?kaptCode=";
	
	///// 태훈 인증키
    // 공동주택 단지 목록제공 서비스
    public final static String LTH_GET_KEY = "pSMWn87uRCBPH7CYLz7cgSugrQD0U9uYMQFBty6bZximARbBws232iw2fV0uIyjWmee7s8pEeEiBuwuuOhJV5A%3D%3D";
    
    // 공동주택 기본 정보제공 서비스
    public final static String LTH_GET_KEY2 = "pSMWn87uRCBPH7CYLz7cgSugrQD0U9uYMQFBty6bZximARbBws232iw2fV0uIyjWmee7s8pEeEiBuwuuOhJV5A%3D%3D";
    
    ////// 진호 인증키
    // 공동주택 단지 목록제공 서비스
    public final static String PJH_GET_KEY = "P0A6kob9lKrE3P7EPU%2BF0WLOsKloQi7iZSxlSZIpHvF2ljpECCnFqgh90uSMPzt2Pdk9U9I2KwJEt4lJQGXS6A%3D%3D";
    
    // 공동주택 기본 정보제공 서비스
    public final static String PJH_GET_KEY2 = "P0A6kob9lKrE3P7EPU%2BF0WLOsKloQi7iZSxlSZIpHvF2ljpECCnFqgh90uSMPzt2Pdk9U9I2KwJEt4lJQGXS6A%3D%3D";
    
    ////// 영석 인증키
    // 공동주택 단지 목록제공 서비스
    public final static String YYS_GET_KEY = "bkomOZtMIiCFnE5738mjIbiMLRTAWDxcAF7hQrAhB9UNIB3%2FfjfVqA6IcLmTaTx3grGZgYc3bAavc3BubwFGXQ%3D%3D";
    
    // 공동주택 기본 정보제공 서비스
    public final static String YYS_GET_KEY2 = "";
    
    ////// 유영 인증키
    // 공동주택 단지 목록제공 서비스
    public final static String JYY_GET_KEY = "";
    
    // 공동주택 기본 정보제공 서비스
    public final static String JYY_GET_KEY2 = "";
    
    ////// 진희 인증키
    // 공동주택 단지 목록제공 서비스
    public final static String PJH2_GET_KEY = "";
    
    // 공동주택 기본 정보제공 서비스
    public final static String PJH2_GET_KEY2 = "";
    
    ////// 예비 인증키1
    // 공동주택 단지 목록제공 서비스
    public final static String KSH_GET_KEY = "xvqCf7tkTGQZ3W82UGq7LTVf09swWdh9ahkz%2BWe6EohVausaAx%2FnJta8kjBJU6lv521V3KU4cZgdyjCqrL0rVw%3D%3D";
    
    // 공동주택 기본 정보제공 서비스
    public final static String KSH_GET_KEY2 = "xvqCf7tkTGQZ3W82UGq7LTVf09swWdh9ahkz%2BWe6EohVausaAx%2FnJta8kjBJU6lv521V3KU4cZgdyjCqrL0rVw%3D%3D";
    
    ////// 예비 인증키2
    // 공동주택 단지 목록제공 서비스
    public final static String LSY_GET_KEY = "It7hTbFaURgkgwAsaWlo9F27Uq4rMVfll%2BYT2hoW%2BgbqrsCIKSEi7XzejdQe6Q%2FnyWcJJG82EHNtO26BayebhA%3D%3D";
    
    // 공동주택 기본 정보제공 서비스
    public final static String LSY_GET_KEY2 = "It7hTbFaURgkgwAsaWlo9F27Uq4rMVfll%2BYT2hoW%2BgbqrsCIKSEi7XzejdQe6Q%2FnyWcJJG82EHNtO26BayebhA%3D%3D";
    
    ////// 예비 인증키3
    // 공동주택 단지 목록제공 서비스
    public final static String LJS_GET_KEY = "http://apis.data.go.kr/1611000/AptBasisInfoService/getAphusBassInfo?kaptCode=A13528105&ServiceKey=xvqCf7tkTGQZ3W82UGq7LTVf09swWdh9ahkz%2BWe6EohVausaAx%2FnJta8kjBJU6lv521V3KU4cZgdyjCqrL0rVw%3D%3D";
    
    // 공동주택 기본 정보제공 서비스
    public final static String LJS_GET_KEY2 = "http://apis.data.go.kr/1611000/AptBasisInfoService/getAphusBassInfo?kaptCode=A13528105&ServiceKey=xvqCf7tkTGQZ3W82UGq7LTVf09swWdh9ahkz%2BWe6EohVausaAx%2FnJta8kjBJU6lv521V3KU4cZgdyjCqrL0rVw%3D%3D";
    
    
    String test = "http://apis.data.go.kr/1611000/AptBasisInfoService/getAphusBassInfo?kaptCode=A13509009&ServiceKey=pSMWn87uRCBPH7CYLz7cgSugrQD0U9uYMQFBty6bZximARbBws232iw2fV0uIyjWmee7s8pEeEiBuwuuOhJV5A%3D%3D";
    String test2 = "http://apis.data.go.kr/1611000/AptListService/getRoadnameAptList?loadCode=116802122002&ServiceKey=pSMWn87uRCBPH7CYLz7cgSugrQD0U9uYMQFBty6bZximARbBws232iw2fV0uIyjWmee7s8pEeEiBuwuuOhJV5A%3D%3D";
    
    @Autowired
    private TestDAO tDao;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	@RequestMapping(value = "map", method=RequestMethod.GET)
	public String map(){
		return "map";
	}
	
	@RequestMapping(value = "test", method=RequestMethod.GET)
	public String test(){
		return "test";
	}
	@ResponseBody
	@RequestMapping(value="loca", method = RequestMethod.GET)
	public String loca(@RequestBody String emdCode){
		System.out.println("locacacaca");
		return "test";
	}
	
	@ResponseBody
	@RequestMapping(value = "test11", method=RequestMethod.POST)
	public aptName test(@RequestBody String loadCode){
		System.out.println("12x3123x");
		aptName aN = new aptName();
		
		try {
			String juso = LTH_GET_URL+"?"+loadCode+"&ServiceKey="+PJH_GET_KEY;
			System.out.println(juso);
			URL url = new URL(juso);
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
	    				addr = xpp.getText();
	    			
	    			}
	    		}else if (event_type == XmlPullParser.END_TAG){
	    			tag = xpp.getName();
	    			if(tag.equals("item")){
	    				
	    				aN.setAptName(addr);
	    				list.add(addr);
	    			}
	    		}
	    		event_type = xpp.next();
	    	}
	    	System.out.println(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return aN;
	}
	
	/*@ResponseBody
	@RequestMapping(value="getDoroCD", method=RequestMethod.POST, produces="application/text; charset=utf-8")
	public String testDong(@RequestBody String testGu) {
		System.out.println(testGu);
		//aptName aN = new aptName(null);
		ArrayList<String> aN = new ArrayList<>();
		ArrayList<String> aN2 = new ArrayList<>();
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> addr = new ArrayList<>();
		ArrayList<String> addrList = new ArrayList<String>();
		ArrayList<String> loadCode = new ArrayList<>();
		HashSet AddrSet = new HashSet();
		
		loadCode = tDao.getDoroCD(testGu);
		
		System.out.println("Doro_CD : " + loadCode);
		int event_type = 0;
		int event_type2 = 0;
		String tag = null;
		String tag2 = null;
		//String addr = null;
		String addr2 = null;
		
		XmlPullParserFactory factory = null;
		XmlPullParser xpp = null;
		try {
			factory = XmlPullParserFactory.newInstance();
			xpp = factory.newPullParser();
		} catch (XmlPullParserException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedInputStream bis;
		try {
			for(int i = 0; i < loadCode.size(); i++) {
				String juso = LTH_GET_URL+loadCode.get(i)+"&ServiceKey="+KSH_GET_KEY;
				System.out.println(juso);
				URL url = new URL(juso);
				bis = new BufferedInputStream(url.openStream());
				factory.setNamespaceAware(true);
				
				xpp.setInput(bis,"utf-8");
				
				event_type = xpp.getEventType();
				
				while(event_type != XmlPullParser.END_DOCUMENT){
					if(event_type == XmlPullParser.START_TAG) {
						tag = xpp.getName();
					}
					if(event_type == XmlPullParser.TEXT) {
						if(tag.equals("kaptCode")){
							addr.add(xpp.getText());
						}
					}
					if (event_type == XmlPullParser.END_TAG) {
						tag = xpp.getName();
						if(tag.equals("item")){
						//if(tag.equals("item")) {
							//aN.setAptName(addr);
							//aN.add(addr);
							//addr.add(xpp.getText());
							System.out.println("KaptCode 개수 : "+addr.size());
						}
					}
					event_type = xpp.next();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////KaptCode받아오기//////////////////////////////////////////////////////////
		try {
			for(int i = 0; i < addr.size(); i++) {
				String juso = LTH_GET_URL2+addr.get(i)+"&ServiceKey="+KSH_GET_KEY2;
				System.out.println(juso);
				URL url2 = new URL(juso);
				XmlPullParserFactory factory2 = XmlPullParserFactory.newInstance();
				factory2.setNamespaceAware(true);
				XmlPullParser xpp2 = factory2.newPullParser();
				BufferedInputStream bis2 = new BufferedInputStream(url2.openStream());
				xpp2.setInput(bis2,"utf-8");
				
				event_type2 = xpp2.getEventType();
				
				while(event_type2 != XmlPullParser.END_DOCUMENT) {
					if(event_type2 == XmlPullParser.START_TAG) {
						tag2 = xpp2.getName();
					}
					if(event_type2 == XmlPullParser.TEXT) {
						if(tag2.equals("kaptAddr")) {
							//addr2 = xpp.getText();
							addrList.add(xpp2.getText());
						}
					}
					if (event_type2 == XmlPullParser.END_TAG) {
						tag2 = xpp2.getName();
						//if(tag.equals("item")){
						if(tag2.equals("item")) {
							//aN.setAptName(addr2);
							//aN2.add(addr2);
							//addrList.add(xpp.getText());
						}
					}
					event_type2 = xpp2.next();
				}
				for(int j = 0; j < addrList.size(); j++) {
					if(addrList.get(j).contains("삼성동")) {
						AddrSet.add(addrList.get(j));
					}
				}
				System.out.println(addrList);
				System.out.println("아파트 개수 : "+addrList.size());
				System.out.println(AddrSet);
				System.out.println("해당동 아파트 개수 : "+AddrSet.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}*/
	
}
