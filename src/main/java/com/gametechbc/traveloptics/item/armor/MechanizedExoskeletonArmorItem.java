/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity
 *  com.github.L_Ender.cataclysm.entity.projectile.Laser_Beam_Entity
 *  com.github.L_Ender.cataclysm.init.ModEntities
 *  com.github.L_Ender.cataclysm.init.ModSounds
 *  io.redspace.ironsspellbooks.util.ParticleHelper
 *  net.minecraft.ChatFormatting
 *  net.minecraft.client.KeyMapping
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.screens.Screen
 *  net.minecraft.client.player.LocalPlayer
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.network.chat.Component
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ArmorItem$Type
 *  net.minecraft.world.item.ElytraItem
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.levelgen.Heightmap$Types
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  software.bernie.geckolib.core.animatable.GeoAnimatable
 *  software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
 *  software.bernie.geckolib.core.animation.AnimatableManager$ControllerRegistrar
 *  software.bernie.geckolib.core.animation.Animation$LoopType
 *  software.bernie.geckolib.core.animation.AnimationController
 *  software.bernie.geckolib.core.animation.AnimationState
 *  software.bernie.geckolib.core.animation.RawAnimation
 *  software.bernie.geckolib.core.object.PlayState
 *  software.bernie.geckolib.renderer.GeoArmorRenderer
 *  software.bernie.geckolib.util.GeckoLibUtil
 */
package com.gametechbc.traveloptics.item.armor;

import com.gametechbc.traveloptics.TravelopticsMod;
import com.gametechbc.traveloptics.api.particle.ElytraParticleManager;
import com.gametechbc.traveloptics.api.particle.JetpackParticleManager;
import com.gametechbc.traveloptics.api.utils.IKeybindArmor;
import com.gametechbc.traveloptics.api.utils.TOArmorUtils;
import com.gametechbc.traveloptics.api.utils.TOCurioUtils;
import com.gametechbc.traveloptics.api.utils.TOGeneralUtils;
import com.gametechbc.traveloptics.data_manager.PlasmaFuelManager;
import com.gametechbc.traveloptics.entity.armor.mechanized_exoskeleton_armor.MechanizedExoskeletonModel;
import com.gametechbc.traveloptics.entity.armor.mechanized_exoskeleton_armor.MechanizedExoskeletonRenderer;
import com.gametechbc.traveloptics.entity.extended_projectiles.ExtendedWitherHomingMissileEntity;
import com.gametechbc.traveloptics.init.TravelopticsItems;
import com.gametechbc.traveloptics.init.TravelopticsKeybinds;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.item.TravelopticsArmorMaterials;
import com.gametechbc.traveloptics.item.UnbreakableImbueableArmor;
import com.gametechbc.traveloptics.util.TravelopticsKeybindManager;
import com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import com.github.L_Ender.cataclysm.entity.projectile.Laser_Beam_Entity;
import com.github.L_Ender.cataclysm.init.ModEntities;
import com.github.L_Ender.cataclysm.init.ModSounds;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

