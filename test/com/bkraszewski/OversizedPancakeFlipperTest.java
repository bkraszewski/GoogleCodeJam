package com.bkraszewski;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OversizedPancakeFlipperTest {

    @Test
    void shouldSolveFirstCase() {
        assertEquals(3, OversizedPancakeFlipper.countFlips("---+-++-", 3));
    }

    @Test
    void shouldSolveSecondCase() {
        assertEquals(0, OversizedPancakeFlipper.countFlips("++++++", 4));

    }

    @Test
    void shouldSolveThirdCase() {
        assertEquals(-1, OversizedPancakeFlipper.countFlips("-+-+-", 4));
    }

}
