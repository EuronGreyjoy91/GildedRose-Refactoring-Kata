package com.gildedrose.exception;

public class InvalidQualityException extends IllegalArgumentException {
    public InvalidQualityException() {
        super("Quality value cannot be lower than 0");
    }
}
