import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Response {

	private String type = null;
	private Map<String, String> headers = null;
	private byte[] data = null;

	public Response() {
		headers = new HashMap<String, String>();
		data = new byte[0];
	}

	public Map<String, String> getHeaders() {
		return new HashMap<>(headers);
	}

	public String getHeaderValue(String key) {

		return headers.get(key);
	}

	public void addHedder(String key, String value) {
		headers.put(key, value);

	}

	public String getType() {
		return type;
	}

	public void setType(String value) {
		type = value;
	}

	public boolean isOK() {
		return this.getType().contains("200 OK");
	}

	public void setData(byte[] bFile) {
		this.data = bFile.clone();

	}

	public byte[] getData() {
		return data.clone();
	}

	public void setBadRequest(String httpVersion) {

		setErrorValues(httpVersion, " 400 Bad Request");
	}

	public void setNotSupported(String httpVersion) {

		setErrorValues(httpVersion, " 505 HTTP Version Not Supported");

	}

	public void setNotImplemented(String httpVersion) {

		setErrorValues(httpVersion, " 501 Not Implemented");
	}

	public void setNotFound(String httpVersion) {

		setErrorValues(httpVersion, " 404 Not Found");

	}

	public void setInternalServerError(String httpVersion) {

		setErrorValues(httpVersion, " 500 Internal Server Error");

	}

	public void setNoPermission(String httpVersion) {

		setErrorValues(httpVersion, " 403 Forbidden");

	}

	public void setUnsupportedMediaType(String httpVersion) {

		setErrorValues(httpVersion, " 415 Unsupported Media Type");
	}

	private void setErrorValues(String httpVersion, String errorType) {

		if (!(httpVersion.equals("HTTP/1.0") || httpVersion.equals("HTTP/1.1"))) {
			httpVersion = "HTTP/1.0";
		}

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		this.setType(httpVersion + errorType);
		this.headers.clear();
		this.data = null;
		this.addHedder("Content-Type", "text/html");
		this.addHedder("Content-Length", "0");
		this.addHedder("Connection", "close");
		this.addHedder("Date", dateFormat.format(cal.getTime()));
		this.addHedder("Server", "Shai & Ran");

	}

	public void setOPTIONS(String httpVersion) {

		setDefultHeaders();

		this.addHedder("Allow", "GET, POST, OPTIONS, HEAD, TRACE");

	}

	public void setHEAD(int length, String fileExtention) {

		setDefultHeaders();

		this.addHedder("Content-Type", getContentType(fileExtention));
		String contentLength = String.valueOf(length);
		this.addHedder("Content-Length", contentLength);
	}

	private void setDefultHeaders() {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		this.addHedder("Connection", "close");
		this.addHedder("Date", dateFormat.format(cal.getTime()));
		this.addHedder("Server", "Shai & Ran");

	}

	public void setTRACE(Request request) {

		setDefultHeaders();
		this.addHedder("Content-Type", getContentType(" "));
		StringBuilder stringHeadr = new StringBuilder();
		stringHeadr.append(request.getType() + "\n");

		for (Entry<String, String> req : request.getHedders().entrySet()) {
			stringHeadr.append(req.getKey() + ": " + req.getValue() + "\n");
		}

		stringHeadr.append("\n");

		for (Entry<String, String> req : request.getParams().entrySet()) {
			stringHeadr.append(req.getKey() + "=" + req.getValue() + "&");
		}

		stringHeadr.subSequence(0, stringHeadr.length() - 2);

		data = String.valueOf(stringHeadr).getBytes();
	}

	public void setGET(int length, String fileExtention) {

		setDefultHeaders();

		String contentLength = String.valueOf(length);
		String contentType = getContentType(fileExtention);

		this.addHedder("Content-Type", contentType);
		this.addHedder("Content-Length", contentLength);
	}

	private String getContentType(String fileExtention) {
		String contentType;

		switch (fileExtention) {
		case "html":
			contentType = "text/html";
			break;

		case "bmp":
		case "gif":
		case "png":
		case "jpg":
			contentType = "image";
			break;

		case "ico":
			contentType = "icon";
			break;

		default:
			contentType = "application/octet-stream";
			break;
		}

		return contentType;
	}

}
