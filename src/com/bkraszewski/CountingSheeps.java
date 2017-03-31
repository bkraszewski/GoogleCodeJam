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

        File file = new File(String.format("./input/sheeps/%s.in", fileName));
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String count = reader.readLine();
        int input = Integer.valueOf(count);
        StringBuilder builder = new StringBuilder();

        File output = new File(String.format("./output/sheeps/%s.out", fileName));
        BufferedWriter writer = new BufferedWriter(new FileWriter(output));

        for (int a = 0; a < input; a++) {
            String line = reader.readLine();
            long n = Long.valueOf(line);

            try {
                long value = calculateLatestNumberBeforeGoingToSleep(n);
                String out = String.format("Case #%d: %d", a + 1, value);
                builder.append(out);
                writer.write(out);

            } catch (IllegalArgumentException e) {
                String out = String.format("Case #%d: INSOMNIA", a + 1);
                writer.write(out);
                builder.append(out);

            } finally {
                builder.append("\n");
                writer.newLine();
            }
        }

        writer.close();
        reader.close();
        System.out.println(builder);
    }


}
