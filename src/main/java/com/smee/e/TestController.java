package com.smee.e;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smee.e.dao.TestDAO;

@Controller
public class TestController {
	
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
    
    @ResponseBody
    @RequestMapping(value="getDoroCD", method=RequestMethod.POST)
    public String TestCon(@RequestBody String testGu) throws Exception {
    	System.out.println(testGu);
		ArrayList<String> kaptList = new ArrayList<String>();
		ArrayList<String> loadCode = new ArrayList<>();
		HashSet AddrSet = new HashSet();
		HashMap<String, Object> map = new HashMap<>(); // hashmap 으로 전송한다.
		loadCode = tDao.getDoroCD(testGu);
		System.out.println("Doro_CD : " + loadCode);
		System.out.println("Doro_CD 개수 : "+loadCode.size());
		for(int i = 0; i < loadCode.size(); i++) {
			String juso = LTH_GET_URL+loadCode.get(i)+"&ServiceKey="+KSH_GET_KEY2;
			URL url = new URL(juso);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//conn.setRequestMethod("POST");
			try {
				SAXBuilder builder = new SAXBuilder();
				// url에 xml이 있는경우
				Document jdomdoc = builder.build(url);
				Element root = jdomdoc.getRootElement();
				// root는 데이터 전체를 받는다. 
				// 이중 row를 여러 개체임으로 children을 쓴다. 
				// root Element는 response
				List<Element> test = root.getChildren(); // header랑 body
				//System.out.println(test.get(1));
				Element body = (Element) root.getChildren().get(1); // body
				List<Element> TestList = body.getChildren(); // items, numOfRows, pageNo, totalCount
				//System.out.println(TestList.get(0)); // items
				//System.out.println(TestList.size());
				List<Element> itemList = TestList.get(0).getChildren(); // item
				//System.out.println(itemList.size());
				if(itemList.size() != 0) {
					for(int j = 0; j < itemList.size(); j++) {
						System.out.println(itemList.size()); // item 과 같은 라인의 사이즈
						List<Element> temList = itemList.get(0).getChildren(); // kaptCode, kaptName
						for(int k = 0; k < temList.size(); k++) {
							///kaptList.add(temList.get(0).getValue());
							if (k%2==0){
								kaptList.add(temList.get(k).getValue());
							}
						}
						System.out.println(temList.get(0).getValue());
					}
				}
				System.out.println("아파트코드 : "+kaptList);
				System.out.println("아파트코드 개수 : "+kaptList.size());
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		return "";
		//////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////KaptCode받아오기//////////////////////////////////////////////////////////
		/*try {
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
		}*/
    }
}
