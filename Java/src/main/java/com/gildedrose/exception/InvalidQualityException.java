package com.gildedrose.exception;

public class InvalidQualityException extends IllegalArgumentException {
    public InvalidQualityException() {
        super("Invalid quality value");
    }
}
