/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible
 *  io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.NeutralWizard
 *  io.redspace.ironsspellbooks.entity.mobs.wizards.IMerchantWizard
 *  io.redspace.ironsspellbooks.player.AdditionalWanderingTrades$SimpleTrade
 *  io.redspace.ironsspellbooks.registries.ItemRegistry
 *  it.unimi.dsi.fastutil.objects.ObjectArrayList
 *  javax.annotation.Nullable
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.tags.DamageTypeTags
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.npc.VillagerTrades$ItemListing
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.trading.MerchantOffer
 *  net.minecraft.world.item.trading.MerchantOffers
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.storage.loot.LootParams
 *  net.minecraft.world.level.storage.loot.LootParams$Builder
 *  net.minecraft.world.level.storage.loot.LootTable
 *  net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
 *  org.jetbrains.annotations.Nullable
 *  software.bernie.geckolib.core.animatable.GeoAnimatable
 *  software.bernie.geckolib.core.animation.AnimatableManager$ControllerRegistrar
 *  software.bernie.geckolib.core.animation.AnimationController
 *  software.bernie.geckolib.core.animation.AnimationState
 *  software.bernie.geckolib.core.animation.RawAnimation
 *  software.bernie.geckolib.core.object.PlayState
 */
package com.gametechbc.traveloptics.entity.mobs.fading_mage;

import com.gametechbc.traveloptics.TravelopticsMod;
import com.gametechbc.traveloptics.api.entity.AdvancedTrades;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.animated_particle.FadingMageDespawnSequence;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.animated_particle.FadingMageIdle;
import com.gametechbc.traveloptics.init.TravelopticsItems;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.google.common.collect.Sets;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.NeutralWizard;
import io.redspace.ironsspellbooks.entity.mobs.wizards.IMerchantWizard;
import io.redspace.ironsspellbooks.player.AdditionalWanderingTrades;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

