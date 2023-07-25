/*
 * Copyright (C) 2020 Nathan P. Bombana, IterationFunk
 *
 * This file is part of Deep Mob Learning: Refabricated.
 *
 * Deep Mob Learning: Refabricated is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Deep Mob Learning: Refabricated is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Deep Mob Learning: Refabricated.  If not, see <https://www.gnu.org/licenses/>.
 */

package dev.nathanpb.dml.item

import dev.nathanpb.dml.MOD_ID
import dev.nathanpb.dml.enums.EntityCategory
import dev.nathanpb.dml.identifier
import dev.nathanpb.dml.itemgroup.ITEMS
import dev.nathanpb.dml.utils.ItemTuple
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.text.Text
import net.minecraft.util.Rarity
import java.util.function.Supplier

//val ITEM_GUIDE_TABLET = ItemGuideTablet()
val ITEM_DML = Item(Item.Settings())
val ITEM_DEEP_LEARNER = ItemDeepLearner()
val ITEM_TRIAL_KEY = ItemTrialKey()

val ITEM_DATA_MODEL = ItemDataModel()
val ITEM_DATA_MODEL_NETHER = ItemDataModel(EntityCategory.NETHER)
val ITEM_DATA_MODEL_SLIMY = ItemDataModel(EntityCategory.SLIMY)
val ITEM_DATA_MODEL_OVERWORLD = ItemDataModel(EntityCategory.OVERWORLD)
val ITEM_DATA_MODEL_ZOMBIE = ItemDataModel(EntityCategory.ZOMBIE)
val ITEM_DATA_MODEL_SKELETON = ItemDataModel(EntityCategory.SKELETON)
val ITEM_DATA_MODEL_END = ItemDataModel(EntityCategory.END)
val ITEM_DATA_MODEL_GHOST = ItemDataModel(EntityCategory.GHOST)
val ITEM_DATA_MODEL_ILLAGER = ItemDataModel(EntityCategory.ILLAGER)
val ITEM_DATA_MODEL_OCEAN = ItemDataModel(EntityCategory.OCEAN)

val ITEM_PRISTINE_MATTER_NETHER = ItemPristineMatter()
val ITEM_PRISTINE_MATTER_SLIMY = ItemPristineMatter()
val ITEM_PRISTINE_MATTER_OVERWORLD = ItemPristineMatter()
val ITEM_PRISTINE_MATTER_ZOMBIE = ItemPristineMatter()
val ITEM_PRISTINE_MATTER_SKELETON = ItemPristineMatter()
val ITEM_PRISTINE_MATTER_END = ItemPristineMatter()
val ITEM_PRISTINE_MATTER_GHOST = ItemPristineMatter()
val ITEM_PRISTINE_MATTER_ILLAGER = ItemPristineMatter()
val ITEM_PRISTINE_MATTER_OCEAN = ItemPristineMatter()

val ITEM_SOOT_REDSTONE = Item(FabricItemSettings())
val ITEM_SOOT_PLATE = Item(FabricItemSettings())
val ITEM_SOOT_MACHINE_CASE = Item(FabricItemSettings())

val ITEM_EMERITUS_HAT = ItemEmeritusHat()

val ITEM_PHYSICALLY_CONDENSED_MATRIX_FRAGMENT = Item(FabricItemSettings().fireproof().rarity(Rarity.RARE))
val ITEM_GLITCH_INGOT = Item(FabricItemSettings().fireproof().rarity(Rarity.RARE))

fun registerItems() {
    linkedMapOf(
        //ITEM_GUIDE_TABLET to ItemTuple("guide_tablet"),
        ITEM_DML to ItemTuple(MOD_ID, false),
        ITEM_DEEP_LEARNER to ItemTuple("deep_learner"),
        ITEM_TRIAL_KEY to ItemTuple("trial_key"),
        ITEM_DATA_MODEL to ItemTuple("data_model"),
        ITEM_DATA_MODEL_NETHER to ItemTuple("data_model_nether"),
        ITEM_DATA_MODEL_SLIMY to ItemTuple("data_model_slimy"),
        ITEM_DATA_MODEL_OVERWORLD to ItemTuple("data_model_overworld"),
        ITEM_DATA_MODEL_ZOMBIE to ItemTuple("data_model_zombie"),
        ITEM_DATA_MODEL_SKELETON to ItemTuple("data_model_skeleton"),
        ITEM_DATA_MODEL_END to ItemTuple("data_model_end"),
        ITEM_DATA_MODEL_GHOST to ItemTuple("data_model_ghost"),
        ITEM_DATA_MODEL_ILLAGER to ItemTuple("data_model_illager"),
        ITEM_DATA_MODEL_OCEAN to ItemTuple("data_model_ocean"),
        ITEM_SOOT_REDSTONE to ItemTuple("soot_redstone"),
        ITEM_SOOT_PLATE to ItemTuple("soot_plate"),
        ITEM_SOOT_MACHINE_CASE to ItemTuple("machine_casing"),
        ITEM_PRISTINE_MATTER_NETHER to ItemTuple("pristine_matter_nether"),
        ITEM_PRISTINE_MATTER_SLIMY to ItemTuple("pristine_matter_slimy"),
        ITEM_PRISTINE_MATTER_OVERWORLD to ItemTuple("pristine_matter_overworld"),
        ITEM_PRISTINE_MATTER_ZOMBIE to ItemTuple("pristine_matter_zombie"),
        ITEM_PRISTINE_MATTER_SKELETON to ItemTuple("pristine_matter_skeleton"),
        ITEM_PRISTINE_MATTER_END to ItemTuple("pristine_matter_end"),
        ITEM_PRISTINE_MATTER_GHOST to ItemTuple("pristine_matter_ghost"),
        ITEM_PRISTINE_MATTER_ILLAGER to ItemTuple("pristine_matter_illager"),
        ITEM_PRISTINE_MATTER_OCEAN to ItemTuple("pristine_matter_ocean"),
        ITEM_EMERITUS_HAT to ItemTuple("emeritus_hat", false),
        ITEM_PHYSICALLY_CONDENSED_MATRIX_FRAGMENT to ItemTuple("physically_condensed_matrix_fragment"),
        ITEM_GLITCH_INGOT to ItemTuple("glitch_ingot")
    ).forEach { (item, tuple) ->
        Registry.register(Registries.ITEM, identifier(tuple.identifier), item)
        if(tuple.enabled) ITEMS.add(ItemStack(item))
    }

}