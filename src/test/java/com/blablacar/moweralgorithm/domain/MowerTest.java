package com.blablacar.moweralgorithm.domain;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MowerTest {

    @Test
    void should_create_mower_from_position_and_orientation() {
        Mower mower = Mower.of(Position.of(10, 20), Orientation.N);

        assertEquals("10 20 N", mower.toString());
    }

    @Test
    void should_move_mower_forward() {
        Mower mower = Mower.of(Position.of(0, 0), Orientation.S);
        Lawn lawn = Lawn.of(10, 10, Collections.singletonList(mower));
        mower.moveToForward(lawn);

        assertEquals("0 0 S", mower.toString());
    }

    @Test
    void should_rotate_mower_to_the_right() {
        Mower mower = Mower.of(Position.of(0, 0), Orientation.S);
        mower.rotateToRight();

        assertEquals("0 0 W", mower.toString());
    }

    @Test
    void should_rotate_mower_to_the_left() {
        Mower mower = Mower.of(Position.of(0, 0), Orientation.S);
        mower.rotateToLeft();

        assertEquals("0 0 E", mower.toString());
    }

    @Test
    void should_stay_idle_when_move_forward_to_lawn_boundary() {
        Mower mower = Mower.of(Position.of(0, 0), Orientation.S);
        Lawn lawn = Lawn.of(2, 2, Collections.singletonList(mower));

        mower.moveToForward(lawn);
        mower.moveToForward(lawn);
        mower.moveToForward(lawn);

        assertEquals("0 0 S", mower.toString());
    }

    @Test
    void should_stay_idle_when_move_forward_to_an_other_mower() {
        Mower mower_1 = Mower.of(Position.of(0, 0), Orientation.N);
        Mower mower_2 = Mower.of(Position.of(0, 1), Orientation.N);

        Lawn lawn = Lawn.of(1, 1, asList(mower_1, mower_2));

        mower_1.moveToForward(lawn);
        mower_2.moveToForward(lawn);

        assertEquals("0 0 N", mower_1.toString());
        assertEquals("0 1 N", mower_2.toString());
    }

}
