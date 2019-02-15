package Pub;

import PubExceptions.StomachException;

/**
 * This class represents the stomach of a guest.
 *
 * @author sriem
 */
public class Stomach {

    /**
     * The maximal volume the stomach could have.
     */
    private final double maxVolume;
    /**
     * The current volume the stomach have.
     */
    private double currentVolume;

    /**
     * Creates a new object of Stomach.
     * The currentVolume will be initialized with 0.
     *
     * @param maxVolume the stomach could have in double.
     */
    public Stomach( double maxVolume ) {
        this.maxVolume = maxVolume;
        currentVolume = 0;
    }

    /**
     * Returns the current volume of the stomach.
     *
     * @return a double
     */
    public double getCurrentVolume() {
        return currentVolume;
    }

    /**
     * Returns the maximum volume the stomach could have.
     *
     * @return a double
     */
    public double getMaxVolume() {
        return maxVolume;
    }

    /**
     * This method adds a drink to the stomach volume.
     *
     * @param drink the drink volume which should be added.
     *
     * @throws StomachException when the stomach is too full.
     */
    void add( Beer drink ) throws StomachException {
        double calculatedVolumeToDrink = currentVolume+drink.getBeerSize();
        if(calculatedVolumeToDrink > maxVolume){
            currentVolume += drink.getBeerSize();
            throw new StomachException("BUAHHHJHJ.... GRRRRR...*SPIT*");
        }else{
            currentVolume += drink.getBeerSize();
        }
        
    }

}
