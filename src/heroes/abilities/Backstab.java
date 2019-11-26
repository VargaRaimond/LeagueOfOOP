package heroes.abilities;

import common.Constants;
import heroes.Player;
import heroes.PlayerType;

import java.util.Map;

public final class Backstab extends Ability {

    private int roundsToCrit;
    private int critCooldown;

    Backstab(final float baseDmg, final float lvlScale, final Map<PlayerType,
            Float> raceModifiers, final int roundsToCrit) {
        super(baseDmg, lvlScale, raceModifiers);
        type = AbilityType.Backstab;
        this.roundsToCrit = roundsToCrit;
        critCooldown = 0;
    }

    public int computeBaseDamage(final int level, final float landModifier) {
        float dmg = baseDamage + level * dmgScalePerLevel;
        critCooldown--;
        dmg += dmg * landModifier;
        if (landModifier != 0f && critCooldown <= 0) {
            dmg *= Constants.BACKSTAB_CRIT;
            // reset the rounds until next possible crit;
            critCooldown += roundsToCrit;
        }
        return Math.round(dmg);
    }

    public int addRaceModif(final Player player, final int dmg) {
        return Math.round(dmg + dmg * modifierByRace.get(player.getType()));
    }
}
