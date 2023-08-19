/*
 *
 *  Copyright (C) 2021 Nathan P. Bombana, IterationFunk
 *
 *  This file is part of Deep Mob Learning: Refabricated.
 *
 *  Deep Mob Learning: Refabricated is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Deep Mob Learning: Refabricated is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Deep Mob Learning: Refabricated.  If not, see <https://www.gnu.org/licenses/>.
 */

package dev.nathanpb.dml.screen.handler

import dev.nathanpb.dml.identifier
import dev.nathanpb.dml.item.ItemDataModel
import dev.nathanpb.dml.item.ItemPristineMatter
import dev.nathanpb.dml.screen.handler.widget.WEnergyComponent
import dev.nathanpb.dml.utils.RenderUtils
import io.github.cottonmc.cotton.gui.SyncedGuiDescription
import io.github.cottonmc.cotton.gui.widget.WItemSlot
import io.github.cottonmc.cotton.gui.widget.WPlainPanel
import io.github.cottonmc.cotton.gui.widget.data.Insets
import io.github.cottonmc.cotton.gui.widget.icon.TextureIcon
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SimpleInventory
import net.minecraft.screen.ScreenHandlerContext

class DataSynthesizerHandler(
    syncId: Int,
    playerInventory: PlayerInventory,
    ctx: ScreenHandlerContext
) : SyncedGuiDescription(
    HANDLER_DATA_SYNTHESIZER,
    syncId, playerInventory,
    getBlockInventory(ctx),
    getBlockPropertyDelegate(ctx)
) {
    init {
        val root = WPlainPanel()
        setRootPanel(root)
        root.insets = Insets.ROOT_PANEL


        val dataModelSlot = WItemSlot.of(blockInventory, 0, 1, 1).apply {
            setFilter { stack ->
                stack.item is ItemDataModel // FIXME: Replace with tag
            }

            icon = TextureIcon(identifier("textures/gui/slot_background/data_model_slot_background.png"))
        }
        root.add(dataModelSlot, 4*18, 2*18+6)

        val energyComponent = WEnergyComponent(0, 1, blockInventory, 1)
        root.add(energyComponent, 0, 1*18-6)


        root.add(createPlayerInventoryPanel(), 0, 5*18)

        root.validate(this)

        (blockInventory as? SimpleInventory)?.addListener {
            sendContentUpdates()
        }
    }

    override fun canUse(entity: PlayerEntity?) = true

    override fun addPainters() {
        rootPanel.backgroundPainter = RenderUtils.DEFAULT_BACKGROUND_PAINTER
    }

    override fun getTitleColor(): Int {
        return RenderUtils.getDefaultTextColor(world)
    }

}