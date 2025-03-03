package heroes.abilities;

import heroes.Player;
import heroes.PlayerType;

import java.util.HashMap;
import java.util.Map;

public abstract class Ability {
    protected Float baseDamage;
    protected Float dmgScalePerLevel;
    protected AbilityType type;
    protected Map<PlayerType, Float> modifierByRace;

    public Ability(final float baseDmg, final float lvlScale,
                   final Map<PlayerType, Float> raceModifiers) {
        modifierByRace = new HashMap<>();
        baseDamage = baseDmg;
        dmgScalePerLevel = lvlScale;
        modifierByRace.putAll(raceModifiers);
    }

    /**
     * Calculates base damage dealt by an ability.
     * @param level the level of the player casting the ability
     * @param landModifier the bonus damage given by the land type
     * @return the damage dealt by the ability after level scale and
     * land modifier
     */
    public int computeBaseDamage(final int level, final Float landModifier) {
        Float dmg = baseDamage + level * dmgScalePerLevel;
        dmg += dmg * landModifier;
        return Math.round(dmg);
    }

    /**
     * Adds race modifiers on damage.
     * @param player the hero which will be attacked
     * @param dmg damage without modifiers
     * @return final damage
     */
    public int addRaceModif(final Player player, final int dmg) {
        return Math.round(dmg + dmg * modifierByRace.get(player.getType()));
    }

    public final void updateModifiers(final Float changer) {
        for (Map.Entry<PlayerType, Float> element : modifierByRace.entrySet()) {
            element.setValue(element.getValue() + changer);
        }
    }

}
