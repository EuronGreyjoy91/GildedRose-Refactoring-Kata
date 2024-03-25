package com.gildedrose.exception;

public class InvalidSellInException extends IllegalArgumentException {
    public InvalidSellInException() {
        super("SellIn value cannot be lower than 0");
    }
}
