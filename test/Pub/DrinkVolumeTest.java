package Pub;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author sriem
 */
public class DrinkVolumeTest {
    LocalTime time = LocalTime.now();
    @Test
    public void testValues() {
        assertNotNull(DrinkVolume.values());

    }

    @Test
    public void testValueOf() {
        assertEquals(DrinkVolume.PINT, DrinkVolume.valueOf("PINT"));
        assertEquals(DrinkVolume.SMALL, DrinkVolume.valueOf("SMALL"));
    }

    @Test
    public void size_of_pint() {
        Beer pint = new Beer(DrinkVolume.PINT, time, 0);
        assertEquals(0.2, pint.getBeerSize(),0);
        assertNotEquals(0.1, pint.getBeerSize(), 0);

    }

    @Test
    public void size_of_small() {
        Beer small = new Beer(DrinkVolume.SMALL, time, 0);
        assertEquals(0.57, small.getBeerSize(),0);
        assertNotEquals(0.1, small.getBeerSize(), 0);
    }



}
