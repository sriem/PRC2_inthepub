package PubExceptions;


/**
 * This exception should be thrown when the stock is empty and the barkeeper tries to tap beer from it.
 * @author sriem
 */
public class EmptyStockException extends Exception{

    public EmptyStockException(String message) {
        super(message);
    }
}

