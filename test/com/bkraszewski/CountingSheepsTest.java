package com.bkraszewski;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CountingSheepsTest {

    @Test()
    public void shouldSolveFirstTestCase() {
        try {

            CountingSheeps.calculateLatestNumberBeforeGoingToSleep(0);
            fail("Should throw an exception");

        } catch (IllegalArgumentException ex) {

        }
    }

    @Test
    public void shouldSolveSecondTestCase() {
        assertEquals(10, CountingSheeps.calculateLatestNumberBeforeGoingToSleep(1));
    }

    @Test
    public void shouldSolveThirdTestCase() {
        assertEquals(90, CountingSheeps.calculateLatestNumberBeforeGoingToSleep(2));
    }

    @Test
    public void shouldSolveFourthTestCase() {
        assertEquals(110, CountingSheeps.calculateLatestNumberBeforeGoingToSleep(11));
    }

    @Test
    public void shouldSolveFifthTestCase() {
        assertEquals(5076, CountingSheeps.calculateLatestNumberBeforeGoingToSleep(1692));
    }
}
