package com.Heroes.Abilities;

import com.Heroes.Player;
import com.Heroes.PlayerType;

public class Drain extends Ability {

    Drain(final float baseDmg, final float lvlScale, final float rogueDmg, final float knightDmg, final float pyroDmg, final float wizardDmg) {
        type = AbilityType.Drain;
        baseDamage = baseDmg;
        dmgScalePerLevel = lvlScale;
        modifierByRace.put(PlayerType.Rogue, rogueDmg);
        modifierByRace.put(PlayerType.Pyromancer, pyroDmg);
        modifierByRace.put(PlayerType.Knight, knightDmg);
        modifierByRace.put(PlayerType.Wizard, wizardDmg);
    }

    public float computeBaseDamage(Player player, int level) {
        float dmg = (float)(Math.min(0.3*player.getMaxHp(), player.getCurrentHp()));
        //TODO map multiplier
        float percent = baseDamage + level * dmgScalePerLevel;
        percent += percent * modifierByRace.get(player.type);
        return dmg * percent;
    }
}
