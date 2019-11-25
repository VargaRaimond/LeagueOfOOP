package heroes.abilities;

import heroes.Player;

public final class Ignite extends Ability {

    float baseDotDmg;
    float dotLvlScale;
    int dotDuration;

    Ignite(final float baseDmg, final float lvlScale, final float rogueDmg, final float knightDmg, final float pyroDmg, final float wizardDmg, final float baseDot, final float dotLvlScale, final int dotDuration) {
        super(baseDmg, lvlScale, rogueDmg, knightDmg, pyroDmg, wizardDmg);
        type = AbilityType.Ignite;
        baseDotDmg = baseDot;
        this.dotLvlScale = dotLvlScale;
        this.dotDuration = dotDuration;
    }

    public int computeBaseDamage(int level, float landModifier) {
        float dmg = baseDamage + level * dmgScalePerLevel;
        dmg += dmg * landModifier;
        return Math.round(dmg);
    }

    public int addRaceModif(Player player, int dmg) {
        return Math.round(dmg + dmg * modifierByRace.get(player.type));
    }

    public void computeDot(Player player, int level, float landModifier) {
        float dot = baseDotDmg + level * dotLvlScale;
        dot += dot * landModifier;
        player.currentDotDamage = Math.round(dot + dot * modifierByRace.get(player.type));
        player.currentDotDuration = dotDuration;
        player.stunned = false;
    }
}
