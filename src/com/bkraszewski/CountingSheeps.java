package com.bkraszewski;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class CountingSheeps {

    public static long calculateLatestNumberBeforeGoingToSleep(long n) {
        if (n == 0) {
            throw new IllegalArgumentException("Insomnia");
        }

        long multiplier = 1L;
        long newNumber = n;
        Set<Integer> allNumbers = new HashSet<>();
        while (allNumbers.size() < 10) {

            newNumber = n * multiplier;
            multiplier++;

            String number = String.valueOf(newNumber);
            number.chars()
                    .mapToObj(value -> (char) value)
                    .forEach(value -> {
                        allNumbers.add(value - 48);
                    });

            System.out.println(allNumbers);

        }

        return newNumber;
    }

    public static void main(String args[]) throws IOException {
        String fileName = "A-large-practice";

        String inputFile = String.format("./input/sheeps/%s.in", fileName);
        String outputFile = String.format("./output/sheeps/%s.out", fileName);

        GenericLineReader reader = new GenericLineReader();
        reader.mapInputToOutput(inputFile, outputFile, (line) -> {
            long n = Long.valueOf(line);

            try {
                long value = calculateLatestNumberBeforeGoingToSleep(n);
                return String.valueOf(value);


            } catch (IllegalArgumentException e) {
                return "INSOMNIA";
            }
        });
    }


}