public class MechanizedExoskeletonArmorItem
extends UnbreakableImbueableArmor
implements IKeybindArmor {
    private int onKeyTickCounter = 0;
    private int lastFuelLevel = Integer.MAX_VALUE;
    private boolean isUsingMissiles = false;
    private boolean isUsingLasers = false;
    private int lastLaserUseTick = 0;
    private int lastMissileUseTick = 0;
    private static final int ABILITY_TIMEOUT_TICKS = 20;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache((GeoAnimatable)this);

    public MechanizedExoskeletonArmorItem(ArmorItem.Type slot, Item.Properties settings) {
        super(TravelopticsArmorMaterials.MECHANIZED_EXOSKELETON, slot, settings);
    }

    @Override
    protected Set<ArmorItem.Type> getImbuableArmorTypes() {
        return Set.of(ArmorItem.Type.CHESTPLATE);
    }

    @Override
    protected Map<ArmorItem.Type, Integer> getMaxSpellSlots() {
        return Map.of(ArmorItem.Type.CHESTPLATE, 1);
    }

    public boolean elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks) {
        return true;
    }

    public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
        return ElytraItem.isFlyEnabled((ItemStack)stack);
    }

    public void useOn(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        super.useOn(stack, level, entity, slot, selected);
        if (entity instanceof Player) {
            Player player = (Player)entity;
            ItemStack chestplate = player.shouldRemoveSoulSpeed(EquipmentSlot.CHEST);
            if (chestplate.onUseTick() || chestplate.setRepairCost() != this) {
                return;
            }
            int currentFuel = PlasmaFuelManager.getPlasmaFuel(chestplate);
            if (this.isWearingFullSet(player)) {
                if (this.isUsingLasers && player.getTags - this.lastLaserUseTick > 20) {
                    this.isUsingLasers = false;
                }
                if (this.isUsingMissiles && player.getTags - this.lastMissileUseTick > 20) {
                    this.isUsingMissiles = false;
                }
                if (level.isClientSide && TravelopticsKeybindManager.getClientSidePlayer() == player) {
                    if (TravelopticsKeybindManager.isKeyDown(TravelopticsKeybinds.KEY_X)) {
                        TravelopticsKeybindManager.sendArmorKeyPacket(EquipmentSlot.CHEST, TravelopticsKeybinds.KEY_X);
                        this.onKeyPacket(player, stack, TravelopticsKeybinds.KEY_X);
                    }
                    if (TravelopticsKeybindManager.isKeyDown(TravelopticsKeybinds.KEY_Z)) {
                        TravelopticsKeybindManager.sendArmorKeyPacket(EquipmentSlot.CHEST, TravelopticsKeybinds.KEY_Z);
                        this.onKeyPacket(player, stack, TravelopticsKeybinds.KEY_Z);
                    }
                    if (TravelopticsKeybindManager.isKeyDown(TravelopticsKeybinds.KEY_B)) {
                        TravelopticsKeybindManager.sendArmorKeyPacket(EquipmentSlot.CHEST, TravelopticsKeybinds.KEY_B);
                        this.onKeyPacket(player, stack, TravelopticsKeybinds.KEY_B);
                    }
                }
                if (currentFuel < 300) {
                    boolean canRegenFuel;
                    boolean bl = canRegenFuel = player.onGround() || player.getY() - (double)player.level().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, player.blockPosition()).getY() <= 1.8;
                    if (this.isWearingFullSet(player) && canRegenFuel && !this.isUsingMissiles && !this.isUsingLasers && player.getTags % 2 == 0) {
                        PlasmaFuelManager.addPlasmaFuel(chestplate, player, 1);
                    }
                }
                if (player.isFallFlying()) {
                    if (!level.isClientSide && player.getTags % 2 == 0) {
                        TOGeneralUtils.applyFlightSpeedLimit(player, 3.8, true, true);
                    }
                    if (currentFuel >= 4) {
                        ElytraParticleManager.spawnParticles(player, (ParticleOptions)ParticleTypes.FLAME, 2, true);
                    } else {
                        ElytraParticleManager.spawnParticles(player, (ParticleOptions)ParticleTypes.CAMPFIRE_COSY_SMOKE, 1, true);
                    }
                }
                if (!level.isClientSide) {
                    if (currentFuel <= 3 && this.lastFuelLevel > 3) {
                        player.level().gameEvent(null, player.blockPosition(), (SoundEvent)TravelopticsSounds.JETPACK_WARNING.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
                    }
                    if (currentFuel < 3 && !player.onGround()) {
                        this.consumeItemForFuel(player, chestplate);
                    }
                }
                this.lastFuelLevel = currentFuel;
            }
        }
    }

    private void consumeItemForFuel(Player player, ItemStack chestplate) {
        Inventory inventory = player.getInventory();
        if (TOCurioUtils.getWearingCurio((LivingEntity)player, (Item)TravelopticsItems.ELYTRA_JETPACK_COMPONENT.get())) {
            for (int i = 0; i < inventory.removeItemNoUpdate(); ++i) {
                ItemStack stack = inventory.setItems(i);
                if (stack.setRepairCost() != TravelopticsItems.PLASMA_POWER_CELL.get()) continue;
                stack.shrink(1);
                PlasmaFuelManager.setPlasmaFuel(chestplate, 300);
                JetpackParticleManager.spawnParticles(player, ParticleHelper.ELECTRICITY, 15, true);
                player.updateTutorialInventoryAction((Component)Component.translatable((String)"item.traveloptics.mechanized_exoskeleton.message.power_cell_fuel").withStyle(style -> style.applyTo(15219515)).withStyle(ChatFormatting.BOLD), true);
                player.level().gameEvent(null, player.blockPosition(), (SoundEvent)TravelopticsSounds.JETPACK_THRUST.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
                return;
            }
        } else {
            for (int i = 0; i < inventory.removeItemNoUpdate(); ++i) {
                ItemStack stack = inventory.setItems(i);
                if (stack.setRepairCost() != ((Block)ACBlockRegistry.TESLA_BULB.get()).asItem()) continue;
                stack.shrink(1);
                int currentFuel = PlasmaFuelManager.getPlasmaFuel(chestplate);
                int maxFuel = 300;
                int newFuel = Math.min(currentFuel + maxFuel / 2, maxFuel);
                PlasmaFuelManager.setPlasmaFuel(chestplate, newFuel);
                JetpackParticleManager.spawnParticles(player, ParticleHelper.ELECTRICITY, 15, true);
                player.updateTutorialInventoryAction((Component)Component.translatable((String)"item.traveloptics.mechanized_exoskeleton.message.tesla_bulb_fuel").withStyle(style -> style.applyTo(15219515)).withStyle(ChatFormatting.BOLD), true);
                player.level().gameEvent(null, player.blockPosition(), (SoundEvent)TravelopticsSounds.JETPACK_THRUST.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
                return;
            }
        }
    }

    @Override
    public void onKeyPacket(Player player, ItemStack itemStack, KeyMapping key) {
        ++this.onKeyTickCounter;
        ItemStack chestplate = player.shouldRemoveSoulSpeed(EquipmentSlot.CHEST);
        if (chestplate.onUseTick() || chestplate.setRepairCost() != this) {
            return;
        }
        int currentFuel = PlasmaFuelManager.getPlasmaFuel(chestplate);
        if (key == TravelopticsKeybinds.KEY_X && this.isWearingFullSet(player)) {
            boolean hasFuelForJetpack;
            boolean hasFuelForBoost = currentFuel >= 2;
            boolean bl = hasFuelForJetpack = currentFuel >= 2;
            if (player.isFallFlying()) {
                if (hasFuelForBoost) {
                    int newFuel = currentFuel - 2;
                    PlasmaFuelManager.setPlasmaFuel(chestplate, newFuel);
                    Vec3 boost = player.getLookAngle().x(0.2);
                    player.setDeltaMovement(player.getDeltaMovement().reverse(boost));
                    ElytraParticleManager.spawnParticles(player, ParticleHelper.EMBERS, 6, false);
                    TravelopticsMod.PROXY.playWorldSound(player, (byte)1);
                } else {
                    player.updateTutorialInventoryAction((Component)Component.translatable((String)"item.traveloptics.mechanized_exoskeleton.message.no_fuel").withStyle(ChatFormatting.RED), true);
                }
            } else if (hasFuelForJetpack) {
                int newFuel = currentFuel - 2;
                PlasmaFuelManager.setPlasmaFuel(chestplate, newFuel);
                Vec3 velocity = player.getDeltaMovement();
                double newUpwardVelocity = Math.min(velocity.multiply + 0.1, 1.5);
                player.setIsInPowderSnow(velocity.z, Math.max(newUpwardVelocity, 0.1), velocity.reverse);
                player.hasCustomName = 0.0f;
                JetpackParticleManager.spawnParticles(player, ParticleHelper.EMBERS, 6, false);
                TravelopticsMod.PROXY.playWorldSound(player, (byte)1);
            } else {
                player.updateTutorialInventoryAction((Component)Component.translatable((String)"item.traveloptics.mechanized_exoskeleton.message.no_fuel").withStyle(ChatFormatting.RED), true);
            }
        }
        if (key == TravelopticsKeybinds.KEY_Z && this.isWearingFullSet(player)) {
            this.isUsingLasers = true;
            this.lastLaserUseTick = player.getTags;
            if (this.onKeyTickCounter % 5 == 0) {
                int fuelRequired = 8;
                if (!player.onGround() && TOCurioUtils.getWearingCurio((LivingEntity)player, (Item)TravelopticsItems.ELYTRA_JETPACK_COMPONENT.get())) {
                    fuelRequired = 4;
                }
                if (currentFuel < fuelRequired) {
                    player.updateTutorialInventoryAction((Component)Component.translatable((String)"item.traveloptics.mechanized_exoskeleton.message.no_fuel2").withStyle(ChatFormatting.RED), true);
                    this.isUsingLasers = false;
                    return;
                }
                int newFuel = currentFuel - fuelRequired;
                PlasmaFuelManager.setPlasmaFuel(chestplate, newFuel);
                this.fireLasers(player, 8.0f);
            }
        } else {
            this.isUsingLasers = false;
        }
        if (key == TravelopticsKeybinds.KEY_B && this.isWearingFullSet(player)) {
            this.isUsingMissiles = true;
            this.lastMissileUseTick = player.getTags;
            if (this.onKeyTickCounter % 20 == 0) {
                if (currentFuel < 30) {
                    player.updateTutorialInventoryAction((Component)Component.translatable((String)"item.traveloptics.mechanized_exoskeleton.message.no_fuel2").withStyle(ChatFormatting.RED), true);
                    this.isUsingMissiles = false;
                    return;
                }
                int newFuel = currentFuel - 30;
                PlasmaFuelManager.setPlasmaFuel(chestplate, newFuel);
                this.fireMissiles(player, 10.0f);
            }
        } else {
            this.isUsingMissiles = false;
        }
    }

    private void fireLasers(Player player, float laserDamage) {
        double offsetSide = 0.57;
        double offsetFrontBack = 0.55;
        double laserYOffset = -0.4;
        float yawRad = player.getYRot() * ((float)Math.PI / 180);
        Vec3 lookVec = player.getLookAngle().multiply();
        double offsetXLeft = -offsetSide * (double)Mth.randomBetween((float)yawRad);
        double offsetZLeft = -offsetSide * (double)Mth.outFromOrigin((float)yawRad);
        double offsetXRight = offsetSide * (double)Mth.randomBetween((float)yawRad);
        double offsetZRight = offsetSide * (double)Mth.outFromOrigin((float)yawRad);
        double offsetXFrontBack = lookVec.z * offsetFrontBack;
        double offsetZFrontBack = lookVec.reverse * offsetFrontBack;
        Vec3 laserPosLeft = new Vec3(player.getX() + offsetXLeft + offsetXFrontBack, player.getEyeY() + laserYOffset, player.getZ() + offsetZLeft + offsetZFrontBack);
        Vec3 laserPosRight = new Vec3(player.getX() + offsetXRight + offsetXFrontBack, player.getEyeY() + laserYOffset, player.getZ() + offsetZRight + offsetZFrontBack);
        Vec3 lookVecNormalized = player.getZ(1.0f).multiply();
        double powerX = lookVecNormalized.z * 1.3;
        double powerY = lookVecNormalized.multiply * 1.3;
        double powerZ = lookVecNormalized.reverse * 1.3;
        float yRot = (float)(Mth.roundToward((double)lookVecNormalized.reverse, (double)lookVecNormalized.z) * 57.29577951308232) + 90.0f;
        float xRot = (float)(-(Mth.roundToward((double)lookVecNormalized.multiply, (double)Math.sqrt(lookVecNormalized.z * lookVecNormalized.z + lookVecNormalized.reverse * lookVecNormalized.reverse)) * 57.29577951308232));
        Laser_Beam_Entity leftLaser = new Laser_Beam_Entity((EntityType)ModEntities.LASER_BEAM.get(), (LivingEntity)player, laserPosLeft.z, laserPosLeft.multiply, laserPosLeft.reverse, powerX, powerY, powerZ, laserDamage, player.level());
        leftLaser.setYRot(yRot);
        leftLaser.setXRot(xRot);
        player.level().addFreshEntity((Entity)leftLaser);
        Laser_Beam_Entity rightLaser = new Laser_Beam_Entity((EntityType)ModEntities.LASER_BEAM.get(), (LivingEntity)player, laserPosRight.z, laserPosRight.multiply, laserPosRight.reverse, powerX, powerY, powerZ, laserDamage, player.level());
        rightLaser.setYRot(yRot);
        rightLaser.setXRot(xRot);
        player.level().addFreshEntity((Entity)rightLaser);
        ScreenShake_Entity.ScreenShake((Level)player.level(), (Vec3)player.position(), (float)8.0f, (float)0.012f, (int)2, (int)3);
        player.level().gameEvent(null, player.blockPosition(), (SoundEvent)TravelopticsSounds.LASER_SHOOT.get(), SoundSource.PLAYERS, 0.7f, 1.0f);
    }

    private void fireMissiles(Player player, float missilesDamage) {
        double offsetDistance = 0.7;
        double missileYOffset = -1.5;
        double offsetXLeft = -offsetDistance * (double)Mth.randomBetween((float)(player.getYRot() * ((float)Math.PI / 180)));
        double offsetZLeft = -offsetDistance * (double)Mth.outFromOrigin((float)(player.getYRot() * ((float)Math.PI / 180)));
        double offsetXRight = offsetDistance * (double)Mth.randomBetween((float)(player.getYRot() * ((float)Math.PI / 180)));
        double offsetZRight = offsetDistance * (double)Mth.outFromOrigin((float)(player.getYRot() * ((float)Math.PI / 180)));
        LivingEntity target = this.findMissileTarget(player);
        if (target != null) {
            Vec3 velocity = target.position().multiply(player.position()).multiply().x(0.5);
            ExtendedWitherHomingMissileEntity leftMissile = new ExtendedWitherHomingMissileEntity((LivingEntity)player, velocity, player.level(), 0.0f, target);
            leftMissile.setExtendedHomingMissilesDamage(missilesDamage);
            leftMissile.setShouldDealMagicDamage(true);
            leftMissile.getRandomX(player.getX() + offsetXLeft, player.getEyeY() + missileYOffset, player.getZ() + offsetZLeft);
            ExtendedWitherHomingMissileEntity rightMissile = new ExtendedWitherHomingMissileEntity((LivingEntity)player, velocity, player.level(), 0.0f, target);
            rightMissile.setExtendedHomingMissilesDamage(missilesDamage);
            rightMissile.setShouldDealMagicDamage(true);
            rightMissile.getRandomX(player.getX() + offsetXRight, player.getEyeY() + missileYOffset, player.getZ() + offsetZRight);
            player.level().addFreshEntity((Entity)leftMissile);
            player.level().addFreshEntity((Entity)rightMissile);
        }
        ScreenShake_Entity.ScreenShake((Level)player.level(), (Vec3)player.position(), (float)8.0f, (float)0.016f, (int)8, (int)10);
        player.level().gameEvent(null, player.blockPosition(), (SoundEvent)ModSounds.ROCKET_LAUNCH.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
    }

    private LivingEntity findMissileTarget(Player player) {
        double range = 20.0;
        Vec3 start = player.getEyePosition();
        Vec3 end = start.reverse(player.getLookAngle().x(range));
        LivingEntity closestTarget = null;
        double closestDistance = range * range;
        for (LivingEntity entity : player.level().getNearbyEntities(LivingEntity.class, player.getBoundingBox().clip(end).inflate(1.0))) {
            double distance;
            AABB entityBox;
            Optional hit;
            if (entity == player || !entity.isAlive() || entity.isAlliedTo((Entity)player) || !(hit = (entityBox = entity.getBoundingBox().inflate(0.2)).clip(start, end)).isPresent() || !((distance = start.lengthSqr((Vec3)hit.get())) < closestDistance)) continue;
            closestDistance = distance;
            closestTarget = entity;
        }
        if (closestTarget == null) {
            double searchRadius = 20.0;
            List nearbyEntities = player.level().getNearbyEntities(LivingEntity.class, player.getBoundingBox().inflate(searchRadius), e -> e != player && e.isAlive() && !e.isAlliedTo((Entity)player));
            Collections.shuffle(nearbyEntities);
            if (!nearbyEntities.isEmpty()) {
                closestTarget = (LivingEntity)nearbyEntities.get(0);
            }
        }
        return closestTarget;
    }

    public void resolvePage(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        super.resolvePage(stack, world, tooltip, flag);
        int fuel = PlasmaFuelManager.getPlasmaFuel(stack);
        int fuelPercentage = (int)((double)fuel / 300.0 * 100.0);
        tooltip.add((Component)Component.score((String)""));
        if (Screen.hasShiftDown()) {
            tooltip.add((Component)Component.selector((String)"item.traveloptics.mechanized_exoskeleton.tooltip.jetpack_keybind", (Object[])new Object[]{TravelopticsKeybinds.KEY_X.getTranslatedKeyMessage()}));
            tooltip.add((Component)Component.selector((String)"item.traveloptics.mechanized_exoskeleton.tooltip.boost_keybind", (Object[])new Object[]{TravelopticsKeybinds.KEY_X.getTranslatedKeyMessage()}));
            tooltip.add((Component)Component.selector((String)"item.traveloptics.mechanized_exoskeleton.tooltip.laser_keybind", (Object[])new Object[]{TravelopticsKeybinds.KEY_Z.getTranslatedKeyMessage()}));
            tooltip.add((Component)Component.selector((String)"item.traveloptics.mechanized_exoskeleton.tooltip.missile_keybind", (Object[])new Object[]{TravelopticsKeybinds.KEY_B.getTranslatedKeyMessage()}));
            tooltip.add((Component)Component.score((String)""));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.mechanized_exoskeleton.tooltip2"));
        } else {
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.mechanized_exoskeleton.tooltip").withStyle(ChatFormatting.GREEN));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.mechanized_exoskeleton.tooltip1"));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.shift_hold.advanced_tooltips"));
            if (this.getType == ArmorItem.Type.CHESTPLATE) {
                tooltip.add((Component)Component.score((String)""));
                tooltip.add((Component)Component.selector((String)"item.traveloptics.mechanized_exoskeleton.tooltip.fuel", (Object[])new Object[]{fuelPercentage + "%"}).withStyle(ChatFormatting.YELLOW));
            }
        }
        tooltip.add((Component)Component.score((String)""));
    }

    private boolean isWearingFullSet(Player player) {
        return TOArmorUtils.isWearingFullSet(player, MechanizedExoskeletonArmorItem.class);
    }

    @Override
    @OnlyIn(value=Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new MechanizedExoskeletonRenderer(new MechanizedExoskeletonModel());
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @OnlyIn(value=Dist.CLIENT)
    private PlayState jetpack(AnimationState<MechanizedExoskeletonArmorItem> animationState) {
        boolean isUsingLaser;
        LocalPlayer player = Minecraft.getInstance().getTelemetryManager;
        boolean isUsingJetpack = player != null && TravelopticsKeybindManager.isKeyDown(TravelopticsKeybinds.KEY_X);
        boolean bl = isUsingLaser = player != null && TravelopticsKeybindManager.isKeyDown(TravelopticsKeybinds.KEY_Z);
        if (player != null && isUsingLaser) {
            animationState.getController().setAnimation(RawAnimation.begin().then("laser_launch", Animation.LoopType.LOOP));
        } else if (player != null && isUsingJetpack) {
            animationState.getController().setAnimation(RawAnimation.begin().then("jetpack_thrust", Animation.LoopType.LOOP));
        } else if (player != null && (player.getAbilities().setWalkingSpeed || player.isFallFlying())) {
            animationState.getController().setAnimation(RawAnimation.begin().then("fall_flying", Animation.LoopType.LOOP));
        } else if (!(player == null || player.getAbilities().setWalkingSpeed || player.isFallFlying() && player.onGround())) {
            animationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController[]{new AnimationController((GeoAnimatable)this, "jetpack_armor", 5, this::jetpack)});
    }
}

