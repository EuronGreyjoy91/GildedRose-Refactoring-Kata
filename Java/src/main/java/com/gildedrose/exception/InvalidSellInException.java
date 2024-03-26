package com.gildedrose.exception;

public class InvalidSellInException extends IllegalArgumentException {
    public InvalidSellInException() {
        super("Invalid sellIn value");
    }
}
