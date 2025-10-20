/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiGraphics
 *  net.minecraft.client.player.LocalPlayer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.client.event.RenderGuiOverlayEvent$Post
 *  net.minecraftforge.client.gui.overlay.VanillaGuiOverlay
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber$Bus
 */
package com.gametechbc.traveloptics.overlay.weapon_overlay;

import com.gametechbc.traveloptics.data_manager.PlasmaCoreManager;
import com.gametechbc.traveloptics.init.TravelopticsItems;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid="traveloptics", bus=Mod.EventBusSubscriber.Bus.FORGE, value={Dist.CLIENT})
public class MechanizedWraithbladeOverlay {
    private static final ResourceLocation WEAPON_SILHOUETTE = new ResourceLocation("traveloptics", "textures/gui/weapon/mechanized_wraithblade_silhouette.png");
    public static boolean ENABLE_OVERLAY = false;
    private static boolean isCharging = false;
    private static int chargeProgress = 0;
    private static float networkDamage = 8.0f;

    public static void updateWeaponNetworkState(boolean charging, int progress, float damage) {
        isCharging = charging;
        chargeProgress = progress;
        networkDamage = damage;
    }

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Post event) {
        if (!ENABLE_OVERLAY || event.getOverlay() != VanillaGuiOverlay.HOTBAR.type()) {
            return;
        }
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.getTelemetryManager;
        if (player == null) {
            return;
        }
        ItemStack mainHandItem = player.getMainHandItem();
        if (!MechanizedWraithbladeOverlay.isMechanizedWraithblade(mainHandItem)) {
            return;
        }
        int currentPlasma = PlasmaCoreManager.getPlasmaCore(mainHandItem);
        int maxPlasma = 250;
        float currentDamage = networkDamage;
        MechanizedWraithbladeOverlay.renderTacticalHUD(event.getGuiGraphics(), mc, currentPlasma, maxPlasma, currentDamage);
    }

    private static boolean isMechanizedWraithblade(ItemStack stack) {
        return stack.onDestroyed((Item)TravelopticsItems.MECHANIZED_WRAITHBLADE.get()) || stack.onDestroyed((Item)TravelopticsItems.MECHANIZED_WRAITHBLADE_LEVEL_ONE.get()) || stack.onDestroyed((Item)TravelopticsItems.MECHANIZED_WRAITHBLADE_LEVEL_TWO.get()) || stack.onDestroyed((Item)TravelopticsItems.MECHANIZED_WRAITHBLADE_LEVEL_THREE.get());
    }

    private static void renderTacticalHUD(GuiGraphics guiGraphics, Minecraft mc, int plasma, int maxPlasma, float damage) {
        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();
        int hudWidth = 200;
        int hudHeight = 70;
        int margin = 20;
        int startX = screenWidth - hudWidth - margin;
        int startY = screenHeight - hudHeight - margin;
        PoseStack poseStack = guiGraphics.enableScissor();
        poseStack.mulPoseMatrix();
        MechanizedWraithbladeOverlay.renderMagicalFrame(guiGraphics, startX, startY, hudWidth, hudHeight, plasma, maxPlasma);
        MechanizedWraithbladeOverlay.renderMagicalBackgroundParticles(guiGraphics, startX, startY, hudWidth, hudHeight, plasma, maxPlasma);
        MechanizedWraithbladeOverlay.renderPlasmaLabel(guiGraphics, startX + 8, startY + 8);
        MechanizedWraithbladeOverlay.renderAmmoCount(guiGraphics, startX + hudWidth - 8, startY + 8, plasma, maxPlasma);
        MechanizedWraithbladeOverlay.renderMagicalPlasmaBar(guiGraphics, startX + 8, startY + 25, hudWidth - 80, plasma, maxPlasma);
        MechanizedWraithbladeOverlay.renderChargingBar(guiGraphics, startX + 8, startY + 35, hudWidth - 80);
        MechanizedWraithbladeOverlay.renderBottomLeftDamage(guiGraphics, startX + 8, startY + 48, damage);
        MechanizedWraithbladeOverlay.renderWeaponSilhouette(guiGraphics, startX + hudWidth - 72, startY + 42);
        poseStack.popPose();
    }

    private static void renderChargingBar(GuiGraphics guiGraphics, int x, int y, int width) {
        int barHeight = 3;
        guiGraphics.renderComponentHoverEffect(x, y, x + width, y + barHeight, -15065564);
        guiGraphics.renderComponentHoverEffect(x + 1, y + 1, x + width - 1, y + barHeight - 1, -16777216);
        if (isCharging && chargeProgress > 0) {
            float progressRatio = (float)chargeProgress / 8.0f;
            int progressWidth = (int)((float)(width - 2) * progressRatio);
            int chargeColor = progressRatio < 0.5f ? MechanizedWraithbladeOverlay.interpolateColor(-5364940, -1557701, progressRatio * 2.0f) : MechanizedWraithbladeOverlay.interpolateColor(-1557701, -300259, (progressRatio - 0.5f) * 2.0f);
            guiGraphics.renderComponentHoverEffect(x + 1, y + 1, x + 1 + progressWidth, y + barHeight - 1, chargeColor);
            if (progressWidth > 1) {
                guiGraphics.renderComponentHoverEffect(x + progressWidth, y, x + progressWidth + 1, y + barHeight, MechanizedWraithbladeOverlay.addAlpha(chargeColor, 0.8f));
            }
            long time = System.currentTimeMillis();
            float aura = (float)((double)0.2f + (double)0.1f * Math.sin((double)time * 0.01));
            int auraColor = (int)(aura * 255.0f) << 24 | chargeColor & 0xFFFFFF;
            guiGraphics.renderComponentHoverEffect(x - 1, y - 1, x + width + 1, y, auraColor);
            guiGraphics.renderComponentHoverEffect(x - 1, y + barHeight, x + width + 1, y + barHeight + 1, auraColor);
        } else {
            int readyColor = -11919585;
            guiGraphics.renderComponentHoverEffect(x + 1, y + 1, x + width - 1, y + barHeight - 1, readyColor);
            long time = System.currentTimeMillis();
            float readyGlow = (float)((double)0.1f + (double)0.05f * Math.sin((double)time * 0.005));
            int readyAura = (int)(readyGlow * 255.0f) << 24 | 0xAE2334;
            guiGraphics.renderComponentHoverEffect(x - 1, y - 1, x + width + 1, y, readyAura);
            guiGraphics.renderComponentHoverEffect(x - 1, y + barHeight, x + width + 1, y + barHeight + 1, readyAura);
        }
        long time = System.currentTimeMillis();
        float borderPulse = (float)((double)0.15f + (double)0.08f * Math.sin((double)time * 0.006));
        int borderGlow = (int)(borderPulse * 255.0f) << 24 | 0xE83B3B;
        guiGraphics.renderComponentHoverEffect(x - 1, y - 1, x, y + barHeight + 1, borderGlow);
        guiGraphics.renderComponentHoverEffect(x + width, y - 1, x + width + 1, y + barHeight + 1, borderGlow);
    }

    private static void renderMagicalFrame(GuiGraphics guiGraphics, int x, int y, int width, int height, int plasma, int maxPlasma) {
        int darkTop = -268435456;
        int darkMid = -401599978;
        int darkBottom = -535159260;
        long time = System.currentTimeMillis();
        float magicalShimmer = (float)(0.02 + 0.01 * Math.sin((double)time * 0.003));
        int shimmerTop = MechanizedWraithbladeOverlay.addMagicalShimmer(darkTop, magicalShimmer *= (float)plasma / (float)maxPlasma * 0.5f);
        int shimmerBottom = MechanizedWraithbladeOverlay.addMagicalShimmer(darkBottom, magicalShimmer * 0.7f);
        MechanizedWraithbladeOverlay.fillGradientVertical(guiGraphics, x, y, x + width, y + height, shimmerTop, shimmerBottom);
        MechanizedWraithbladeOverlay.fillGradientVertical(guiGraphics, x + 1, y + 1, x + width - 1, y + height - 1, darkMid, -802936784);
        int primaryEnergy = MechanizedWraithbladeOverlay.getMagicalBorderColor(plasma, maxPlasma);
        int accentEnergy = -1557701;
        guiGraphics.renderComponentHoverEffect(x, y, x + width, y + 1, primaryEnergy);
        guiGraphics.renderComponentHoverEffect(x, y + height - 1, x + width, y + height, primaryEnergy);
        guiGraphics.renderComponentHoverEffect(x, y, x + 1, y + height, primaryEnergy);
        guiGraphics.renderComponentHoverEffect(x + width - 1, y, x + width, y + height, primaryEnergy);
        MechanizedWraithbladeOverlay.renderEnhancedCornerAccents(guiGraphics, x, y, width, height, accentEnergy, plasma, maxPlasma);
        float innerGlowIntensity = 0.25f + 0.15f * (float)plasma / (float)maxPlasma;
        int innerGlow = (int)(innerGlowIntensity * 255.0f) << 24 | 0xAE2334;
        guiGraphics.renderComponentHoverEffect(x + 2, y + 2, x + width - 2, y + 3, innerGlow);
    }

    private static void renderMagicalBackgroundParticles(GuiGraphics guiGraphics, int x, int y, int width, int height, int plasma, int maxPlasma) {
        long time = System.currentTimeMillis();
        float plasmaRatio = (float)plasma / (float)maxPlasma;
        if (plasma > 50) {
            int particleCount = (int)(plasmaRatio * 3.0f);
            for (int i = 0; i < particleCount; ++i) {
                float particleTime = (float)(time + (long)(i * 2000)) * 0.001f;
                int particleX = x + 10 + (int)((double)(width - 20) * ((double)0.3f + (double)0.4f * Math.sin(particleTime * 0.5f + (float)i)));
                int particleY = y + 10 + (int)((double)(height - 20) * ((double)0.3f + (double)0.4f * Math.cos(particleTime * 0.3f + (float)i * 1.5f)));
                float glowIntensity = (float)((double)0.15f + (double)0.1f * Math.sin(particleTime * 2.0f + (float)i));
                int particleColor = (int)((glowIntensity *= plasmaRatio) * 255.0f) << 24 | MechanizedWraithbladeOverlay.getMagicalParticleColor(plasma, maxPlasma);
                guiGraphics.renderComponentHoverEffect(particleX, particleY, particleX + 1, particleY + 1, particleColor);
            }
        }
    }

    private static void renderPlasmaLabel(GuiGraphics guiGraphics, int x, int y) {
        String lightningIcon = "\u26a1";
        guiGraphics.renderComponentHoverEffect(Minecraft.getInstance().getVersionType, lightningIcon, x, y, -1557701, true);
        guiGraphics.renderComponentHoverEffect(Minecraft.getInstance().getVersionType, "PLASMA", x + 12, y, -4473925, true);
    }

    private static void renderAmmoCount(GuiGraphics guiGraphics, int x, int y, int plasma, int maxPlasma) {
        String ammoCount = String.format("%d/%d", plasma, maxPlasma);
        int countColor = MechanizedWraithbladeOverlay.getPlasmaColor(plasma, maxPlasma);
        int textWidth = Minecraft.getInstance().getVersionType.getSplitter(ammoCount);
        guiGraphics.renderComponentHoverEffect(Minecraft.getInstance().getVersionType, ammoCount, x - textWidth, y, countColor, true);
    }

    private static void renderMagicalPlasmaBar(GuiGraphics guiGraphics, int x, int y, int width, int plasma, int maxPlasma) {
        int barHeight = 6;
        guiGraphics.renderComponentHoverEffect(x, y, x + width, y + barHeight, -15065564);
        guiGraphics.renderComponentHoverEffect(x + 1, y + 1, x + width - 1, y + barHeight - 1, -16777216);
        long time = System.currentTimeMillis();
        float barAura = (float)((double)0.1f + (double)0.05f * Math.sin((double)time * 0.004));
        int auraColor = (int)((barAura *= (float)plasma / (float)maxPlasma) * 255.0f) << 24 | 0xE83B3B;
        guiGraphics.renderComponentHoverEffect(x - 1, y - 1, x + width + 1, y, auraColor);
        guiGraphics.renderComponentHoverEffect(x - 1, y + barHeight, x + width + 1, y + barHeight + 1, auraColor);
        guiGraphics.renderComponentHoverEffect(x - 1, y, x, y + barHeight, auraColor);
        guiGraphics.renderComponentHoverEffect(x + width, y, x + width + 1, y + barHeight, auraColor);
        if (plasma > 0) {
            float fillRatio = (float)plasma / (float)maxPlasma;
            int fillWidth = (int)((float)(width - 2) * fillRatio);
            int fillColor = MechanizedWraithbladeOverlay.getBarColor(plasma, maxPlasma);
            guiGraphics.renderComponentHoverEffect(x + 1, y + 1, x + 1 + fillWidth, y + barHeight - 1, fillColor);
            float highlightIntensity = 0.6f + 0.2f * fillRatio;
            guiGraphics.renderComponentHoverEffect(x + 1, y + 1, x + 1 + fillWidth, y + 2, MechanizedWraithbladeOverlay.addAlpha(fillColor, highlightIntensity));
            if (fillWidth > 3 && plasma > 100) {
                float sparkleIntensity = (float)((double)0.4f + (double)0.3f * Math.sin((double)time * 0.01));
                int sparkleColor = (int)(sparkleIntensity * 255.0f) << 24 | 0xFFFFFF;
                guiGraphics.renderComponentHoverEffect(x + fillWidth - 1, y + 1, x + fillWidth + 1, y + barHeight - 1, sparkleColor);
            }
        }
        if (plasma < 20 && plasma > 0 && time / 400L % 2L == 0L) {
            guiGraphics.renderComponentHoverEffect(x, y, x + width, y + barHeight, 1085154100);
        }
    }

    private static void renderBottomLeftDamage(GuiGraphics guiGraphics, int x, int y, float damage) {
        String damageText = String.format("DMG: %.1f", Float.valueOf(damage));
        int damageColor = MechanizedWraithbladeOverlay.getDamageColor(damage);
        guiGraphics.renderComponentHoverEffect(Minecraft.getInstance().getVersionType, damageText, x, y, damageColor, true);
    }

    private static void renderWeaponSilhouette(GuiGraphics guiGraphics, int x, int y) {
        try {
            guiGraphics.renderComponentHoverEffect(WEAPON_SILHOUETTE, x, y, 0.0f, 0.0f, 64, 23, 64, 23);
        }
        catch (Exception e) {
            guiGraphics.renderComponentHoverEffect(Minecraft.getInstance().getVersionType, "\ud83d\udd2b", x, y, -1557701, true);
        }
    }

    private static void renderEnhancedCornerAccents(GuiGraphics guiGraphics, int x, int y, int width, int height, int accentColor, int plasma, int maxPlasma) {
        int cornerSize = 6;
        float plasmaRatio = (float)plasma / (float)maxPlasma;
        float cornerIntensity = 0.7f + 0.3f * plasmaRatio;
        int enhancedAccent = MechanizedWraithbladeOverlay.addAlpha(accentColor, cornerIntensity);
        guiGraphics.renderComponentHoverEffect(x, y, x + cornerSize, y + 1, enhancedAccent);
        guiGraphics.renderComponentHoverEffect(x, y, x + 1, y + cornerSize, enhancedAccent);
        guiGraphics.renderComponentHoverEffect(x + width - cornerSize, y, x + width, y + 1, enhancedAccent);
        guiGraphics.renderComponentHoverEffect(x + width - 1, y, x + width, y + cornerSize, enhancedAccent);
        if (plasma > 150) {
            long time = System.currentTimeMillis();
            float magicalGlow = (float)((double)0.2f + (double)0.1f * Math.sin((double)time * 0.006));
            int magicalColor = (int)(magicalGlow * 255.0f) << 24 | 0xFB6B1D;
            guiGraphics.renderComponentHoverEffect(x + 2, y + 2, x + 4, y + 4, magicalColor);
            guiGraphics.renderComponentHoverEffect(x + width - 4, y + 2, x + width - 2, y + 4, magicalColor);
        }
    }

    private static int addMagicalShimmer(int color, float shimmer) {
        int alpha = color >> 24 & 0xFF;
        int newAlpha = (int)((float)alpha * (1.0f + shimmer));
        newAlpha = Math.min(255, newAlpha);
        return newAlpha << 24 | color & 0xFFFFFF;
    }

    private static int getMagicalBorderColor(int plasma, int maxPlasma) {
        float ratio = (float)plasma / (float)maxPlasma;
        if (ratio >= 1.0f) {
            return -300259;
        }
        if (ratio > 0.7f) {
            return -1557701;
        }
        return -5364940;
    }

    private static int getMagicalParticleColor(int plasma, int maxPlasma) {
        float ratio = (float)plasma / (float)maxPlasma;
        if (ratio >= 1.0f) {
            return 16476957;
        }
        if (ratio > 0.7f) {
            return 15219515;
        }
        return 11412276;
    }

    private static void fillGradientVertical(GuiGraphics guiGraphics, int x1, int y1, int x2, int y2, int colorTop, int colorBottom) {
        int height = y2 - y1;
        for (int i = 0; i < height; ++i) {
            float ratio = (float)i / (float)height;
            int color = MechanizedWraithbladeOverlay.interpolateColor(colorTop, colorBottom, ratio);
            guiGraphics.renderComponentHoverEffect(x1, y1 + i, x2, y1 + i + 1, color);
        }
    }

    private static int interpolateColor(int color1, int color2, float ratio) {
        ratio = Math.max(0.0f, Math.min(1.0f, ratio));
        int a1 = color1 >> 24 & 0xFF;
        int r1 = color1 >> 16 & 0xFF;
        int g1 = color1 >> 8 & 0xFF;
        int b1 = color1 & 0xFF;
        int a2 = color2 >> 24 & 0xFF;
        int r2 = color2 >> 16 & 0xFF;
        int g2 = color2 >> 8 & 0xFF;
        int b2 = color2 & 0xFF;
        int a = (int)((float)a1 + (float)(a2 - a1) * ratio);
        int r = (int)((float)r1 + (float)(r2 - r1) * ratio);
        int g = (int)((float)g1 + (float)(g2 - g1) * ratio);
        int b = (int)((float)b1 + (float)(b2 - b1) * ratio);
        return a << 24 | r << 16 | g << 8 | b;
    }

    private static int getPlasmaColor(int plasma, int maxPlasma) {
        float ratio = (float)plasma / (float)maxPlasma;
        if (ratio >= 1.0f) {
            return -300259;
        }
        if (ratio > 0.6f) {
            return -1557701;
        }
        if (ratio > 0.2f) {
            return -300259;
        }
        return -5364940;
    }

    private static int getBarColor(int current, int max) {
        float ratio = (float)current / (float)max;
        if (ratio >= 1.0f) {
            return -300259;
        }
        if (ratio > 0.6f) {
            return -1557701;
        }
        if (ratio > 0.2f) {
            return -39373;
        }
        return -5364940;
    }

    private static int getDamageColor(float damage) {
        if (damage >= 14.0f) {
            return -300259;
        }
        if (damage >= 12.0f) {
            return -1557701;
        }
        if (damage >= 10.0f) {
            return -5364940;
        }
        return -3355444;
    }

    private static int addAlpha(int color, float alpha) {
        int a = (int)(alpha * 255.0f) << 24;
        return a | color & 0xFFFFFF;
    }
}

