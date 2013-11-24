import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class Response {
	
	private Map<String, String> respone = null;
	final String TYPE = "type";
	
	public Response() {
		respone = new HashMap<String, String>();
		
	}
	
	public String getHeaderValue(String key){
		
		 return respone.get(TYPE);
	}
	
	public void add(String key, String value){
		respone.put(key, value);
		
	}
	
	public String getResponsType(){
		return respone.get(TYPE);
	}
	
	public void addResponsType(String value){
		respone.put(TYPE, value);
	}
	
	
	
	public void setBadRequest(String httpVersion){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		this.addResponsType(httpVersion + "400 Bad Request");
		this.add("Connection", "close");
		this.add("Date", dateFormat.format(cal.getTime()));
		this.add("Server", "Shai & Ran");
;	}
	
	
	public void setNotSupported(String httpVersion){

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		
		this.addResponsType(httpVersion + "505 HTTP Version Not Supported");
		this.add("Content-Type", "text/html");
		//check with ran!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		this.add("Content-Length", "379");
		this.add("Connection", "close");
		this.add("Date", dateFormat.format(cal.getTime()));
		this.add("Server", "Shai & Ran");
	}


	public void setNotImplemented(String httpVersion){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		
		this.addResponsType(httpVersion + "501 Not Implemented");
		this.add("Content-Type", "text/html");
		//check with ran!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		this.add("Content-Length", "367");
		this.add("Connection", "close");
		this.add("Date", dateFormat.format(cal.getTime()));
		this.add("Server", "Shai & Ran");
	}
	public void setNotFound(String httpVersion){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		
		this.addResponsType(httpVersion + "404 Not Found");
		this.add("Content-Type", "text/html");
		this.add("Connection", "close");
		this.add("Date", dateFormat.format(cal.getTime()));
		this.add("Server", "Shai & Ran");
		
	}
	public void setInternalServerError(String httpVersion){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		
		this.addResponsType(httpVersion + "500 Internal Server Error");
		this.add("Content-Type", "text/html");
		this.add("Connection", "close");
		this.add("Date", dateFormat.format(cal.getTime()));
		this.add("Server", "Shai & Ran");
		
	}

}
