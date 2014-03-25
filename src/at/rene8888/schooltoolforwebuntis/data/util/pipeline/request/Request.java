package at.rene8888.schooltoolforwebuntis.data.util.pipeline.request;

import at.rene8888.schooltoolforwebuntis.data.util.pipeline.response.ResponseListener;

public class Request {

	private ResponseListener responseListener;
	private RequestData requestData;

	public Request(ResponseListener responseListener, RequestData requestData) {
		this.responseListener = responseListener;
		this.requestData = requestData;
	}

	public ResponseListener getResponseListener() {
		return this.responseListener;
	}

	public RequestData getRequest() {
		return this.requestData;
	}

}
