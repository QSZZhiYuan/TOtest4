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
package com.gametechbc.traveloptics.entity.mobs.aqua_grandmaster;

import com.gametechbc.traveloptics.entity.mobs.aqua_grandmaster.AquaGrandmasterEmissiveLayer;
import com.gametechbc.traveloptics.entity.mobs.aqua_grandmaster.AquaGrandmasterModel;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobModel;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoRenderer;

public class AquaGrandmasterRenderer
extends AbstractSpellCastingMobRenderer {
    public AquaGrandmasterRenderer(EntityRendererProvider.Context context) {
        super(context, (AbstractSpellCastingMobModel)new AquaGrandmasterModel());
        this.addRenderLayer(new AquaGrandmasterEmissiveLayer((GeoRenderer<AbstractSpellCastingMob>)this));
    }
}

