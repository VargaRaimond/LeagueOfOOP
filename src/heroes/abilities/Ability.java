package heroes.abilities;

import heroes.Player;
import heroes.PlayerType;

import java.util.HashMap;
import java.util.Map;

public abstract class Ability {
    protected float baseDamage;
    protected float dmgScalePerLevel;
    protected AbilityType type;
    protected Map<PlayerType, Float> modifierByRace;

    public Ability(final float baseDmg, final float lvlScale,
                   final Map<PlayerType, Float> raceModifiers) {
        modifierByRace = new HashMap<>();
        baseDamage = baseDmg;
        dmgScalePerLevel = lvlScale;
        modifierByRace.putAll(raceModifiers);
    }

    public Ability(final Ability other) {
        this(other.baseDamage, other.dmgScalePerLevel, other.modifierByRace);
    }

    /**
     * Calculates base damage dealt by an ability.
     * @param level the level of the player casting the ability
     * @param landModifier the bonus damage given by the land type
     * @return the damage dealt by the ability after level scale and
     * land modifier
     */
    public int computeBaseDamage(final int level, final float landModifier) {
        float dmg = baseDamage + level * dmgScalePerLevel;
        dmg += dmg * landModifier;
        return Math.round(dmg);
    }

    public final int addRaceModif(final Player player, final int dmg) {
        return Math.round(dmg + dmg * modifierByRace.get(player.getType()));
    }

    public final void updateModifiers(final float changer) {
        for(Map.Entry<PlayerType, Float> element : modifierByRace.entrySet()) {
            element.setValue(element.getValue() + changer);
        }
    }

}
