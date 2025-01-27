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

import dev.nathanpb.dml.enums.DataModelTier
import dev.nathanpb.dml.enums.EntityCategory
import dev.nathanpb.dml.identifier
import dev.nathanpb.dml.modular_armor.core.EffectStackOption
import dev.nathanpb.dml.modular_armor.core.ModularEffectContext
import dev.nathanpb.dml.modular_armor.modularArmorConfig
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.text.Text

class JumpBoostEffect : StatusEffectLikeEffect(
    identifier("jump_boost"),
    EntityCategory.SLIMY,
    modularArmorConfig.glitchArmor.costs::jumpBoost,
    EffectStackOption.PRIORITIZE_GREATER
) {

    override val name = Text.translatable("effect.minecraft.jump_boost")
    override val description = Text.translatable("effect.minecraft.jump_boost.module_description")

    override fun createEffectInstance(context: ModularEffectContext): StatusEffectInstance {
        return StatusEffectInstance(StatusEffects.JUMP_BOOST, 20 * 17, context.tier.ordinal / 2, false, false)
    }

    override fun acceptTier(tier: DataModelTier) = tier.ordinal >= 2
    override fun minimumTier(): DataModelTier = DataModelTier.ADVANCED
    override fun isScaled(): Boolean = true

}
