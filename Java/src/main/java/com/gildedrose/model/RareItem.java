package com.gildedrose.model;

import com.gildedrose.exception.InvalidQualityException;

public class RareItem extends Item {
    public RareItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);

        if (quality > 50)
            throw new InvalidQualityException();
    }

    @Override
    void updateQuality() {
        this.quality = Math.min(50, this.quality + 1);
    }

    @Override
    void updateSellIn() {
        this.sellIn = Math.max(0, this.sellIn - 1);
    }
}
