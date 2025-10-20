/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.ItemDisplayContext
 *  software.bernie.geckolib.model.GeoModel
 *  software.bernie.geckolib.renderer.GeoItemRenderer
 */
package com.gametechbc.traveloptics.entity.item.infernal_devastator;

import com.gametechbc.traveloptics.api.render.TORenderEmissiveLayer;
import com.gametechbc.traveloptics.entity.item.infernal_devastator.InfernalDevastatorModel;
import com.gametechbc.traveloptics.item.bossweapon.infernal_devastator.InfernalDevastatorItem;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class InfernalDevastatorRenderer
extends GeoItemRenderer<InfernalDevastatorItem> {
    private static final ResourceLocation LAYER = new ResourceLocation("traveloptics", "textures/models/item/infernal_devastator_layer.png");
    private static final float SCALE_RECIPROCAL = 0.0625f;
    protected boolean renderArms = false;
    protected MultiBufferSource currentBuffer;
    protected RenderType renderType;
    public ItemDisplayContext transformType;
    protected InfernalDevastatorItem animatable;
    private final Set<String> hiddenBones = new HashSet<String>();
    private final Set<String> suppressedBones = new HashSet<String>();

    public InfernalDevastatorRenderer() {
        super((GeoModel)new InfernalDevastatorModel());
        this.addRenderLayer(new TORenderEmissiveLayer(this, RenderType.glintDirect((ResourceLocation)LAYER), 1.0f, 1.0f, 1.0f, 1.0f));
    }
}

