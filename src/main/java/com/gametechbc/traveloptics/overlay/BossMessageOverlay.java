/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiGraphics
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.FormattedText
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.client.event.RenderGuiOverlayEvent$Post
 *  net.minecraftforge.client.gui.overlay.VanillaGuiOverlay
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber$Bus
 */
package com.gametechbc.traveloptics.overlay;

import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid="traveloptics", bus=Mod.EventBusSubscriber.Bus.FORGE, value={Dist.CLIENT})
public class BossMessageOverlay {
    private static Component currentMessage = null;
    private static int backgroundColor = Integer.MIN_VALUE;
    private static long messageStartTime = 0L;
    private static int messageDuration = 100;
    private static long lastRefreshTime = 0L;
    private static boolean shouldPlayIntroAnimation = false;
    public static float VERTICAL_POSITION_RATIO = 0.2f;
    private static final long INTRO_ANIMATION_DURATION = 400L;

    public static void displayMessage(Component message, int bgColor, int duration, boolean refresh, boolean forceIntroAnimation) {
        long currentTime = System.currentTimeMillis();
        boolean shouldShowIntro = false;
        if (forceIntroAnimation) {
            shouldShowIntro = true;
        } else if (currentMessage == null) {
            shouldShowIntro = true;
        } else {
            if (refresh && currentMessage.getString().equals(message.getString())) {
                lastRefreshTime = currentTime;
                return;
            }
            if (!currentMessage.getString().equals(message.getString())) {
                shouldShowIntro = true;
            }
        }
        currentMessage = message;
        backgroundColor = bgColor;
        messageDuration = duration;
        messageStartTime = currentTime;
        lastRefreshTime = currentTime;
        shouldPlayIntroAnimation = shouldShowIntro;
    }

