import java.util.HashMap;
import java.util.Map;

public class Request {

	private Map<String, String> request = null;

	public Request() {
		request = new HashMap<String, String>();
	}

	public void Add(String obj) throws Exception {
		if (request.isEmpty()) {
			if (obj.split(" ").length != 3)
				throw new Exception();
			request.put("type", obj);
		} else {
			String key = obj.substring(0, obj.indexOf(":"));
			String value = obj.substring(obj.indexOf(":") + 1);
			request.put(key.trim(), value.trim());

		}
	}
	
	public Map<String, String> GetHedders() {
		Map<String, String> retMap = new HashMap<>(request);
		retMap.remove("type");
		return retMap;
	}

	public String GetHedderValue(String hedder) {
		return request.get(hedder);
	}

	public String GetType() {
		return this.GetHedderValue("type");
	}

	public void BadRequast() {
		try {
			this.Add("type:BAD Requast");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String GetHttpVer() {
		return this.GetType().split(" ")[2];
	}

	public String getMethod() {
		return this.GetType().split(" ")[0];
	}
	
	public String getPath() {
		return this.GetType().split(" ")[1];
	}

}
