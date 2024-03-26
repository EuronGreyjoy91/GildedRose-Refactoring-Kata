package com.gildedrose;

import com.gildedrose.exception.InvalidQualityException;
import com.gildedrose.exception.InvalidSellInException;
import com.gildedrose.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GildedRoseTest {

    @Test
    @DisplayName("No se pueden crear objetos con fecha de caducidad menor a 0")
    void cannotCreateObjectsWithSellInLowerThanZero() {
        Exception exception = assertThrows(InvalidSellInException.class, () -> new NormalItem("Test", -1, 2));

        String expectedMessage = "Invalid sellIn value";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("No se pueden crear objetos con calidad menor a 0")
    void cannotCreateObjectsWithQualityLowerThanZero() {
        Exception exception = assertThrows(InvalidQualityException.class, () -> new NormalItem("Test", 2, -1));

        String expectedMessage = "Invalid quality value";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Una vez pasada la fecha de caducidad, la calidad se degrada el doble rapido")
    void qualityDegradesWithDoubleSpeedWhenExpired() {
        GildedRose app = new GildedRose(List.of(
            new NormalItem("Test", 0, 2)
        ));
        app.processItems();

        assertEquals(0, app.getItems().get(0).quality);
    }

    @Test
    @DisplayName("La fecha de caducidad nunca es negativa")
    void sellInCannotBeNegative() {
        GildedRose app = new GildedRose(List.of(
            new NormalItem("Test", 0, 0)
        ));
        app.processItems();

        assertTrue(app.getItems().get(0).sellIn > -1);
    }

    @Test
    @DisplayName("La calidad de un articulo nunca es negativa")
    void qualityCannotBeNegative() {
        GildedRose app = new GildedRose(List.of(
            new NormalItem("Test", 0, 0)
        ));
        app.processItems();

        assertTrue(app.getItems().get(0).quality > -1);
    }

    @Test
    @DisplayName("El 'brie añejado' aumenta su calidad a medida que envejece")
    void agedBrieIncreasesQualityAsTimeGoesBy() {
        GildedRose app = new GildedRose(List.of(
            new RareItem("Aged Brie", 5, 6)
        ));
        app.processItems();

        assertEquals(7, app.getItems().get(0).quality);
    }

    @Test
    @DisplayName("La calidad de un artículo nunca es superior a 50")
    void qualityCannotBeBiggerThan50IfNotLegendary() {
        GildedRose app = new GildedRose(List.of(
            new RareItem("Aged Brie", 5, 50)
        ));
        app.processItems();

        assertEquals(50, app.getItems().get(0).quality);
    }

    @Test
    @DisplayName("'Sulfuras', al ser un objeto legendario, nunca tiene que venderse y su calidad nunca disminuye")
    void legendaryObjectsCannotBeSold() {
        GildedRose app = new GildedRose(List.of(
            new LegendaryItem("Sulfuras, Hand of Ragnaros", 5, 80)
        ));
        app.processItems();

        assertEquals(80, app.getItems().get(0).quality);
    }

    @Test
    @DisplayName("Los 'Backstage passes' aumentan su calidad a medida que se acerca su valor SellIn")
    void qualityIncreaseForBackstagePassesWhenSellInAproaches() {
        GildedRose app = new GildedRose((List.of(
            new BackstageItem("Backstage passes to a TAFKAL80ETC concert", 12, 10),
            new BackstageItem("Backstage passes to a TAFKAL80ETC concert", 10, 10),
            new BackstageItem("Backstage passes to a TAFKAL80ETC concert", 5, 10)
        )));
        app.processItems();

        assertEquals(11, app.getItems().get(0).quality);
        assertEquals(12, app.getItems().get(1).quality);
        assertEquals(13, app.getItems().get(2).quality);
    }

    @Test
    @DisplayName("Los 'Backstage passes' pierden su calidad al llegar a la fecha de del concierto")
    void qualityBecomesZeroForBackstagePassesWhenSellInIsReached() {
        GildedRose app = new GildedRose(List.of(
            new BackstageItem("Backstage passes to a TAFKAL80ETC concert", 0, 10)
        ));
        app.processItems();

        assertEquals(0, app.getItems().get(0).quality);
    }

    @Test
    @DisplayName("La calidad de los objetos 'conjurados' se degrada el doble de rápido que la de los objetos normales")
    void conjuredItemsDegradeTwiceAsFastThanNormal() {
        GildedRose app = new GildedRose(List.of(
            new ConjuratedItem("Conjured Mana Cake", 5, 20)
        ));
        app.processItems();

        assertEquals(18, app.getItems().get(0).quality);
    }
}
