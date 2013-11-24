import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
				try {
					request.Add(line);
				} catch (Exception e) {
					// TODO bad requst
				}
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
