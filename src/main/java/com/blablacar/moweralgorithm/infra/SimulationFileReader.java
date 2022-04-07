package com.blablacar.moweralgorithm.infra;


import com.blablacar.moweralgorithm.infra.exception.WrongFileException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class SimulationFileReader {

    public SimulationFileReader() {
    }

    public List<String> read(final String filePath) throws WrongFileException {

        File file = new File(filePath);
        if (!file.exists() || file.isDirectory()) {
            throw new WrongFileException(String.format("Wrong input path file parameter (NOT FOUND): %s", filePath));
        }
        String[] lines;
        try {
            Stream<String> linesStream = Files.lines(Paths.get(filePath)).filter(line -> line.length() > 0);
            lines = linesStream.toArray(String[]::new);
            linesStream.close();
        } catch (IOException e) {
            throw new WrongFileException(String.format("Wrong input path file parameter (I/O Error): %s", filePath));
        }
        var count = lines.length;
        if (count == 0) {
            throw new WrongFileException(String.format("Wrong input path file parameter (Empty File): %s", filePath));
        } else if (count % 2 == 0 || count == 1) {
            throw new WrongFileException(String.format("Wrong input path file parameter (File Format): %s", filePath));
        }
        return Arrays.asList(lines);
    }

    public String getPath(final String nameFile) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(nameFile)).getFile());
        return file.getAbsolutePath();
    }
}

