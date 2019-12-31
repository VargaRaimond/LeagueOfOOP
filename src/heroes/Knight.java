package heroes;

import common.HeroVisitor;
import heroes.abilities.Execute;
import heroes.abilities.Slam;
import heroes.abilities.AbilityFactory;
import heroes.abilities.AbilityType;
import heroes.strategies.KnightStrategy;
import map.LandType;

import java.awt.Point;

public final class Knight extends Player {

    private Execute execute;
    private Slam slam;

    public Knight(final Point coordinates, final int baseHp, final int hpScale, final int id) {
        super(coordinates, baseHp, hpScale, id);

        AbilityFactory abilityFactory = AbilityFactory.getInstance();
        execute = (Execute) abilityFactory.getAbility(AbilityType.Execute);
        execute = new Execute(execute);
        slam = (Slam) abilityFactory.getAbility(AbilityType.Slam);
        slam = new Slam(slam);

        type = PlayerType.Knight;
        landWithBonus = LandType.Land;

        strategy = new KnightStrategy();
    }

    @Override
    public void takeDamage(final Player other) {
        other.dealDamage(this);
    }

    @Override
    public void dealDamage(final Knight knight) {
        int executeDmg, slamDmg;

        executeDmg = execute.computeBaseDamage(level, getLandBonus());
        slamDmg = slam.computeBaseDamage(level, getLandBonus());
        // execute modifier for knight is 0 so I skip this operation
        slam.updateStun(knight);
        if (execute.checkInstantKill(knight, level)) {
            executeDmg = knight.getCurrentHp();
        }
        int finalDmg = executeDmg + slam.addRaceModif(knight, slamDmg);
        knight.setCurrentHp(knight.getCurrentHp() - finalDmg);
    }

    @Override
    public void dealDamage(final Rogue rogue) {
        int executeDmg, slamDmg;

        executeDmg = execute.computeBaseDamage(level, getLandBonus());
        slamDmg = slam.computeBaseDamage(level, getLandBonus());

        if (!execute.checkInstantKill(rogue, level)) {
            executeDmg = execute.addRaceModif(rogue, executeDmg);
        } else {
            executeDmg = rogue.getCurrentHp();
        }
        slam.updateStun(rogue);
        int finalDmg = executeDmg + slam.addRaceModif(rogue, slamDmg);
        rogue.setCurrentHp(rogue.getCurrentHp() - finalDmg);
    }

    @Override
    public void dealDamage(final Wizard wizard) {
        int executeDmg, slamDmg;

        executeDmg = execute.computeBaseDamage(level, getLandBonus());
        slamDmg = slam.computeBaseDamage(level, getLandBonus());

        wizard.updateDmgForDeflect(executeDmg + slamDmg);
        if (!execute.checkInstantKill(wizard, level)) {
            executeDmg = execute.addRaceModif(wizard, executeDmg);
        } else {
            executeDmg = wizard.getCurrentHp();
        }
        slam.updateStun(wizard);
        int finalDmg = executeDmg + slam.addRaceModif(wizard, slamDmg);
        wizard.setCurrentHp(wizard.getCurrentHp() - finalDmg);
    }

    @Override
    public void dealDamage(final Pyromancer pyromancer) {
        int executeDmg, slamDmg;

        executeDmg = execute.computeBaseDamage(level, getLandBonus());
        slamDmg = slam.computeBaseDamage(level, getLandBonus());

        if (!execute.checkInstantKill(pyromancer, level)) {
            executeDmg = execute.addRaceModif(pyromancer, executeDmg);
        } else {
            executeDmg = pyromancer.getCurrentHp();
        }
        slam.updateStun(pyromancer);
        int finalDmg = executeDmg + slam.addRaceModif(pyromancer, slamDmg);
        pyromancer.setCurrentHp(pyromancer.getCurrentHp() - finalDmg);
    }

    public void accept(final HeroVisitor angel) {
        angel.visit(this);
    }

    @Override
    public void updateAbilities(final Float changer) {
        execute.updateModifiers(changer);
        slam.updateModifiers(changer);
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
