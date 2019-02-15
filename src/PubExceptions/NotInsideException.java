package PubExceptions;

/**
 * This exception should be thrown when a guest orders a beer but he/she is not
 * older than and ageLimit.
 *
 *
 * @author sriem
 */
public class NotInsideException extends Exception {

    public NotInsideException(String message) {
        super(message);
    }
}
