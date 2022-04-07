package com.blablacar.moweralgorithm.infra.exception;

public class WrongFileException extends RuntimeException {
    public WrongFileException(final String errorMessage) {
        super(errorMessage);
    }
}
