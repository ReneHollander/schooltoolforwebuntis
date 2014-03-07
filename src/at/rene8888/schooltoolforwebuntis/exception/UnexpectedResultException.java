package at.rene8888.schooltoolforwebuntis.exception;

import org.json.JSONObject;

public class UnexpectedResultException extends Exception {

	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JSONObject getInput() {
		return input;
	}

	public int getErrorcode() {
		return errorcode;
	}

	private JSONObject input;
	private int errorcode;

	public UnexpectedResultException(JSONObject input, String errormessage, int errorcode) {
		super(errormessage);
		this.input = input;
		this.errorcode = errorcode;
	}
}
