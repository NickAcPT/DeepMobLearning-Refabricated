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
import dev.nathanpb.dml.data.dataModel
import dev.nathanpb.dml.enums.DataModelTier
import dev.nathanpb.dml.enums.EntityCategory
import dev.nathanpb.dml.utils.getInfoText
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.client.MinecraftClient
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class ItemDataModel(val category: EntityCategory? = null) : Item(FabricItemSettings().maxCount(1).fireproof()) {

    override fun appendTooltip(
        stack: ItemStack,
        world: World?,
        tooltip: MutableList<Text>,
        context: TooltipContext?
    ) {
        if(category == null) return
        if(world?.isClient == true) {
            stack.dataModel.let { data ->
                if(!data.tier().isMaxTier()) {
                    tooltip.add(getInfoText(
                        Text.translatable(
                                "tooltip.${MOD_ID}.data_amount.1"
                        ),
                        Text.translatable(
                            "tooltip.${MOD_ID}.data_amount.2",
                            data.dataAmount, data.tier().nextTierOrCurrent().dataAmount
                        )
                    ))
                }

                tooltip.add(getInfoText(
                    Text.translatable(
                        "tooltip.${MOD_ID}.tier.1"
                    ),
                    Text.translatable(
                        "tooltip.${MOD_ID}.tier.2",
                        data.tier().text
                    )
                ))

                MinecraftClient.getInstance().player?.let { player ->
                    if(player.isCreative) {
                        tooltip.add(Text.translatable("tooltip.${MOD_ID}.cheat").formatted(Formatting.GRAY, Formatting.ITALIC))
                    }
                }
            }
        }
    }

    // cheat/debug
    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        if(world.isClient) return TypedActionResult.pass(user.getStackInHand(hand))
        if(!user.isCreative || !user.isSneaking) return TypedActionResult.pass(user.getStackInHand(hand))

        val stack = user.getStackInHand(hand)
        if(stack.item is ItemDataModel) {
            val tier = stack.dataModel.tier()
            stack.dataModel.dataAmount = if(!tier.isMaxTier()) tier.nextTierOrCurrent().dataAmount else DataModelTier.FAULTY.dataAmount
            user.sendMessage(stack.dataModel.tier().text, true)
            TypedActionResult.success(user.getStackInHand(hand), false)
        }
        return TypedActionResult.pass(user.getStackInHand(hand))
    }
}
