/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemDisplayContext
 *  net.minecraft.world.item.ItemStack
 *  software.bernie.geckolib.cache.object.BakedGeoModel
 *  software.bernie.geckolib.model.GeoModel
 *  software.bernie.geckolib.renderer.GeoItemRenderer
 *  software.bernie.geckolib.renderer.GeoRenderer
 */
package com.gametechbc.traveloptics.entity.item.voidstrike_reaper.evo_three;

import com.gametechbc.traveloptics.entity.item.voidstrike_reaper.evo_three.VoidstrikeReaperLevelThreeLayer;
import com.gametechbc.traveloptics.entity.item.voidstrike_reaper.evo_three.VoidstrikeReaperLevelThreeModel;
import com.gametechbc.traveloptics.item.bossweapon.voidstrikereaper.VoidstrikeReaperLevelThreeItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.renderer.GeoRenderer;

public class VoidstrikeReaperLevelThreeRenderer
extends GeoItemRenderer<VoidstrikeReaperLevelThreeItem> {
    private static final float SCALE_RECIPROCAL = 0.0625f;
    protected boolean renderArms = false;
    protected MultiBufferSource currentBuffer;
    protected RenderType renderType;
    public ItemDisplayContext transformType;
    protected VoidstrikeReaperLevelThreeItem animatable;
    private final Set<String> hiddenBones = new HashSet<String>();
    private final Set<String> suppressedBones = new HashSet<String>();

    public VoidstrikeReaperLevelThreeRenderer() {
        super((GeoModel)new VoidstrikeReaperLevelThreeModel());
        this.addRenderLayer(new VoidstrikeReaperLevelThreeLayer((GeoRenderer<VoidstrikeReaperLevelThreeItem>)this));
    }

    public RenderType getRenderType(VoidstrikeReaperLevelThreeItem animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent((ResourceLocation)this.getTextureLocation(animatable));
    }

    public void renderByItem(ItemStack stack, ItemDisplayContext transformType, PoseStack matrixStack, MultiBufferSource bufferIn, int combinedLightIn, int p_239207_6_) {
        this.transformType = transformType;
        if (this.animatable != null) {
            this.animatable.getTransformType(transformType);
        }
        super.renderByItem(stack, transformType, matrixStack, bufferIn, combinedLightIn, p_239207_6_);
    }

    public void actuallyRender(PoseStack matrixStackIn, VoidstrikeReaperLevelThreeItem animatable, BakedGeoModel model, RenderType type, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, boolean isRenderer, float partialTicks, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.currentBuffer = renderTypeBuffer;
        this.renderType = type;
        this.animatable = animatable;
        super.actuallyRender(matrixStackIn, (Item)animatable, model, type, renderTypeBuffer, vertexBuilder, isRenderer, partialTicks, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        if (this.renderArms) {
            this.renderArms = false;
        }
    }

    public ResourceLocation getTextureLocation(VoidstrikeReaperLevelThreeItem instance) {
        return super.getTextureLocation((Item)instance);
    }
}

