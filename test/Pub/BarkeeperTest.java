package Pub;

import PubExceptions.BobException;
import PubExceptions.EmptyStockException;
import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Pia Erbrath
 */
public class BarkeeperTest {

    private Barkeeper barkeeper;
    private Pub pub;
    private Guest guest;
    private Guest bob;

    @Before
    public void setUp() {
        pub = new Pub(25);
        guest = new Guest(18, 0.8, false);
        bob = new Guest(17,0.5, false);
        barkeeper = new Barkeeper();
    }

    @Test
    public void test_tapBeer_ok() {
        Beer result = null;
        try {
            result = barkeeper.tapBeer(pub.getStock(),guest, DrinkVolume.SMALL);
        } catch (BobException | EmptyStockException e) {
            e.printStackTrace();
        }
        assertNotNull(result);
    }

    @Test (expected = BobException.class)
    public void test_tapBeer_BobException() throws BobException{
        try {
            barkeeper.tapBeer(pub.getStock(),bob, DrinkVolume.SMALL);
        } catch (EmptyStockException e) {
            e.printStackTrace();
        }
    }


    @Test (expected = EmptyStockException.class)
    public void test_tapBeer_EmptyStockException() throws EmptyStockException {
        Pub emptyPub = new Pub(0);
        try {
            barkeeper.tapBeer(emptyPub.getStock(),guest, DrinkVolume.SMALL);
        } catch (BobException e) {
            e.printStackTrace();
        }
    }

}
