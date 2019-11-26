package heroes.abilities;

import common.Constants;
import heroes.Player;
import heroes.PlayerType;

import java.util.Map;

public final class Drain extends Ability {

    Drain(final float baseDmg, final float lvlScale, final Map<PlayerType, Float> raceModifiers) {
        super(baseDmg, lvlScale, raceModifiers);
        type = AbilityType.Drain;
    }

    public int computeBaseDamage(final Player player, final int level, final float landModifier) {
        float dmg = Math.min(Constants.DRAIN_BASE_SCALE * player.getMaxHp(), player.getCurrentHp());
        float percent = baseDamage + level * dmgScalePerLevel;
        percent += percent * landModifier;
        percent += percent * modifierByRace.get(player.getType());
        return Math.round(dmg * percent);
    }
}
