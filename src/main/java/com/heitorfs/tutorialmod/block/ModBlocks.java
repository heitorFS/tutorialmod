package com.heitorfs.tutorialmod.block;

import com.heitorfs.tutorialmod.TutorialMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {
    private static final Block PINK_GARNET_BLOCK = registerBlock("pink_garnet_block",
                                                                Block::new,
                                                                AbstractBlock.Settings.create().sounds(BlockSoundGroup.AMETHYST_BLOCK),
                                                                true);

    private static final Block RAW_PINK_GARNET_BLOCK = registerBlock("raw_pink_garnet_block",
                                                                Block::new,
                                                                AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE),
                                                                true);

    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings, boolean should_register_item)
    {
        final RegistryKey<Block> blockKey = keyOfBlock(name);
        final Block block = factory.apply(settings.registryKey(blockKey));

        if (should_register_item)
        {
            final RegistryKey<Item> itemKey = keyOfItem(name);
            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock (String name)
    {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(TutorialMod.MOD_ID, name));
    }

    private static RegistryKey<Item> keyOfItem (String name)
    {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TutorialMod.MOD_ID, name));
    }

    public static void registerModBlocks()
    {
        TutorialMod.LOGGER.info("Registering mod blocks for " + TutorialMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(PINK_GARNET_BLOCK);
            entries.add(RAW_PINK_GARNET_BLOCK);
        });

    }
}
