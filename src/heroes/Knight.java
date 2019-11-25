package heroes;

import heroes.abilities.*;
import map.LandType;

import java.awt.*;

public final class Knight extends Player {

    Execute execute;
    Slam slam;

    public Knight(Point coordinates, int baseHp, int hpScale) {
        super(coordinates);

        AbilityFactory abilityFactory = AbilityFactory.getInstance();
        execute = (Execute)abilityFactory.getAbility(AbilityType.Execute);
        slam = (Slam)abilityFactory.getAbility(AbilityType.Slam);

        type = PlayerType.Knight;
        setCurrentHp(baseHp);
        setMaxHp(baseHp);
        hpScalePerLevel = hpScale;
        landWithBonus = LandType.Land;
    }

    @Override
    public void takeDamage(Player other) {
        other.dealDamage(this);
    }

    @Override
    public void dealDamage(Knight knight) {
        int baseDmg1, baseDmg2;

        if(currentLand.type.equals(landWithBonus)) {
            baseDmg1 = execute.computeBaseDamage(knight, level, currentLand.landModifier);
            baseDmg2 = slam.computeBaseDamage(level, currentLand.landModifier);
        } else {
            baseDmg1 = execute.computeBaseDamage(knight, level, 0f);
            baseDmg2 = slam.computeBaseDamage(level, 0f);
        }
        if(!execute.checkIntantKill(knight, level)) {
            baseDmg1 = execute.addRaceModif(knight, baseDmg1);
        }
        slam.updateStun(knight);
        int dmg = baseDmg1 + slam.addRaceModif(knight, baseDmg2);
        knight.setCurrentHp(knight.getCurrentHp() - dmg);
    }

    @Override
    public void dealDamage(Rogue rogue) {
        int baseDmg1, baseDmg2;

        if(currentLand.type.equals(landWithBonus)) {
            baseDmg1 = execute.computeBaseDamage(rogue, level, currentLand.landModifier);
            baseDmg2 = slam.computeBaseDamage(level, currentLand.landModifier);
        } else {
            baseDmg1 = execute.computeBaseDamage(rogue, level, 0f);
            baseDmg2 = slam.computeBaseDamage(level, 0f);
        }
        if(!execute.checkIntantKill(rogue, level)) {
            baseDmg1 = execute.addRaceModif(rogue, baseDmg1);
        }
        slam.updateStun(rogue);
        int dmg = baseDmg1 + slam.addRaceModif(rogue, baseDmg2);
        rogue.setCurrentHp(rogue.getCurrentHp() - dmg);
    }

    @Override
    public void dealDamage(Wizard wizard) {
        int baseDmg1, baseDmg2;

        if(currentLand.type.equals(landWithBonus)) {
            baseDmg1 = execute.computeBaseDamage(wizard, level, currentLand.landModifier);
            baseDmg2 = slam.computeBaseDamage(level, currentLand.landModifier);
        } else {
            baseDmg1 = execute.computeBaseDamage(wizard, level, 0f);
            baseDmg2 = slam.computeBaseDamage(level, 0f);
        }
        wizard.dmgForDeflect = baseDmg1 + baseDmg2;
        if(!execute.checkIntantKill(wizard, level)) {
            baseDmg1 = execute.addRaceModif(wizard, baseDmg1);
        }
        slam.updateStun(wizard);
        int dmg = baseDmg1 + slam.addRaceModif(wizard, baseDmg2);
        wizard.setCurrentHp(wizard.getCurrentHp() - dmg);
    }

    @Override
    public void dealDamage(Pyromancer pyromancer) {
        int baseDmg1, baseDmg2;

        if(currentLand.type.equals(landWithBonus)) {
            baseDmg1 = execute.computeBaseDamage(pyromancer, level, currentLand.landModifier);
            baseDmg2 = slam.computeBaseDamage(level, currentLand.landModifier);
        } else {
            baseDmg1 = execute.computeBaseDamage(pyromancer, level, 0f);
            baseDmg2 = slam.computeBaseDamage(level, 0f);
        }
        if(!execute.checkIntantKill(pyromancer, level)) {
            baseDmg1 = execute.addRaceModif(pyromancer, baseDmg1);
        }
        slam.updateStun(pyromancer);
        int dmg = baseDmg1 + slam.addRaceModif(pyromancer, baseDmg2);
        pyromancer.setCurrentHp(pyromancer.getCurrentHp() - dmg);
    }

    @Override
    public String toString() {
        if(getCurrentHp() <= 0) {
            return "K dead";
        } else {
            return "K " + level + " " + xp + " " + getCurrentHp() +
                    " " + coordinates.x + " " + coordinates.y;
        }
    }

}
