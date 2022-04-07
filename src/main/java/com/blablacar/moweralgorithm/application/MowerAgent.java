package com.blablacar.moweralgorithm.application;

import com.blablacar.moweralgorithm.domain.ILawn;
import com.blablacar.moweralgorithm.domain.IMower;
import com.blablacar.moweralgorithm.domain.Lawn;

import java.util.List;
import java.util.concurrent.Callable;

public class MowerAgent implements Callable<String> {
    private final IMower mower;
    private final ILawn lawn;
    private final List<Instruction> instructions;

    private MowerAgent(IMower mower, ILawn lawn, List<Instruction> instructions) {
        this.mower = mower;
        this.lawn = lawn;
        this.instructions = instructions;
    }

    public static MowerAgent of(IMower mower, ILawn lawn, List<Instruction> instructions) {
        return new MowerAgent(mower, lawn, instructions);
    }

    @Override
    public String toString() {
        return mower.toString();
    }

    @Override
    public String call() {
        instructions.forEach(instruction -> {
            switch (instruction) {
                case F:
                    mower.moveToForward((Lawn) lawn);
                    break;
                case R:
                    mower.rotateToRight();
                    break;
                case L:
                    mower.rotateToLeft();
                    break;
            }
        });
        return mower.toString();
    }
}
