package heroes.abilities;

import heroes.Player;
import heroes.PlayerType;

import java.util.Map;

public final class Paralysis extends Ability {

    private float baseDotDmg;
    private float dotLvlScale;
    private int dotDuration;

    Paralysis(final Map<PlayerType, Float> raceModifiers, final float baseDot,
              final float dotLvlScale, final int dotDuration) {
        super(0, 0, raceModifiers);
        type = AbilityType.Paralysis;
        baseDotDmg = baseDot;
        this.dotLvlScale = dotLvlScale;
        this.dotDuration = dotDuration;
    }

    public int computeDot(final Player player, final int level, final float landModifier) {
        float dot = baseDotDmg + level * dotLvlScale;
        dot += dot * landModifier;
        player.setCurrentDotDamage(Math.round(dot + dot * modifierByRace.get(player.getType())));
        if (landModifier != 0f) {
            player.setCurrentDotDuration(2 * dotDuration);
        } else {
            player.setCurrentDotDuration(dotDuration);
        }
        player.setStunned(true);
        return Math.round(dot);
    }
}
