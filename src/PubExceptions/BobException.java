package PubExceptions;


/**
 * This exception should be thrown when a guest is a bob and want to drink beer.
 * @author sriem
 */


public class BobException extends Exception{

    public BobException(String message) {
        super(message);
    }
}
