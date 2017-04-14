package com.bkraszewski;

import java.io.IOException;

public class OversizedPancakeFlipper {
    public static long countFlips(String pancakesStack, long flipperSize) {
        char[] pancakes = pancakesStack.toCharArray();
        long flips = 0L;

        for (int a = 0; a < pancakes.length; a++) {
            char current = pancakes[a];

            if (current == '+') {
                continue;
            } else {
                if (a + flipperSize > pancakes.length) {
                    //can't do a flip
                    System.out.println("Can,t go more " + String.valueOf(pancakes));
                    return -1;
                } else {

                    //flip k items
                    for (int b = a; b < a + flipperSize; b++) {
                        pancakes[b] = swap(pancakes[b]);
                    }

                    flips++;
                }
            }

            System.out.println(String.valueOf(pancakes));
        }

        return flips;
    }

    private static char swap(char pancake) {
        return pancake == '+' ? '-' : '+';
    }

    public static void main(String args[]) throws IOException {
//        String fileName = "B-small-practice";
        String fileName = "A-large";

        String inputFile = String.format("./input/pancakes/%s.in", fileName);
        String outputFile = String.format("./output/pancakes/%s.out", fileName);

        GenericLineReader reader = new GenericLineReader();
        reader.mapInputToOutput(inputFile, outputFile, (line) ->
                {
                    String[] arr = line.split("\\s+");
                    String pancakes = arr[0];
                    long flipperSize = Long.valueOf(arr[1]);

                    long result = countFlips(pancakes, flipperSize);
                    if (result < 0) {
                        return "IMPOSSIBLE";
                    }

                    return String.valueOf(result);
                }
        );
    }
}
