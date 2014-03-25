package at.rene8888.schooltoolforwebuntis.data.util.pipeline.response;

import at.rene8888.schooltoolforwebuntis.data.util.pipeline.request.Request;

public class Response {

	private Request request;
	private ResponseData responseData;

	public Response(Request request, ResponseData responseData) {
		this.request = request;
		this.responseData = responseData;
	}

	public Request getRequest() {
		return this.request;
	}

	public ResponseData getResponseData() {
		return this.responseData;
	}

}
