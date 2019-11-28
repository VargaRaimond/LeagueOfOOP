package heroes;

import heroes.abilities.Execute;
import heroes.abilities.Slam;
import heroes.abilities.AbilityFactory;
import heroes.abilities.AbilityType;
import map.LandType;

import java.awt.Point;

public final class Knight extends Player {

    private Execute execute;
    private Slam slam;

    public Knight(final Point coordinates, final int baseHp, final int hpScale) {
        super(coordinates, baseHp, hpScale);

        AbilityFactory abilityFactory = AbilityFactory.getInstance();
        execute = (Execute) abilityFactory.getAbility(AbilityType.Execute);
        slam = (Slam) abilityFactory.getAbility(AbilityType.Slam);

        type = PlayerType.Knight;
        landWithBonus = LandType.Land;
    }

    @Override
    public void takeDamage(final Player other) {
        other.dealDamage(this);
    }

    @Override
    public void dealDamage(final Knight knight) {
        int executeDmg, slamDmg;

        executeDmg = execute.computeBaseDamage(knight, level, getLandBonus());
        slamDmg = slam.computeBaseDamage(level, getLandBonus());

        if (!execute.checkInstantKill(knight, level)) {
            executeDmg = execute.addRaceModif(knight, executeDmg);
        }
        slam.updateStun(knight);
        int finalDmg = executeDmg + slam.addRaceModif(knight, slamDmg);
        knight.setCurrentHp(knight.getCurrentHp() - finalDmg);
    }

    @Override
    public void dealDamage(final Rogue rogue) {
        int executeDmg, slamDmg;

        executeDmg = execute.computeBaseDamage(rogue, level, getLandBonus());
        slamDmg = slam.computeBaseDamage(level, getLandBonus());

        if (!execute.checkInstantKill(rogue, level)) {
            executeDmg = execute.addRaceModif(rogue, executeDmg);
        }
        slam.updateStun(rogue);
        int finalDmg = executeDmg + slam.addRaceModif(rogue, slamDmg);
        rogue.setCurrentHp(rogue.getCurrentHp() - finalDmg);
    }

    @Override
    public void dealDamage(final Wizard wizard) {
        int executeDmg, slamDmg;

        executeDmg = execute.computeBaseDamage(wizard, level, getLandBonus());
        slamDmg = slam.computeBaseDamage(level, getLandBonus());

        wizard.updateDmgForDeflect(executeDmg + slamDmg);
        if (!execute.checkInstantKill(wizard, level)) {
            executeDmg = execute.addRaceModif(wizard, executeDmg);
        }
        slam.updateStun(wizard);
        int finalDmg = executeDmg + slam.addRaceModif(wizard, slamDmg);
        wizard.setCurrentHp(wizard.getCurrentHp() - finalDmg);
    }

    @Override
    public void dealDamage(final Pyromancer pyromancer) {
        int executeDmg, slamDmg;

        executeDmg = execute.computeBaseDamage(pyromancer, level, getLandBonus());
        slamDmg = slam.computeBaseDamage(level, getLandBonus());

        if (!execute.checkInstantKill(pyromancer, level)) {
            executeDmg = execute.addRaceModif(pyromancer, executeDmg);
        }
        slam.updateStun(pyromancer);
        int finalDmg = executeDmg + slam.addRaceModif(pyromancer, slamDmg);
        pyromancer.setCurrentHp(pyromancer.getCurrentHp() - finalDmg);
    }

    @Override
    public String toString() {
        if (!isAlive()) {
            return "K dead";
        } else {
            return "K " + level + " " + xp + " " + getCurrentHp()
                    + " " + coordinates.x + " " + coordinates.y;
        }
    }

}
