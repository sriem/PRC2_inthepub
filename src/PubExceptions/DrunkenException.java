package PubExceptions;


/**
 * This exception should be thrown when a guest get the StomachException.
 * @author sriem
 */

public class DrunkenException extends Exception{

    public DrunkenException(String message) {
        super(message);
    }
}