package Heroes.Abilities;

import Heroes.Player;
import Heroes.PlayerType;

public final class Deflect extends Ability {


    Deflect(final float baseDmg, final float lvlScale, final float rogueDmg, final float knightDmg, final float pyroDmg) {
        super(baseDmg, lvlScale, rogueDmg, knightDmg, pyroDmg, 0f);
        type = AbilityType.Deflect;
    }

    public int computeBaseDamage(Player player, int heroLvl, float landModifier, float damageTaken) {
        float damage = damageTaken;
        float percent = Math.min((baseDamage + heroLvl * dmgScalePerLevel), 0.7f);
        percent += percent * landModifier;
        percent += percent * modifierByRace.get(player.type);
        return Math.round(damage * percent);

    }
}
