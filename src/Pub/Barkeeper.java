package Pub;

import PubExceptions.BobException;
import PubExceptions.EmptyStockException;

import java.time.LocalTime;

/**
 * This class represent the barkeeper of the pub.
 * @author Pia Erbrath
 */
public class Barkeeper {

    /**
     * The default constructor. 
     */
    Barkeeper() {
    }

    /**
     * This method taps a beer from the stock.
     * @param stock from where the beer should be taps.
     * @param orderer the guest which is ordered.
     * @param volume the size of the beer which is ordered.
     * @return a new Beer object.
     * @throws BobException when the orderer is the bob.
     * @throws EmptyStockException when the stock does not have enough liter for
     *  this beer
     */
    public Beer tapBeer(Stock stock, Guest orderer, DrinkVolume volume) throws 
             BobException, EmptyStockException {
        Beer drink = new Beer(volume, LocalTime.now(), (stock.getOrderCount() + 1));
        if(orderer.getAge() < 18){
            throw new BobException("YOU ARE BOB");
        }else{
            if(stock.getCurrentSize() < drink.getBeerSize()){
                throw new EmptyStockException("Stock is Empty");
            }else{
                return drink;
            }
        }
    }

}
