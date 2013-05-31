package util;



import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
public class WebRequest {
	private HttpURLConnection urlConn = null;
	private URL url;
	private String result = "";
	public String getResult() {
		return result;
	}
	private InputStream resultStream;
	public InputStream getResultStream() {
		return resultStream;
	}
	
	public int getMethod(String string_url, String context) throws Exception {
		int responeseCode = 0;
		url = new URL(string_url + "?" + context);
		urlConn = (HttpURLConnection) url.openConnection();
		urlConn.setRequestMethod("GET");
		urlConn.setDoInput(true);
		urlConn.setDoOutput(false);
		result = "";
		responeseCode = urlConn.getResponseCode();
		System.out.println(string_url + "?" + context);
		if (responeseCode == 200) {
			resultStream =urlConn.getInputStream(); 
		} else {
			resultStream =urlConn.getErrorStream(); 
		}
		return responeseCode;
	}
	public void close(){
		if(urlConn!=null)
		{
			urlConn.disconnect();
			urlConn = null;
		}
	}
}
