package com.heitorfs.tutorialmod.item;

import com.heitorfs.tutorialmod.TutorialMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {
    public static final Item RAW_PINK_GARNET = registerItem("raw_pink_garnet", Item::new, new Item.Settings());
    public static final Item PINK_GARNET = registerItem("pink_garnet", Item::new, new Item.Settings());
    public static final Item RICE = registerItem("rice", Item::new, new Item.Settings().food(new FoodComponent.Builder().nutrition(4).saturationModifier(1).build()));
    public static final Item TOMATO = registerItem("tomato", Item::new, new Item.Settings().food(new FoodComponent.Builder().nutrition(2).saturationModifier(2.5f).build()));
    public static final Item MUSTARD = registerItem("mustard", Item::new, new Item.Settings().food(new FoodComponent.Builder().nutrition(1).build()));
    public static final Item STROGONOFF = registerItem("strogonoff", Item::new, new Item.Settings().food(new FoodComponent.Builder().nutrition(12).saturationModifier(14).build()));

    private static Item registerItem(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TutorialMod.MOD_ID, name));
        Item item = itemFactory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);
        return item;
    }

    public static void registerModItems() {
        TutorialMod.LOGGER.info("registering mod items for " + TutorialMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(PINK_GARNET);
            entries.add(RAW_PINK_GARNET);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(RICE);
            entries.add(TOMATO);
            entries.add(MUSTARD);
            entries.add(STROGONOFF);
        });
    }
}
