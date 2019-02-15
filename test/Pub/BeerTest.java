package Pub;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sriem
 */
public class BeerTest {

    private static final Clock CLOCK = Clock.fixed( Instant.parse( "2018-01-15T12:30:12.00Z" ), ZoneId.systemDefault() );
    private LocalTime time;

    @Before
    public void setUp() {
        time = LocalTime.now(CLOCK);
    }

    @Test
    public void test_getVolume() {
        Beer pint = new Beer(DrinkVolume.PINT, time, 1);
        assertEquals(0.2, pint.getBeerSize(),0);
        Beer small = new Beer(DrinkVolume.PINT, time, 1);
        assertEquals(0.2, small.getBeerSize(),0);
    }

    @Test
    public void test_getTime() {
        Beer beerTime = new Beer(DrinkVolume.PINT, time, 0);
        assertEquals(time, beerTime.getOrderedTime());
    }

    @Test
    public void test_getOrderID() {
        Beer beerOrderID = new Beer(DrinkVolume.PINT, time, 0);
        assertEquals(0, beerOrderID.getOrderID());
    }

}
