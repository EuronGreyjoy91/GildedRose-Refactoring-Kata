package com.gildedrose.model;

import java.util.List;

public class GildedRose {
    private List<Item> items;

    public GildedRose(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void processItems() {
        items.forEach(item -> {
            item.updateSellIn();
            item.updateQuality();
        });
    }
}
