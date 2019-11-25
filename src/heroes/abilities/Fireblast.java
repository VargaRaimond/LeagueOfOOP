package heroes.abilities;

import heroes.Player;

public final class Fireblast extends Ability {
    Fireblast(final float baseDmg, final float lvlScale, final float rogueDmg, final float knightDmg, final float pyroDmg, final float wizardDmg) {
        super(baseDmg, lvlScale, rogueDmg, knightDmg, pyroDmg, wizardDmg);
        type = AbilityType.Fireblast;
    }

    public int computeBaseDamage(int level, float landModifier) {
        float dmg = baseDamage + level * dmgScalePerLevel;
        dmg += dmg * landModifier;
        return Math.round(dmg);
    }

    public int addRaceModif(Player player, int dmg) {
        return Math.round(dmg + dmg * modifierByRace.get(player.type));
    }
}
