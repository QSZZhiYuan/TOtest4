/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.world.item.ItemDisplayContext
 *  software.bernie.geckolib.model.GeoModel
 *  software.bernie.geckolib.renderer.GeoItemRenderer
 *  software.bernie.geckolib.renderer.GeoRenderer
 */
package com.gametechbc.traveloptics.entity.item.wand_of_final_light;

import com.gametechbc.traveloptics.entity.item.wand_of_final_light.WandOfFinalLightLayer;
import com.gametechbc.traveloptics.entity.item.wand_of_final_light.WandOfFinalLightModel;
import com.gametechbc.traveloptics.item.staff.WandOfFinalLightItem;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.ItemDisplayContext;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.renderer.GeoRenderer;

public class WandOfFinalLightRenderer
extends GeoItemRenderer<WandOfFinalLightItem> {
    private static final float SCALE_RECIPROCAL = 0.0625f;
    protected boolean renderArms = false;
    protected MultiBufferSource currentBuffer;
    protected RenderType renderType;
    public ItemDisplayContext transformType;
    protected WandOfFinalLightItem animatable;
    private final Set<String> hiddenBones = new HashSet<String>();
    private final Set<String> suppressedBones = new HashSet<String>();

    public WandOfFinalLightRenderer() {
        super((GeoModel)new WandOfFinalLightModel());
        this.addRenderLayer(new WandOfFinalLightLayer((GeoRenderer<WandOfFinalLightItem>)this));
    }
}

