package PubExceptions;


/**
 * This exception should be thrown when the maximal volume of the stomach is 
 * reached.
 * @author sriem
 */
public class StomachException extends Exception{

    public StomachException(String message) {
        super(message);
    }
}
