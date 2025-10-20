/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.GlStateManager$DestFactor
 *  com.mojang.blaze3d.platform.GlStateManager$SourceFactor
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.vertex.BufferBuilder
 *  com.mojang.blaze3d.vertex.BufferBuilder$RenderedBuffer
 *  com.mojang.blaze3d.vertex.BufferUploader
 *  com.mojang.blaze3d.vertex.DefaultVertexFormat
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.Tesselator
 *  com.mojang.blaze3d.vertex.VertexFormat$Mode
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiGraphics
 *  net.minecraft.client.renderer.GameRenderer
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  org.joml.Matrix4f
 */
package com.gametechbc.traveloptics.overlay;

import com.gametechbc.traveloptics.entity.misc.TOPowerInversionEntity;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Matrix4f;

@OnlyIn(value=Dist.CLIENT)
public class ScreenEffectOverlayHelper {
    public static void renderFlashOverlay(GuiGraphics guiGraphics, float alpha, int color) {
        Minecraft mc = Minecraft.getInstance();
        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();
        float red = (float)(color >> 16 & 0xFF) / 255.0f;
        float green = (float)(color >> 8 & 0xFF) / 255.0f;
        float blue = (float)(color & 0xFF) / 255.0f;
        PoseStack poseStack = guiGraphics.enableScissor();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        BufferBuilder buffer = Tesselator.getInstance().getBuilder();
        buffer.vertex(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        Matrix4f matrix = poseStack.last().pose();
        buffer.normal(matrix, 0.0f, (float)screenHeight, 0.0f).normal(red, green, blue, alpha).endVertex();
        buffer.normal(matrix, (float)screenWidth, (float)screenHeight, 0.0f).normal(red, green, blue, alpha).endVertex();
        buffer.normal(matrix, (float)screenWidth, 0.0f, 0.0f).normal(red, green, blue, alpha).endVertex();
        buffer.normal(matrix, 0.0f, 0.0f, 0.0f).normal(red, green, blue, alpha).endVertex();
        BufferUploader.bindImmediateBuffer((BufferBuilder.RenderedBuffer)buffer.end());
        RenderSystem.disableBlend();
    }

    public static void renderPowerInversionOverlay(GuiGraphics guiGraphics, TOPowerInversionEntity.PowerEffectData effectData, boolean shouldInvert, int flashColor) {
        Minecraft mc = Minecraft.getInstance();
        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();
        if (effectData.flashIntensity > 0.0f) {
            float red = (float)(flashColor >> 16 & 0xFF) / 255.0f;
            float green = (float)(flashColor >> 8 & 0xFF) / 255.0f;
            float blue = (float)(flashColor & 0xFF) / 255.0f;
            float alpha = effectData.flashIntensity;
            PoseStack poseStack = guiGraphics.enableScissor();
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.setShader(GameRenderer::getPositionColorShader);
            BufferBuilder buffer = Tesselator.getInstance().getBuilder();
            buffer.vertex(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
            Matrix4f matrix = poseStack.last().pose();
            buffer.normal(matrix, 0.0f, (float)screenHeight, 0.0f).normal(red, green, blue, alpha).endVertex();
            buffer.normal(matrix, (float)screenWidth, (float)screenHeight, 0.0f).normal(red, green, blue, alpha).endVertex();
            buffer.normal(matrix, (float)screenWidth, 0.0f, 0.0f).normal(red, green, blue, alpha).endVertex();
            buffer.normal(matrix, 0.0f, 0.0f, 0.0f).normal(red, green, blue, alpha).endVertex();
            BufferUploader.bindImmediateBuffer((BufferBuilder.RenderedBuffer)buffer.end());
            RenderSystem.disableBlend();
        }
        if (effectData.effectIntensity > 0.0f) {
            if (shouldInvert) {
                ScreenEffectOverlayHelper.renderEnhancedInversionPattern(guiGraphics, screenWidth, screenHeight, effectData.effectIntensity);
            } else {
                ScreenEffectOverlayHelper.renderEnhancedDesaturation(guiGraphics, screenWidth, screenHeight, effectData.effectIntensity);
            }
        }
    }

    public static void renderEnhancedInversionPattern(GuiGraphics guiGraphics, int screenWidth, int screenHeight, float intensity) {
        if (intensity <= 0.0f) {
            return;
        }
        PoseStack poseStack = guiGraphics.enableScissor();
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE_MINUS_DST_COLOR, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        BufferBuilder buffer = Tesselator.getInstance().getBuilder();
        buffer.vertex(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        Matrix4f matrix = poseStack.last().pose();
        float inversionAlpha = Math.min(intensity * 1.5f, 0.95f);
        inversionAlpha *= inversionAlpha;
        buffer.normal(matrix, 0.0f, (float)screenHeight, 0.0f).normal(0.97f, 0.97f, 0.97f, inversionAlpha).endVertex();
        buffer.normal(matrix, (float)screenWidth, (float)screenHeight, 0.0f).normal(0.97f, 0.97f, 0.97f, inversionAlpha).endVertex();
        buffer.normal(matrix, (float)screenWidth, 0.0f, 0.0f).normal(0.97f, 0.97f, 0.97f, inversionAlpha).endVertex();
        buffer.normal(matrix, 0.0f, 0.0f, 0.0f).normal(0.97f, 0.97f, 0.97f, inversionAlpha).endVertex();
        BufferUploader.bindImmediateBuffer((BufferBuilder.RenderedBuffer)buffer.end());
        RenderSystem.blendFunc((GlStateManager.SourceFactor)GlStateManager.SourceFactor.DST_COLOR, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        buffer.vertex(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        float contrastAlpha = intensity * 0.7f;
        contrastAlpha *= contrastAlpha;
        float contrastDarkness = 0.25f;
        buffer.normal(matrix, 0.0f, (float)screenHeight, 0.0f).normal(contrastDarkness, contrastDarkness, contrastDarkness, contrastAlpha).endVertex();
        buffer.normal(matrix, (float)screenWidth, (float)screenHeight, 0.0f).normal(contrastDarkness, contrastDarkness, contrastDarkness, contrastAlpha).endVertex();
        buffer.normal(matrix, (float)screenWidth, 0.0f, 0.0f).normal(contrastDarkness, contrastDarkness, contrastDarkness, contrastAlpha).endVertex();
        buffer.normal(matrix, 0.0f, 0.0f, 0.0f).normal(contrastDarkness, contrastDarkness, contrastDarkness, contrastAlpha).endVertex();
        BufferUploader.bindImmediateBuffer((BufferBuilder.RenderedBuffer)buffer.end());
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
    }

    public static void renderEnhancedDesaturation(GuiGraphics guiGraphics, int screenWidth, int screenHeight, float intensity) {
        PoseStack poseStack = guiGraphics.enableScissor();
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.SourceFactor)GlStateManager.SourceFactor.DST_COLOR, (GlStateManager.DestFactor)GlStateManager.DestFactor.SRC_COLOR);
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        BufferBuilder buffer = Tesselator.getInstance().getBuilder();
        buffer.vertex(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        Matrix4f matrix = poseStack.last().pose();
        float desatAlpha = Math.min(intensity * 1.4f, 1.0f);
        float grayValue = 0.4f;
        buffer.normal(matrix, 0.0f, (float)screenHeight, 0.0f).normal(grayValue, grayValue, grayValue, desatAlpha).endVertex();
        buffer.normal(matrix, (float)screenWidth, (float)screenHeight, 0.0f).normal(grayValue, grayValue, grayValue, desatAlpha).endVertex();
        buffer.normal(matrix, (float)screenWidth, 0.0f, 0.0f).normal(grayValue, grayValue, grayValue, desatAlpha).endVertex();
        buffer.normal(matrix, 0.0f, 0.0f, 0.0f).normal(grayValue, grayValue, grayValue, desatAlpha).endVertex();
        BufferUploader.bindImmediateBuffer((BufferBuilder.RenderedBuffer)buffer.end());
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
    }

    public static void renderFullScreenQuad(PoseStack poseStack, int screenWidth, int screenHeight, float red, float green, float blue, float alpha) {
        BufferBuilder buffer = Tesselator.getInstance().getBuilder();
        buffer.vertex(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        Matrix4f matrix = poseStack.last().pose();
        buffer.normal(matrix, 0.0f, (float)screenHeight, 0.0f).normal(red, green, blue, alpha).endVertex();
        buffer.normal(matrix, (float)screenWidth, (float)screenHeight, 0.0f).normal(red, green, blue, alpha).endVertex();
        buffer.normal(matrix, (float)screenWidth, 0.0f, 0.0f).normal(red, green, blue, alpha).endVertex();
        buffer.normal(matrix, 0.0f, 0.0f, 0.0f).normal(red, green, blue, alpha).endVertex();
        BufferUploader.bindImmediateBuffer((BufferBuilder.RenderedBuffer)buffer.end());
    }
}

