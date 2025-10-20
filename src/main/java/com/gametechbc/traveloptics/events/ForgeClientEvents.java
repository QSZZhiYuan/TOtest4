/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiGraphics
 *  net.minecraft.client.player.LocalPlayer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.FormattedText
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  net.minecraftforge.client.event.CustomizeGuiOverlayEvent$BossEventProgress
 *  net.minecraftforge.client.event.RenderGuiOverlayEvent$Post
 *  net.minecraftforge.client.event.ViewportEvent$ComputeCameraAngles
 *  net.minecraftforge.client.event.ViewportEvent$ComputeFogColor
 *  net.minecraftforge.client.gui.overlay.VanillaGuiOverlay
 *  net.minecraftforge.event.TickEvent$ClientTickEvent
 *  net.minecraftforge.event.TickEvent$Phase
 *  net.minecraftforge.eventbus.api.EventPriority
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber$Bus
 */
package com.gametechbc.traveloptics.events;

import com.gametechbc.traveloptics.ClientProxy;
import com.gametechbc.traveloptics.config.ClientConfig;
import com.gametechbc.traveloptics.entity.misc.TOFollowingScreenShakeEntity;
import com.gametechbc.traveloptics.entity.misc.TOPowerInversionEntity;
import com.gametechbc.traveloptics.entity.misc.TOScreenFlashEntity;
import com.gametechbc.traveloptics.entity.misc.TOScreenShakeEntity;
import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.gametechbc.traveloptics.overlay.ScreenEffectOverlayHelper;
import com.gametechbc.traveloptics.util.TravelopticsKeybindManager;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid="traveloptics", bus=Mod.EventBusSubscriber.Bus.FORGE, value={Dist.CLIENT})
public class ForgeClientEvents {
    private static final float[] VIOLET_SKY_COLOR = new float[]{0.25f, 0.0f, 0.45f};
    private static final ResourceLocation NIGHTWARDEN_BOSS_BAR = new ResourceLocation("traveloptics", "textures/gui/boss_bar/nightwarden_boss_bar.png");
    private static final ResourceLocation ZAEVORATH_NATURE_BOSS_BAR = new ResourceLocation("traveloptics", "textures/gui/boss_bar/zaevorath_boss_bar_nature.png");
    private static final ResourceLocation ZAEVORATH_FIRE_BOSS_BAR = new ResourceLocation("traveloptics", "textures/gui/boss_bar/zaevorath_boss_bar_fire.png");
    private static final ResourceLocation ZAEVORATH_AQUA_BOSS_BAR = new ResourceLocation("traveloptics", "textures/gui/boss_bar/zaevorath_boss_bar_aqua.png");

