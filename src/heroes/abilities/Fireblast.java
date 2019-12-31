package heroes.abilities;

import common.Constants;
import heroes.Player;
import heroes.PlayerType;

import java.util.Map;

public final class Fireblast extends Ability {
    Fireblast(final float baseDmg, final float lvlScale,
              final Map<PlayerType, Float> raceModifiers) {
        super(baseDmg, lvlScale, raceModifiers);
        type = AbilityType.Fireblast;
    }

    public Fireblast(final Fireblast other) {
        this(other.baseDamage, other.dmgScalePerLevel, other.modifierByRace);
    }

    // Can't use the method from Ability because the result will be ?.5 instead of ?.49999
    public int addRaceModif(final Player player, final int dmg) {
        return Math.round(dmg + dmg * (modifierByRace.get(player.getType())
                - Constants.FLOAT_DIFFERENCE));
    }
}
