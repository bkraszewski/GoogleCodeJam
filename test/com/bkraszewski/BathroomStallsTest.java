package com.bkraszewski;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void wtf(){
        PriorityQueue<BathroomStalls.Cabin> best = createBestComparator();

        BathroomStalls.Cabin c1 = new BathroomStalls.Cabin(1);
        c1.ls = 0;
        c1.rs = 3;
        c1.updateInternal();

        BathroomStalls.Cabin c2 = new BathroomStalls.Cabin(2);
        c2.ls = 1;
        c2.rs = 2;
        c2.updateInternal();

        BathroomStalls.Cabin c3 = new BathroomStalls.Cabin(3);
        c3.ls = 2;
        c3.rs = 1;
        c3.updateInternal();

        BathroomStalls.Cabin c4 = new BathroomStalls.Cabin(4);
        c4.ls = 3;
        c4.rs = 0;
        c4.updateInternal();

        best.add(c1);
        best.add(c2);
        best.add(c3);
        best.add(c4);

        BathroomStalls.Cabin r = best.poll();
        assertEquals(r.index, 2);


        c1.ls = 0;
        c1.rs = 0;
        c1.updateInternal();
        best.remove(c1);
        best.add(c1);

        c3.ls = 0;
        c3.rs = 1;
        c3.updateInternal();
        best.remove(c3);
        best.add(c3);

        c4.ls = 1;
        c4.rs = 0;
        c4.updateInternal();
        best.remove(c4);
        best.add(c4);

        r = best.poll();
        assertEquals(r.index, 3);

    }

    private PriorityQueue<BathroomStalls.Cabin> createBestComparator() {
        return new PriorityQueue<BathroomStalls.Cabin>((o1, o2) -> {
                if (o2.p > o1.p) {
                    return 1;
                } else if (o2.p == o1.p) {
                    if (o2.q == o1.q) {
                        return o1.index - o2.index;
                    }

                    return o2.q - o1.q;
                }

                return o2.p - o1.p;
            });
    }
}
