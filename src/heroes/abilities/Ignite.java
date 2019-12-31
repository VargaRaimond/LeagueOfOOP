package heroes.abilities;

import heroes.PlayerType;
import heroes.Player;

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

    public Ignite(final Ignite other) {
        this(other.baseDamage, other.dmgScalePerLevel, other.modifierByRace,
                other.baseDotDmg, other.dotLvlScale, other.dotDuration);
    }

    public void computeDot(final Player player, final int level, final float landModifier) {
        float dot = baseDotDmg + level * dotLvlScale;
        dot += dot * landModifier;
        dot = Math.round(dot);
        player.setCurrentDotDamage(Math.round(dot + dot * modifierByRace.get(player.getType())));
        player.setCurrentDotDuration(dotDuration);
        player.setStunned(false);
    }
}
