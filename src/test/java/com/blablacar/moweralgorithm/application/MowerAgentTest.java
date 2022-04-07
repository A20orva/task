package com.blablacar.moweralgorithm.application;

import com.blablacar.moweralgorithm.domain.Lawn;
import com.blablacar.moweralgorithm.domain.Mower;
import com.blablacar.moweralgorithm.domain.Orientation;
import com.blablacar.moweralgorithm.domain.Position;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MowerAgentTest {

    @Test
    public void should_execute_instructions() {

        Mower mower = Mower.of(Position.of(0, 0), Orientation.N);
        Lawn lawn = Lawn.of(1, 1, singletonList(mower));

        MowerAgent mowerAgent = MowerAgent.of(mower, lawn, asList(Instruction.F, Instruction.F));
        mowerAgent.call();
        assertEquals("0 1 N", mowerAgent.toString());
    }

    static class SimulationFactoryTest {
        @Test
        public void should_create_simulation() {
            List<String> lines = asList("5 5", "1 2 N", "LFLFLFLFF", "3 3 N", "LFLFLFLFF");

            Simulation simulation = SimulationFactory.create(lines);
            assertNotNull(simulation);
        }
    }
}
