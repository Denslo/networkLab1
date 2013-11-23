import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpRequest implements Runnable {

	private Socket socket = null;
	private HttpRequestQueue queue = null;
	private Request request = null;
	private Hedder header = null;

	public HttpRequest(HttpRequestQueue httpRequestQueue, Socket socket) throws IOException {
		this.socket = socket;
		this.queue = httpRequestQueue;
		this.request = new Request();
		this.header = new Hedder();
	}

	@Override
	public void run() {

		try {
			
			//read all requast
			String line;
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			while (!(line = input.readLine()).equals("")) {
				System.out.println(line);
				request.Add(line);
			}
			
			//cheack requst
			
			//creat header and data
			switch (request.GetType()) {
			case "GET":

				break;
				
			case "POST":
				
				break;
				
			case "OPTIONS":
				responseOptions();
				break;
			
			case "HEAD":
				
				break;
			
			case "TRACE":
				
				break;

			default:
				
				break;
			}
			
			//send data and requst
			PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
			//close therad
			
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

	private void responseOptions() {

		header.add(HedderUtil.getOK(request.GetHttpVer()));
		header.add("Allow: GET, POST, OPTIONS, HEAD, TRACE");
		header.add("Content-Length: 0");
	
	}

}
