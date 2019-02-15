package PubExceptions;


/**
 * This exception should be thrown when an time object has an invalid value for 
 * hour or/and minute.
 * @author sriem
 */
public class InvalidTimeException extends Exception{

    public InvalidTimeException(String message) {
        super(message);
    }
}