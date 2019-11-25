package heroes.abilities;

import heroes.Player;

public final class Slam extends Ability {
    int stunDuration;

    Slam(final float baseDmg, final float lvlScale, final float rogueDmg, final float knightDmg, final float pyroDmg, final float wizardDmg, final int stunDuration) {
        super(baseDmg, lvlScale, rogueDmg, knightDmg, pyroDmg, wizardDmg);
        type = AbilityType.Slam;
        this.stunDuration = stunDuration;
    }

    public int computeBaseDamage(int level, float landModifier) {
        float dmg = baseDamage + level * dmgScalePerLevel;
        dmg += dmg * landModifier;
        return Math.round(dmg);
    }

    public int addRaceModif(Player player, int dmg) {
        return Math.round(dmg + dmg * modifierByRace.get(player.type));
    }

    public void updateStun(Player player) {
        player.currentDotDuration = stunDuration;
        player.stunned = true;
        player.currentDotDamage = 0;
    }
}
