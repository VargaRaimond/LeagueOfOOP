package heroes.abilities;

import heroes.PlayerType;

import java.util.Map;

public final class Fireblast extends Ability {
    Fireblast(final float baseDmg, final float lvlScale,
              final Map<PlayerType, Float> raceModifiers) {
        super(baseDmg, lvlScale, raceModifiers);
        type = AbilityType.Fireblast;
    }
}
