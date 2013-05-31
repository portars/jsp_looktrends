package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;


public class getjson {
	
	public static String getJson() {
		WebRequest wr=new WebRequest();
		
		String result="test";
		try {
			wr.getMethod("http://hawttrends.appspot.com/api/terms/", "");
			
			StringBuilder sb=new StringBuilder();
			
			BufferedReader r = new BufferedReader(new InputStreamReader(wr.getResultStream()));
			String str="";
			while((str=r.readLine())!=null)
			{
				sb.append(str);
				
			}

			wr.close();
//			result=sb.toString();
//			wr.getMethod("http://hawttrends.appspot.com/api/terms/", "");
//			wr.close();
			
			int n = sb.lastIndexOf("}");
			sb.delete(n, n+1);
			wr.getMethod("http://apis.daum.net/socialpick/search", "n=20");
			result=sb.toString()+find(wr.getResultStream());			
			wr.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}		
		return result;
	}
	static public String find(InputStream is) throws Exception {
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(is);
		Element root = doc.getRootElement();
		List<Element> els = root.getChildren("item");
		StringBuilder sb = new StringBuilder();
		if (els.size() > 1) {
			// {"1": ["Prancercise"],
			// "3": ["Sslc Result"],
			// "4": ["Devday","디브데이"],
			// "5": ["Julius Richard Petri"],
			// "6": ["hoyajigi"],
			// "8": ["The Great Gatsby"],
			// "9": ["Demi Lovato"],
			// "10": ["호야지기"],
			// "12": ["멘탈 붕괴"],
			// "13": ["무소속"],
			// "14": ["뿌잉뿌잉"]}
			sb.append(",\"15\": [");
			for (int i = 0; i < els.size(); i++) {
				if (i < els.size() - 1) {
					System.out.println();
					
					sb.append("\"" +els.get(i).getChild("keyword").getValue()
							+ "\",");
				} else {
					
					sb.append("\"" +els.get(i).getChild("keyword").getValue()
							+ "\"]}");
				}
			}
			return sb.toString();
		}
		return ",\"15\": [test]}";
	}
}
