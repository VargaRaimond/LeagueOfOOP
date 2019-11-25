package Heroes.Abilities;

import Heroes.PlayerType;

import java.util.HashMap;
import java.util.Map;

public abstract class Ability {
    float baseDamage;
    float dmgScalePerLevel;
    public AbilityType type;
    Map<PlayerType, Float> modifierByRace;

    public Ability(final float baseDmg, final float lvlScale, final float rogueDmg, final float knightDmg, final float pyroDmg, final float wizardDmg) {
        modifierByRace = new HashMap<>();
        baseDamage = baseDmg;
        dmgScalePerLevel = lvlScale;
        modifierByRace.put(PlayerType.Rogue, rogueDmg);
        modifierByRace.put(PlayerType.Pyromancer, pyroDmg);
        modifierByRace.put(PlayerType.Knight, knightDmg);
        modifierByRace.put(PlayerType.Wizard, wizardDmg);
    }

}
