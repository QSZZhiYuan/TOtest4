/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob
 *  io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobModel
 *  net.minecraft.resources.ResourceLocation
 */
package com.gametechbc.traveloptics.entity.mobs.nightwarden_boss;

import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.NightwardenBossEntity;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobModel;
import net.minecraft.resources.ResourceLocation;

public class NightwardenBossModel
extends AbstractSpellCastingMobModel {
    public static final ResourceLocation MODEL = new ResourceLocation("traveloptics", "geo/nightwarden.geo.json");
    public static final ResourceLocation TEXTURE = new ResourceLocation("traveloptics", "textures/models/entity/nightwarden/nightwarden.png");
    public static final ResourceLocation ANIMATION = new ResourceLocation("traveloptics", "animations/casting_animations.json");

    public ResourceLocation getModelResource(AbstractSpellCastingMob object) {
        return MODEL;
    }

    public ResourceLocation getTextureResource(AbstractSpellCastingMob object) {
        NightwardenBossEntity nightwarden;
        if (object instanceof NightwardenBossEntity && (nightwarden = (NightwardenBossEntity)object).hasPendingResurgence()) {
            return new ResourceLocation("traveloptics", "textures/models/entity/nightwarden/nightwarden_resurgence.png");
        }
        return TEXTURE;
    }

    public ResourceLocation getAnimationResource(AbstractSpellCastingMob object) {
        return ANIMATION;
    }
}