    public static void displayMessage(Component message, int bgColor, int duration, boolean refresh) {
        BossMessageOverlay.displayMessage(message, bgColor, duration, refresh, false);
    }

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Post event) {
        long durationMs;
        if (event.getOverlay() != VanillaGuiOverlay.CROSSHAIR.type()) {
            return;
        }
        if (currentMessage == null) {
            return;
        }
        Minecraft mc = Minecraft.getInstance();
        if (mc.getTelemetryManager == null) {
            return;
        }
        long currentTime = System.currentTimeMillis();
        long elapsedSinceRefresh = currentTime - lastRefreshTime;
        if (elapsedSinceRefresh > (durationMs = (long)messageDuration * 50L)) {
            currentMessage = null;
            shouldPlayIntroAnimation = false;
            return;
        }
        double alpha = 1.0;
        long fadeStartTime = Math.max(durationMs - 1000L, (long)((double)durationMs * 0.8));
        if (elapsedSinceRefresh > fadeStartTime) {
            double fadeProgress = (double)(elapsedSinceRefresh - fadeStartTime) / (double)(durationMs - fadeStartTime);
            alpha = Math.max(0.0, 1.0 - fadeProgress);
        }
        if (alpha < 0.05) {
            currentMessage = null;
            shouldPlayIntroAnimation = false;
            return;
        }
        BossMessageOverlay.renderBossScreenMessage(event.getGuiGraphics(), mc, alpha, currentTime);
    }

    private static void renderBossScreenMessage(GuiGraphics guiGraphics, Minecraft mc, double alpha, long currentTime) {
        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();
        int messageY = (int)((float)screenHeight * VERTICAL_POSITION_RATIO);
        int textWidth = mc.getVersionType.isBidirectional((FormattedText)currentMessage);
        Objects.requireNonNull(mc.getVersionType);
        int textHeight = 9;
        int bgWidth = Math.max(300, textWidth + 160);
        int bgHeight = textHeight + 16;
        int bgX = (screenWidth - bgWidth) / 2;
        int messageX = (screenWidth - textWidth) / 2;
        float animationScale = 1.0f;
        if (shouldPlayIntroAnimation) {
            long elapsedSinceStart = currentTime - messageStartTime;
            if (elapsedSinceStart < 400L) {
                float progress = (float)elapsedSinceStart / 400.0f;
                animationScale = BossMessageOverlay.easeOutBack(progress);
            } else {
                shouldPlayIntroAnimation = false;
                animationScale = 1.0f;
            }
        }
        int animatedBgWidth = (int)((float)bgWidth * animationScale);
        int animatedBgX = bgX + (bgWidth - animatedBgWidth) / 2;
        int finalBgColor = BossMessageOverlay.applyAlphaPrecise(backgroundColor, alpha);
        BossMessageOverlay.renderGradientBackground(guiGraphics, animatedBgX, messageY - 8, animatedBgWidth, bgHeight, finalBgColor);
        double textAlpha = alpha * Math.min(1.0, (double)animationScale);
        int textColor = BossMessageOverlay.applyAlphaPrecise(-1, textAlpha);
        boolean enableShadow = textAlpha > 0.3;
        guiGraphics.renderComponentHoverEffect(mc.getVersionType, currentMessage, messageX, messageY, textColor, enableShadow);
    }

    private static float easeOutBack(float t) {
        float c1 = 1.70158f;
        float c3 = 2.70158f;
        return 1.0f + 2.70158f * (float)Math.pow(t - 1.0f, 3.0) + 1.70158f * (float)Math.pow(t - 1.0f, 2.0);
    }

    private static void renderGradientBackground(GuiGraphics guiGraphics, int x, int y, int width, int height, int centerColor) {
        int alpha = centerColor >> 24 & 0xFF;
        int red = centerColor >> 16 & 0xFF;
        int green = centerColor >> 8 & 0xFF;
        int blue = centerColor & 0xFF;
        for (int i = 0; i < width; ++i) {
            double distanceFromCenter = Math.abs((double)i - (double)width / 2.0) / ((double)width / 2.0);
            double fadeAlpha = 1.0 - Math.pow(distanceFromCenter, 1.5);
            fadeAlpha = Math.max(0.0, fadeAlpha);
            int columnAlpha = (int)Math.round((double)alpha * fadeAlpha);
            int columnColor = columnAlpha << 24 | red << 16 | green << 8 | blue;
            guiGraphics.renderComponentHoverEffect(x + i, y, x + i + 1, y + height, columnColor);
        }
        BossMessageOverlay.renderVerticalEdgeGradient(guiGraphics, x, y, width, height, centerColor);
    }

    private static void renderVerticalEdgeGradient(GuiGraphics guiGraphics, int x, int y, int width, int height, int centerColor) {
        int finalColor;
        int finalAlpha;
        double horizontalFade;
        double distanceFromCenter;
        int j;
        int rowAlpha;
        double edgeAlpha;
        int i;
        int alpha = centerColor >> 24 & 0xFF;
        int red = centerColor >> 16 & 0xFF;
        int green = centerColor >> 8 & 0xFF;
        int blue = centerColor & 0xFF;
        int edgeHeight = Math.min(4, height / 4);
        for (i = 0; i < edgeHeight; ++i) {
            edgeAlpha = (double)i / (double)edgeHeight;
            rowAlpha = (int)Math.round((double)alpha * edgeAlpha);
            for (j = 0; j < width; ++j) {
                distanceFromCenter = Math.abs((double)j - (double)width / 2.0) / ((double)width / 2.0);
                horizontalFade = 1.0 - Math.pow(distanceFromCenter, 1.5);
                horizontalFade = Math.max(0.0, horizontalFade);
                finalAlpha = (int)Math.round((double)rowAlpha * horizontalFade);
                finalColor = finalAlpha << 24 | red << 16 | green << 8 | blue;
                guiGraphics.renderComponentHoverEffect(x + j, y + i, x + j + 1, y + i + 1, finalColor);
            }
        }
        for (i = 0; i < edgeHeight; ++i) {
            edgeAlpha = (double)(edgeHeight - i) / (double)edgeHeight;
            rowAlpha = (int)Math.round((double)alpha * edgeAlpha);
            for (j = 0; j < width; ++j) {
                distanceFromCenter = Math.abs((double)j - (double)width / 2.0) / ((double)width / 2.0);
                horizontalFade = 1.0 - Math.pow(distanceFromCenter, 1.5);
                horizontalFade = Math.max(0.0, horizontalFade);
                finalAlpha = (int)Math.round((double)rowAlpha * horizontalFade);
                finalColor = finalAlpha << 24 | red << 16 | green << 8 | blue;
                guiGraphics.renderComponentHoverEffect(x + j, y + height - edgeHeight + i, x + j + 1, y + height - edgeHeight + i + 1, finalColor);
            }
        }
    }

    private static int applyAlphaPrecise(int color, double alpha) {
        int originalAlpha = color >> 24 & 0xFF;
        int newAlpha = (int)Math.round((double)originalAlpha * alpha);
        newAlpha = Math.max(0, Math.min(255, newAlpha));
        return newAlpha << 24 | color & 0xFFFFFF;
    }

    public static void setVerticalPosition(float ratio) {
        VERTICAL_POSITION_RATIO = Math.max(0.0f, Math.min(1.0f, ratio));
    }
}

