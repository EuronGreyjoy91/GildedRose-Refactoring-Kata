package com.gildedrose.model;

import com.gildedrose.exception.InvalidQualityException;

public class ConjuratedItem extends Item {
    public ConjuratedItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);

        if (quality > 50)
            throw new InvalidQualityException();
    }

    @Override
    void updateQuality() {
        this.quality = Math.max(0, this.quality - 2);
    }

    @Override
    void updateSellIn() {
        this.sellIn = Math.max(0, this.sellIn - 1);
    }
}
