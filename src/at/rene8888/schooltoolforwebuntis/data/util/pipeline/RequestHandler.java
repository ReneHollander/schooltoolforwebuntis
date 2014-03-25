package at.rene8888.schooltoolforwebuntis.data.util.pipeline;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import at.rene8888.schooltoolforwebuntis.data.util.pipeline.request.Request;
import at.rene8888.schooltoolforwebuntis.data.util.pipeline.response.Response;

public abstract class RequestHandler extends Thread {

	private static RequestHandler INSTANCE;

	private boolean running;
	private Queue<Request> requestQueue;

	public RequestHandler() {
		this.requestQueue = new ConcurrentLinkedQueue<Request>();
		INSTANCE = this;
		this.start();
	}

	public void run() {
		while (this.running) {
			Request request = requestQueue.poll();
			if (request != null) {
				this.handleRequest(request);
			}
		}
	}

	public boolean putRequest(Request request) {
		return this.requestQueue.offer(request);
	}

	public static boolean putStaticRequest(Request request) {
		return INSTANCE.putRequest(request);
	}

	public void shutdown() {
		this.running = false;
	}

	public void postResponse(Response response) {
		response.getRequest().getResponseListener().handleResponse(response);
	}

	public abstract void handleRequest(Request request);

}
