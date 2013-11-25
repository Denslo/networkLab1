import java.util.HashMap;
import java.util.Map;


public class Response {
	
	private Map<String, String> headers = null;
	private String type = null;
	
	public Response() {
		headers = new HashMap<String, String>();
		
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

}
