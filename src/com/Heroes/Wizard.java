package com.Heroes;

import com.Heroes.Abilities.*;

import java.awt.*;

public class Wizard extends Player {

    Drain drain;
    Deflect deflect;

    Wizard(Point coordinates, int baseHp, int hpScale) {
        super(coordinates);

        AbilityFactory abilityFactory = AbilityFactory.getInstance();
        drain = (Drain)(abilityFactory.getAbility(AbilityType.Drain));
        deflect = (Deflect)(abilityFactory.getAbility(AbilityType.Deflect));

        setMaxHp(baseHp);
        setCurrentHp(baseHp);
        hpScalePerLevel = hpScale;
        type = PlayerType.Wizard;
    }

    @Override
    public void takeDamage(Player other) {
        other.dealDamage(this);
    }

    @Override
    public void dealDamage(Wizard wizard) {
        // only Drain
        int dmg = Math.round(drain.computeBaseDamage(wizard, level));
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

    }
}
