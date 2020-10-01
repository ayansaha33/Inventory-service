package bootup.assignment.inventory.exception;

public class BlockedProductException extends RuntimeException {
	
	
	/**
	 * BlockedProductException is application level custom exception.
	 * This is used to handle different negative scenarios across application.
	 */
	private static final long serialVersionUID = 1545687211182434953L;

	public BlockedProductException(final String inMessageKey) {
        super(inMessageKey);
    }

    public BlockedProductException(final String inMessageKey, final Throwable inException) {
        super(inMessageKey, inException);
    }

    public BlockedProductException(final Throwable inException) {
        super(inException);
    }
}
