package com.company;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Alina on 14.03.2017.
 */
public class FilesWork {

    public static List<String> readFromFile(String path) {
        List<String> words = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            stream.forEach(s -> words.addAll(Arrays.asList(s.split(" "))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    public static void writeToFile(List<String> output, String file) {
        Path path = Paths.get(file);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(String.valueOf(output));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
