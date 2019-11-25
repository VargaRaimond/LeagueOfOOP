package com.Heroes.Abilities;

import com.Heroes.Player;
import com.Heroes.PlayerType;

public final class Deflect extends Ability {

    float damageTaken;


    Deflect(final float baseDmg, final float lvlScale, final float rogueDmg, final float knightDmg, final float pyroDmg) {
        type = AbilityType.Deflect;
        this.baseDamage = baseDmg;
        dmgScalePerLevel = lvlScale;
        modifierByRace.put(PlayerType.Rogue, rogueDmg);
        modifierByRace.put(PlayerType.Pyromancer, pyroDmg);
        modifierByRace.put(PlayerType.Knight, knightDmg);
    }

    public float computeBaseDamage(Player player, int heroLvl) {
        float damage = damageTaken;
        damage *= Math.min((baseDamage + heroLvl * dmgScalePerLevel), 0.7);
        damage += damage * modifierByRace.get(player.type);
        //TODO mapcell modifier
        return damage;
    }

    public void updateDamageTaken(int dmg) {
        damageTaken = dmg;
    }
}
