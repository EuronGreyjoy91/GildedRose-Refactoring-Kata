package com.gildedrose.model;

import com.gildedrose.exception.InvalidQualityException;

public class BackstageItem extends Item {
    public BackstageItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);

        if (quality > 50)
            throw new InvalidQualityException();
    }

    @Override
    void updateQuality() {
        int quality = Math.min(50, this.quality + 1);

        if (this.sellIn <= 10)
            quality = this.quality + 2;

        if (this.sellIn <= 5)
            quality = this.quality + 3;

        if (this.sellIn == 0)
            quality = 0;

        this.quality = quality;
    }

    @Override
    void updateSellIn() {
        this.sellIn = Math.max(0, this.sellIn - 1);
    }
}
