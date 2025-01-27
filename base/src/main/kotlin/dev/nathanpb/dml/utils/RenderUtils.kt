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

package dev.nathanpb.dml.utils

import com.mojang.blaze3d.systems.RenderSystem
import dev.nathanpb.dml.identifier
import dev.nathanpb.dml.utils.RenderUtils.Companion.ALT_STYLE
import dev.nathanpb.dml.utils.RenderUtils.Companion.STYLE
import io.github.cottonmc.cotton.gui.client.BackgroundPainter
import io.github.cottonmc.cotton.gui.client.NinePatchBackgroundPainter
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.DiffuseLighting
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.LivingEntity
import net.minecraft.particle.DustParticleEffect
import net.minecraft.screen.ScreenTexts
import net.minecraft.text.MutableText
import net.minecraft.text.Style
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.math.RotationAxis
import net.minecraft.util.math.Vec3d
import java.text.NumberFormat
import java.util.*

// FIXME rename to something less client-sidey??
class RenderUtils {

    companion object {
        /* Textures */
        val DEFAULT_BACKGROUND_PAINTER: NinePatchBackgroundPainter by lazy {
            BackgroundPainter.createNinePatch(identifier("textures/gui/dml_background_painter.png"))
        }
        val INNER_BACKGROUND_PAINTER: NinePatchBackgroundPainter by lazy {
            BackgroundPainter.createNinePatch(identifier("textures/gui/inner_background_painter.png"))
        }
        val DML_WIDGETS: Identifier = identifier("textures/gui/dml_widgets.png")

        val ARROW: Identifier = identifier("textures/gui/arrow.png")
        val ARROW_BACKGROUND: Identifier = identifier("textures/gui/arrow_background.png")



        /* Energy Bars */
        val ENERGY_BAR_ARROW: Identifier = identifier("textures/gui/energy_bar/energy_bar_arrow.png")

        val ENERGY_BAR_BACKGROUND: Identifier = identifier("textures/gui/energy_bar/energy_bar_background.png")
        val ENERGY_BAR_BACKGROUND_BIG: Identifier = identifier("textures/gui/energy_bar/energy_bar_background_big.png")

        val ENERGY_BAR: Identifier = identifier("textures/gui/energy_bar/energy_bar.png")
        val ENERGY_BAR_BIG: Identifier = identifier("textures/gui/energy_bar/energy_bar_big.png")

        val PRISTINE_ENERGY_BAR: Identifier = identifier("textures/gui/energy_bar/pristine_energy_bar.png")
        val PRISTINE_ENERGY_BAR_BIG: Identifier = identifier("textures/gui/energy_bar/pristine_energy_bar_big.png")



        /* Text Colors & Styles */
        const val TITLE_COLOR: Int = 0x04FCC4
        const val ALT_TITLE_COLOR: Int = 0x62D8FF
        val STYLE: Style = Style.EMPTY.withColor(TITLE_COLOR)
        val ALT_STYLE: Style = Style.EMPTY.withColor(ALT_TITLE_COLOR)
        val ENERGY_COLOR: Int = 0xFCD904
        val ENERGY_STYLE: Style = Style.EMPTY.withColor(ENERGY_COLOR)

        /* Particles */
        val GLITCH_PARTICLE: DustParticleEffect = DustParticleEffect(Vec3d.unpackRgb(TITLE_COLOR).toVector3f(), 1F)
        val ALT_GLITCH_PARTICLE: DustParticleEffect = DustParticleEffect(Vec3d.unpackRgb(ALT_TITLE_COLOR).toVector3f(), 1F)

    }
}

fun getBooleanInfoText(primaryText: MutableText, boolean: Boolean, primaryStyle: Style, secondaryStyle: Style): MutableText {
    val onOffText = ScreenTexts.onOrOff(boolean).copy()
    return getInfoText(primaryText, onOffText, primaryStyle, secondaryStyle)
}

fun getInfoText(primaryText: MutableText, secondaryText: MutableText, primaryStyle: Style = STYLE, secondaryStyle: Style = ALT_STYLE): MutableText {
    primaryText.style = primaryStyle
    secondaryText.style = secondaryStyle

    return primaryText.append(secondaryText)
}

// primaryText | secondaryText
fun getPipeText(primaryText: MutableText, secondaryText: MutableText, parenthesisStyle: Style = ALT_STYLE): MutableText {
    val pipeText = Text.translatable(
        "tooltip.dml-refabricated.pipe",
        primaryText,
        secondaryText
    )
    pipeText.style = parenthesisStyle

    return pipeText
}

// primaryText (value)
fun getParenthesisText(primaryText: MutableText, value: Any, parenthesisStyle: Style = ALT_STYLE, addSpace: Boolean = true): MutableText {
    val parenthesisText = if(addSpace) Text.literal(" ") else Text.empty()
    parenthesisText.append(Text.translatable(
        "tooltip.dml-refabricated.parenthesis",
        value
    ))
    parenthesisText.style = parenthesisStyle

    return primaryText.append(parenthesisText)
}

/*
 * Used to apply commas and periods to numbers according to the client's language
 *
 * en_us: 10000 -> 10,000
 * pt_br: 10000 -> 10.000
 */
@Environment(EnvType.CLIENT)
fun formatAccordingToLanguage(): NumberFormat {
    val locale: Locale = Locale.forLanguageTag(
        MinecraftClient.getInstance().languageManager.language.replace(
            "_",
            "-"
        )
    )

    return NumberFormat.getNumberInstance(locale)
}

// not stolen from mojang, I promise
@Environment(EnvType.CLIENT)
fun drawEntity(
    entity: LivingEntity,
    x: Int, y: Int, size: Int,
    tickDelta: Int,
    rotationZ: Float,
    rotationY: Float,
) {
    val matrixStack = RenderSystem.getModelViewStack()
    matrixStack.push()
    matrixStack.translate(x.toDouble(), y.toDouble(), 1050.0)
    matrixStack.scale(1.0f, 1.0f, -1.0f)
    RenderSystem.applyModelViewMatrix()
    val matrixStack2 = MatrixStack()
    matrixStack2.translate(0.0, 0.0, 1000.0)
    matrixStack2.scale(size.toFloat(), size.toFloat(), size.toFloat())
    val quaternion = RotationAxis.POSITIVE_Z.rotationDegrees(rotationZ)
    val quaternion2 = RotationAxis.POSITIVE_Y.rotationDegrees(rotationY)
    quaternion.mul(quaternion2)
    matrixStack2.multiply(quaternion)


    DiffuseLighting.method_34742()
    val entityRenderDispatcher = MinecraftClient.getInstance().entityRenderDispatcher

    quaternion2.conjugate()
    entityRenderDispatcher.rotation = quaternion2

    entityRenderDispatcher.setRenderShadows(false)
    val immediate = MinecraftClient.getInstance().bufferBuilders.entityVertexConsumers
    RenderSystem.runAsFancy {
        entityRenderDispatcher.render(
            entity,
            0.0,
            0.0,
            0.0,
            0.0f,
            tickDelta.toFloat(),
            matrixStack2,
            immediate,
            15728880
        )
    }
    immediate.draw()
    entityRenderDispatcher.setRenderShadows(true)
    matrixStack.pop()
    RenderSystem.applyModelViewMatrix()
    DiffuseLighting.enableGuiDepthLighting()
}
