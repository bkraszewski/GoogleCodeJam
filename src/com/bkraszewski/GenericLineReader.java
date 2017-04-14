package com.bkraszewski;

import java.io.*;
import java.util.function.Function;

public class GenericLineReader {
    public void mapInputToOutput(String inputFile, String outputFile, Function<String, String> mapper) throws IOException {
        File file = new File(inputFile);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String count = reader.readLine();
        int input = Integer.valueOf(count);
        StringBuilder builder = new StringBuilder();

        File output = new File(outputFile);
        BufferedWriter writer = new BufferedWriter(new FileWriter(output));

        for (int a = 0; a < input; a++) {
            String line = reader.readLine();
            String out = mapper.apply(line);
            String formattedOut = String.format("Case #%d: %s", a + 1, out);
            writer.write(formattedOut);
            writer.newLine();

            builder.append(formattedOut);
            builder.append("\n");

        }

        writer.close();
        reader.close();
    }
}
