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

    public Execute(final Execute other) {
        this(other.baseDamage, other.dmgScalePerLevel, other.modifierByRace, other.executePercent);
    }

    public int computeBaseDamage(final int level, final float landModifier) {
        return super.computeBaseDamage(level, landModifier);
    }

    public boolean checkInstantKill(final Player player, final int level) {
        return player.getCurrentHp() <= player.getMaxHp()
                * (executePercent + Constants.EXECUTE_SCALE * level);
    }
}
