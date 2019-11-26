package heroes.abilities;

import heroes.Player;
import heroes.PlayerType;

import java.util.Map;

public final class Ignite extends Ability {

    private float baseDotDmg;
    private float dotLvlScale;
    private int dotDuration;

    Ignite(final float baseDmg, final float lvlScale, final Map<PlayerType, Float> raceModif,
           final float baseDot, final float dotLvlScale, final int dotDuration) {
        super(baseDmg, lvlScale, raceModif);
        type = AbilityType.Ignite;
        baseDotDmg = baseDot;
        this.dotLvlScale = dotLvlScale;
        this.dotDuration = dotDuration;
    }

    public int computeBaseDamage(final int level, final float landModifier) {
        float dmg = baseDamage + level * dmgScalePerLevel;
        dmg += dmg * landModifier;
        return Math.round(dmg);
    }

    public int addRaceModif(final Player player, final int dmg) {
        return Math.round(dmg + dmg * modifierByRace.get(player.getType()));
    }

    public void computeDot(final Player player, final int level, final float landModifier) {
        float dot = baseDotDmg + level * dotLvlScale;
        dot += dot * landModifier;
        player.setCurrentDotDamage(Math.round(dot + dot * modifierByRace.get(player.getType())));
        player.setCurrentDotDuration(dotDuration);
        player.setStunned(false);
    }
}
