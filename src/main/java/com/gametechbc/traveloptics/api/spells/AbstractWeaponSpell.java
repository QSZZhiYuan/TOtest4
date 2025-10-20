/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 */
package com.gametechbc.traveloptics.api.spells;

import io.redspace.ironsspellbooks.api.spells.AbstractSpell;

public abstract class AbstractWeaponSpell
extends AbstractSpell {
    public boolean allowLooting() {
        return false;
    }

    public boolean allowCrafting() {
        return true;
    }
}

