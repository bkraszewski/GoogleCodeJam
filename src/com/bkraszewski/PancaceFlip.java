package com.bkraszewski;

import java.io.IOException;

public class PancaceFlip {
    public static long countFlips(String stackOfPancaces) {

        char[] pancakes = stackOfPancaces.toCharArray();
        int flips = 0;

        for (int a = pancakes.length - 1; a >= 0; a--) {
            char current = pancakes[a];

            if (current == '+') {
                continue;
            } else {
                //flip current view and all other in the future
                for (int b = a; b >= 0; b--) {
                    pancakes[b] = swap(pancakes[b]);
                }

                flips++;
            }
        }

        return flips;

    }

    private static char swap(char pancake) {
        return pancake == '+' ? '-' : '+';
    }


    public static void main(String args[]) throws IOException {
//        String fileName = "B-small-practice";
        String fileName = "B-large-practice";

        String inputFile = String.format("./input/pancakes/%s.in", fileName);
        String outputFile = String.format("./output/pancakes/%s.out", fileName);

        GenericLineReader reader = new GenericLineReader();
        reader.mapInputToOutput(inputFile, outputFile, (line) ->
                String.valueOf(countFlips(line))
        );
    }
}
