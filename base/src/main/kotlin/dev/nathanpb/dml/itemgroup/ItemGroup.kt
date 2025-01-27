package dev.nathanpb.dml.itemgroup

import dev.nathanpb.dml.MOD_ID
import dev.nathanpb.dml.block.*
import dev.nathanpb.dml.identifier
import dev.nathanpb.dml.item.*
import dev.nathanpb.dml.utils.getEmptyAndFullCapacityEnergyItem
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.text.Text


val ITEM_GROUP_KEY: RegistryKey<ItemGroup> = RegistryKey.of(RegistryKeys.ITEM_GROUP, identifier("tab_${MOD_ID}"))
val ITEMS = ArrayList<ItemStack>()

fun registerItemGroup() {
    ITEMS.add(0, ItemStack(BLOCK_CAFETERIA))

    Registry.register(Registries.ITEM_GROUP, ITEM_GROUP_KEY, FabricItemGroup.builder()
        .displayName(Text.translatable("itemGroup.$MOD_ID.tab_$MOD_ID"))
        .icon { ItemStack(ITEM_DML) }
        .entries { _: ItemGroup.DisplayContext, entries: ItemGroup.Entries ->
            entries.addAll(ITEMS)
        }
    .build())

    ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP_KEY).register {
        val glitchedBlocks = listOf(
            BLOCK_DISRUPTIONS_CORE,
            BLOCK_GLITCHED_WALL,
            BLOCK_GLITCHED_SLAB,
            BLOCK_GLITCHED_STAIRS,
            BLOCK_GLITCHED_TILE,
        )

        glitchedBlocks.forEach { block ->
            it.addAfter(BLOCK_CAFETERIA, block)
        }

        it.addBefore(ITEM_TRIAL_KEY, BLOCK_DATA_SYNTHESIZER)
        getEmptyAndFullCapacityEnergyItem(ITEM_ENERGY_OCTAHEDRON).forEach { stack ->
            it.addAfter(BLOCK_DATA_SYNTHESIZER, stack)
        }

        it.addAfter(ITEM_TRIAL_KEY, BLOCK_TRIAL_KEYSTONE)
        it.addBefore(ITEM_PRISTINE_MATTER_OVERWORLD, BLOCK_LOOT_FABRICATOR)


        it.addAfter(ItemStack(ITEM_GLITCH_INGOT), ITEM_GLITCH_UPGRADE_SMITHING_TEMPLATE)

        getEmptyAndFullCapacityEnergyItem(ITEM_GLITCH_SWORD).forEach { stack ->
            it.addAfter(ITEM_GLITCH_UPGRADE_SMITHING_TEMPLATE, stack)
        }
    }

}