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

package dev.nathanpb.dml.enums

import dev.nathanpb.dml.MOD_ID
import dev.nathanpb.dml.baseConfig
import dev.nathanpb.dml.identifier
import dev.nathanpb.dml.item.*
import net.minecraft.entity.EntityType
import net.minecraft.item.Item
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.text.Text


enum class EntityCategory(val tagKey: TagKey<EntityType<*>>, var exchangeRatio: Int, private val itemForRendering: ()->Item = ::ITEM_DATA_MODEL) {

    OVERWORLD(TagKey.of(RegistryKeys.ENTITY_TYPE, identifier("overworld_mobs")), baseConfig.machines.lootFabricator.overworldExchangeRatio, ::ITEM_DATA_MODEL_OVERWORLD),
    ZOMBIE(TagKey.of(RegistryKeys.ENTITY_TYPE, identifier("zombie_mobs")), baseConfig.machines.lootFabricator.zombieExchangeRatio, ::ITEM_DATA_MODEL_ZOMBIE),
    SKELETON(TagKey.of(RegistryKeys.ENTITY_TYPE, identifier("skeleton_mobs")), baseConfig.machines.lootFabricator.skeletonExchangeRatio, ::ITEM_DATA_MODEL_SKELETON),
    SLIMY(TagKey.of(RegistryKeys.ENTITY_TYPE, identifier("slimy_mobs")), baseConfig.machines.lootFabricator.slimyExchangeRatio, ::ITEM_DATA_MODEL_SLIMY),
    ILLAGER(TagKey.of(RegistryKeys.ENTITY_TYPE, identifier("illager_mobs")), baseConfig.machines.lootFabricator.illagerExchangeRatio, ::ITEM_DATA_MODEL_ILLAGER),
    OCEAN(TagKey.of(RegistryKeys.ENTITY_TYPE, identifier("ocean_mobs")), baseConfig.machines.lootFabricator.oceanExchangeRatio, ::ITEM_DATA_MODEL_OCEAN),
    GHOST(TagKey.of(RegistryKeys.ENTITY_TYPE, identifier("ghost_mobs")), baseConfig.machines.lootFabricator.ghostExchangeRatio, ::ITEM_DATA_MODEL_GHOST),
    NETHER(TagKey.of(RegistryKeys.ENTITY_TYPE, identifier("nether_mobs")), baseConfig.machines.lootFabricator.netherExchangeRatio, ::ITEM_DATA_MODEL_NETHER),
    END(TagKey.of(RegistryKeys.ENTITY_TYPE, identifier("end_mobs")), baseConfig.machines.lootFabricator.endExchangeRatio, ::ITEM_DATA_MODEL_END);


    val displayName: Text = Text.translatable("mobcategory.${MOD_ID}.${tagKey.id.path}")
}
