import com.gildedrose.model.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("OMGHAI!");

        List<Item> items = List.of(
            new NormalItem("+5 Dexterity Vest", 10, 20),
            new RareItem("Aged Brie", 2, 0),
            new NormalItem("Elixir of the Mongoose", 5, 7),
            new LegendaryItem("Sulfuras, Hand of Ragnaros", 0, 80),
            new BackstageItem("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new BackstageItem("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new BackstageItem("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            new ConjuratedItem("Conjured Mana Cake", 3, 6)
        );

        GildedRose app = new GildedRose(items);

        int days = 2;
        if (args.length > 0) {
            days = Integer.parseInt(args[0]) + 1;
        }

        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, sellIn, quality");
            for (Item item : items) {
                System.out.println(item);
            }
            System.out.println();
            app.processItems();
        }
    }
}
