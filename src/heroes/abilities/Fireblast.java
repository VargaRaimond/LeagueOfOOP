package heroes.abilities;

import heroes.Player;
import heroes.PlayerType;

import java.util.Map;

public final class Fireblast extends Ability {
    Fireblast(final float baseDmg, final float lvlScale,
              final Map<PlayerType, Float> raceModifiers) {
        super(baseDmg, lvlScale, raceModifiers);
        type = AbilityType.Fireblast;
    }

    public int computeBaseDamage(final int level, final float landModifier) {
        float dmg = baseDamage + level * dmgScalePerLevel;
        dmg += dmg * landModifier;
        return Math.round(dmg);
    }

    public int addRaceModif(final Player player, final int dmg) {
        return Math.round(dmg + dmg * modifierByRace.get(player.getType()));
    }
}
