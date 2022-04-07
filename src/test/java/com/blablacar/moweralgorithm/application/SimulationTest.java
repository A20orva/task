package com.blablacar.moweralgorithm.application;

import com.blablacar.moweralgorithm.domain.Lawn;
import com.blablacar.moweralgorithm.domain.Mower;
import com.blablacar.moweralgorithm.domain.Orientation;
import com.blablacar.moweralgorithm.domain.Position;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SimulationTest {

    @Test
    public void should_run_simulation_with_one_mower() throws InterruptedException {
        Mower mower = Mower.of(Position.of(0, 0), Orientation.N);
        Lawn lawn = Lawn.of(1, 1, singletonList(mower));

        MowerAgent mowerAgent = MowerAgent.of(mower, lawn, asList(Instruction.F, Instruction.F));
        Simulation simulation = Simulation.of(singletonList(mowerAgent));

        simulation.run();

        assertEquals("0 1 N", mowerAgent.toString());
    }

    @Test
    public void should_run_simulation_with_multiple_mowers() throws InterruptedException {
        Mower mower_1 = Mower.of(Position.of(0, 0), Orientation.N);
        Mower mower_2 = Mower.of(Position.of(0, 1), Orientation.S);
        Mower mower_3 = Mower.of(Position.of(1, 1), Orientation.S);

        Lawn lawn = Lawn.of(2, 2, asList(mower_1, mower_2, mower_3));

        MowerAgent mowerAgent_1 = MowerAgent.of(mower_1, lawn, asList(Instruction.F, Instruction.F));
        MowerAgent mowerAgent_2 = MowerAgent.of(mower_2, lawn, asList(Instruction.F, Instruction.F));
        MowerAgent mowerAgent_3 = MowerAgent.of(mower_3, lawn, asList(Instruction.F, Instruction.F, Instruction.R, Instruction.F));
        Simulation simulation = Simulation.of(asList(mowerAgent_1, mowerAgent_2, mowerAgent_3));

        simulation.run();

        assertEquals("0 0 N", mowerAgent_1.toString());
        assertEquals("0 1 S", mowerAgent_2.toString());
        assertEquals("1 0 W", mowerAgent_3.toString());
    }

}
