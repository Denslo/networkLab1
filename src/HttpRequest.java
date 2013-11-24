import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

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
					// TODO bad requst 400
				}
			}

			// set response as 200 ok

			if (!isMethodImplemented()) {
				// set response as 501 not implemented
				// do action
			}

			if (isVersionSuported()) {
				if (!isVerOK()) {
					//400 bad requst
					//do action
				}
			} else {
				//set as 505 not suported ver
				//do action
			}

			//do action

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		// print requast
		// pars http requast
		// handle by requast type
		// create http respons header
		// print header
		// send response
		// deque
	}

	private boolean isVersionSuported() {
		 String version = request.GetHttpVer();
		 if (version.equals("HTTP/1.0") || version.equals("HTTP/1.1")) {
			 return true;
		}
		return false;
	}

	private boolean isMethodImplemented() {
		// TODO Auto-generated method stub
		return false;
	}

}
