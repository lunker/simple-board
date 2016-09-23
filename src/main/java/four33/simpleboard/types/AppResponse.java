package four33.simpleboard.types;

public class AppResponse {
	
	private String status;
	private String message;
	private Article[] data;
	
	public AppResponse(){}
	
	public AppResponse(String status, String message) {
		
		this.status = status;
		this.message = message;
		data = null;
	}
	
	public AppResponse(String status, String message, Article[] data) {

		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Article[] getData() {
		return data;
	}

	public void setData(Article[] data) {
		this.data = data;
	}
	
	
	
	
}