    @SubscribeEvent
    public static void onSkyRender(ViewportEvent.ComputeFogColor event) {
        MobEffectInstance effect;
        Minecraft mc = Minecraft.getInstance();
        if (mc.getTelemetryManager != null && (effect = mc.getTelemetryManager.getStandingEyeHeight((MobEffect)TravelopticsEffects.ASTRAL_SENSE_TREASURE.get())) != null) {
            event.setRed(VIOLET_SKY_COLOR[0]);
            event.setGreen(VIOLET_SKY_COLOR[1]);
            event.setBlue(VIOLET_SKY_COLOR[2]);
        }
        if (mc.getTelemetryManager != null && (effect = mc.getTelemetryManager.getStandingEyeHeight((MobEffect)TravelopticsEffects.ASTRAL_SENSE.get())) != null) {
            event.setRed(VIOLET_SKY_COLOR[0]);
            event.setGreen(VIOLET_SKY_COLOR[1]);
            event.setBlue(VIOLET_SKY_COLOR[2]);
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public static void renderBossOverlay(CustomizeGuiOverlayEvent.BossEventProgress event) {
        if (ClientProxy.bossBarRenderTypes.containsKey(event.getBossEvent().getId())) {
            int shieldProgressWidth;
            int shieldBarY;
            int shieldBarX;
            PoseStack poseStack;
            int j1;
            int i1;
            int l;
            int progressWidth;
            int renderTypeFor = ClientProxy.bossBarRenderTypes.get(event.getBossEvent().getId());
            int i = event.getGuiGraphics().renderComponentHoverEffect();
            int j = event.getY();
            Component component = event.getBossEvent().getName();
            if (renderTypeFor == 0) {
                event.setCanceled(true);
                event.getGuiGraphics().renderComponentHoverEffect(NIGHTWARDEN_BOSS_BAR, event.getX(), event.getY() + 1, 26.0f, 30.0f, 188, 27, 241, 115);
                progressWidth = (int)(event.getBossEvent().getProgress() * 188.0f);
                event.getGuiGraphics().renderComponentHoverEffect(NIGHTWARDEN_BOSS_BAR, event.getX(), event.getY() + 1, 26.0f, 1.0f, progressWidth, 27, 241, 115);
                l = Minecraft.getInstance().getVersionType.isBidirectional((FormattedText)component);
                i1 = i / 2 - l / 2;
                j1 = j - 9;
                poseStack = event.getGuiGraphics().enableScissor();
                poseStack.mulPoseMatrix();
                poseStack.mulPoseMatrix((float)i1, (float)j1, 0.0f);
                Minecraft.getInstance().getVersionType.isBidirectional(component.getVisualOrderText(), 0.0f, 0.0f, 9978623, 2557773, poseStack.last().pose(), (MultiBufferSource)event.getGuiGraphics().bufferSource(), 240);
                poseStack.popPose();
                event.setIncrement(event.getIncrement() + 7);
            }
            if (renderTypeFor == 1) {
                event.setCanceled(true);
                int adaptationBarX = event.getX() + 69;
                int adaptationBarY = event.getY() + 1;
                event.getGuiGraphics().renderComponentHoverEffect(NIGHTWARDEN_BOSS_BAR, adaptationBarX, adaptationBarY, 95.0f, 87.0f, 50, 13, 241, 115);
                float adaptationProgress = event.getBossEvent().getProgress();
                int adaptationProgressWidth = (int)(adaptationProgress * 50.0f);
                event.getGuiGraphics().renderComponentHoverEffect(NIGHTWARDEN_BOSS_BAR, adaptationBarX, adaptationBarY, 95.0f, 64.0f, adaptationProgressWidth, 13, 241, 115);
                event.setIncrement(event.getIncrement() + 5);
            }
            if (renderTypeFor == 2) {
                event.setCanceled(true);
                event.getGuiGraphics().renderComponentHoverEffect(ZAEVORATH_NATURE_BOSS_BAR, event.getX(), event.getY() + 1, 26.0f, 0.0f, 188, 23, 241, 115);
                progressWidth = (int)(event.getBossEvent().getProgress() * 188.0f);
                event.getGuiGraphics().renderComponentHoverEffect(ZAEVORATH_NATURE_BOSS_BAR, event.getX(), event.getY() + 1, 26.0f, 30.0f, progressWidth, 23, 241, 115);
                l = Minecraft.getInstance().getVersionType.isBidirectional((FormattedText)component);
                i1 = i / 2 - l / 2;
                j1 = j - 9;
                poseStack = event.getGuiGraphics().enableScissor();
                poseStack.mulPoseMatrix();
                poseStack.mulPoseMatrix((float)i1, (float)j1, 0.0f);
                Minecraft.getInstance().getVersionType.isBidirectional(component.getVisualOrderText(), 0.0f, 0.0f, 9978623, 2557773, poseStack.last().pose(), (MultiBufferSource)event.getGuiGraphics().bufferSource(), 240);
                poseStack.popPose();
                event.setIncrement(event.getIncrement() + 7);
            }
            if (renderTypeFor == 3) {
                event.setCanceled(true);
                shieldBarX = event.getX() + 4;
                shieldBarY = event.getY() - 2;
                event.getGuiGraphics().renderComponentHoverEffect(ZAEVORATH_NATURE_BOSS_BAR, shieldBarX, shieldBarY, 30.0f, 65.0f, 181, 4, 241, 115);
                shieldProgressWidth = (int)(event.getBossEvent().getProgress() * 181.0f);
                event.getGuiGraphics().renderComponentHoverEffect(ZAEVORATH_NATURE_BOSS_BAR, shieldBarX, shieldBarY, 30.0f, 85.0f, shieldProgressWidth, 4, 241, 115);
                event.setIncrement(event.getIncrement() + 3);
            }
            if (renderTypeFor == 4) {
                event.setCanceled(true);
                event.getGuiGraphics().renderComponentHoverEffect(ZAEVORATH_FIRE_BOSS_BAR, event.getX(), event.getY() + 1, 26.0f, 0.0f, 188, 23, 241, 115);
                progressWidth = (int)(event.getBossEvent().getProgress() * 188.0f);
                event.getGuiGraphics().renderComponentHoverEffect(ZAEVORATH_FIRE_BOSS_BAR, event.getX(), event.getY() + 1, 26.0f, 30.0f, progressWidth, 23, 241, 115);
                l = Minecraft.getInstance().getVersionType.isBidirectional((FormattedText)component);
                i1 = i / 2 - l / 2;
                j1 = j - 9;
                poseStack = event.getGuiGraphics().enableScissor();
                poseStack.mulPoseMatrix();
                poseStack.mulPoseMatrix((float)i1, (float)j1, 0.0f);
                Minecraft.getInstance().getVersionType.isBidirectional(component.getVisualOrderText(), 0.0f, 0.0f, 9978623, 2557773, poseStack.last().pose(), (MultiBufferSource)event.getGuiGraphics().bufferSource(), 240);
                poseStack.popPose();
                event.setIncrement(event.getIncrement() + 7);
            }
            if (renderTypeFor == 5) {
                event.setCanceled(true);
                shieldBarX = event.getX() + 4;
                shieldBarY = event.getY() - 2;
                event.getGuiGraphics().renderComponentHoverEffect(ZAEVORATH_FIRE_BOSS_BAR, shieldBarX, shieldBarY, 30.0f, 65.0f, 181, 4, 241, 115);
                shieldProgressWidth = (int)(event.getBossEvent().getProgress() * 181.0f);
                event.getGuiGraphics().renderComponentHoverEffect(ZAEVORATH_FIRE_BOSS_BAR, shieldBarX, shieldBarY, 30.0f, 85.0f, shieldProgressWidth, 4, 241, 115);
                event.setIncrement(event.getIncrement() + 3);
            }
            if (renderTypeFor == 6) {
                event.setCanceled(true);
                event.getGuiGraphics().renderComponentHoverEffect(ZAEVORATH_AQUA_BOSS_BAR, event.getX(), event.getY() + 1, 26.0f, 0.0f, 188, 23, 241, 115);
                progressWidth = (int)(event.getBossEvent().getProgress() * 188.0f);
                event.getGuiGraphics().renderComponentHoverEffect(ZAEVORATH_AQUA_BOSS_BAR, event.getX(), event.getY() + 1, 26.0f, 30.0f, progressWidth, 23, 241, 115);
                l = Minecraft.getInstance().getVersionType.isBidirectional((FormattedText)component);
                i1 = i / 2 - l / 2;
                j1 = j - 9;
                poseStack = event.getGuiGraphics().enableScissor();
                poseStack.mulPoseMatrix();
                poseStack.mulPoseMatrix((float)i1, (float)j1, 0.0f);
                Minecraft.getInstance().getVersionType.isBidirectional(component.getVisualOrderText(), 0.0f, 0.0f, 9978623, 2557773, poseStack.last().pose(), (MultiBufferSource)event.getGuiGraphics().bufferSource(), 240);
                poseStack.popPose();
                event.setIncrement(event.getIncrement() + 7);
            }
            if (renderTypeFor == 7) {
                event.setCanceled(true);
                shieldBarX = event.getX() + 4;
                shieldBarY = event.getY() - 2;
                event.getGuiGraphics().renderComponentHoverEffect(ZAEVORATH_AQUA_BOSS_BAR, shieldBarX, shieldBarY, 30.0f, 65.0f, 181, 4, 241, 115);
                shieldProgressWidth = (int)(event.getBossEvent().getProgress() * 181.0f);
                event.getGuiGraphics().renderComponentHoverEffect(ZAEVORATH_AQUA_BOSS_BAR, shieldBarX, shieldBarY, 30.0f, 85.0f, shieldProgressWidth, 4, 241, 115);
                event.setIncrement(event.getIncrement() + 3);
            }
        }
    }

    @SubscribeEvent
    @OnlyIn(value=Dist.CLIENT)
    public static void onCameraSetup(ViewportEvent.ComputeCameraAngles event) {
        LocalPlayer player = Minecraft.getInstance().getTelemetryManager;
        if (player != null && !Minecraft.getInstance().isPaused() && ((Boolean)ClientConfig.activateScreenShake.get()).booleanValue()) {
            float delta = Minecraft.getInstance().getFrameTime();
            float ticksExistedDelta = (float)player.getTags + delta;
            float shakeAmplitude = 0.0f;
            for (Entity screenShake : player.level().getNearbyEntities(TOScreenShakeEntity.class, player.getBoundingBox().getYsize(50.0, 50.0, 50.0))) {
                if (!(screenShake.getY((Entity)player) <= screenShake.getRadius())) continue;
                shakeAmplitude += screenShake.getShakeAmount((Player)player, delta);
            }
            for (Entity screenShake : player.level().getNearbyEntities(TOFollowingScreenShakeEntity.class, player.getBoundingBox().getYsize(50.0, 50.0, 50.0))) {
                if (!(screenShake.getY((Entity)player) <= screenShake.getRadius())) continue;
                shakeAmplitude += screenShake.getShakeAmount((Player)player, delta);
            }
            if (shakeAmplitude > 1.0f) {
                shakeAmplitude = 1.0f;
            }
            if (shakeAmplitude > 0.0f) {
                event.setPitch((float)((double)event.getPitch() + (double)shakeAmplitude * Math.cos(ticksExistedDelta * 3.0f + 2.0f) * 25.0));
                event.setYaw((float)((double)event.getYaw() + (double)shakeAmplitude * Math.cos(ticksExistedDelta * 5.0f + 1.0f) * 25.0));
                event.setRoll((float)((double)event.getRoll() + (double)shakeAmplitude * Math.cos(ticksExistedDelta * 4.0f) * 25.0));
            }
        }
    }

    @SubscribeEvent
    @OnlyIn(value=Dist.CLIENT)
    public static void onRenderOverlay(RenderGuiOverlayEvent.Post event) {
        LocalPlayer player;
        if (event.getOverlay() == VanillaGuiOverlay.CROSSHAIR.type() && (player = Minecraft.getInstance().getTelemetryManager) != null && !Minecraft.getInstance().isPaused() && ((Boolean)ClientConfig.activateScreenFlash.get()).booleanValue()) {
            float delta = Minecraft.getInstance().getFrameTime();
            GuiGraphics guiGraphics = event.getGuiGraphics();
            ForgeClientEvents.processScreenFlashEffects((Player)player, delta, guiGraphics);
            ForgeClientEvents.processPowerInversionEffects((Player)player, delta, guiGraphics);
        }
    }

    @OnlyIn(value=Dist.CLIENT)
    private static void processScreenFlashEffects(Player player, float delta, GuiGraphics guiGraphics) {
        float totalFlashAlpha = 0.0f;
        int flashColor = 0xFFFFFF;
        for (TOScreenFlashEntity flash : player.level().getNearbyEntities(TOScreenFlashEntity.class, player.getBoundingBox().getYsize(50.0, 50.0, 50.0))) {
            float alpha;
            if (!(flash.getY((Entity)player) <= flash.getRadius()) || !((alpha = flash.getFlashAlpha(player, delta)) > totalFlashAlpha)) continue;
            totalFlashAlpha = alpha;
            flashColor = flash.getFlashColor();
        }
        if (totalFlashAlpha > 1.0f) {
            totalFlashAlpha = 1.0f;
        }
        if (totalFlashAlpha > 0.0f) {
            ScreenEffectOverlayHelper.renderFlashOverlay(guiGraphics, totalFlashAlpha, flashColor);
        }
    }

    @OnlyIn(value=Dist.CLIENT)
    private static void processPowerInversionEffects(Player player, float delta, GuiGraphics guiGraphics) {
        TOPowerInversionEntity.PowerEffectData combinedEffect = new TOPowerInversionEntity.PowerEffectData(0.0f, 0.0f, TOPowerInversionEntity.EffectPhase.NONE);
        boolean shouldInvert = false;
        int flashColor = 0xF8F8F8;
        for (TOPowerInversionEntity powerEffect : player.level().getNearbyEntities(TOPowerInversionEntity.class, player.getBoundingBox().getYsize(50.0, 50.0, 50.0))) {
            if (!(powerEffect.getY((Entity)player) <= powerEffect.getRadius())) continue;
            TOPowerInversionEntity.PowerEffectData effectData = powerEffect.getEffectData(player, delta);
            if (effectData.phase == TOPowerInversionEntity.EffectPhase.NONE || !(effectData.flashIntensity > combinedEffect.flashIntensity) && !(effectData.effectIntensity > combinedEffect.effectIntensity)) continue;
            combinedEffect = effectData;
            shouldInvert = powerEffect.shouldInvertColors();
            flashColor = powerEffect.getFlashColor();
        }
        if (combinedEffect.phase != TOPowerInversionEntity.EffectPhase.NONE) {
            ScreenEffectOverlayHelper.renderPowerInversionOverlay(guiGraphics, combinedEffect, shouldInvert, flashColor);
        }
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            TravelopticsKeybindManager.updateKeyStates();
        }
    }
}

