package heroes.abilities;

import heroes.Player;

public final class Backstab extends Ability {

    public int roundsToCrit;
    public int critCooldown;

    Backstab(final float baseDmg, final float lvlScale, final float rogueDmg, final float knightDmg, final float pyroDmg, final float wizardDmg, final int roundsToCrit) {
        super(baseDmg, lvlScale, rogueDmg, knightDmg, pyroDmg, wizardDmg);
        type = AbilityType.Backstab;
        this.roundsToCrit = roundsToCrit;
        critCooldown = 0;
    }

    public int computeBaseDamage(int level, float landModifier) {
        float dmg = baseDamage + level * dmgScalePerLevel;
        critCooldown--;
        dmg += dmg * landModifier;
        if(landModifier != 0f && critCooldown <= 0) {
            dmg *= 1.5f;
            critCooldown += roundsToCrit;
        }
        return Math.round(dmg);
    }

    public int addRaceModif(Player player, int dmg) {
        return Math.round(dmg + dmg * modifierByRace.get(player.type));
    }
}
