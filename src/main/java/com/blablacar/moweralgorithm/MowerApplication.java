package com.blablacar.moweralgorithm;

import com.blablacar.moweralgorithm.application.Simulation;
import com.blablacar.moweralgorithm.application.SimulationFactory;
import com.blablacar.moweralgorithm.infra.SimulationFileReader;
import com.blablacar.moweralgorithm.infra.exception.WrongFileException;

import java.util.List;

public class MowerApplication {

    public static void main(String[] args) throws InterruptedException {
        if (args != null && args.length > 0 && args[0] != null) {
            SimulationFileReader reader = new SimulationFileReader();
            List<String> lines = reader.read(reader.getPath(args[0]));

            Simulation simulation = SimulationFactory.create(lines);
            simulation.run();
        } else {
            throw new WrongFileException("Wrong input path file parameter (NOT FOUND)");
        }
    }
}
