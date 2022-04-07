package com.blablacar.moweralgorithm.infra;

import com.blablacar.moweralgorithm.infra.exception.WrongFileException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SimulationFileReaderTest {

    /**
     * Right input file test.
     */
    @Test
    public void should_read_file() {
        SimulationFileReader simulationFileReader = new SimulationFileReader();
        List<String> lines = simulationFileReader.read(getPath("mowers.txt"));
        assertEquals("5 5 1 2 N LFLFLFLFF 3 3 E FFRFFRFRRF 0 1 E FFFFLFFF 1 0 N FFFFF".trim(), String.join(" ", lines));
    }


    /**
     * Empty input file test.
     */
    @Test
    public void should_throw_exception_when_empty_file() {
        assertThrows(WrongFileException.class, () -> {
            SimulationFileReader simulationFileReader = new SimulationFileReader();
            simulationFileReader.read(getPath("empty_file.txt"));
        });

    }

    /**
     * un-existed input file test.
     */
    @Test
    public void should_throw_exception_when_file_does_not_exist() {
        assertThrows(NullPointerException.class, () -> {
            getPath("un_existed_file.txt");
        });

    }

    private String getPath(final String nameFile) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(nameFile)).getFile());
        return file.getAbsolutePath();
    }

}
