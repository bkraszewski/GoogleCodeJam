package com.bkraszewski;

import org.junit.jupiter.api.Test;
import sun.jvm.hotspot.utilities.Assert;

import static org.junit.jupiter.api.Assertions.*;

class PancaceFlipTest {

    @Test
    public void shouldSolveFirstTestCase() {
        assertEquals(1, PancaceFlip.countFlips("-"));
    }

    @Test
    public void shouldSecondFirstTestCase() {
        assertEquals(1, PancaceFlip.countFlips("-+"));
    }

    @Test
    public void shouldThirdFirstTestCase() {
        assertEquals(2, PancaceFlip.countFlips("+-"));
    }

    @Test
    public void shouldFourthFirstTestCase() {
        assertEquals(0, PancaceFlip.countFlips("+++"));
    }

    @Test
    public void shouldFifthFirstTestCase() {
        assertEquals(3, PancaceFlip.countFlips("--+-"));
    }
}
