package net.jdazher.domain.tasks.error;

/**
 * An exception that is thrown to indicate that an illegal or invalid date has been encountered.
 *
 * <p>This custom exception is a subclass of {@link Exception} and is typically used
 * to handle cases where a date does not meet expected conditions or formats.</p>
 *
 * @see Exception
 */
public class IllegalDateException extends Exception {

    /**
     * Constructs a new IllegalDateException with the specified detail message.
     *
     * @param message The detail message that explains the reason for the exception.
     */
    public IllegalDateException(final String message) {
        super(message);
    }
}
