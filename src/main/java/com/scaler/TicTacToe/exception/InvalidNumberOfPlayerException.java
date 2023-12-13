package com.scaler.TicTacToe.exception;

public class InvalidNumberOfPlayerException extends RuntimeException {
    public InvalidNumberOfPlayerException(String message) {
        super(message);
    }
}
