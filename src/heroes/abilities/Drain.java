package heroes.abilities;

import heroes.Player;

public final class Drain extends Ability {

    Drain(final float baseDmg, final float lvlScale, final float rogueDmg, final float knightDmg, final float pyroDmg, final float wizardDmg) {
        super(baseDmg, lvlScale, rogueDmg, knightDmg, pyroDmg, wizardDmg);
        type = AbilityType.Drain;
    }

    public int computeBaseDamage(Player player, int level, float landModifier) {
        float dmg = (float)(Math.min(0.3*player.getMaxHp(), player.getCurrentHp()));
        // land multiplier first
        float percent = baseDamage + level * dmgScalePerLevel;
        percent += percent * landModifier;
        percent += percent * modifierByRace.get(player.type);
        return Math.round(dmg * percent);
    }
}
