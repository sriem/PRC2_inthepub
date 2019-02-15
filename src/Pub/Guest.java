package Pub;

import PubExceptions.DrunkenException;
import PubExceptions.StomachException;

/**
 * This class represent a typical guest of a pub. He / she want drink beer and
 * can be drunken.
 *
 * @author Pia Erbrath
 */
public class Guest {
    private boolean isDrunk;
    private int age;
    private Stomach stomach;

    public Guest(int age, double stomachMaxVolume, boolean isDrunk) {
        this.isDrunk = isDrunk;
        this.age = age;
        this.stomach = new Stomach(stomachMaxVolume);
    }

    /**
     * *
     * Drinks a beer, optionally get drunk, Hickup.
     *
     * @param beer to consume
     * @throws DrunkenException when overfilled.
     */
    public void drink( Beer beer ) throws DrunkenException {
        double currentVolume = stomach.getCurrentVolume();
        double maxStomachVolume = stomach.getMaxVolume();
        double volumeToDrink = beer.getBeerSize();
        double calculatedVolumeToDrink = currentVolume+volumeToDrink;

        if(calculatedVolumeToDrink > maxStomachVolume){
            isDrunk = true;
            throw new DrunkenException("Iam Drunk");
        } else {
            try {
                stomach.add(beer);
            } catch (StomachException e) {
                e.printStackTrace();
            }

        }
        
    }

    public boolean isDrunk() {
        return isDrunk;
    }

    public int getAge() {
        return age;
    }

    public Stomach getStomach() {
        return stomach;
    }
}
