package net.greenbeansit.jobtracker.shared.exceptions;

public class InvalidInput extends RuntimeException {

	public InvalidInput() {
	}

	public InvalidInput(String message) {
		super(message);
	}

	public InvalidInput(Throwable cause) {
		super(cause);
	}

	public InvalidInput(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidInput(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
