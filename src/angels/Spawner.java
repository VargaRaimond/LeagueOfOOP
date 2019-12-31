package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

import java.awt.Point;

public final class Spawner extends Angel {
    public Spawner(final AngelType type, final Point coordinates) {
        super(type, coordinates);
    }

    private static class Modifiers {
        public static final int KNIGHT = 200;
        public static final int PYRO = 150;
        public static final int ROGUE = 180;
        public static final int WIZARD = 120;
    }

    public boolean isGood() {
        return true;
    }

    // revive a hero
    public void visit(final Knight knight) {
        if (!knight.isAlive()) {
            knight.setCurrentHp(Modifiers.KNIGHT);
        }
    }

    public void visit(final Pyromancer pyromancer) {
        if (!pyromancer.isAlive()) {
            pyromancer.setCurrentHp(Modifiers.PYRO);
        }
    }

    public void visit(final Wizard wizard) {
        if (!wizard.isAlive()) {
            wizard.setCurrentHp(Modifiers.WIZARD);
        }
    }

    public void visit(final Rogue rogue) {
        if (!rogue.isAlive()) {
            rogue.setCurrentHp(Modifiers.ROGUE);
        }
    }
}
