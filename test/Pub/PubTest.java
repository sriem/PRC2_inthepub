package Pub;

import PubExceptions.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * The pub should be professional, that is all exceptions that could happen
 * should be dealt with by the pub, otherwise the pub will be closed (shut down,
 * program stops by the JVM).
 *
 * @author sriem
 */
public class PubTest {

    private Pub pub;

    @Before
    public void setUp() {
        pub = new Pub( 30.0 );
    }

    @Test
    public void get_current_stock_volume() {
        assertEquals( "size should be 30,0", 30.0,
                pub.getCurrentStockVolume(), 0.001 );
    }

    /**
     * Fill stock with no exception occurring.
     */
    @Test
    public void fill_stock_without_exception(){

    }

    /**
     * Fill stock with pub overflow,
     */
    @Test (expected = OverflowedStockException.class)
    public void fill_stock_with_exception() throws OverflowedStockException {
        pub.fillStock(100.0);
    }

    @Test
    public void add_guest() {

        Guest guest = new Guest(18,0.8,false);
        assertEquals(0,pub.getNumberOfGuests());
        pub.welcomeGuest(guest);
        assertEquals(1,pub.getNumberOfGuests());

    }

    @Test
    public void sayGoodBye_to_guest() {
        Guest guest = new Guest(18,0.8,false);
        assertEquals(0,pub.getNumberOfGuests());
        pub.welcomeGuest(guest);
        assertEquals(1,pub.getNumberOfGuests());
        pub.goodByeGuest(guest);
        assertEquals(0,pub.getNumberOfGuests());
    }

    @Test
    public void test_getNumberOfGuests() {
        Guest guest = new Guest(18,0.8,false);
        assertEquals(0,pub.getNumberOfGuests());
        pub.welcomeGuest(guest);
        assertEquals(1,pub.getNumberOfGuests());
    }

    @Test (expected = NotInsideException.class)
    public void test_orderBeer_without_being_in() throws EmptyStockException,
            BobException, NotInsideException {
        Guest guest = new Guest(18,0.8,false);
            pub.orderBeer(guest, DrinkVolume.SMALL);

    }

    @Test
    public void test_orderBeer_without_Exceptions() {
        Guest guest = new Guest(18,0.8,false);
        pub.welcomeGuest(guest);
        try {
            pub.orderBeer(guest, DrinkVolume.SMALL);
        } catch (NotInsideException e) {
            e.printStackTrace();
        } catch (BobException e) {
            e.printStackTrace();
        } catch (EmptyStockException e) {
            e.printStackTrace();
        }
    }

    @Test (expected = BobException.class)
    public void test_orderBeer_with_BobException() throws EmptyStockException, BobException,
            NotInsideException {
        Guest guest = new Guest(17,0.8,false);
        pub.welcomeGuest(guest);
        pub.orderBeer(guest, DrinkVolume.SMALL);
    }



    @Test (expected = DrunkenException.class)
    public void test_orderBeer_with_DrunkenException() throws BobException, EmptyStockException,
            NotInsideException, DrunkenException {
        Guest guest = new Guest(18,0.4,false);
        pub.welcomeGuest(guest);

        Beer orderPint = pub.orderBeer(guest, DrinkVolume.SMALL);
        guest.drink(orderPint);

        assertEquals(0.25, guest.getStomach());

        Beer orderSmall = pub.orderBeer(guest, DrinkVolume.SMALL);
        guest.drink(orderSmall);
    }

    @Test (expected = EmptyStockException.class)
    public void test_orderBeer_with_EmptyStockException() throws BobException, EmptyStockException,
            NotInsideException, DrunkenException {
        Pub pubWithEmptystock = new Pub(0);
        Guest guest = new Guest(18,0.8,false);
        pubWithEmptystock.welcomeGuest(guest);
        Beer orderPint = pubWithEmptystock.orderBeer(guest, DrinkVolume.SMALL);
        guest.drink(orderPint);
    }

}
