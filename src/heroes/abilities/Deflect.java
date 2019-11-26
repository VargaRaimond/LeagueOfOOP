package heroes.abilities;

import common.Constants;
import heroes.Player;
import heroes.PlayerType;

import java.util.Map;

public final class Deflect extends Ability {


    Deflect(final float baseDmg, final float lvlScale, final Map<PlayerType, Float> raceModif) {
        super(baseDmg, lvlScale, raceModif);
        type = AbilityType.Deflect;
    }

    public int computeBaseDamage(final Player player, final int heroLvl, final float landModifier,
                                 final float damageTaken) {
        float percent;
        percent = Math.min((baseDamage + heroLvl * dmgScalePerLevel), Constants.MAX_DEFLECT_SCALE);
        percent += percent * landModifier;
        percent += percent * modifierByRace.get(player.getType());
        return Math.round(damageTaken * percent);

    }
}
