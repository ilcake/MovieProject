package movie;

import java.io.BufferedInputStream;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class InfoEx {
 public InfoEx() throws Exception{
         
         JSONParser jsonparser2 = new JSONParser();
         JSONObject jsonobject2 = (JSONObject)jsonparser2.parse(readUrl());
//         System.out.println(jsonobject2.toJSONString());
         JSONObject json1 =  (JSONObject) jsonobject2.get("movieInfoResult");
         JSONObject json2 =  (JSONObject) json1.get("movieInfo");
         JSONArray array = (JSONArray)json2.get("directors");
         for(int i = 0 ; i < array.size(); i++){
             JSONObject entity = (JSONObject)array.get(i);
             String movieNm = (String) entity.get("peopleNm");
             System.out.println(movieNm);
         }
    }
    private static String readUrl() throws Exception {
        BufferedInputStream reader1 = null;
        try {
            URL url = new URL(
            			" http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json"
                    + "?key=c1635cef98ea88052ed1414320cbccc4"
                    + "&movieCd=20162207");
            reader1 = new BufferedInputStream(url.openStream());
            StringBuffer buffer = new StringBuffer();
            int i;
            byte[] b = new byte[4096];
            while( (i = reader1.read(b)) != -1){
              buffer.append(new String(b, 0, i));
            }
            return buffer.toString();
        } finally {
            if (reader1 != null)
                reader1.close();
        }
    }
 
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            new InfoEx();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
