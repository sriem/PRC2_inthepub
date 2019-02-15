package Pub;

import PubExceptions.StomachException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.*;

/**
 *
 * @author sriem
 */
public class StomachTest {
    
    Stomach sto;
    
    @Before
    public void setUp() {
        sto = new Stomach(0.9);
    }
    LocalTime time = LocalTime.now();

    @Test
    public void test_getVolume() {
        assertEquals(0.0, sto.getCurrentVolume(),0);
    }

    @Test
    public void test_getMaxVolume() {
        assertEquals(0.9, sto.getMaxVolume(),0);
    }

    @Test
    public void test_addBeer_ok() {
        Beer pint = new Beer(DrinkVolume.PINT, time, 0);
        try {
            sto.add(pint);
        } catch (StomachException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = StomachException.class)
    public void test_addBeer_Exception() throws StomachException {
        Beer beer1 = new Beer(DrinkVolume.SMALL, time, 0);
        Beer beer2 = new Beer(DrinkVolume.SMALL, time, 0);
        sto.add(beer1);
        sto.add(beer2);
    }

    
}
