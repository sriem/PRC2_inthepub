package PubExceptions;

/**
 * This exception should be thrown when an object tries to fill the stock but 
 * the maximal volume is reached. 
 * @author sriem
 */
public class OverflowedStockException extends Exception{

    public OverflowedStockException(String message) {
        super(message);
    }
}