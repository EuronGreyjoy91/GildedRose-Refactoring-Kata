package com.gildedrose.model;

import com.gildedrose.exception.InvalidQualityException;

public class LegendaryItem extends Item {
    public LegendaryItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);

        if (quality != 80) throw new InvalidQualityException();
    }

    @Override
    void updateQuality() {
    }

    @Override
    void updateSellIn() {
    }
}
