package Heroes;

import java.awt.*;

public class Knight extends Player {

    public Knight(Point coordinates) {
        super(coordinates);
        type = PlayerType.Knight;
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
