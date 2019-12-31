package heroes.abilities;

import common.Constants;
import heroes.Player;
import heroes.PlayerType;

import java.util.Map;

public final class Paralysis extends Ability {

    private float baseDotDmg;
    private float dotLvlScale;
    private int dotDuration;

    Paralysis(final Map<PlayerType, Float> raceModifiers, final Float baseDot,
              final Float dotLvlScale, final int dotDuration) {
        super(0, 0, raceModifiers);
        type = AbilityType.Paralysis;
        baseDotDmg = baseDot;
        this.dotLvlScale = dotLvlScale;
        this.dotDuration = dotDuration;
    }

    public Paralysis(final Paralysis other) {
        this(other.modifierByRace, other.baseDotDmg, other.dotLvlScale, other.dotDuration);
    }

    public int addRaceModif(final Player player, final int dmg) {
        return Math.round(dmg + dmg * modifierByRace.get(player.getType())
                - Constants.FLOAT_DIFFERENCE);
    }

    public int computeDot(final Player player, final int level, final Float landModifier) {
        Float dot = baseDotDmg + level * dotLvlScale;
        dot += dot * landModifier;
        player.setCurrentDotDamage(Math.round(dot + dot * (modifierByRace.get(player.getType())
                - Constants.FLOAT_DIFFERENCE)));
        if (landModifier != 0f) {
            player.setCurrentDotDuration(2 * dotDuration);
        } else {
            player.setCurrentDotDuration(dotDuration);
        }
        player.setStunned(true);
        return Math.round(dot);
    }
}
