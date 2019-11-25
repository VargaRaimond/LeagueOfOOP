package heroes;

import heroes.abilities.AbilityFactory;
import heroes.abilities.AbilityType;
import heroes.abilities.Fireblast;
import heroes.abilities.Ignite;
import map.LandType;

import java.awt.*;

public final class Pyromancer extends Player {

    Fireblast fireblast;
    Ignite ignite;

    public Pyromancer(Point coordinates, int baseHp, int hpScale) {
        super(coordinates);

        AbilityFactory abilityFactory = AbilityFactory.getInstance();
        fireblast = (Fireblast)abilityFactory.getAbility(AbilityType.Fireblast);
        ignite = (Ignite) abilityFactory.getAbility(AbilityType.Ignite);

        type = PlayerType.Pyromancer;
        setCurrentHp(baseHp);
        setMaxHp(baseHp);
        hpScalePerLevel = hpScale;
        landWithBonus = LandType.Volcanic;
    }

    @Override
    public void takeDamage(Player other) {
        other.dealDamage(this);
    }

    @Override
    public void dealDamage(Knight knight) {
        int baseDmg1, baseDmg2;

        if(currentLand.type.equals(landWithBonus)) {
            baseDmg1 = fireblast.computeBaseDamage(level, currentLand.landModifier);
            baseDmg2 = ignite.computeBaseDamage(level, currentLand.landModifier);
            ignite.computeDot(knight, level, currentLand.landModifier);
        } else {
            baseDmg1 = fireblast.computeBaseDamage(level, 0f);
            baseDmg2 = ignite.computeBaseDamage(level, 0f);
            ignite.computeDot(knight, level, 0f);
        }

        int dmg = fireblast.addRaceModif(knight, baseDmg1) + ignite.addRaceModif(knight, baseDmg2);
        knight.setCurrentHp(knight.getCurrentHp() - dmg);
    }

    @Override
    public void dealDamage(Rogue rogue) {
        int baseDmg1, baseDmg2;

        if(currentLand.type.equals(landWithBonus)) {
            baseDmg1 = fireblast.computeBaseDamage(level, currentLand.landModifier);
            baseDmg2 = ignite.computeBaseDamage(level, currentLand.landModifier);
            ignite.computeDot(rogue, level, currentLand.landModifier);
        } else {
            baseDmg1 = fireblast.computeBaseDamage(level, 0f);
            baseDmg2 = ignite.computeBaseDamage(level, 0f);
            ignite.computeDot(rogue, level, 0f);
        }
        int dmg = fireblast.addRaceModif(rogue, baseDmg1) + ignite.addRaceModif(rogue, baseDmg2);
        rogue.setCurrentHp(rogue.getCurrentHp() - dmg);
    }

    @Override
    public void dealDamage(Wizard wizard) {
        int baseDmg1, baseDmg2;

        if(currentLand.type.equals(landWithBonus)) {
            baseDmg1 = fireblast.computeBaseDamage(level, currentLand.landModifier);
            baseDmg2 = ignite.computeBaseDamage(level, currentLand.landModifier);
            ignite.computeDot(wizard, level, currentLand.landModifier);
        } else {
            baseDmg1 = fireblast.computeBaseDamage(level, 0f);
            baseDmg2 = ignite.computeBaseDamage(level, 0f);
            ignite.computeDot(wizard, level, 0f);
        }
        wizard.dmgForDeflect = baseDmg1 + baseDmg2;
        int dmg = fireblast.addRaceModif(wizard, baseDmg1) + ignite.addRaceModif(wizard, baseDmg2);
        wizard.setCurrentHp(wizard.getCurrentHp() - dmg);
    }

    @Override
    public void dealDamage(Pyromancer pyromancer) {
        int baseDmg1, baseDmg2;

        if(currentLand.type.equals(landWithBonus)) {
            baseDmg1 = fireblast.computeBaseDamage(level, currentLand.landModifier);
            baseDmg2 = ignite.computeBaseDamage(level, currentLand.landModifier);
            ignite.computeDot(pyromancer, level, currentLand.landModifier);
        } else {
            baseDmg1 = fireblast.computeBaseDamage(level, 0f);
            baseDmg2 = ignite.computeBaseDamage(level, 0f);
            ignite.computeDot(pyromancer, level, 0f);
        }

        int dmg = fireblast.addRaceModif(pyromancer, baseDmg1) + ignite.addRaceModif(pyromancer, baseDmg2);
        pyromancer.setCurrentHp(pyromancer.getCurrentHp() - dmg);
    }

    @Override
    public String toString() {
        if(getCurrentHp() <= 0) {
            return "P dead";
        } else {
            return "P " + level + " " + xp + " " + getCurrentHp() +
                    " " + coordinates.x + " " + coordinates.y;
        }
    }
}
