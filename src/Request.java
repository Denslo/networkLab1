import java.util.HashMap;
import java.util.Map;


public class Request {
	
	private Map<String, String> request = null;
	
	public Request(){
		request = new HashMap<String, String>();
	}
	
	public void Add(String obj){
		if(obj != null){
			if (request.isEmpty()) {
				request.put("type",obj);
			} else {
				if(obj.indexOf(":") != -1){
					String key = obj.substring(0, obj.indexOf(":"));
					String value = obj.substring(obj.indexOf(":") + 1);
					request.put(key.trim(), value.trim());
				}
			}
		}
	}
	
	public String GetHedderValue(String hedder){
		return request.get(hedder);
	}
	
	public String GetType(){
		return this.GetHedderValue("type");
	}
	
	public void BadRequast(){
		 this.Add("type:BAD Requast");
	}

	public String GetHttpVer() {
		return this.GetType().split(" ")[2];
	}
	
}
