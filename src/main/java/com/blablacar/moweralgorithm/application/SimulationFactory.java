package com.blablacar.moweralgorithm.application;

import com.blablacar.moweralgorithm.domain.Lawn;
import com.blablacar.moweralgorithm.domain.Mower;
import com.blablacar.moweralgorithm.infra.SimulationParser;

import java.util.ArrayList;
import java.util.List;

public class SimulationFactory {

    public static Simulation create(List<String> lines) {
        Lawn lawn = SimulationParser.parseLawn(lines.get(0));
        List<Mower> mowers = new ArrayList<>();
        lawn.setMowers(mowers);
        List<MowerAgent> agents = new ArrayList<>();

        for (var i = 1; i < lines.size(); i += 2) {
            Mower mower = SimulationParser.parseMower(lines.get(i), lawn);
            List<Instruction> instructions = SimulationParser.parseInstructions(lines.get(i + 1));

            mowers.add(mower);
            agents.add(MowerAgent.of(mower, lawn, instructions));
        }

        return Simulation.of(agents);
    }
}
