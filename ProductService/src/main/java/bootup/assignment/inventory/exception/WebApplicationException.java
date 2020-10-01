package bootup.assignment.inventory.exception;

/**
 * WebApplicationException is application level custom exception.
 * This is used to handle different negative scenarios across application.
 */
public class WebApplicationException extends RuntimeException {

    private static final long serialVersionUID = 7303744796795747314L;

    public WebApplicationException(final String inMessageKey) {
        super(inMessageKey);
    }

    public WebApplicationException(final String inMessageKey, final Throwable inException) {
        super(inMessageKey, inException);
    }

    public WebApplicationException(final Throwable inException) {
        super(inException);
    }

}
