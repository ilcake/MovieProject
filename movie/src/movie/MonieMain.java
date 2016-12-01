package movie;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
 
public class MonieMain {
    
	JSONObject entity;
	JSONObject title;
	
    public MonieMain() throws Exception{
         
    	JSONParser jsonparser = new JSONParser();
        JSONObject jsonobject = (JSONObject)jsonparser.parse(readUrl());
        /*JSONObject json =  (JSONObject) jsonobject.get("boxOfficeResult");
        JSONArray array = (JSONArray)json.get("dailyBoxOfficeList");*/
        JSONObject json =  (JSONObject) jsonobject.get("channel");
//        System.out.println(json.toJSONString());
        JSONArray array = (JSONArray)json.get("item");
        System.out.println(array.toJSONString());
//        JSONArray array2 = (JSONArray)array.get(0);
//        System.out.println(array2.toJSONString());
        for(int i = 0 ; i < array.size(); i++){
            entity = (JSONObject)array.get(i);/////////////////////////////////////////////////
            System.out.println(entity.toJSONString());
            JSONArray array3 = (JSONArray)entity.get("thumbnail");
//            JSONArray array3 = (JSONArray)entity.get("story");
            System.out.println(array3.toJSONString());
            for(int j = 0 ; j < array3.size(); j++){
            	title = (JSONObject)array3.get(j);
            	System.out.println(title.toJSONString());
            	String movieNm = (String) title.get("content");
                System.out.println(movieNm);
            }
        }
        
        Gson gson = new Gson();
        Year s = gson.fromJson(""+title+"", Year.class);
        System.out.println(s);
    }
    private static String readUrl() throws Exception {
        BufferedInputStream reader = null;
        
        String q = "미생";
        q = URLEncoder.encode(q, "UTF-8");
        
        String requestUrl = "http://apis.daum.net/contents/movie";
		requestUrl += "?apikey=" + "a188d2c93898808992de298cdd35a91a"; //발급된 키
        requestUrl += "&q=" + q; //검색어
        requestUrl += "&result=" + "10"; //출력될 결과수
        requestUrl += "&pageno=" + "1"; //페이지 번호
        requestUrl += "&output=" + "json";
        
        try {
            URL url = new URL(
//                   "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/"
//                    + "searchDailyBoxOfficeList.json"
//                    + "?key=c1635cef98ea88052ed1414320cbccc4"
//                    + "&targetDt=20161129"
//            		"https://apis.daum.net/contents/movie?apikey={a188d2c93898808992de298cdd35a91a}&q=harrypotter&output=json"
            		requestUrl
            		);
            reader = new BufferedInputStream(url.openStream());
            StringBuffer buffer = new StringBuffer();
            int i;
            byte[] b = new byte[4096];
            while( (i = reader.read(b)) != -1){
              buffer.append(new String(b, 0, i));
            }
            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
 
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            new MonieMain();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
}
