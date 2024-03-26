package com.gildedrose.model;

import com.gildedrose.exception.InvalidQualityException;

public class NormalItem extends Item {
    public NormalItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);

        if (quality > 50)
            throw new InvalidQualityException();
    }

    @Override
    void updateQuality() {
        int quality = Math.max(0, this.quality - 1);

        if (this.sellIn == 0)
            quality = Math.max(0, this.quality - 2);

        this.quality = quality;
    }

    @Override
    void updateSellIn() {
        this.sellIn = Math.max(0, this.sellIn - 1);
    }
}
