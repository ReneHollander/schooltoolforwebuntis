package at.rene8888.schooltoolforwebuntis.exception;

public class UnexceptedErrorException extends Exception {

	private static final long serialVersionUID = 1L;

	public UnexceptedErrorException() {
		super();
	}

	public UnexceptedErrorException(String s) {
		super(s);
	}

	public UnexceptedErrorException(Throwable t) {
		super(t);
	}

}
