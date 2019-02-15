package Pub;

import PubExceptions.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class which takes a simulation of a pub.
 *
 * @author Pia Erbrath
 */
public class PubSimulator {


    private static List<Guest> guests;
    private Guest guest;
    private Pub pub;
    private int notInsideCount, bobCount, drunkenCount, emptyStockCount;


    /**
     * Randomizer to create random int, double and boolean.
     */
    private Random rand = new Random();

    public PubSimulator() {
        this.notInsideCount = 0;
        this.bobCount = 0;
        this.drunkenCount = 0;
        this.emptyStockCount = 0;

        this.guests = new ArrayList<Guest>();
        this.pub = new Pub(rand.nextInt(100) + 25);


        int rnd = (rand.nextInt(150) + 50);
        for(int i = 0 ; i < rnd ;i++){
            int age = (rand.nextInt(70) + 16); // get random age between 16 and 70 years
            double randomStomachVolume = 1.2 + (1.3 - 1.2) * rand.nextDouble(); //get a random double Value
            double  roundedStomachVolume = Math.round( randomStomachVolume * 100 ) / 100.0; //round the randomized double value to 2 numbers after the point

            guest = new Guest(age,roundedStomachVolume, false);
            guests.add(guest);
        }
        System.out.println("InitialGuestCount is: " + guests.size());

    }








    /**
     * Method which starts a simulation.
     *
     * @param args the command line arguments
     */
    public static void main( String[] args ) {
         try{
             new PubSimulator().run();
         } catch (NullPointerException e) {
             e.printStackTrace();
         }

    }

    enum PubActivity {
        WELCOME(0), ORDER(1), DRINK(2), CHECK_STOCK(3), FILL_STOCK(4), GOODBYE(5);

        private final int activity;

        PubActivity(int activity) {
            this.activity = activity;
        }

        public int getActivity() {
            return this.activity;
        }
    }

    public void run() {
        int runTimeLifeCylce = 20000;

        for (int i = 0; i < runTimeLifeCylce; i++){
            int rndActivity = rand.nextInt(5);

            switch(rndActivity){
                case 0:
                    doRandomThings(PubActivity.WELCOME);
                    break;
                case 1:
                    doRandomThings(PubActivity.ORDER);
                    break;
                case 2:
                    doRandomThings(PubActivity.DRINK);
                    break;
                case 3:
                    doRandomThings(PubActivity.CHECK_STOCK);
                    break;
                case 4:
                    doRandomThings(PubActivity.FILL_STOCK);
                    break;
                case 5:
                    doRandomThings(PubActivity.GOODBYE);
                    break;


            }

        }
        System.out.println("###########################################################");
        System.out.println( "notInsideCount:  " + notInsideCount);
        System.out.println( "drunkenCount:  " + drunkenCount);
        System.out.println( "emptyStockCount:  " + emptyStockCount);
        System.out.println( "visitorsCountInsidePub:  " + pub.getNumberOfGuests());
        System.out.println( "orderCount:  " + pub.getStock().getOrderCount());
        System.out.println( "bobCount:  " + bobCount);
        System.out.println( "simulation done" );
    }
    private static final Logger LOG
            = Logger.getLogger( PubSimulator.class.getName() );


    private void doRandomThings(PubActivity p){
        switch(p){
            case WELCOME:
                randomWelcome(guests, pub);
                break;
            case ORDER:
                randomOrderingBeer(guests, pub);
                break;
            case DRINK:
                randomDrinkBeer(guests, pub);
                break;
            case CHECK_STOCK:
                double stockValue = randomCheckPubStock(pub);
                if(stockValue<0.5){
                    doRandomThings(PubActivity.FILL_STOCK);
                }
                break;
            case FILL_STOCK:
                randomFillStock(pub);
                break;
            case GOODBYE:
                randomGoodBye(pub);
                break;
        }

    }

    /**
     * Orders for a random guest a random beer.
     *
     * @param guests List with guests of the pub.
     * @param pub where beer is ordered.
     */
    void randomOrderingBeer( List<Guest> guests, Pub pub ) {
        Guest aRndGuest = guests.get(rand.nextInt(guests.size()));

        try {
            pub.orderBeer(aRndGuest,getRndDrink());
        } catch (NotInsideException e) {
            notInsideCount++;
            e.printStackTrace();
        } catch (BobException e) {
            bobCount++;
            e.printStackTrace();
        } catch (EmptyStockException e) {
            emptyStockCount++;
            e.printStackTrace();
        }

    }

    /**
     * Guest orders a Random beer and drinks it.
     *
     * @param guests List with guests of the pub.
     * @param pub where beer is ordered.
     */
    void randomDrinkBeer( List<Guest> guests, Pub pub ) {
        Guest aRndGuest = guests.get(rand.nextInt(guests.size()));

        Beer beer = null;
        try {
            beer = pub.orderBeer(aRndGuest,getRndDrink());
            try {
                aRndGuest.drink(beer);
            } catch (DrunkenException e) {
                e.printStackTrace();
            }
        } catch (NotInsideException e) {
            notInsideCount++;
            e.printStackTrace();
        } catch (BobException e) {
            bobCount++;
            e.printStackTrace();
        } catch (EmptyStockException e) {

            e.printStackTrace();
        }


    }


    /**
     * Add a new guest to the pub.
     *
     * @param guests list with all guests if the pub.
     * @param pub where a guest should be added.
     */
    void randomWelcome( List<Guest> guests, Pub pub ) {
        Guest aRndGuest = guests.get(rand.nextInt(guests.size()));
        pub.welcomeGuest(aRndGuest);
        
    }

    /**
     * Remove a random guest from the pub.
     *
     * @param guests list with all guests of the pub.
     * @param pub where the guest should be removed.
     */
    private void randomGoodBye( Pub pub ) {
        List<Guest> pubGuestList = pub.getGuests();
        int numberOfGuests = pubGuestList.size();
        Guest dareGuy = pubGuestList.get(rand.nextInt(numberOfGuests));

        pub.goodByeGuest(dareGuy);
        
    }


    /**
     * Checks stock of a Pub
     *
     * @param pubToCheck where the stock should be checked
     */
    private double randomCheckPubStock(Pub pubToCheck) {

        return pubToCheck.getCurrentStockVolume();
    }

    /**
     * Fill the stock of a pub with a random volume.
     *
     * @param pub where the stock should be filled.
     */
    void randomFillStock( Pub pub ) {
        double maxSizeOfStock = pub.getStock().getSize();
        double randomFillVolume = 10.0 + (maxSizeOfStock - 10.0) * rand.nextDouble(); //get a random double Value
        double  roundedFillVolume = Math.round( randomFillVolume * 100 ) / 100.0; //round the randomized double value to 2 numbers after the point

        try {
            pub.fillStock(roundedFillVolume);
        } catch (OverflowedStockException e) {
            e.printStackTrace();
        }

    }

    private DrinkVolume getRndDrink(){
        int rndNr = rand.nextInt(2);
        if(rndNr<2){
            return DrinkVolume.SMALL;
        }else{
            return DrinkVolume.PINT;
        }
    }


}
