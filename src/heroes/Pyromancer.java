package heroes;

import common.HeroVisitor;
import heroes.abilities.AbilityFactory;
import heroes.abilities.AbilityType;
import heroes.abilities.Fireblast;
import heroes.abilities.Ignite;
import heroes.strategies.PyroStrategy;
import map.LandType;

import java.awt.Point;

public final class Pyromancer extends Player {

    private Fireblast fireblast;
    private Ignite ignite;

    public Pyromancer(final Point coordinates, final int baseHp, final int hpScale, final int id) {
        super(coordinates, baseHp, hpScale, id);

        AbilityFactory abilityFactory = AbilityFactory.getInstance();
        fireblast = (Fireblast) abilityFactory.getAbility(AbilityType.Fireblast);
        ignite = (Ignite) abilityFactory.getAbility(AbilityType.Ignite);

        type = PlayerType.Pyromancer;
        landWithBonus = LandType.Volcanic;
        strategy = new PyroStrategy();
    }

    @Override
    public void takeDamage(final Player other) {
        other.dealDamage(this);
    }

    @Override
    public void dealDamage(final Knight knight) {
        int blastDmg, igniteDmg;

        blastDmg = fireblast.computeBaseDamage(level, getLandBonus());
        igniteDmg = ignite.computeBaseDamage(level, getLandBonus());
        ignite.computeDot(knight, level, getLandBonus());

        int finalDmg = fireblast.addRaceModif(knight, blastDmg);
        finalDmg += ignite.addRaceModif(knight, igniteDmg);
        knight.setCurrentHp(knight.getCurrentHp() - finalDmg);
    }

    @Override
    public void dealDamage(final Rogue rogue) {
        int blastDmg, igniteDmg;

        blastDmg = fireblast.computeBaseDamage(level, getLandBonus());
        igniteDmg = ignite.computeBaseDamage(level, getLandBonus());
        ignite.computeDot(rogue, level, getLandBonus());

        int finalDmg = fireblast.addRaceModif(rogue, blastDmg);
        finalDmg += ignite.addRaceModif(rogue, igniteDmg);
        rogue.setCurrentHp(rogue.getCurrentHp() - finalDmg);
    }

    @Override
    public void dealDamage(final Wizard wizard) {
        int blastDmg, igniteDmg;

        blastDmg = fireblast.computeBaseDamage(level, getLandBonus());
        igniteDmg = ignite.computeBaseDamage(level, getLandBonus());
        ignite.computeDot(wizard, level, getLandBonus());

        wizard.updateDmgForDeflect(blastDmg + igniteDmg);
        int finalDmg = fireblast.addRaceModif(wizard, blastDmg);
        finalDmg += ignite.addRaceModif(wizard, igniteDmg);
        wizard.setCurrentHp(wizard.getCurrentHp() - finalDmg);
    }

    @Override
    public void dealDamage(final Pyromancer pyromancer) {
        int blastDmg, igniteDmg;

        blastDmg = fireblast.computeBaseDamage(level, getLandBonus());
        igniteDmg = ignite.computeBaseDamage(level, getLandBonus());
        ignite.computeDot(pyromancer, level, getLandBonus());

        int finalDmg = fireblast.addRaceModif(pyromancer, blastDmg);
        finalDmg += ignite.addRaceModif(pyromancer, igniteDmg);
        pyromancer.setCurrentHp(pyromancer.getCurrentHp() - finalDmg);
    }

    public void accept(HeroVisitor angel) {
        angel.visit(this);
    }

    @Override
    public void updateAbilities(float changer) {
        fireblast.updateModifiers(changer);
        ignite.updateModifiers(changer);
    }

    @Override
    public String toString() {
        if (!isAlive()) {
            return "P dead";
        } else {
            return "P " + level + " " + xp + " " + getCurrentHp()
                    + " " + coordinates.x + " " + coordinates.y;
        }
    }
}
