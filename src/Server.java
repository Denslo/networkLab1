import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class Server {

	private static final String CONFIG_FILE = "config.ini";

	public static Properties prop = new Properties();

	public static void main(String[] args) {

		loadPropFile();
		
		startLisning();
		
	}

	private static void startLisning() {
		
		try {
			
			HttpRequestQueue queue = new HttpRequestQueue(Integer.parseInt(prop.getProperty("maxThreads")));
			@SuppressWarnings("resource")
			ServerSocket socket = new ServerSocket(Integer.parseInt(prop.getProperty("port")));
			
			while (true)
			{
			    // Listen for a TCP connection request.
			    Socket connection = socket.accept();
			    queue.Enqueue(connection);
			    
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void loadPropFile() {
		try {
			// load a properties file
			prop.load(new FileInputStream(CONFIG_FILE));

		} catch (IOException ex) {
			// TODO handle config file not found
			ex.printStackTrace();
		}
	}
}
