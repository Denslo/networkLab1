import static org.junit.Assert.*;

import org.junit.Test;


public class Tests {

	@Test
	public void requestAddParams() {
		Request req = new Request();
		req.addParams("aaa=111&bbb=222&ccc=333");
		req = new Request();
		req.addParams("aaa=111&bbb=222");
		req = new Request();
		req.addParams("aaa=111");
		
		req = new Request();
		req.addParams("");
		req = new Request();
		req.addParams(null);
		req = new Request();
		req.addParams("aaa=");
		req = new Request();
		req.addParams("aaa");
	}

}
