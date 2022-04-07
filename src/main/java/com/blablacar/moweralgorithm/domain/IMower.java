package com.blablacar.moweralgorithm.domain;

public interface IMower {

    void moveToForward(Lawn lawn);

    Position calculateNextPosition();

    void rotateToRight();

    void rotateToLeft();
}
