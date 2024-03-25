package com.gildedrose.model;

import com.gildedrose.exception.InvalidQualityException;
import com.gildedrose.exception.InvalidSellInException;

public class Item {
    public String name;
    public int sellIn;
    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;

        if (sellIn < 0)
            throw new InvalidSellInException();

        this.sellIn = sellIn;

        if (quality < 0)
            throw new InvalidQualityException();

        this.quality = quality;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}
