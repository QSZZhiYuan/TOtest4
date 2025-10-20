/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob
 *  io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobModel
 *  net.minecraft.resources.ResourceLocation
 */
package com.gametechbc.traveloptics.entity.mobs.testwizard;

import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobModel;
import net.minecraft.resources.ResourceLocation;

public class TestWizardModel
extends AbstractSpellCastingMobModel {
    public static final ResourceLocation MODEL = new ResourceLocation("traveloptics", "geo/test_wizard.geo.json");
    public static final ResourceLocation TEXTURE = new ResourceLocation("traveloptics", "textures/entity/test_wizard.png");
    public static final ResourceLocation ANIMATION = new ResourceLocation("traveloptics", "animations/casting_animations.json");

    public ResourceLocation getModelResource(AbstractSpellCastingMob object) {
        return MODEL;
    }

    public ResourceLocation getTextureResource(AbstractSpellCastingMob object) {
        return TEXTURE;
    }

    public ResourceLocation getAnimationResource(AbstractSpellCastingMob object) {
        return ANIMATION;
    }
}

