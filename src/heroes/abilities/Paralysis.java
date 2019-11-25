package heroes.abilities;

import heroes.Player;

public final class Paralysis extends Ability {

    float baseDotDmg;
    float dotLvlScale;
    int dotDuration;

    Paralysis(final float rogueDmg, final float knightDmg, final float pyroDmg, final float wizardDmg, final float baseDot, final float dotLvlScale, final int dotDuration) {
        super(0, 0, rogueDmg, knightDmg, pyroDmg, wizardDmg);
        type = AbilityType.Paralysis;
        baseDotDmg = baseDot;
        this.dotLvlScale = dotLvlScale;
        this.dotDuration = dotDuration;
    }

    public int computeDot(Player player, int level, float landModifier) {
        float dot = baseDotDmg + level * dotLvlScale;
        dot += dot * landModifier;
        player.currentDotDamage = Math.round(dot + dot * modifierByRace.get(player.type));
        if(landModifier != 0f) {
            player.currentDotDuration = 2 * dotDuration;
        } else {
            player.currentDotDuration = dotDuration;
        }
        player.stunned = true;
        return Math.round(dot);
    }

    public int addRaceModif(Player player, int dmg) {
        return Math.round(dmg + dmg * modifierByRace.get(player.type));
    }
}
