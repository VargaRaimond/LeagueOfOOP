package com.Heroes;

import java.awt.*;

public class Rogue extends Player {
    public Rogue(Point coordinates) {
        super(coordinates);
        type = PlayerType.Rogue;
    }

    @Override
    public void takeDamage(Player other) {
        other.dealDamage(this);
    }

    @Override
    public void dealDamage(Knight knight) {

    }

    @Override
    public void dealDamage(Rogue rogue) {

    }

    @Override
    public void dealDamage(Wizard wizard) {

    }

    @Override
    public void dealDamage(Pyromancer pyromancer) {

    }
}
