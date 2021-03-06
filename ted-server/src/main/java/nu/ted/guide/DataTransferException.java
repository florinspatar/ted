package nu.ted.guide;

/**
 * Due to some network or unforeseen error, your data isn't there.
 *
 */
public class DataTransferException extends DataSourceException {

	private static final long serialVersionUID = 1L;

	public DataTransferException() {
	}

	public DataTransferException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataTransferException(String message) {
		super(message);
	}

	public DataTransferException(Throwable cause) {
		super(cause);
	}

}
