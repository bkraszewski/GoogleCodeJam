package com.bkraszewski;

import java.io.IOException;
import java.math.BigInteger;

public class TidyNumbers {
    public static BigInteger findLastTidyNumberInRange(BigInteger n) {

        String current = n.toString();
        int firstFailedIndex;
        while ((firstFailedIndex = findFirst(current)) != -1) {


            BigInteger num = new BigInteger(current);
            current = num.toString();
            char[] arr = current.toCharArray();
            for (int a = firstFailedIndex + 1 ; a < arr.length; a++) {
                arr[a] = '9';
            }

            num = new BigInteger(new String(arr));

            int pow = current.length() - firstFailedIndex - 1;
            long substractor = (long) Math.pow(10, pow  );

            num = num.subtract(BigInteger.valueOf(substractor));
            current = num.toString();
        }

        return new BigInteger(current);
    }

    public static int findFirst(String current) {
        for (int a = 1; a < current.length(); a++) {

            if (current.charAt(a) >= current.charAt(a - 1)) {
                continue;
            } else {
                return a - 1;
            }
        }

        return -1;
    }


    public static void main(String args[]) throws IOException {
//        String fileName = "B-small-practice";
        String fileName = "B-large-practice";

        String inputFile = String.format("./input/numbers/%s.in", fileName);
        String outputFile = String.format("./output/numbers/%s.out", fileName);

        GenericLineReader reader = new GenericLineReader();
        reader.mapInputToOutput(inputFile, outputFile, (line) ->
                {

                    BigInteger number = new BigInteger(line);
                    BigInteger result = findLastTidyNumberInRange(number);

                    return result.toString();
                }
        );
    }
}
