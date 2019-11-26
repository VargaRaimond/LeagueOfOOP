package heroes.abilities;

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

}
