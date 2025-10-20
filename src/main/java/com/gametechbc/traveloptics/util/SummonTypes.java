/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.entity.mobs.MagicSummon
 */
package com.gametechbc.traveloptics.util;

import com.gametechbc.traveloptics.entity.summons.SummonedAptrgangr;
// import com.gametechbc.traveloptics.entity.summons.SummonedAtlatitan; // REMOVED (AC dependency)
import com.gametechbc.traveloptics.entity.summons.SummonedEnderGolem;
// import com.gametechbc.traveloptics.entity.summons.SummonedForsaken; // REMOVED (AC dependency)
// import com.gametechbc.traveloptics.entity.summons.SummonedGumWorm; // REMOVED (AC dependency)
// import com.gametechbc.traveloptics.entity.summons.SummonedHullbreaker; // REMOVED (AC dependency)
import com.gametechbc.traveloptics.entity.summons.SummonedIgnitedBerserker;
import com.gametechbc.traveloptics.entity.summons.SummonedIgnitedRevenant;
import com.gametechbc.traveloptics.entity.summons.SummonedKobolediator;
import com.gametechbc.traveloptics.entity.summons.SummonedKoboleton;
// import com.gametechbc.traveloptics.entity.summons.SummonedMagnetron; // REMOVED (AC dependency)
// import com.gametechbc.traveloptics.entity.summons.SummonedTheProwler; // REMOVED (AC dependency)
import com.gametechbc.traveloptics.entity.summons.SummonedTheWatcher;
// import com.gametechbc.traveloptics.entity.summons.SummonedTremorsaurus; // REMOVED (AC dependency)
// import com.gametechbc.traveloptics.entity.summons.SummonedVallumraptor; // REMOVED (AC dependency)
import com.gametechbc.traveloptics.entity.summons.SummonedWadjet;
import io.redspace.ironsspellbooks.entity.mobs.MagicSummon;
import java.util.HashSet;
import java.util.Set;

public class SummonTypes {
    private static final Set<Class<? extends MagicSummon>> KAIJU_SUMMONS = new HashSet<Class<? extends MagicSummon>>();
    private static final Set<Class<? extends MagicSummon>> MINIBOSS_SUMMONS = new HashSet<Class<? extends MagicSummon>>();
    private static final Set<Class<? extends MagicSummon>> GROUP_SUMMONS = new HashSet<Class<? extends MagicSummon>>();

    public static void registerKaijuSummons() {
        // KAIJU_SUMMONS.add(SummonedAtlatitan.class); // REMOVED (AC dependency)
        // KAIJU_SUMMONS.add(SummonedGumWorm.class); // REMOVED (AC dependency)
        // KAIJU_SUMMONS.add(SummonedForsaken.class); // REMOVED (AC dependency)
        // KAIJU_SUMMONS.add(SummonedHullbreaker.class); // REMOVED (AC dependency)
    }

    public static void registerMinibossSummons() {
        MINIBOSS_SUMMONS.add(SummonedIgnitedBerserker.class);
        MINIBOSS_SUMMONS.add(SummonedIgnitedRevenant.class);
        MINIBOSS_SUMMONS.add(SummonedKobolediator.class);
        MINIBOSS_SUMMONS.add(SummonedAptrgangr.class);
        // MINIBOSS_SUMMONS.add(SummonedMagnetron.class); // REMOVED (AC dependency)
        MINIBOSS_SUMMONS.add(SummonedEnderGolem.class);
    }

    public static void registerGroupSummons() {
        GROUP_SUMMONS.add(SummonedWadjet.class);
        // GROUP_SUMMONS.add(SummonedTheProwler.class); // REMOVED (AC dependency)
        // GROUP_SUMMONS.add(SummonedTremorsaurus.class); // REMOVED (AC dependency)
        GROUP_SUMMONS.add(SummonedKoboleton.class);
        GROUP_SUMMONS.add(SummonedTheWatcher.class);
        // GROUP_SUMMONS.add(SummonedVallumraptor.class); // REMOVED (AC dependency)
    }

    public static Set<Class<? extends MagicSummon>> getKaijuSummons() {
        return KAIJU_SUMMONS;
    }

    public static Set<Class<? extends MagicSummon>> getMinibossSummons() {
        return MINIBOSS_SUMMONS;
    }

    public static Set<Class<? extends MagicSummon>> getGroupSummons() {
        return GROUP_SUMMONS;
    }
}

