package heroes;

import heroes.abilities.AbilityFactory;
import heroes.abilities.AbilityType;
import heroes.abilities.Backstab;
import heroes.abilities.Paralysis;
import map.LandType;

import java.awt.*;

public final class Rogue extends Player {

    Backstab backstab;
    Paralysis paralysis;

    public Rogue(Point coordinates, int baseHp, int lvlScale) {
        super(coordinates);

        AbilityFactory abilityFactory = AbilityFactory.getInstance();
        backstab = (Backstab)abilityFactory.getAbility(AbilityType.Backstab);
        paralysis = (Paralysis)abilityFactory.getAbility(AbilityType.Paralysis);

        setCurrentHp(baseHp);
        setMaxHp(baseHp);
        hpScalePerLevel = lvlScale;
        type = PlayerType.Rogue;
        landWithBonus = LandType.Woods;
    }

    @Override
    public void takeDamage(Player other) {
        other.dealDamage(this);
    }

    @Override
    public void dealDamage(Knight knight) {
        int baseDmg1, baseDmg2;

        if (currentLand.type.equals(landWithBonus)) {
            baseDmg1 = backstab.computeBaseDamage(level, currentLand.landModifier);
            baseDmg2 = paralysis.computeDot(knight, level, currentLand.landModifier);
        } else {
            baseDmg1 = backstab.computeBaseDamage(level, 0f);
            baseDmg2 = paralysis.computeDot(knight, level, 0f);
        }
        int dmg = backstab.addRaceModif(knight, baseDmg1) + paralysis.addRaceModif(knight, baseDmg2);
        knight.setCurrentHp(knight.getCurrentHp() - dmg);
    }

    @Override
    public void dealDamage(Rogue rogue) {
        int baseDmg1, baseDmg2;

        if (currentLand.type.equals(landWithBonus)) {
            baseDmg1 = backstab.computeBaseDamage(level, currentLand.landModifier);
            baseDmg2 = paralysis.computeDot(rogue, level, currentLand.landModifier);
        } else {
            baseDmg1 = backstab.computeBaseDamage(level, 0f);
            baseDmg2 = paralysis.computeDot(rogue, level, 0f);
        }
        int dmg = backstab.addRaceModif(rogue, baseDmg1) + paralysis.addRaceModif(rogue, baseDmg2);
        rogue.setCurrentHp(rogue.getCurrentHp() - dmg);
    }

    @Override
    public void dealDamage(Wizard wizard) {
        int baseDmg1, baseDmg2;

        if (currentLand.type.equals(landWithBonus)) {
            baseDmg1 = backstab.computeBaseDamage(level, currentLand.landModifier);
            baseDmg2 = paralysis.computeDot(wizard, level, currentLand.landModifier);
        } else {
            baseDmg1 = backstab.computeBaseDamage(level, 0f);
            baseDmg2 = paralysis.computeDot(wizard, level, 0f);
        }
        wizard.dmgForDeflect = baseDmg1 + baseDmg2;
        int dmg = backstab.addRaceModif(wizard, baseDmg1) + paralysis.addRaceModif(wizard, baseDmg2);
        wizard.setCurrentHp(wizard.getCurrentHp() - dmg);
    }

    @Override
    public void dealDamage(Pyromancer pyromancer) {
        int baseDmg1, baseDmg2;

        if (currentLand.type.equals(landWithBonus)) {
            baseDmg1 = backstab.computeBaseDamage(level, currentLand.landModifier);
            baseDmg2 = paralysis.computeDot(pyromancer, level, currentLand.landModifier);
        } else {
            baseDmg1 = backstab.computeBaseDamage(level, 0f);
            baseDmg2 = paralysis.computeDot(pyromancer, level, 0f);
        }
        int dmg = backstab.addRaceModif(pyromancer, baseDmg1) + paralysis.addRaceModif(pyromancer, baseDmg2);
        pyromancer.setCurrentHp(pyromancer.getCurrentHp() - dmg);
    }

    @Override
    public String toString() {
        if (getCurrentHp() <= 0) {
            return "R dead";
        } else {
            return "R " + level + " " + xp + " " + getCurrentHp() +
                    " " + coordinates.x + " " + coordinates.y;
        }
    }
}
