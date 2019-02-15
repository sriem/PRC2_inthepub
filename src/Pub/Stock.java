package Pub;

import PubExceptions.EmptyStockException;
import PubExceptions.OverflowedStockException;

import java.time.LocalTime;

/**
 * This class represents a stock. It has a maximal volume and from this volume a
 * beer could be tap or the volume could be filled again.
 * @author sriem
 */
public class Stock {

    /**
     * The maximal volume of the stock.
     */
    private final double size;
    /**
     * The current volume of the stock. 
     */
    private double currentSize;

    /**
     * The Current Ordercount
     */
    private int orderCount;
    /**
     * This constructor creates a new stock object with an maximum volume.
     * The currentSize is equals to maximum volume, because the stock will be 
     * created as a full stock. 
     * @param generalSize the maximum volume of the stock in double. 
     */
    public Stock(double generalSize) {
        this.size = generalSize;
        this.currentSize = generalSize;
        this.orderCount = 0;
    }

    /**
     * This method taps a beer from the stock.
     * @param volume the size of the beer.
     * @param time the beer is ordered.
     * @return a Beer object.
     * @throws EmptyStockException when the stock has not enough volume for the 
     *  ordered beer. 
     */
    public Beer getBeer(DrinkVolume volume, LocalTime time) throws 
            EmptyStockException {

        Beer beer = new Beer(volume,time,orderCount);
        if(beer.getBeerSize() <= getCurrentSize()){
            orderCount++;
            currentSize = (currentSize - beer.getBeerSize());
            return beer;
        }else{
            throw new EmptyStockException("HELP OUR BEERSTOCK IS EMPTY ****ARGHGHGHE* MONEY MONEY MONEY");
        }
    }

    public void fillStock(double litersToFill) throws OverflowedStockException{
            while(!isFull()){
                currentSize += 0.01;
                litersToFill-= 0.01;
                System.out.println("Filling up the Sorage current Status: " + getCurrentSize() + " of " + getSize());
            }
            if(isFull() && litersToFill >0){
            throw new OverflowedStockException("Stock is Full, you brought to much, there are: " + round(litersToFill) + " liters left.");
            }
    }

    /**
     * Returns the current amount of liter in the stock.
     * @return liters in double.
     */
    public double getCurrentSize() {
        return round(currentSize);
    }

    public double getSize() {
        return round(size);
    }

    public int getOrderCount() {
        return orderCount;
    }

    public boolean isFull(){
        if(getCurrentSize() == getSize()){
            return true;
        }
        return false;
    }

    private double round(double num){

        return Math.round( num * 100 ) / 100.0;
    }
}
