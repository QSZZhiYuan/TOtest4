/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.init.ModItems
 *  com.github.L_Ender.cataclysm.init.ModSounds
 *  com.google.common.collect.Sets
 *  io.redspace.ironsspellbooks.api.entity.IMagicEntity
 *  io.redspace.ironsspellbooks.api.registry.SpellRegistry
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.api.spells.SchoolType
 *  io.redspace.ironsspellbooks.api.util.Utils
 *  io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob
 *  io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.NeutralWizard
 *  io.redspace.ironsspellbooks.entity.mobs.goals.PatrolNearLocationGoal
 *  io.redspace.ironsspellbooks.entity.mobs.goals.SpellBarrageGoal
 *  io.redspace.ironsspellbooks.entity.mobs.goals.WizardAttackGoal
 *  io.redspace.ironsspellbooks.entity.mobs.goals.WizardRecoverGoal
 *  io.redspace.ironsspellbooks.entity.mobs.wizards.IMerchantWizard
 *  io.redspace.ironsspellbooks.item.InkItem
 *  io.redspace.ironsspellbooks.loot.SpellFilter
 *  io.redspace.ironsspellbooks.player.AdditionalWanderingTrades$InkBuyTrade
 *  io.redspace.ironsspellbooks.player.AdditionalWanderingTrades$InkSellTrade
 *  io.redspace.ironsspellbooks.player.AdditionalWanderingTrades$RandomScrollTrade
 *  io.redspace.ironsspellbooks.player.AdditionalWanderingTrades$SimpleBuy
 *  io.redspace.ironsspellbooks.player.AdditionalWanderingTrades$SimpleSell
 *  io.redspace.ironsspellbooks.player.AdditionalWanderingTrades$SimpleTrade
 *  io.redspace.ironsspellbooks.registries.ItemRegistry
 *  io.redspace.ironsspellbooks.registries.SoundRegistry
 *  it.unimi.dsi.fastutil.objects.ObjectArrayList
 *  javax.annotation.Nullable
 *  net.minecraft.ChatFormatting
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.tags.DamageTypeTags
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.SpawnGroupData
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal
 *  net.minecraft.world.entity.npc.VillagerTrades$ItemListing
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.trading.MerchantOffer
 *  net.minecraft.world.item.trading.MerchantOffers
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.storage.loot.LootParams
 *  net.minecraft.world.level.storage.loot.LootParams$Builder
 *  net.minecraft.world.level.storage.loot.LootTable
 *  net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
 *  org.jetbrains.annotations.Nullable
 */
package com.gametechbc.traveloptics.entity.mobs.aqua_grandmaster;

import com.gametechbc.traveloptics.TravelopticsMod;
import com.gametechbc.traveloptics.api.entity.AdvancedTrades;
import com.gametechbc.traveloptics.api.init.TravelopticsSchools;
import com.gametechbc.traveloptics.init.TravelopticsItems;
import com.gametechbc.traveloptics.init.TravelopticsSpells;
import com.gametechbc.traveloptics.util.TravelopticsTags;
import com.github.L_Ender.cataclysm.init.ModItems;
import com.github.L_Ender.cataclysm.init.ModSounds;
import com.google.common.collect.Sets;
import io.redspace.ironsspellbooks.api.entity.IMagicEntity;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.NeutralWizard;
import io.redspace.ironsspellbooks.entity.mobs.goals.PatrolNearLocationGoal;
import io.redspace.ironsspellbooks.entity.mobs.goals.SpellBarrageGoal;
import io.redspace.ironsspellbooks.entity.mobs.goals.WizardAttackGoal;
import io.redspace.ironsspellbooks.entity.mobs.goals.WizardRecoverGoal;
import io.redspace.ironsspellbooks.entity.mobs.wizards.IMerchantWizard;
import io.redspace.ironsspellbooks.item.InkItem;
import io.redspace.ironsspellbooks.loot.SpellFilter;
import io.redspace.ironsspellbooks.player.AdditionalWanderingTrades;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.jetbrains.annotations.Nullable;

