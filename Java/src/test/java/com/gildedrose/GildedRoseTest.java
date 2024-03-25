package com.gildedrose;

import com.gildedrose.exception.InvalidQualityException;
import com.gildedrose.exception.InvalidSellInException;
import com.gildedrose.model.GildedRose;
import com.gildedrose.model.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GildedRoseTest {

    @Test
    @DisplayName("No se pueden crear objetos con fecha de caducidad menor a 0")
    void cannotCreateObjectsWithSellInLowerThanZero() {
        Exception exception = assertThrows(InvalidSellInException.class, () -> new Item("Test", -1, 2));

        String expectedMessage = "SellIn value cannot be lower than 0";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("No se pueden crear objetos con calidad menor a 0")
    void cannotCreateObjectsWithQualityLowerThanZero() {
        Exception exception = assertThrows(InvalidQualityException.class, () -> new Item("Test", 2, -1));

        String expectedMessage = "Quality value cannot be lower than 0";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Una vez pasada la fecha de caducidad, la calidad se degrada el doble rapido")
    void qualityDegradesWithDoubleSpeedWhenExpired() {
        Item[] items = new Item[]{new Item("Test", 0, 2)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(0, app.getItems()[0].quality);
    }

    @Test
    @DisplayName("La fecha de caducidad nunca es negativa")
    void sellInCannotBeNegative() {
        Item[] items = new Item[]{new Item("Test", 0, 0)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertTrue(app.getItems()[0].sellIn > -1);
    }

    @Test
    @DisplayName("La calidad de un articulo nunca es negativa")
    void qualityCannotBeNegative() {
        Item[] items = new Item[]{new Item("Test", 0, 0)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertTrue(app.getItems()[0].quality > -1);
    }

    @Test
    @DisplayName("El 'brie añejado' aumenta su calidad a medida que envejece")
    void agedBrieIncreasesQualityAsTimeGoesBy() {
        Item[] items = new Item[]{new Item("Aged Brie", 5, 6)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(7, app.getItems()[0].quality);
    }

    @Test
    @DisplayName("La calidad de un artículo nunca es superior a 50")
    void qualityCannotBeBiggerThan50IfNotLegendary() {
        Item[] items = new Item[]{new Item("Aged Brie", 5, 50)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(50, app.getItems()[0].quality);
    }

    @Test
    @DisplayName("'Sulfuras', al ser un objeto legendario, nunca tiene que venderse y su calidad nunca disminuye")
    void legendaryObjectsCannotBeSold() {
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 5, 80)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(80, app.getItems()[0].quality);
    }

    @Test
    @DisplayName("Los 'Backstage passes' aumentan su calidad a medida que se acerca su valor SellIn")
    void qualityIncreaseForBackstagePassesWhenSellInAproaches() {
        Item[] items = new Item[]{
            new Item("Backstage passes to a TAFKAL80ETC concert", 11, 10),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(11, app.getItems()[0].quality);
        assertEquals(12, app.getItems()[1].quality);
        assertEquals(13, app.getItems()[2].quality);
    }

    @Test
    @DisplayName("Los 'Backstage passes' pierden su calidad al llegar a la fecha de del concierto")
    void qualityBecomesZeroForBackstagePassesWhenSellInIsReached() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(0, app.getItems()[0].quality);
    }

    @Test
    @DisplayName("La calidad de los objetos 'conjurados' se degrada el doble de rápido que la de los objetos normales")
    void conjuredItemsDegradeTwiceAsFastThanNormal() {
        Item[] items = new Item[]{new Item("Conjured Mana Cake", 5, 20)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(18, app.getItems()[0].quality);
    }
}
