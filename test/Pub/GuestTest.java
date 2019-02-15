package Pub;

import PubExceptions.BobException;
import PubExceptions.DrunkenException;
import java.time.LocalTime;

import PubExceptions.EmptyStockException;
import PubExceptions.NotInsideException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sriem
 */
public class GuestTest {

    private LocalTime time;
    Guest g;

    @Before
    public void setUp() {
        time = LocalTime.now();
        g = new Guest(18,0.8, false);
    }

    @Test
    public void test_getAge() {
        assertEquals(18, g.getAge());
    }

    @Test
    public void test_getStomach() {
        Pub pub = new Pub(25);
        pub.welcomeGuest(g);
        Beer drink = null;
        try {
            drink = pub.orderBeer(g, DrinkVolume.PINT);
        } catch (NotInsideException e) {
            e.printStackTrace();
        } catch (BobException e) {
            e.printStackTrace();
        } catch (EmptyStockException e) {
            e.printStackTrace();
        }
        assertEquals(0.0, g.getStomach().getCurrentVolume(),0);
        assertEquals(0.8, g.getStomach().getMaxVolume(),0);
        try {
            g.drink(drink);
        } catch (DrunkenException e) {
            e.printStackTrace();
        }
        assertEquals(0.2, g.getStomach().getCurrentVolume(),0);

    }


    @Test
    public void drink_ok() {
        Pub pub = new Pub(25);
        pub.welcomeGuest(g);
        Beer drink = null;
        try {
            drink = pub.orderBeer(g, DrinkVolume.PINT);
        } catch (NotInsideException | EmptyStockException | BobException e) {
            e.printStackTrace();
        }

        assertEquals(0.0, g.getStomach().getCurrentVolume(),0);
        assertEquals(0.8, g.getStomach().getMaxVolume(),0);
        try {
            g.drink(drink);
        } catch (DrunkenException e) {
            e.printStackTrace();
        }
        
    }

    @Test (expected = DrunkenException.class)
    public void drink_exception_drunken() throws DrunkenException {
        Pub pub = new Pub(25);
        pub.welcomeGuest(g);
        Beer drink = null;

            for(int i = 0 ; i < 4; i++){
                try {
                    drink = pub.orderBeer(g, DrinkVolume.SMALL);
                    g.drink(drink);
                } catch (NotInsideException | EmptyStockException | BobException e) {
                    e.printStackTrace();
                }
            }

    }
    
    @Test (expected = BobException.class)
    public void test_getBob() throws BobException, EmptyStockException, NotInsideException {
        Pub pub = new Pub(20);
        Guest bob = new Guest(16, 0.8, false);
        pub.welcomeGuest(bob);
        pub.orderBeer(bob, DrinkVolume.PINT);

    }
}
