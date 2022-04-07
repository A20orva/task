package com.blablacar.moweralgorithm.domain;

import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LawnTest {
    @Test
    void should_not_authorize_lawn_to_move_to_occupied_position(){
        Mower mower_1 = Mower.of(Position.of(0, 0), Orientation.S);
        Mower mower_2 = Mower.of(Position.of(1, 0), Orientation.S);
        Lawn lawn = Lawn.of(10,10, asList(mower_1, mower_2));

        boolean authorizedToMove = lawn.isAuthorizedToMove(mower_1, Position.of(1, 0));
        assertFalse(authorizedToMove);
    }

    @Test
    void should_not_authorize_lawn_to_move_to_out_of_lawn_boundaries(){
        Mower mower_1 = Mower.of(Position.of(0, 0), Orientation.S);
        Mower mower_2 = Mower.of(Position.of(1, 0), Orientation.S);
        Lawn lawn = Lawn.of(10,10, asList(mower_1, mower_2));

        boolean authorizedToMove = lawn.isAuthorizedToMove(mower_1, Position.of(-1, 0));
        assertFalse(authorizedToMove);
    }

    @Test
    void should_authorize_lawn_to_move_(){
        Mower mower_1 = Mower.of(Position.of(0, 0), Orientation.S);
        Mower mower_2 = Mower.of(Position.of(1, 0), Orientation.S);
        Lawn lawn = Lawn.of(10,10, asList(mower_1, mower_2));

        boolean authorizedToMove = lawn.isAuthorizedToMove(mower_1, Position.of(1, 1));
        assertTrue(authorizedToMove);
    }
}
