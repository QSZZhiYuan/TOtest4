/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobModel
 *  io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobRenderer
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 */
package com.gametechbc.traveloptics.entity.mobs.testwizard;

import com.gametechbc.traveloptics.entity.mobs.testwizard.TestWizardModel;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobModel;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class TestWizardRenderer
extends AbstractSpellCastingMobRenderer {
    public TestWizardRenderer(EntityRendererProvider.Context context) {
        super(context, (AbstractSpellCastingMobModel)new TestWizardModel());
    }
}

