/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pub;

import PubExceptions.*;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represent a typical pub with guests and beer.
 *
 * @author Pia Erbrath
 */
public class Pub {

    /**
     * The beer stock of this pub.
     */
    private final Stock stock;
    /**
     * The barkeeper of the pub which taps the beer.
     */
    private final Barkeeper barkeeper;
    /**
     * The storage of every guest which is in the pub.
     */
    private final List<Guest> guests = new ArrayList<Guest>();

    /**
     * Creates a new pub with a barkeeper, a stock and no guests.
     *
     * @param stockSize which the pub should have.
     */

    public Pub(double stockSize) {
        this.stock = new Stock(stockSize);
        this.barkeeper = new Barkeeper();
    }

    /**
     * Returns the number of guests which are current in the pub.
     *
     * @return the amount in int.
     */
    public int getNumberOfGuests() {
        return guests.size();
    }

    /**
     * Adds a guests to the pub.
     *
     * @param guest which comes in.
     */
    public void welcomeGuest( Guest guest ) {
        guests.add( guest );
    }

    /**
     * Removes a guest from the pub.
     *
     * @param guest which needs to go / goes.
     */
    public void goodByeGuest( Guest guest ) {
        guests.remove( guest );
    }

    /**
     * Orders a beer for a certain guest.
     *
     * @param customer guest which order the beer.
     * @param type size of the beer.
     */
    public Beer orderBeer( Guest customer, DrinkVolume type ) throws NotInsideException,
             BobException, EmptyStockException{

        if(!guests.contains(customer)){
            throw new NotInsideException("You can not order from outside, of the Pub. Come in.");
        }else{
            return barkeeper.tapBeer(stock, customer, type);
        }
        
    }

    /**
     * Fills the stock.
     *
     * @param litersToFill liters(double) which should be added.
     */
    public void fillStock( double litersToFill ) throws OverflowedStockException{
            stock.fillStock(litersToFill);
    }

    public Stock getStock() {
        return stock;
    }

    /**
     * Returns the current volume of the stock.
     *
     * @return a double with the current stock size.
     */
    public double getCurrentStockVolume() {
        return stock.getCurrentSize();
    }

    public List<Guest> getGuests() {
        return guests;
    }
}
