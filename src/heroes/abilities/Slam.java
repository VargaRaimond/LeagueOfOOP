package heroes.abilities;

import heroes.Player;
import heroes.PlayerType;

import java.util.Map;

public final class Slam extends Ability {
    private int stunDuration;

    Slam(final float baseDmg, final float lvlScale,
         final Map<PlayerType, Float> raceModifiers, final int stunDuration) {
        super(baseDmg, lvlScale, raceModifiers);
        type = AbilityType.Slam;
        this.stunDuration = stunDuration;
    }

    public void updateStun(final Player player) {
        player.setCurrentDotDuration(stunDuration);
        player.setStunned(true);
        player.setCurrentDotDamage(0);
    }
}
