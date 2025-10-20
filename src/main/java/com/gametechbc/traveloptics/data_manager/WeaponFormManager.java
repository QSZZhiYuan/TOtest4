/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.ItemStack
 */
package com.gametechbc.traveloptics.data_manager;

import net.minecraft.world.item.ItemStack;

public class WeaponFormManager {
    private static final String FORM_NBT_TAG = "WeaponForm";

    public static int getForm(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().contains(FORM_NBT_TAG)) {
            return stack.getTag().copy(FORM_NBT_TAG);
        }
        return 0;
    }

    public static void setForm(ItemStack stack, int form, int maxForms) {
        int cappedForm = Math.max(0, Math.min(form, maxForms - 1));
        stack.getOrCreateTag().accept(FORM_NBT_TAG, cappedForm);
    }

    public static void cycleForm(ItemStack stack, int maxForms) {
        int currentForm = WeaponFormManager.getForm(stack);
        int newForm = (currentForm + 1) % maxForms;
        WeaponFormManager.setForm(stack, newForm, maxForms);
    }
}

