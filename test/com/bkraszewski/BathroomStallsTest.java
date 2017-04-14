package com.bkraszewski;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BathroomStallsTest {

    @Test
    public void shouldSolveFirstCase() {
        BathroomStalls.Cabin cabin = BathroomStalls.findLastOccupiedCabin(4, 2);
        assertEquals(1, cabin.maxLsRs);
        assertEquals(0, cabin.minLsRs);
    }

    @Test
    public void shouldSolveSecondCase() {
        BathroomStalls.Cabin cabin = BathroomStalls.findLastOccupiedCabin(5, 2);
        assertEquals(1, cabin.maxLsRs);
        assertEquals(0, cabin.minLsRs);
    }

    @Test
    public void shouldSolveThirdCase() {
        BathroomStalls.Cabin cabin = BathroomStalls.findLastOccupiedCabin(6, 2);
        assertEquals(1, cabin.maxLsRs);
        assertEquals(1, cabin.minLsRs);
    }

    @Test
    public void shouldSolveFourthCase() {
        BathroomStalls.Cabin cabin = BathroomStalls.findLastOccupiedCabin(1000, 1000);
        assertEquals(0, cabin.maxLsRs);
        assertEquals(0, cabin.minLsRs);
    }

    @Test
    public void shouldSolveFourthCaseBis() {
        BathroomStalls.Cabin cabin = BathroomStalls.findLastOccupiedCabin(4, 4);
        assertEquals(0, cabin.maxLsRs);
        assertEquals(0, cabin.minLsRs);
    }

    @Test
    public void shouldSolveFifthCase() {
        BathroomStalls.Cabin cabin = BathroomStalls.findLastOccupiedCabin(1000, 1);
        assertEquals(500, cabin.maxLsRs);
        assertEquals(499, cabin.minLsRs);
    }

    @Test
    public void shouldSolveForBiggerNumbers() {
        //782463 745891
        //752276 589396
        //500000 128
        BathroomStalls.Cabin cabin = BathroomStalls.findLastOccupiedCabin(752276, 589396);
        assertEquals(500, cabin.maxLsRs);
        assertEquals(499, cabin.minLsRs);
    }

    @Test
    public void testAddingToQueue() {
        PriorityQueue<BathroomStalls.Cabin> queue = new PriorityQueue<>(BathroomStalls.getComparator());

        BathroomStalls.Cabin c1 = new BathroomStalls.Cabin(0, 9);
        BathroomStalls.Cabin c2 = new BathroomStalls.Cabin(0, 4);
        BathroomStalls.Cabin c3 = new BathroomStalls.Cabin(5, 9);

        queue.add(c3);
        queue.add(c2);
        queue.add(c1);

        assertEquals(c1, queue.poll());
        assertEquals(c2, queue.poll());
        assertEquals(c3, queue.poll());
    }

    @Test
    public void testInternalCabin(){
        BathroomStalls.Cabin c1 = new BathroomStalls.Cabin(0, 9);
        c1.setupInternal();

        assertEquals(4, c1.minLsRs);
        assertEquals(5, c1.maxLsRs);

        BathroomStalls.Cabin c2 = new BathroomStalls.Cabin(0, 1);
        c2.setupInternal();

        assertEquals(0, c2.minLsRs);
        assertEquals(1, c2.maxLsRs);

        BathroomStalls.Cabin c3 = new BathroomStalls.Cabin(0, 0);
        c3.setupInternal();

        assertEquals(0, c3.minLsRs);
        assertEquals(0, c3.maxLsRs);
    }

//    @Test
//    public void wtf() {
//        int queueLenght = 745891;
//        int n = 782463;
////        Set<BathroomStalls.Cabin> set = new <>();
//        BathroomStalls.Cabin[] t = new BathroomStalls.Cabin[queueLenght];
//        for(int b = 0; b< queueLenght; b++){
////            set.add(new BathroomStalls.Cabin());
//            t[b] = new BathroomStalls.Cabin();
//        }
//
//        for (int a = 0; a < n; a++) {
//            for(BathroomStalls.Cabin cabin : t){
//
//            }
//        }
//    }


}
