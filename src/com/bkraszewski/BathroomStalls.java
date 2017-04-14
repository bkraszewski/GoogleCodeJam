package com.bkraszewski;

import java.io.IOException;
import java.util.*;

public class BathroomStalls {

    private static Comparator<Cabin> comparator;

    public static Cabin findLastOccupiedCabin(int numberOfStalls, int queueLenght) {

        Cabin[] cabins = initializeCabins((int) numberOfStalls);
        SortedArrayList<Integer> occupied = new SortedArrayList<>();
        PriorityQueue<Cabin> best = new PriorityQueue<Cabin>(1, getComparator()) {
            public boolean add(final Cabin e) {
                if (e == null) {
                    throw new NullPointerException("e is null.");
                }
                if (10 <= size()) {
                    final Cabin firstElm = peek();
                    if (comparator.compare(e, firstElm) < 1) {
                        return false;
                    } else {
                        poll();

                    }
                }
                super.add(e);
                return true;
            }
        };

        occupied.add(0);
        int lastAdded = numberOfStalls + 1;
        Cabin nextCabin = null;

        for (int a = 0; a < queueLenght; a++) {
            occupied.insertSorted(lastAdded);

            //Collections.sort(occupied);

            int prev = findFirstSmallerInOccupiedList(occupied, lastAdded);
            int next = findFirstBiggerInOccupiedList(occupied, lastAdded);

            updateCabinsInRange(prev, lastAdded, cabins, best);
            updateCabinsInRange(lastAdded, next, cabins, best);

            nextCabin = best.poll();
            nextCabin.occupied = true;
            lastAdded = nextCabin.index;
        }

        return nextCabin;
    }

    private static Comparator<Cabin> getComparator() {
        if (comparator == null) {
            comparator = (o1, o2) -> {
                if (o2.p > o1.p) {
                    return 1;
                } else if (o2.p == o1.p) {
                    if (o2.q == o1.q) {
                        return o1.index - o2.index;
                    }

                    return o2.q - o1.q;
                }

                return o2.p - o1.p;
            };
        }

        return comparator;
    }

    private static void updateCabinsInRange(int prev, int next, Cabin[] cabins, PriorityQueue<Cabin> best) {
        int left = 0;
        int right = next - prev - 2;

        for (int a = prev + 1; a < next; a++) {
            Cabin cabin = cabins[a];
            cabin.ls = left++;
            cabin.rs = right--;

            cabin.updateInternal();
            best.remove(cabin);
            best.add(cabin);
//            if (best.size() > 10) {
//                PriorityQueue<Cabin>copy = new Pri
//                Cabin top = best.poll();
//                best.clear();
//                best.add(top);
//            }
        }
    }

    private static int findFirstBiggerInOccupiedList(List<Integer> occupied, int lastAdded) {
        //TODO binary search

        int lastIndex = Collections.binarySearch(occupied, lastAdded);
        for (int a = lastIndex + 1; a < occupied.size(); a++) {
            if (occupied.get(a) > lastAdded) {
                return occupied.get(a);
            }
        }

        return lastAdded;
    }

    private static int findFirstSmallerInOccupiedList(List<Integer> occupied, int lastAdded) {
        //TODO binary search

        int lastIndex = Collections.binarySearch(occupied, lastAdded);
        for (int a = lastIndex - 1; a > -1; a--) {
            if (occupied.get(a) <= lastAdded) {
                return occupied.get(a);
            }
        }

        return lastAdded;
    }

    private static Cabin[] initializeCabins(int numberOfStalls) {
        Cabin[] cabins = new Cabin[numberOfStalls + 2];
        for (int a = 0; a < cabins.length; a++) {
            Cabin cabin = new Cabin(a);
            cabin.occupied = a == 0 || a == cabins.length - 1;
            cabins[a] = cabin;
        }
        return cabins;
    }


    public static class Cabin {
        public boolean occupied = false;
        public long maxLsRs = 0;
        public long minLsRs = 0;

        public int ls = 0;
        public int rs = 0;

        public int p;
        public int q;
        public int index = 0;

        public static int maxIndex = 0;

        public Cabin(int index) {
            this.index = index;
        }

        @Override
        public String toString() {
            return "Cabin{" +
                    "occupied=" + occupied +
                    ", ls=" + ls +
                    ", rs=" + rs +
                    ", p=" + p +
                    ", q=" + q +
                    ", index=" + index +
                    '}';
        }

        public void updateInternal() {
            p = Math.min(ls, rs);
            q = Math.max(ls, rs);

            minLsRs = p;
            maxLsRs = q;
        }
    }

    static class SortedArrayList<T> extends ArrayList<T> {

        @SuppressWarnings("unchecked")
        public void insertSorted(T value) {
            add(value);
            Comparable<T> cmp = (Comparable<T>) value;
            for (int i = size() - 1; i > 0 && cmp.compareTo(get(i - 1)) < 0; i--)
                Collections.swap(this, i, i - 1);
        }
    }


    public static void main(String args[]) throws IOException {
//        String fileName = "B-small-practice";
        String fileName = "C-small-2-attempt0";

        String inputFile = String.format("./input/bathrooms/%s.in", fileName);
        String outputFile = String.format("./output/bathrooms/%s.out", fileName);

        GenericLineReader reader = new GenericLineReader();
        reader.mapInputToOutput(inputFile, outputFile, (line) ->
                {
                    String[] params = line.split("\\s+");
                    int stalls = Integer.valueOf(params[0]);
                    int queue = Integer.valueOf(params[1]);

                    Cabin cabin = findLastOccupiedCabin(stalls, queue);
                    String result = String.format("%d %d", cabin.maxLsRs, cabin.minLsRs);
                    System.out.println(result);
                    return result;
                }
        );
    }
}
