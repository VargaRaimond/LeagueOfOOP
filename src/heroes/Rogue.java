package heroes;

import common.HeroVisitor;
import heroes.abilities.AbilityFactory;
import heroes.abilities.AbilityType;
import heroes.abilities.Backstab;
import heroes.abilities.Paralysis;
import map.LandType;

import java.awt.Point;

public final class Rogue extends Player {

    private Backstab backstab;
    private Paralysis paralysis;

    public Rogue(final Point coordinates, final int baseHp, final int lvlScale) {
        super(coordinates, baseHp, lvlScale);

        AbilityFactory abilityFactory = AbilityFactory.getInstance();
        backstab = (Backstab) abilityFactory.getAbility(AbilityType.Backstab);
        paralysis = (Paralysis) abilityFactory.getAbility(AbilityType.Paralysis);

        type = PlayerType.Rogue;
        landWithBonus = LandType.Woods;
    }

    @Override
    public void takeDamage(final Player other) {
        other.dealDamage(this);
    }

    @Override
    public void dealDamage(final Knight knight) {
        int stabDmg, paralysisDmg;


        stabDmg = backstab.computeBaseDamage(level, getLandBonus());
        paralysisDmg = paralysis.computeDot(knight, level, getLandBonus());

        int finalDmg = backstab.addRaceModif(knight, stabDmg);
        finalDmg += paralysis.addRaceModif(knight, paralysisDmg);
        knight.setCurrentHp(knight.getCurrentHp() - finalDmg);
    }

    @Override
    public void dealDamage(final Rogue rogue) {
        int stabDmg, paralysisDmg;

        stabDmg = backstab.computeBaseDamage(level, getLandBonus());
        paralysisDmg = paralysis.computeDot(rogue, level, getLandBonus());

        int finalDmg = backstab.addRaceModif(rogue, stabDmg);
        finalDmg += paralysis.addRaceModif(rogue, paralysisDmg);
        rogue.setCurrentHp(rogue.getCurrentHp() - finalDmg);
    }

    @Override
    public void dealDamage(final Wizard wizard) {
        int stabDmg, paralysisDmg;

        stabDmg = backstab.computeBaseDamage(level, getLandBonus());
        paralysisDmg = paralysis.computeDot(wizard, level, getLandBonus());

        wizard.updateDmgForDeflect(stabDmg + paralysisDmg);
        int finalDmg = backstab.addRaceModif(wizard, stabDmg);
        finalDmg += paralysis.addRaceModif(wizard, paralysisDmg);
        wizard.setCurrentHp(wizard.getCurrentHp() - finalDmg);
    }

    @Override
    public void dealDamage(final Pyromancer pyromancer) {
        int stabDmg, paralysisDmg;

        stabDmg = backstab.computeBaseDamage(level, getLandBonus());
        paralysisDmg = paralysis.computeDot(pyromancer, level, getLandBonus());

        int finalDmg = backstab.addRaceModif(pyromancer, stabDmg);
        finalDmg += paralysis.addRaceModif(pyromancer, paralysisDmg);
        pyromancer.setCurrentHp(pyromancer.getCurrentHp() - finalDmg);
    }

    public void accept(HeroVisitor angel) {
        angel.visit(this);
    }

    @Override
    public void updateAbilities(float changer) {
        backstab.updateModifiers(changer);
        paralysis.updateModifiers(changer);
    }

    @Override
    public String toString() {
        if (!isAlive()) {
            return "R dead";
        } else {
            return "R " + level + " " + xp + " " + getCurrentHp()
                    + " " + coordinates.x + " " + coordinates.y;
        }
    }
}
