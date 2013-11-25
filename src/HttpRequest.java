import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map.Entry;

public class HttpRequest implements Runnable {

	private Socket socket = null;
	private HttpRequestQueue queue = null;
	private Request request = null;
	private Response response = null;

	public HttpRequest(HttpRequestQueue httpRequestQueue, Socket socket)
			throws IOException {
		this.socket = socket;
		this.queue = httpRequestQueue;
		this.request = new Request();
		this.response = new Response();
	}

	@Override
	public void run() {

		try {

			// read all requast
			String line;
			BufferedReader input = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			while (!(line = input.readLine()).equals("")) {
				System.out.println(line);
				try {
					request.Add(line);
				} catch (Exception e) {
					this.response.setBadRequest("HTTP/1.0");
					handleRequest();
				}
			}

			this.response.addResponsType(HedderUtil.getOK(request.GetHttpVer()));

			if (!isMethodImplemented()) {
				// set response as 501 not implemented
				this.response.setNotImplemented(request.GetHttpVer());
				handleRequest();
			}

			if (isVersionSuported()) {
				if (!isVerOK()) {
					//400 bad requst
					this.response.setBadRequest(request.GetHttpVer());
					handleRequest();					
				}
			} else {
				//set as 505 not suported ver
				this.response.setNotSupported(request.GetHttpVer());
				handleRequest();
			}

			handleRequest();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void handleRequest() throws Exception {
		
		if (this.response.isOK()) {
			
			switch (request.getMethod()) {
			case "GET":
				
				break;
			case "POST":
				
				break;
			case "OPTIONS":
				
				if (request.getPath().equals("*")) {
					response.setOPTIONS(request.GetHttpVer());
				} else {
					response.setBadRequest(request.GetHttpVer());
					handleRequest();
				}
				
				break;
			case "HEAD":
				
				break;
			case "TRACE":
				
				for (Entry<String, String> header : request.GetHedders().entrySet()) {
					response.add(header.getKey(), header.getValue());
				}
				break;

			default:
				this.response.setNotImplemented(request.GetHttpVer());
				handleRequest();
				break;
			}

		}
		
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		
		out.println(response.getResponsType());
		System.out.println(response.getResponsType());
		
		for (Entry<String, String> header : response.getHeaders().entrySet()) {
		
			String outStr = header.getKey() + ": " + header.getValue();
			
			out.println(outStr);
			System.out.println(outStr);
		}
		
		this.socket.close();
		this.queue.Dequeue();
			
	}

	private boolean isVersionSuported() {
		 String version = request.GetHttpVer();
		 if (version.equals("HTTP/1.0") || version.equals("HTTP/1.1")) {
			 return true;
		}
		return false;
	}

	private boolean isVerOK() {
		boolean retVal = false;
		
		if (this.request.GetHttpVer().equals("HTTP/1.1") && this.request.GetHedderValue("Host") != null) {
			retVal = true;
		}
		
		if (this.request.GetHttpVer().equals("HTTP/1.0")) {
			retVal = true;
		}
		return retVal;
	}

	private boolean isMethodImplemented() {
		boolean retVal = false;
		if (this.request.getMethod().equals("GET")) {
			retVal = true;
		}
		if (this.request.getMethod().equals("POST")) {
			retVal = true;
		}
		if (this.request.getMethod().equals("OPTIONS")) {
			retVal = true;
		}
		if (this.request.getMethod().equals("HEAD")) {
			retVal = true;
		}
		if (this.request.getMethod().equals("TRACE")) {
			retVal = true;
		}
		return retVal;
	}

}
