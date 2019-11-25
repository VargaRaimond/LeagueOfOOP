package com.Heroes.Abilities;

import com.Heroes.PlayerType;

import java.util.HashMap;
import java.util.Map;

public abstract class Ability {
    float baseDamage;
    float dmgScalePerLevel;
    public AbilityType type;
    Map<PlayerType, Float> modifierByRace;

    public Ability() {
        modifierByRace = new HashMap<>();
    }

}
