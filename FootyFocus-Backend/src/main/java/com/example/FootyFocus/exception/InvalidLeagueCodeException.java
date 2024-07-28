package com.example.FootyFocus.exception;

public class InvalidLeagueCodeException extends RuntimeException {
    public InvalidLeagueCodeException(String message) {
        super(message);
    }
}