package com.bkraszewski;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TidyNumbersTest {

    @Test
    public void shouldTestFirstCase() {
        assertEquals(BigInteger.valueOf(129), TidyNumbers.findLastTidyNumberInRange(BigInteger.valueOf(132)));
    }

    @Test
    public void shouldTestSecondCase() {
        assertEquals(BigInteger.valueOf(999), TidyNumbers.findLastTidyNumberInRange(BigInteger.valueOf(1000)));
    }

    @Test
    public void shouldTestThirdCase() {
        assertEquals(BigInteger.valueOf(7), TidyNumbers.findLastTidyNumberInRange(BigInteger.valueOf(7)));
    }

    @Test
    public void shouldTestFourthCase() {
        assertEquals(new BigInteger("9999999999"), TidyNumbers.findLastTidyNumberInRange(new BigInteger("11111111110")));
    }

    @Test
    public void shouldTestBigCase() {
        assertEquals(new BigInteger("99999999999999999"), TidyNumbers.findLastTidyNumberInRange(new BigInteger("111111111111111110")));
    }

    @Test
    public void shoudlTestOtherBigCase(){
        assertEquals(new BigInteger("111111199999999"), TidyNumbers.findLastTidyNumberInRange(new BigInteger("111111222201458")));
    }

    @Test
    public void shouldFindZeroIndex() {
        assertEquals(0, TidyNumbers.findMaxUnSortedIndex("132"));
        assertEquals(-1, TidyNumbers.findMaxUnSortedIndex("123"));
        assertEquals(2, TidyNumbers.findMaxUnSortedIndex("1000"));
    }
}
