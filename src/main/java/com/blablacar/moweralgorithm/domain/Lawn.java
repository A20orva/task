package com.blablacar.moweralgorithm.domain;

import java.util.Collections;
import java.util.List;

public class Lawn implements ILawn {
    private final int width;
    private final int height;
    private List<Mower> mowers;


    private Lawn(int width, int height, List<Mower> mowers) {
        this.width = width;
        this.height = height;
        this.mowers = mowers;
    }

    public static Lawn of(int width, int height) {
        return new Lawn(width, height, Collections.emptyList());
    }

    public static Lawn of(int width, int height, List<Mower> mowers) {
        return new Lawn(width, height, mowers);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean isAuthorizedToMove(Mower mower, Position nextPosition) {
        if (isWithinLawn(nextPosition)) {
            return mowers.stream()
                    .filter(m -> !(m.equals(mower)))
                    .noneMatch(m -> m.getPosition().equals(nextPosition));

        }
        return false;
    }

    @Override
    public boolean isWithinLawn(Position nextPosition) {
        return nextPosition.getX() >= 0 && nextPosition.getX() <= width && nextPosition.getY() >= 0 && nextPosition.getY() <= height;
    }

    public void setMowers(List<Mower> mowers) {
        this.mowers = mowers;
    }
}
