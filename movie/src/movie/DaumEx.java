package movie;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class DaumEx {
	
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		new DaumEx();
	}
	
	public DaumEx() throws IOException, ParserConfigurationException, SAXException
	{
		//링크 주소 만들기
		String requestUrl = "http://apis.daum.net/contents/movie";
		requestUrl += "?apikey=" + "a188d2c93898808992de298cdd35a91a"; //발급된 키
        requestUrl += "&q=" + "love"; //검색어
        requestUrl += "&result=" + "20"; //출력될 결과수
        requestUrl += "&pageno=" + "1"; //페이지 번호
        requestUrl += "&output=" + "json";
		URL url = new URL(requestUrl);
		
		//API 요청 및 반환
		URLConnection conn = url.openConnection();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(conn.getInputStream());
		
		//channel노드를 객체화 하기
		Node node = doc.getElementsByTagName("channel").item(0);
		for (int i=0 ;i< node.getChildNodes().getLength();i++) {
			Node channelNode = node.getChildNodes().item(i);
			String nodeName = channelNode.getNodeName();
			
			//item 노드들일 경우
			if ("item".equals(nodeName)) 
			{
				//item노드의 자식노드를 검색
				for (int j=0 ;j< channelNode.getChildNodes().getLength();j++) 
				{
					Node itemNode = channelNode.getChildNodes().item(j);
					//title노드 일 경우 출력
					if("title".equals(itemNode.getNodeName()))
						System.out.println(itemNode.getTextContent());
				}
			}
		}
	}
}