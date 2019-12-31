package heroes;

import common.HeroVisitor;
import heroes.abilities.Drain;
import heroes.abilities.Deflect;
import heroes.abilities.AbilityFactory;
import heroes.abilities.AbilityType;
import heroes.strategies.WizardStrategy;
import map.LandType;

import java.awt.Point;

public final class Wizard extends Player {

    private Drain drain;
    private Deflect deflect;
    private float dmgForDeflect;

    Wizard(final Point coordinates, final int baseHp, final int hpScale, final int id) {
        super(coordinates, baseHp, hpScale, id);

        AbilityFactory abilityFactory = AbilityFactory.getInstance();
        drain = (Drain) (abilityFactory.getAbility(AbilityType.Drain));
        drain = new Drain(drain);
        deflect = (Deflect) (abilityFactory.getAbility(AbilityType.Deflect));
        deflect = new Deflect(deflect);

        type = PlayerType.Wizard;
        landWithBonus = LandType.Desert;

        strategy = new WizardStrategy();
    }

    public void updateDmgForDeflect(final int newDamage) {
        dmgForDeflect = newDamage;
    }

    @Override
    public void takeDamage(final Player other) {
        other.dealDamage(this);
    }

    @Override
    public void dealDamage(final Wizard wizard) {
        // only Drain
        int drainDmg;
        drainDmg = drain.computeBaseDamage(wizard, level, getLandBonus());
        wizard.setCurrentHp(wizard.getCurrentHp() - drainDmg);
    }

    @Override
    public void dealDamage(final Knight knight) {
        int drainDmg, deflectDmg;

        drainDmg = drain.computeBaseDamage(knight, level, getLandBonus());
        deflectDmg = deflect.computeBaseDamage(knight, level, getLandBonus(), dmgForDeflect);

        knight.setCurrentHp(knight.getCurrentHp() - drainDmg - deflectDmg);
    }

    @Override
    public void dealDamage(final Rogue rogue) {
        int drainDmg, deflectDmg;

        drainDmg = drain.computeBaseDamage(rogue, level, getLandBonus());
        deflectDmg = deflect.computeBaseDamage(rogue, level, getLandBonus(), dmgForDeflect);

        rogue.setCurrentHp(rogue.getCurrentHp() - drainDmg - deflectDmg);
    }

    @Override
    public void dealDamage(final Pyromancer pyromancer) {
        int drainDmg, deflectDmg;

        drainDmg = drain.computeBaseDamage(pyromancer, level, getLandBonus());
        deflectDmg = deflect.computeBaseDamage(pyromancer, level, getLandBonus(), dmgForDeflect);
        pyromancer.setCurrentHp(pyromancer.getCurrentHp() - drainDmg - deflectDmg);
    }

    public void accept(final HeroVisitor angel) {
        angel.visit(this);
    }

    @Override
    public void updateAbilities(final Float changer) {
        drain.updateModifiers(changer);
        deflect.updateModifiers(changer);
    }

    @Override
    public String toString() {
        if (!isAlive()) {
            return "W dead";
        } else {
            return "W " + level + " " + xp + " " + getCurrentHp()
                    + " " + coordinates.x + " " + coordinates.y;
        }
    }
}
