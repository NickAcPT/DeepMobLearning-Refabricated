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

package dev.nathanpb.dml.modular_armor.effects

import dev.nathanpb.dml.MOD_ID
import dev.nathanpb.dml.enums.DataModelTier
import dev.nathanpb.dml.enums.EntityCategory
import dev.nathanpb.dml.identifier
import dev.nathanpb.dml.modular_armor.DEPTH_STRIDER_EFFECT
import dev.nathanpb.dml.modular_armor.core.EffectStackOption
import dev.nathanpb.dml.modular_armor.core.ModularEffectContext
import dev.nathanpb.dml.modular_armor.core.ModularEffectTriggerPayload
import dev.nathanpb.dml.modular_armor.modularArmorConfig
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.text.Text

class DepthStriderEffect : StatusEffectLikeEffect(
    identifier("depth_strider"),
    EntityCategory.OCEAN,
    modularArmorConfig.glitchArmor.costs::depthStrider,
    EffectStackOption.PRIORITIZE_GREATER
) {

    override val name = Text.translatable("effect.${MOD_ID}.depth_strider")
    override val description = Text.translatable("effect.${MOD_ID}.depth_strider.description")

    override fun createEffectInstance(context: ModularEffectContext): StatusEffectInstance {
        return StatusEffectInstance(DEPTH_STRIDER_EFFECT, 16 * 20, context.tier.ordinal / 2, true, false)
    }

    override fun acceptTier(tier: DataModelTier): Boolean = tier.ordinal >= 2 && tier != DataModelTier.SELF_AWARE
    override fun minimumTier(): DataModelTier = DataModelTier.ADVANCED

    override fun canApply(context: ModularEffectContext, payload: ModularEffectTriggerPayload): Boolean {
        return super.canApply(context, payload) && context.player.isTouchingWater
    }

}
