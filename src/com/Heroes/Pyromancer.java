package com.Heroes;

import java.awt.*;

public class Pyromancer extends Player {
    public Pyromancer(Point coordinates) {
        super(coordinates);
        type = PlayerType.Pyromancer;
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
