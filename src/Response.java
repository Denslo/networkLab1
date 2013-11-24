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

}
