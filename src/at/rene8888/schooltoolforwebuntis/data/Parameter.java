package at.rene8888.schooltoolforwebuntis.data;

import org.json.JSONObject;

public class Parameter {

	private String method;
	private JSONObject params;

	public Parameter(String method, JSONObject params) {
		this.method = method;
		this.params = params;
	}

	public String getMethod() {
		return method;
	}

	public JSONObject getParams() {
		return params;
	}

}
