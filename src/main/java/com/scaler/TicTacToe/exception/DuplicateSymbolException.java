package com.scaler.TicTacToe.exception;

public class DuplicateSymbolException extends RuntimeException {
    public DuplicateSymbolException(String message) {
        super(message);
    }
}
