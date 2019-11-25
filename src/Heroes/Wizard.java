package Heroes;

import Heroes.Abilities.*;
import Map.LandType;

import java.awt.*;

public class Wizard extends Player {

    Drain drain;
    Deflect deflect;
    public float dmgForDeflect;

    Wizard(Point coordinates, int baseHp, int hpScale) {
        super(coordinates);

        AbilityFactory abilityFactory = AbilityFactory.getInstance();
        drain = (Drain)(abilityFactory.getAbility(AbilityType.Drain));
        deflect = (Deflect)(abilityFactory.getAbility(AbilityType.Deflect));

        setMaxHp(baseHp);
        setCurrentHp(baseHp);
        hpScalePerLevel = hpScale;
        type = PlayerType.Wizard;
        landWithBonus = LandType.Desert;
        dmgForDeflect = 0;
    }

    @Override
    public void takeDamage(Player other) {
        other.dealDamage(this);
    }

    @Override
    public void dealDamage(Wizard wizard) {
        // only Drain
        int dmg = 0;
        if(currentLand.type.equals(landWithBonus)) {
             dmg = drain.computeBaseDamage(wizard, level, currentLand.landModifier);
        } else {
             dmg = drain.computeBaseDamage(wizard, level, 0f);
        }
        wizard.setCurrentHp(wizard.getCurrentHp() - dmg);
    }

    @Override
    public void dealDamage(Knight knight) {

    }

    @Override
    public void dealDamage(Rogue rogue) {

    }

    @Override
    public void dealDamage(Pyromancer pyromancer) {
        int dmg1, dmg2;
        if(currentLand.type.equals(landWithBonus)) {
            dmg1 = drain.computeBaseDamage(pyromancer, level, currentLand.landModifier);
            dmg2 = deflect.computeBaseDamage(pyromancer, level, currentLand.landModifier, dmgForDeflect);
        } else {
            dmg1 = drain.computeBaseDamage(pyromancer, level, 0f);
            dmg2 = deflect.computeBaseDamage(pyromancer, level, 0f, dmgForDeflect);
        }
        pyromancer.setCurrentHp(pyromancer.getCurrentHp() - dmg1 - dmg2);
    }

    @Override
    public String toString() {
        if(getCurrentHp() <= 0) {
            return "W dead";
        } else {
            return "W " + level + " " + xp + " " + getCurrentHp() +
                    " " + coordinates.x + " " + coordinates.y;
        }
    }
}
