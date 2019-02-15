package Pub;

import java.time.LocalTime;

/**
 * THis class represents a beer.
 * A beer will have an id, a size and a ordered time.
 * @author sriem
 */
public class Beer {
    private double beerSize;
    private int orderID;
    private LocalTime orderedTime;

    public Beer(DrinkVolume beerSize, LocalTime orderTime, int orderID) {
        this.orderedTime = orderTime;
        this.orderID = orderID;
        switch(beerSize){
            case PINT:
                this.beerSize = 0.2;
                break;
            case SMALL:
                this.beerSize = 0.57;
                break;
        }

    }

    public double getBeerSize(){return beerSize;}

    public int getOrderID() {
        return orderID;
    }

    public LocalTime getOrderedTime() {
        return orderedTime;
    }
}
