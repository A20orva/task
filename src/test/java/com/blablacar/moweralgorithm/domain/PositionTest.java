package com.blablacar.moweralgorithm.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PositionTest {
    @Test
    public void should_create_position_from_coordinates() {
        Position position = Position.of(10, 20);
        assertEquals(10, position.getX());
        assertEquals(20, position.getY());
    }

    @Test
    public void should_move_position_by_orientation() {
        Position position = Position.of(0, 0);

        position.moveBy(Orientation.N);
        assertEquals(0, position.getX());
        assertEquals(1, position.getY());

        position.moveBy(Orientation.E);
        assertEquals(1, position.getX());
        assertEquals(1, position.getY());

        position.moveBy(Orientation.S);
        assertEquals(1, position.getX());
        assertEquals(0, position.getY());

        position.moveBy(Orientation.W);
        assertEquals(0, position.getX());
        assertEquals(0, position.getY());
    }

}
