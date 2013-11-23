import java.net.Socket;

public class HttpRequestQueue {

	private int maxSize;
	private int counter;

	public HttpRequestQueue(int max) {
		this.maxSize = max;
		this.counter = 0;
	}

	public synchronized void Enqueue(Socket socket) {
		
		while (maxSize == counter) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		this.addTocounter();
		
		(new Thread(new HttpRequest(this, socket))).start();
		
	}
	
	public synchronized void Dequeue() {
		this.removeFromcounter();
		notifyAll();
	}
	
	private void addTocounter(){
		this.counter++;
	}
	
	private void removeFromcounter(){
		this.counter--;
	}
}
