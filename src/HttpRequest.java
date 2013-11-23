import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class HttpRequest implements Runnable {

	private Socket socket = null;
	private HttpRequestQueue queue = null;

	public HttpRequest(HttpRequestQueue httpRequestQueue, Socket socket) {
		this.socket = socket;
		this.queue = httpRequestQueue;
	}

	@Override
	public void run() {

		try {

			String line;
			Request request = new Request();
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			while (!(line = input.readLine()).equals("")) {
				System.out.println(line);
				request.Add(line);
			}
			
			
			switch (request.GetType()) {
			case "GET":
				
				break;
				
			case "POST":
				
				break;
				
			case "OPTIONS":
	
				break;
			
			case "HEAD":
				
				break;
			
			case "TRACE":
				
				break;

			default:
				
				break;
			}
			
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

}