public class FadingMageEntity
extends NeutralWizard
implements IMerchantWizard,
AntiMagicSusceptible {
    private static final EntityDataAccessor<Boolean> DESPAWN_TRIGGERED = SynchedEntityData.assignValue(FadingMageEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Float> FADE_OPACITY = SynchedEntityData.assignValue(FadingMageEntity.class, (EntityDataSerializer)EntityDataSerializers.FLOAT);
    private int despawnTimer = -1;
    private int dialogueIndex = 0;
    private int dialogueCooldown = 0;
    private static final int DESPAWN_DURATION = 100;
    private static final List<Component> LORE_DIALOGUE = List.of(Component.translatable((String)"entity.traveloptics.fading_mage.dialogue_1"), Component.translatable((String)"entity.traveloptics.fading_mage.dialogue_2"), Component.translatable((String)"entity.traveloptics.fading_mage.dialogue_3"), Component.translatable((String)"entity.traveloptics.fading_mage.dialogue_4"), Component.translatable((String)"entity.traveloptics.fading_mage.dialogue_5"), Component.translatable((String)"entity.traveloptics.fading_mage.dialogue_6"), Component.translatable((String)"entity.traveloptics.fading_mage.dialogue_7"), Component.translatable((String)"entity.traveloptics.fading_mage.dialogue_8"), Component.translatable((String)"entity.traveloptics.fading_mage.dialogue_9"), Component.translatable((String)"entity.traveloptics.fading_mage.dialogue_10"), Component.translatable((String)"entity.traveloptics.fading_mage.dialogue_11"), Component.translatable((String)"entity.traveloptics.fading_mage.dialogue_12"), Component.translatable((String)"entity.traveloptics.fading_mage.dialogue_13"), Component.translatable((String)"entity.traveloptics.fading_mage.dialogue_14"), Component.translatable((String)"entity.traveloptics.fading_mage.dialogue_15"), Component.translatable((String)"entity.traveloptics.fading_mage.dialogue_16"), Component.translatable((String)"entity.traveloptics.fading_mage.dialogue_17"));
    @javax.annotation.Nullable
    private Player tradingPlayer;
    @javax.annotation.Nullable
    protected MerchantOffers offers;
    private long lastRestockGameTime;
    private int numberOfRestocksToday;
    private long lastRestockCheckDayTime;
    private static final List<VillagerTrades.ItemListing> fillerOffers = List.of(new AdvancedTrades.FlexibleTrade(10, new ItemStack((ItemLike)TravelopticsItems.VOID_MANUSCRIPT.get()), 2, 4, new ItemStack((ItemLike)ItemRegistry.INK_EPIC.get()), 1, 1, 0.1f), new AdvancedTrades.FlexibleTrade(5, new ItemStack((ItemLike)TravelopticsItems.VOID_MANUSCRIPT.get()), 6, 8, new ItemStack((ItemLike)ItemRegistry.INK_LEGENDARY.get()), 1, 1, 0.15f), new AdvancedTrades.FlexibleTrade(6, new ItemStack((ItemLike)TravelopticsItems.VOID_MANUSCRIPT.get()), 6, 12, new ItemStack((ItemLike)TravelopticsItems.OBSIDIAN_BOOKSHELF.get()), 1, 1, 0.07f), new AdvancedTrades.DualItemTrade(3, new ItemStack((ItemLike)TravelopticsItems.VOID_MANUSCRIPT.get()), 58, 64, new ItemStack((ItemLike)ItemRegistry.UPGRADE_ORB.get()), 1, 1, new ItemStack((ItemLike)TravelopticsItems.ELDRITCH_UPGRADE_ORB.get()), 1, 1, 0.2f), new AdvancedTrades.FlexibleTrade(2, new ItemStack((ItemLike)TravelopticsItems.VOID_MANUSCRIPT.get()), 35, 42, new ItemStack((ItemLike)TravelopticsItems.ELDRITCH_ECHO.get()), 1, 1, 0.15f), new AdvancedTrades.FlexibleTrade(6, new ItemStack((ItemLike)TravelopticsItems.VOID_MANUSCRIPT.get()), 32, 48, new ItemStack((ItemLike)TravelopticsItems.POCKET_BLACK_HOLE_BELT.get()), 1, 1, 0.1f));
    private final RawAnimation idle = RawAnimation.begin().thenLoop("fading_mage_idle");
    private final RawAnimation counterspelled = RawAnimation.begin().thenPlay("fading_mage_counterspelled");

    public FadingMageEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        this.setPersistenceRequired();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.makeBoundingBox.assignValue(DESPAWN_TRIGGERED, (Object)false);
        this.makeBoundingBox.assignValue(FADE_OPACITY, (Object)Float.valueOf(1.0f));
    }

    public boolean isDespawnTriggered() {
        return (Boolean)this.makeBoundingBox.packDirty(DESPAWN_TRIGGERED);
    }

    public float getFadeOpacity() {
        return ((Float)this.makeBoundingBox.packDirty(FADE_OPACITY)).floatValue();
    }

    private void setFadeOpacity(float opacity) {
        this.makeBoundingBox.packDirty(FADE_OPACITY, (Object)Float.valueOf(Math.max(0.0f, Math.min(1.0f, opacity))));
    }

    public boolean sendSystemMessage(DamageSource source, float amount) {
        if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            this.discard();
            return true;
        }
        return false;
    }

    public void lerpMotion() {
        super.lerpMotion();
        if (this.dialogueCooldown > 0) {
            --this.dialogueCooldown;
        }
        if (!this.level().isClientSide && this.isDespawnTriggered()) {
            if (this.despawnTimer < 0) {
                this.despawnTimer = 100;
            } else if (this.despawnTimer > 0) {
                --this.despawnTimer;
                this.updateFadeOpacity();
                if (this.despawnTimer == 0) {
                    this.discard();
                }
            }
        }
        if (this.level().isClientSide) {
            if (!this.isDespawnTriggered()) {
                FadingMageIdle.spawnFadingParticles(this, 0.12, 0.15, -0.12, 180.0, 1, 3);
            } else {
                FadingMageDespawnSequence.spawnDespawnTornado(this, this.despawnTimer, 100);
            }
        }
    }

    private void updateFadeOpacity() {
        if (this.despawnTimer >= 0) {
            float newOpacity;
            float progress = 1.0f - (float)this.despawnTimer / 100.0f;
            if (progress <= 0.8f) {
                float phase1Progress = progress / 0.8f;
                newOpacity = 1.0f - phase1Progress * 0.5f;
            } else {
                float phase2Progress = (progress - 0.8f) / 0.2f;
                newOpacity = 0.5f - phase2Progress * 0.5f;
            }
            this.setFadeOpacity(newOpacity);
        }
    }

    public void onAntiMagic(MagicData magicData) {
        if (!this.isDespawnTriggered()) {
            this.makeBoundingBox.packDirty(DESPAWN_TRIGGERED, (Object)true);
        }
    }

    public boolean isPickable() {
        return true;
    }

    public boolean isPushable() {
        return false;
    }

    public boolean canBeCollidedWith() {
        return true;
    }

    protected boolean shouldDespawnInPeaceful() {
        return false;
    }

    public boolean isPersistenceRequired() {
        return true;
    }

    public Optional<SoundEvent> getAngerSound() {
        return Optional.of((SoundEvent)TravelopticsSounds.SPECTRAL_BLINK_FAILED.get());
    }

    protected InteractionResult rewardTradeXp(Player player, InteractionHand hand) {
        if (hand == InteractionHand.MAIN_HAND && !this.level().isClientSide) {
            boolean preventTrade;
            if (this.dialogueIndex < LORE_DIALOGUE.size()) {
                if (this.dialogueCooldown <= 0) {
                    player.updateTutorialInventoryAction(LORE_DIALOGUE.get(this.dialogueIndex), true);
                    SoundEvent sound = this.getId.nextBoolean() ? (SoundEvent)TravelopticsSounds.SPIRIT_POINTS_THRESHOLD_ONE.get() : (SoundEvent)TravelopticsSounds.SPIRIT_POINTS_THRESHOLD_TWO.get();
                    float pitch = 0.8f + this.getId.nextFloat() * 0.2f;
                    this.updateTutorialInventoryAction(sound, 1.0f, pitch);
                    ++this.dialogueIndex;
                    this.dialogueCooldown = 10;
                    if (this.dialogueIndex >= LORE_DIALOGUE.size()) {
                        this.dialogueCooldown = 15;
                    }
                }
                return InteractionResult.SUCCESS;
            }
            if (this.dialogueCooldown <= 0 && !(preventTrade = this.getOffers().isEmpty())) {
                if (this.shouldRestock()) {
                    this.restock();
                }
                this.startTrading(player);
                return InteractionResult.SUCCESS;
            }
        }
        return super.rewardTradeXp(player, hand);
    }

    private void startTrading(Player pPlayer) {
        this.setTradingPlayer(pPlayer);
        this.openTradingScreen(pPlayer, this.getDisplayName(), 0);
    }

    public int getRestocksToday() {
        return this.numberOfRestocksToday;
    }

    public void setRestocksToday(int restocks) {
        this.numberOfRestocksToday = restocks;
    }

    public long getLastRestockGameTime() {
        return this.lastRestockGameTime;
    }

    public void setLastRestockGameTime(long time) {
        this.lastRestockGameTime = time;
    }

    public long getLastRestockCheckDayTime() {
        return this.lastRestockCheckDayTime;
    }

    public void setLastRestockCheckDayTime(long time) {
        this.lastRestockCheckDayTime = time;
    }

    public Level merchantLevel() {
        return this.level();
    }

    public void setTradingPlayer(@Nullable Player pTradingPlayer) {
        this.tradingPlayer = pTradingPlayer;
    }

    public Player getTradingPlayer() {
        return this.tradingPlayer;
    }

    public MerchantOffers getOffers() {
        if (this.offers == null) {
            this.offers = new MerchantOffers();
            this.offers.addAll(this.createRandomOffers(3, 5));
            int journalTrade = this.getId.nextInt(3);
            switch (journalTrade) {
                case 0: {
                    this.offers.add((Object)new JournalOneToUpgradeOrb().getOffer((Entity)this, this.getId));
                    break;
                }
                case 1: {
                    this.offers.add((Object)new JournalTwoToUpgradeOrb().getOffer((Entity)this, this.getId));
                    break;
                }
                case 2: {
                    this.offers.add((Object)new JournalThreeToUpgradeOrb().getOffer((Entity)this, this.getId));
                }
            }
            this.offers.removeIf(Objects::isNull);
            ++this.numberOfRestocksToday;
        }
        return this.offers;
    }

    private Collection<MerchantOffer> createRandomOffers(int min, int max) {
        HashSet set = Sets.newHashSet();
        int fillerTrades = this.getId.triangle(min, max);
        for (int i = 0; i < 10 && set.size() < fillerTrades; ++i) {
            set.add(this.getId.nextInt(fillerOffers.size()));
        }
        ArrayList<MerchantOffer> offers = new ArrayList<MerchantOffer>();
        for (Integer integer : set) {
            offers.add(fillerOffers.get(integer).getOffer((Entity)this, this.getId));
        }
        return offers;
    }

    public void openTradingScreen(MerchantOffers pOffers) {
    }

    protected boolean isImmobile() {
        return super.isImmobile() || this.isTrading();
    }

    public void openTradingScreen(MerchantOffer pOffer) {
        pOffer.increaseUses();
        this.getHandSlots = -this.getAmbientSoundInterval();
    }

    public void notifyTradeUpdated(ItemStack pStack) {
        if (!this.level().isClientSide && this.getHandSlots > -this.getAmbientSoundInterval() + 20) {
            this.getHandSlots = -this.getAmbientSoundInterval();
            this.updateTutorialInventoryAction(this.getTradeUpdatedSound(!pStack.onUseTick()), this.getSoundVolume(), this.getVoicePitch());
        }
    }

    protected SoundEvent getTradeUpdatedSound(boolean pIsYesSound) {
        return pIsYesSound ? (SoundEvent)SoundEvents.build : (SoundEvent)TravelopticsSounds.SPECTRAL_BLINK_FAILED.get();
    }

    public SoundEvent getNotifyTradeSound() {
        return (SoundEvent)TravelopticsSounds.SPECTRAL_BLINK_FAILED.get();
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        this.serializeMerchant(pCompound, this.offers, this.lastRestockGameTime, this.numberOfRestocksToday);
        pCompound.accept("FadingMageLoreDialogueIndex", this.dialogueIndex);
        pCompound.accept("DespawnTriggered", this.isDespawnTriggered());
        pCompound.accept("DespawnTimer", this.despawnTimer);
        pCompound.accept("FadeOpacity", this.getFadeOpacity());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.deserializeMerchant(pCompound, c -> {
            this.offers = c;
        });
        if (pCompound.contains("FadingMageLoreDialogueIndex")) {
            this.dialogueIndex = pCompound.copy("FadingMageLoreDialogueIndex");
        }
        if (pCompound.contains("DespawnTriggered")) {
            this.makeBoundingBox.packDirty(DESPAWN_TRIGGERED, (Object)pCompound.getBoolean("DespawnTriggered"));
        }
        if (pCompound.contains("DespawnTimer")) {
            this.despawnTimer = pCompound.copy("DespawnTimer");
        }
        if (pCompound.contains("FadeOpacity")) {
            this.setFadeOpacity(pCompound.getFloat("FadeOpacity"));
        }
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController[]{new AnimationController((GeoAnimatable)this, "idle", 0, this::animationPredicate)});
    }

    private PlayState animationPredicate(AnimationState<?> event) {
        if (this.isDespawnTriggered()) {
            event.getController().setAnimation(this.counterspelled);
        } else {
            event.getController().setAnimation(this.idle);
        }
        return PlayState.CONTINUE;
    }

    public boolean shouldBeExtraAnimated() {
        return false;
    }

    public boolean shouldAlwaysAnimateHead() {
        return false;
    }

    static class JournalOneToUpgradeOrb
    extends AdditionalWanderingTrades.SimpleTrade {
        private JournalOneToUpgradeOrb() {
            super((trader, random) -> {
                LootParams context;
                LootTable loottable;
                ObjectArrayList items;
                if (!trader.level().isClientSide && !(items = (loottable = trader.level().getServer().reloadableRegistries().getLootTable(TravelopticsMod.id("magic_items/nightwarden_journal_one"))).getAvailableSlots(context = new LootParams.Builder((ServerLevel)trader.level()).create(LootContextParamSets.getKey))).isEmpty()) {
                    ItemStack journal = (ItemStack)items.get(0);
                    ItemStack middleItem = new ItemStack((ItemLike)ItemRegistry.UPGRADE_ORB.get());
                    ItemStack output = new ItemStack((ItemLike)TravelopticsItems.ELDRITCH_UPGRADE_ORB.get());
                    return new MerchantOffer(journal, middleItem, output, 1, 5, 0.5f);
                }
                return new MerchantOffer(ItemStack.onUseTick, ItemStack.onUseTick, 0, 0, 0.0f);
            });
        }
    }

    static class JournalTwoToUpgradeOrb
    extends AdditionalWanderingTrades.SimpleTrade {
        private JournalTwoToUpgradeOrb() {
            super((trader, random) -> {
                LootParams context;
                LootTable loottable;
                ObjectArrayList items;
                if (!trader.level().isClientSide && !(items = (loottable = trader.level().getServer().reloadableRegistries().getLootTable(TravelopticsMod.id("magic_items/nightwarden_journal_two"))).getAvailableSlots(context = new LootParams.Builder((ServerLevel)trader.level()).create(LootContextParamSets.getKey))).isEmpty()) {
                    ItemStack journal = (ItemStack)items.get(0);
                    ItemStack middleItem = new ItemStack((ItemLike)ItemRegistry.UPGRADE_ORB.get());
                    ItemStack output = new ItemStack((ItemLike)TravelopticsItems.ELDRITCH_UPGRADE_ORB.get());
                    return new MerchantOffer(journal, middleItem, output, 1, 5, 0.5f);
                }
                return new MerchantOffer(ItemStack.onUseTick, ItemStack.onUseTick, 0, 0, 0.0f);
            });
        }
    }

    static class JournalThreeToUpgradeOrb
    extends AdditionalWanderingTrades.SimpleTrade {
        private JournalThreeToUpgradeOrb() {
            super((trader, random) -> {
                LootParams context;
                LootTable loottable;
                ObjectArrayList items;
                if (!trader.level().isClientSide && !(items = (loottable = trader.level().getServer().reloadableRegistries().getLootTable(TravelopticsMod.id("magic_items/nightwarden_journal_three"))).getAvailableSlots(context = new LootParams.Builder((ServerLevel)trader.level()).create(LootContextParamSets.getKey))).isEmpty()) {
                    ItemStack journal = (ItemStack)items.get(0);
                    ItemStack middleItem = new ItemStack((ItemLike)ItemRegistry.UPGRADE_ORB.get());
                    ItemStack output = new ItemStack((ItemLike)TravelopticsItems.ELDRITCH_UPGRADE_ORB.get());
                    return new MerchantOffer(journal, middleItem, output, 1, 5, 0.5f);
                }
                return new MerchantOffer(ItemStack.onUseTick, ItemStack.onUseTick, 0, 0, 0.0f);
            });
        }
    }
}

