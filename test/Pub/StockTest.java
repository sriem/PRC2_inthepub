/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pub;

import PubExceptions.EmptyStockException;
import PubExceptions.OverflowedStockException;
import java.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sriem
 */
public class StockTest {

    private LocalTime time;
    private Stock stock;

    @Before
    public void setUp() {
        time = LocalTime.now();
        stock = new Stock( 30.57 );
    }

    @Test
    public void test_getMaximumSize() {
        assertEquals(30.57, stock.getSize(),0);
    }

    @Test
    public void test_getCurrentSize() {
        assertEquals(30.57, stock.getCurrentSize(),0);
    }

    @Test
    public void test_getBeer() {
        assertEquals(0, stock.getOrderCount());
        assertEquals(30.57, stock.getCurrentSize(),0);
        try {
            stock.getBeer(DrinkVolume.SMALL,LocalTime.now());
        } catch (EmptyStockException e) {
            e.printStackTrace();
        }

        assertEquals(30.00, stock.getCurrentSize(),0);
        assertEquals(1, stock.getOrderCount());
    }

    @Test(expected = EmptyStockException.class)
    public void test_EmptyStockException() throws EmptyStockException{
        Stock almostEmptyStock = new Stock(0.30);
        almostEmptyStock.getBeer(DrinkVolume.SMALL,LocalTime.now());

    }

    @Test
    public void test_Fillstock() {
        Stock aEmptyStock = new Stock(0.57);
        assertEquals(0.57, aEmptyStock.getCurrentSize(),0);


        try {
            aEmptyStock.getBeer(DrinkVolume.SMALL,LocalTime.now());
        } catch (EmptyStockException e) {
            e.printStackTrace();
        }

        assertEquals(0.00, aEmptyStock.getCurrentSize(),0);

        try {
            aEmptyStock.fillStock(0.57);
        } catch (OverflowedStockException e) {
            e.printStackTrace();
        }
        assertEquals(0.57, aEmptyStock.getCurrentSize(), 0);

    }

    @Test (expected = OverflowedStockException.class)
    public void test_OverflowedStockException_fill() throws OverflowedStockException {
        Stock a2EmptyStock = new Stock(0.57);
        assertEquals(0.57, a2EmptyStock.getCurrentSize(),0);
        try {
            a2EmptyStock.getBeer(DrinkVolume.SMALL,LocalTime.now());
        } catch (EmptyStockException e) {
            e.printStackTrace();
        }
        assertEquals(0.00, a2EmptyStock.getCurrentSize(),0);
        a2EmptyStock.fillStock(0.90);

        assertEquals(0.57, a2EmptyStock.getCurrentSize(), 0);
    }
}
