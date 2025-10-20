/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob
 *  io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobModel
 *  net.minecraft.resources.ResourceLocation
 */
package com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_defeated;

import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobModel;
import net.minecraft.resources.ResourceLocation;

public class NightwardenDefeatedModel
extends AbstractSpellCastingMobModel {
    public static final ResourceLocation modelResource = new ResourceLocation("traveloptics", "geo/nightwarden.geo.json");
    public static final ResourceLocation textureResource = new ResourceLocation("traveloptics", "textures/models/entity/nightwarden/nightwarden_defeated.png");
    public static final ResourceLocation animationResource = new ResourceLocation("traveloptics", "animations/nightwarden_defeated.animation.json");

    public ResourceLocation getModelResource(AbstractSpellCastingMob object) {
        return modelResource;
    }

    public ResourceLocation getTextureResource(AbstractSpellCastingMob object) {
        return textureResource;
    }

    public ResourceLocation getAnimationResource(AbstractSpellCastingMob animatable) {
        return animationResource;
    }
}

