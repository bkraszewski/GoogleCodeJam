package com.bkraszewski;

import java.io.IOException;
import java.util.*;

public class BathroomStalls {

    public static Comparator<Cabin> getComparator() {
        return (o1, o2) -> {
            long diff1 = o1.stop - o1.start;
            long diff2 = o2.stop - o2.start;

            if (diff1 == diff2) {
                return (int)(o1.start - o2.start);
            }

            return (int)(diff2 - diff1);
        };
    }

    public static Cabin findLastOccupiedCabin(long numberOfStalls, long queueLenght) {

        Cabin cabin = new Cabin(0, numberOfStalls - 1);
        PriorityQueue<Cabin> cabins = new PriorityQueue<>(getComparator());

        cabins.add(cabin);

        for (int a = 0; a < queueLenght; a++) {

            cabin = cabins.poll();

            if (cabin.stop - cabin.start == 0) {
                //nothing to do, only place in range occupied
            } else {
                long current = cabin.start + (cabin.stop - cabin.start) / 2;
                Cabin left = new Cabin(cabin.start, current - 1);
                Cabin right = new Cabin(current + 1, cabin.stop);

                addIfCorrect(left, cabins);
                addIfCorrect(right, cabins);
            }
        }

        cabin.setupInternal();
        return cabin;
    }

    public static void addIfCorrect(Cabin cabin, PriorityQueue<Cabin> queue) {
        if (cabin.start <= cabin.stop) {
            queue.add(cabin);
        }
    }


    public static class Cabin {
        private long start;
        private long stop;
        public long maxLsRs = 0;
        public long minLsRs = 0;

        public Cabin(long start, long stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public String toString() {
            return "Cabin{" +
                    "start=" + start +
                    ", stop=" + stop +
                    '}';
        }

        public void setupInternal() {
            long ls;
            long rs;

            long mid = start + (stop - start) / 2;

            ls = mid - start;
            rs = stop - mid;

            minLsRs = Math.min(ls, rs);
            maxLsRs = Math.max(ls, rs);
        }
    }


    public static void main(String args[]) throws IOException {
//        String fileName = "B-small-practice";
        String fileName = "C-large-practice";

        String inputFile = String.format("./input/bathrooms/%s.in", fileName);
        String outputFile = String.format("./output/bathrooms/%s.out", fileName);

        GenericLineReader reader = new GenericLineReader();
        reader.mapInputToOutput(inputFile, outputFile, (line) ->
                {
                    String[] params = line.split("\\s+");
                    long stalls = Long.valueOf(params[0]);
                    long queue = Long.valueOf(params[1]);

                    Cabin cabin = findLastOccupiedCabin(stalls, queue);
                    String result = String.format("%d %d", cabin.maxLsRs, cabin.minLsRs);
                    System.out.println(result);
                    return result;
                }
        );
    }
}
