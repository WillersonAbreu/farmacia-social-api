package br.com.farmaciasocialapi.resources;

public class Response {
	private String message;
	private Integer code;
	private Object data;
	
	
	
	public Response(String message, Integer code, Object data) {
		super();
		this.message = message;
		this.code = code;
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