public class AquaGrandmasterEntity
extends NeutralWizard
implements IMerchantWizard {
    @javax.annotation.Nullable
    private Player tradingPlayer;
    @javax.annotation.Nullable
    protected MerchantOffers offers;
    private long lastRestockGameTime;
    private int numberOfRestocksToday;
    private long lastRestockCheckDayTime;
    private static final List<VillagerTrades.ItemListing> fillerOffers = List.of(new AdditionalWanderingTrades.SimpleBuy(16, new ItemStack((ItemLike)Items.BRAIN_CORAL, 1), 2, 2), new AdditionalWanderingTrades.SimpleBuy(16, new ItemStack((ItemLike)Items.BUBBLE_CORAL, 1), 2, 2), new AdditionalWanderingTrades.SimpleBuy(16, new ItemStack((ItemLike)Items.FIRE_CORAL, 1), 2, 2), new AdditionalWanderingTrades.SimpleBuy(16, new ItemStack((ItemLike)Items.HORN_CORAL, 1), 2, 2), new AdditionalWanderingTrades.SimpleBuy(16, new ItemStack((ItemLike)Items.TUBE_CORAL, 1), 2, 2), new AdditionalWanderingTrades.SimpleSell(8, new ItemStack((ItemLike)Items.BRAIN_CORAL, 4), 10, 14), new AdditionalWanderingTrades.SimpleSell(8, new ItemStack((ItemLike)Items.BUBBLE_CORAL, 4), 10, 14), new AdditionalWanderingTrades.SimpleSell(8, new ItemStack((ItemLike)Items.FIRE_CORAL, 4), 10, 14), new AdditionalWanderingTrades.SimpleSell(8, new ItemStack((ItemLike)Items.HORN_CORAL, 4), 10, 14), new AdditionalWanderingTrades.SimpleSell(8, new ItemStack((ItemLike)Items.TUBE_CORAL, 4), 10, 14), new AdvancedTrades.FlexibleTrade(8, new ItemStack((ItemLike)Items.EMERALD), 10, 12, new ItemStack((ItemLike)Items.LIGHT_BLUE_DYE), 2, 3, 0.05f), new AdvancedTrades.FlexibleTrade(2, new ItemStack((ItemLike)Items.PIGLIN_BRUTE_SPAWN_EGG), 4, 8, new ItemStack((ItemLike)ModItems.CORAL_CHUNK.get()), 1, 1, 0.05f), new AdvancedTrades.DualItemTrade(5, new ItemStack((ItemLike)Items.WOODEN_PICKAXE), 3, 4, new ItemStack((ItemLike)Items.GLASS_BOTTLE), 1, 1, new ItemStack((ItemLike)TravelopticsItems.BOTTLED_RAINCLOUD.get()), 1, 1, 0.05f), new AdvancedTrades.LootOutputTrade(new ItemStack((ItemLike)Items.HEART_OF_THE_SEA), 1, 1, TravelopticsMod.id("cave_items/abyssal_chasm_map"), 1, 0.5f), new AdvancedTrades.FlexibleTrade(8, new ItemStack((ItemLike)Items.MUSIC_DISC_5), 1, 1, new ItemStack((ItemLike)TravelopticsItems.MUSIC_DISC_169.get()), 1, 1, 0.05f));

    public AquaGrandmasterEntity(EntityType<? extends AbstractSpellCastingMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.getArmorSlots = 50;
        this.setPersistenceRequired();
    }

    protected void registerGoals() {
        this.goalSelector.setControlFlag(1, (Goal)new FloatGoal((Mob)this));
        this.goalSelector.setControlFlag(2, (Goal)new SpellBarrageGoal((IMagicEntity)this, (AbstractSpell)SpellRegistry.BALL_LIGHTNING_SPELL.get(), 6, 7, 100, 240, 1));
        this.goalSelector.setControlFlag(2, (Goal)new SpellBarrageGoal((IMagicEntity)this, (AbstractSpell)TravelopticsSpells.AQUA_MISSILES_SPELL.get(), 3, 4, 200, 240, 1));
        this.goalSelector.setControlFlag(3, (Goal)new WizardAttackGoal((IMagicEntity)this, 1.25, 50, 75).setSpells(List.of((AbstractSpell)TravelopticsSpells.HYDROSHOT_SPELL.get(), (AbstractSpell)TravelopticsSpells.HYDROSHOT_SPELL.get(), (AbstractSpell)TravelopticsSpells.FLOOD_SLASH_SPELL.get(), (AbstractSpell)TravelopticsSpells.BUBBLE_SPRAY_SPELL.get()), List.of((AbstractSpell)TravelopticsSpells.TIDAL_GRASP_SPELL.get()), List.of((AbstractSpell)SpellRegistry.ASCENSION_SPELL.get()), List.of((AbstractSpell)TravelopticsSpells.RAINFALL_SPELL.get())).setSingleUseSpell((AbstractSpell)TravelopticsSpells.TSUNAMI_SPELL.get(), 120, 400, 3, 4).setDrinksPotions());
        this.goalSelector.setControlFlag(4, (Goal)new PatrolNearLocationGoal((PathfinderMob)this, 30.0f, 0.75));
        this.goalSelector.setControlFlag(8, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 8.0f));
        this.goalSelector.setControlFlag(10, (Goal)new WizardRecoverGoal((IMagicEntity)this));
        this.targetSelector.setControlFlag(1, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[0]));
        this.targetSelector.setControlFlag(3, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, 10, true, false, arg_0 -> ((AquaGrandmasterEntity)this).isHostileTowards(arg_0)));
        this.targetSelector.setControlFlag(5, (Goal)new ResetUniversalAngerTargetGoal((Mob)this, false));
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        return LivingEntity.createLivingAttributes().build(Attributes.ATTACK_DAMAGE, 3.0).build(Attributes.ATTACK_KNOCKBACK, 0.0).build(Attributes.register, 120.0).build(Attributes.FOLLOW_RANGE, 64.0).build(Attributes.MOVEMENT_SPEED, 0.25);
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @javax.annotation.Nullable SpawnGroupData pSpawnData, @javax.annotation.Nullable CompoundTag pDataTag) {
        RandomSource randomsource = Utils.random;
        this.hurt(randomsource, pDifficulty);
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    protected void hurt(RandomSource pRandom, DifficultyInstance pDifficulty) {
        this.setLastDeathLocation(EquipmentSlot.CHEST, new ItemStack((ItemLike)TravelopticsItems.DEEPLING_MAGE_ARMOR_ROBE.get()));
        this.setLastDeathLocation(EquipmentSlot.LEGS, new ItemStack((ItemLike)TravelopticsItems.DEEPLING_MAGE_ARMOR_LEGGINGS.get()));
        this.setLastDeathLocation(EquipmentSlot.FEET, new ItemStack((ItemLike)TravelopticsItems.DEEPLING_MAGE_ARMOR_BOOTS.get()));
        this.maybeDisableShield(EquipmentSlot.CHEST, 0.0f);
        this.maybeDisableShield(EquipmentSlot.LEGS, 0.0f);
        this.maybeDisableShield(EquipmentSlot.FEET, 0.0f);
    }

    public static boolean checkGrandmasterSpawnRules(EntityType<? extends Mob> entityType, LevelAccessor world, MobSpawnType spawnReason, BlockPos pos, RandomSource random) {
        BlockPos blockpos = pos.below();
        if (random.nextFloat() > 0.05f) {
            return false;
        }
        return spawnReason == MobSpawnType.SPAWNER || world.getBlockState(blockpos).isFaceSturdy((BlockGetter)world, blockpos, entityType);
    }

    public boolean canFreeze() {
        return true;
    }

    public boolean fireImmune() {
        return true;
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    @javax.annotation.Nullable
    protected SoundEvent giveExperiencePoints(DamageSource p_21239_) {
        return (SoundEvent)ModSounds.DEEPLING_HURT.get();
    }

    @javax.annotation.Nullable
    protected SoundEvent getDeathSound() {
        return (SoundEvent)SoundEvents.CODEC;
    }

    public Optional<SoundEvent> getAngerSound() {
        return Optional.of((SoundEvent)SoundRegistry.TRADER_NO.get());
    }

    public boolean sendSystemMessage(DamageSource source, float damage) {
        if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return super.sendSystemMessage(source, damage);
        }
        if ((double)(this.getHealth() / this.getMaxHealth()) < 0.5) {
            damage = Math.min(this.DamageCap(), damage);
        }
        return super.sendSystemMessage(source, damage);
    }

    public float DamageCap() {
        return 10.0f;
    }

    public boolean isAlliedTo(Entity entityIn) {
        if (entityIn == this) {
            return true;
        }
        if (super.isAlliedTo(entityIn)) {
            return true;
        }
        if (entityIn.getType().tryCast(TravelopticsTags.TEAM_AQUA_GRANDMASTER)) {
            return this.getTeam() == null && entityIn.getTeam() == null;
        }
        return false;
    }

    protected InteractionResult rewardTradeXp(Player pPlayer, InteractionHand pHand) {
        long time;
        boolean isNight;
        boolean preventTrade;
        boolean bl = preventTrade = this.getOffers().isEmpty() || this.getTarget() != null || this.playerDied((LivingEntity)pPlayer);
        if (pHand != InteractionHand.MAIN_HAND || !preventTrade || !this.level().isClientSide) {
            // empty if block
        }
        boolean bl2 = isNight = (time = this.level().getDayTime() % 24000L) >= 13000L && time <= 23000L;
        if (!(preventTrade || this.level().isClientSide || this.getOffers().isEmpty())) {
            if (isNight) {
                if (this.shouldRestock()) {
                    this.restock();
                }
                if (!this.isTrading()) {
                    this.startTrading(pPlayer);
                    return InteractionResult.sidedSuccess((boolean)this.level().isClientSide);
                }
            } else if (!this.level().isClientSide) {
                String[] dayMessages = new String[]{"entity.traveloptics.aqua_grandmaster.trade_morning1", "entity.traveloptics.aqua_grandmaster.trade_morning2", "entity.traveloptics.aqua_grandmaster.trade_morning3", "entity.traveloptics.aqua_grandmaster.trade_morning4", "entity.traveloptics.aqua_grandmaster.trade_morning5"};
                String messageKey = dayMessages[(int)(Math.random() * (double)dayMessages.length)];
                pPlayer.updateTutorialInventoryAction((Component)Component.translatable((String)messageKey).withStyle(ChatFormatting.RED), true);
            }
        }
        return super.rewardTradeXp(pPlayer, pHand);
    }

    private void startTrading(Player pPlayer) {
        this.setTradingPlayer(pPlayer);
        this.lookControl.rotateTowards((Entity)pPlayer);
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
            if (this.getId.nextFloat() < 0.25f) {
                this.offers.add((Object)new AdditionalWanderingTrades.InkBuyTrade((InkItem)ItemRegistry.INK_UNCOMMON.get()).getOffer((Entity)this, this.getId));
            }
            if (this.getId.nextFloat() < 0.25f) {
                this.offers.add((Object)new AdditionalWanderingTrades.InkBuyTrade((InkItem)ItemRegistry.INK_RARE.get()).getOffer((Entity)this, this.getId));
            }
            if (this.getId.nextFloat() < 0.25f) {
                this.offers.add((Object)new AdditionalWanderingTrades.InkSellTrade((InkItem)ItemRegistry.INK_EPIC.get()).getOffer((Entity)this, this.getId));
            }
            this.offers.add((Object)new AdditionalWanderingTrades.RandomScrollTrade(new SpellFilter((SchoolType)TravelopticsSchools.AQUA.get()), 0.0f, 0.25f).getOffer((Entity)this, this.getId));
            if (this.getId.nextFloat() < 0.8f) {
                this.offers.add((Object)new AdditionalWanderingTrades.RandomScrollTrade(new SpellFilter((SchoolType)TravelopticsSchools.AQUA.get()), 0.3f, 0.7f).getOffer((Entity)this, this.getId));
            }
            if (this.getId.nextFloat() < 0.8f) {
                this.offers.add((Object)new AdditionalWanderingTrades.RandomScrollTrade(new SpellFilter((SchoolType)TravelopticsSchools.AQUA.get()), 0.8f, 1.0f).getOffer((Entity)this, this.getId));
            }
            this.offers.add((Object)new WateryWhispersTrade().getOffer((Entity)this, this.getId));
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
        if (this.tradingPlayer != null && !this.level().isClientSide) {
            String[] messages = new String[]{"entity.traveloptics.aqua_grandmaster.trade.successful_1", "entity.traveloptics.aqua_grandmaster.trade.successful_2", "entity.traveloptics.aqua_grandmaster.trade.successful_3", "entity.traveloptics.aqua_grandmaster.trade.successful_4", "entity.traveloptics.aqua_grandmaster.trade.successful_5", "entity.traveloptics.aqua_grandmaster.trade.successful_6"};
            String messageKey = messages[(int)(Math.random() * (double)messages.length)];
            this.tradingPlayer.updateTutorialInventoryAction((Component)Component.translatable((String)messageKey).withStyle(ChatFormatting.LIGHT_PURPLE), true);
        }
    }

    public void notifyTradeUpdated(ItemStack pStack) {
        if (!this.level().isClientSide && this.getHandSlots > -this.getAmbientSoundInterval() + 20) {
            this.getHandSlots = -this.getAmbientSoundInterval();
            this.updateTutorialInventoryAction(this.getTradeUpdatedSound(!pStack.onUseTick()), this.getSoundVolume(), this.getVoicePitch());
        }
    }

    protected SoundEvent getTradeUpdatedSound(boolean pIsYesSound) {
        return pIsYesSound ? (SoundEvent)SoundEvents.build : (SoundEvent)SoundEvents.ERROR_EXPECTED_VALUE;
    }

    public SoundEvent getNotifyTradeSound() {
        return (SoundEvent)SoundEvents.build;
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        this.serializeMerchant(pCompound, this.offers, this.lastRestockGameTime, this.numberOfRestocksToday);
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.deserializeMerchant(pCompound, c -> {
            this.offers = c;
        });
    }

    static class WateryWhispersTrade
    extends AdditionalWanderingTrades.SimpleTrade {
        private WateryWhispersTrade() {
            super((trader, random) -> {
                LootParams context;
                LootTable loottable;
                ObjectArrayList items;
                if (!trader.level().isClientSide && !(items = (loottable = trader.level().getServer().reloadableRegistries().getLootTable(TravelopticsMod.id("magic_items/the_sunken_fate"))).getAvailableSlots(context = new LootParams.Builder((ServerLevel)trader.level()).create(LootContextParamSets.getKey))).isEmpty()) {
                    ItemStack cost = (ItemStack)items.get(0);
                    ItemStack forSale = new ItemStack((ItemLike)TravelopticsItems.GUIDE_TO_WATERY_WHISPERS.get());
                    return new MerchantOffer(cost, forSale, 1, 5, 0.5f);
                }
                return new MerchantOffer(ItemStack.onUseTick, ItemStack.onUseTick, 0, 0, 0.0f);
            });
        }
    }
}

