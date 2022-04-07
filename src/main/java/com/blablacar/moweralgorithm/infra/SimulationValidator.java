package com.blablacar.moweralgorithm.infra;

import com.blablacar.moweralgorithm.application.Instruction;
import com.blablacar.moweralgorithm.domain.Lawn;
import com.blablacar.moweralgorithm.domain.Orientation;
import com.blablacar.moweralgorithm.infra.exception.SimulationParserException;

import java.util.Arrays;

public class SimulationValidator {
    public static void validateLawn(String[] coordinates) {
        if (coordinates.length != 2) {
            throw new SimulationParserException("Lawn size line is not valid (Provide 2 coordinates) : " + Arrays.toString(coordinates));
        }

        if (Arrays.stream(coordinates).map(Integer::valueOf).anyMatch(integer -> integer < 0)) {
            throw new SimulationParserException("Lawn size line is not valid (Provide integer and positive coordinates) : " + Arrays.toString(coordinates));
        }
    }

    public static void validateMower(String[] lines, Lawn lawn) {
        if (lines.length != 3) {
            throw new SimulationParserException("Mower position line is not valid (Provide 2 coordinates and orientation) : " + Arrays.toString(lines));
        }

        int x = Integer.parseInt(lines[0]);
        int y = Integer.parseInt(lines[1]);

        if (x >= lawn.getWidth() || y >= lawn.getHeight() || x < 0 || y < 0) {
            throw new SimulationParserException("Mower position line is not valid (position out of lawn boundaries) : " + Arrays.toString(lines));
        }
        try {
            Orientation.valueOf(lines[2]);
        } catch (IllegalArgumentException e) {
            throw new SimulationParserException("Mower position Orientation is not valid ( N, S, E, W) : " + Arrays.toString(lines));
        }
    }

    public static void validateInstructions(String[] lines) {
        if (lines.length <= 0) {
            throw new SimulationParserException("No available Instructions : " + Arrays.toString(lines));
        }
        try {
            Arrays.stream(lines).forEach(Instruction::valueOf);

        } catch (IllegalArgumentException e) {
            throw new SimulationParserException("Instructions are not valid ( F,R,L) : " + Arrays.toString(lines));
        }
    }
}
