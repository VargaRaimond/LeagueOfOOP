package heroes.abilities;

import common.Constants;
import heroes.Player;
import heroes.PlayerType;

import java.util.Map;

public final class Execute extends Ability {

    private float executePercent;

    Execute(final float baseDmg, final float lvlScale, final Map<PlayerType, Float> raceModifiers,
            final float baseInstKill) {
        super(baseDmg, lvlScale, raceModifiers);
        type = AbilityType.Execute;
        executePercent = baseInstKill;
    }

    public int computeBaseDamage(final Player player, final int level, final float landModifier) {
        if (player.getCurrentHp() <= player.getMaxHp()
                * (executePercent + Constants.EXECUTE_SCALE * level)) {
            return player.getCurrentHp();
        }
        float dmg = baseDamage + level * dmgScalePerLevel;
        dmg += dmg * landModifier;
        return Math.round(dmg);
    }

    public boolean checkIntantKill(final Player player, final int level) {
        return player.getCurrentHp() <= player.getMaxHp()
                * (executePercent + Constants.EXECUTE_SCALE * level);
    }

    public int addRaceModif(final Player player, final int dmg) {
        return Math.round(dmg + dmg * modifierByRace.get(player.getType()));
    }
}
