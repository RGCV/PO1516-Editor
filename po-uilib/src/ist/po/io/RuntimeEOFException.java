package ist.po.io;

/**
 * Class RuntimeEOFException.
 */
public class RuntimeEOFException extends RuntimeException {

	/** Serial number. */
	static final long serialVersionUID = 200610291655L;

	/**
	 * Constructor.
	 */
	public RuntimeEOFException() {
		// intentionally left empty
	}

	/**
	 * @param msg
	 *            message describing the exception.
	 */
	public RuntimeEOFException(String msg) {
		super(msg);
	}

}