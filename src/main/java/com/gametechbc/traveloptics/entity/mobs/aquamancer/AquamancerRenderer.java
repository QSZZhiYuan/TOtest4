/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob
 *  io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobModel
 *  io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobRenderer
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  software.bernie.geckolib.renderer.GeoRenderer
 */
package com.gametechbc.traveloptics.entity.mobs.aquamancer;

import com.gametechbc.traveloptics.entity.mobs.aquamancer.AquamancerEmissiveLayer;
import com.gametechbc.traveloptics.entity.mobs.aquamancer.AquamancerEyeEmissiveLayer;
import com.gametechbc.traveloptics.entity.mobs.aquamancer.AquamancerModel;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobModel;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoRenderer;

public class AquamancerRenderer
extends AbstractSpellCastingMobRenderer {
    public AquamancerRenderer(EntityRendererProvider.Context context) {
        super(context, (AbstractSpellCastingMobModel)new AquamancerModel());
        this.addRenderLayer(new AquamancerEmissiveLayer((GeoRenderer<AbstractSpellCastingMob>)this));
        this.addRenderLayer(new AquamancerEyeEmissiveLayer((GeoRenderer<AbstractSpellCastingMob>)this));
    }
}

