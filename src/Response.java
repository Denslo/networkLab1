import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class Response {
	
	private Map<String, String> headers = null;
	private String type = null;
	
	public Response() {
		headers = new HashMap<String, String>();
		
	}
	
	public Map<String, String> getHeaders(){
		return new HashMap<>(headers);
	}
	
	public String getHeaderValue(String key){
		
		 return headers.get(key);
	}
	
	public void add(String key, String value){
		headers.put(key, value);
		
	}
	
	public String getResponsType(){
		return type;
	}
	
	public void addResponsType(String value){
		type = value;
	}

	public boolean isOK() {
		return this.getResponsType().contains("200 OK");
	}
	
	
	
	public void setBadRequest(String httpVersion){
		
		if (!(httpVersion.equals("HTTP/1.0") || httpVersion.equals("HTTP/1.0"))) {
			httpVersion = "HTTP/1.0";
		}
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		this.addResponsType(httpVersion + " 400 Bad Request");
		this.add("Connection", "close");
		this.add("Date", dateFormat.format(cal.getTime()));
		this.add("Server", "Shai & Ran");
;	}
	
	
	public void setNotSupported(String httpVersion){
		
		if (!(httpVersion.equals("HTTP/1.0") || httpVersion.equals("HTTP/1.0"))) {
			httpVersion = "HTTP/1.0";
		}
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		
		this.addResponsType(httpVersion + " 505 HTTP Version Not Supported");
		this.add("Content-Type", "text/html");
		this.add("Content-Length", "0");
		this.add("Connection", "close");
		this.add("Date", dateFormat.format(cal.getTime()));
		this.add("Server", "Shai & Ran");
	}


	public void setNotImplemented(String httpVersion){
		
		if (!(httpVersion.equals("HTTP/1.0") || httpVersion.equals("HTTP/1.0"))) {
			httpVersion = "HTTP/1.0";
		}
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		
		this.addResponsType(httpVersion + " 501 Not Implemented");
		this.add("Content-Type", "text/html");
		this.add("Content-Length", "0");
		this.add("Connection", "close");
		this.add("Date", dateFormat.format(cal.getTime()));
		this.add("Server", "Shai & Ran");
	}
	public void setNotFound(String httpVersion){
		
		if (!(httpVersion.equals("HTTP/1.0") || httpVersion.equals("HTTP/1.0"))) {
			httpVersion = "HTTP/1.0";
		}
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		
		this.addResponsType(httpVersion + " 404 Not Found");
		this.add("Content-Type", "text/html");
		this.add("Connection", "close");
		this.add("Date", dateFormat.format(cal.getTime()));
		this.add("Server", "Shai & Ran");
		
	}
	public void setInternalServerError(String httpVersion){
		
		if (!(httpVersion.equals("HTTP/1.0") || httpVersion.equals("HTTP/1.0"))) {
			httpVersion = "HTTP/1.0";
		}
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		
		this.addResponsType(httpVersion + " 500 Internal Server Error");
		this.add("Content-Type", "text/html");
		this.add("Connection", "close");
		this.add("Date", dateFormat.format(cal.getTime()));
		this.add("Server", "Shai & Ran");
		
	}

	public void setOPTIONS(String httpVersion) {
		if (!(httpVersion.equals("HTTP/1.0") || httpVersion.equals("HTTP/1.0"))) {
			httpVersion = "HTTP/1.0";
		}
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		
		this.add("Allow", "GET, POST, OPTIONS, HEAD, TRACE");
		this.add("Content-Length", "0");
		this.add("Date", dateFormat.format(cal.getTime()));
		this.add("Server", "Shai & Ran");
		
	}

}
