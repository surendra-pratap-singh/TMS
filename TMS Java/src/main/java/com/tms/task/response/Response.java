package com.tms.task.response;

import lombok.Data;

@Data
public class Response {

	public int status;
	public String message = "Success";
	public Object data;

	public Response(int status, Object data) {
		this.status = status;
		this.data = data;
	}

	public Response(int status, String message, Object data) {
		this.message = message;
		this.status = status;
		this.data = data;
	}

}