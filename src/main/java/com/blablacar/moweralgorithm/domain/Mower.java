package com.blablacar.moweralgorithm.domain;

import java.util.Objects;

public class Mower implements IMower {
    private Position position;
    private Orientation orientation;

    private Mower(Position position, Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    public static Mower of(Position position, Orientation orientation) {
        return new Mower(position, orientation);
    }

    @Override
    public void moveToForward(Lawn lawn) {
        Position nextPosition = calculateNextPosition();
        if (lawn.isAuthorizedToMove(this, nextPosition)) {
            position = nextPosition;
        }
    }

    @Override
    public Position calculateNextPosition() {
        Position nextPosition = Position.from(position);
        nextPosition.moveBy(orientation);
        return nextPosition;
    }

    @Override
    public void rotateToRight() {
        switch (orientation) {
            case N:
                orientation = Orientation.E;
                break;
            case E:
                orientation = Orientation.S;
                break;
            case S:
                orientation = Orientation.W;
                break;
            case W:
                orientation = Orientation.N;
                break;
        }
    }

    @Override
    public void rotateToLeft() {
        switch (orientation) {
            case N:
                orientation = Orientation.W;
                break;
            case W:
                orientation = Orientation.S;
                break;
            case S:
                orientation = Orientation.E;
                break;
            case E:
                orientation = Orientation.N;
                break;
        }
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return position.getX() + " " + position.getY() + " " + orientation.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mower mower = (Mower) o;
        return Objects.equals(position, mower.position) &&
                orientation == mower.orientation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, orientation);
    }
}
