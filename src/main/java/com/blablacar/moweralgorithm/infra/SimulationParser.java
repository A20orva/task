package com.blablacar.moweralgorithm.infra;

import com.blablacar.moweralgorithm.application.Instruction;
import com.blablacar.moweralgorithm.domain.Lawn;
import com.blablacar.moweralgorithm.domain.Mower;
import com.blablacar.moweralgorithm.domain.Orientation;
import com.blablacar.moweralgorithm.domain.Position;
import com.blablacar.moweralgorithm.infra.exception.SimulationParserException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimulationParser {

    public static List<Instruction> parseInstructions(String line) {
        var lines = line.split("");
        try {
            SimulationValidator.validateInstructions(lines);
            return Stream.of(lines)
                    .map(Instruction::valueOf)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new SimulationParserException(String.format("Error parsing Instructions : %s %s", Arrays.toString(lines), e));
        }
    }

    public static Mower parseMower(String line, Lawn lawn) {
        var lines = line.split("\\s");
        try {
            SimulationValidator.validateMower(lines, lawn);
            return Mower.of(Position.of(Integer.parseInt(lines[0]), Integer.parseInt(lines[1])),
                    Orientation.valueOf(lines[2]));
        } catch (Exception e) {
            throw new SimulationParserException(String.format("Error parsing Mower : %s %s", Arrays.toString(lines), e));
        }
    }

    public static Lawn parseLawn(String line) {
        var coordinates = line.split("\\s");
        try {
            SimulationValidator.validateLawn(coordinates);
            return Lawn.of(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
        } catch (NumberFormatException e) {
            throw new SimulationParserException(String.format("Error parsing Lawn : %s %s", Arrays.toString(coordinates), e));
        }
    }
}
