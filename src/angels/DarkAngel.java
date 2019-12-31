package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

import java.awt.Point;

public final class DarkAngel extends Angel {
    public DarkAngel(final AngelType type, final Point coordinates) {
        super(type, coordinates);
    }

    private static class Modifiers {
        public static final int KNIGHT = 40;
        public static final int PYRO = 30;
        public static final int ROGUE = 10;
        public static final int WIZARD = 20;
    }

    public boolean isGood() {
        return false;
    }

    // decrease hero's hit points
    public void visit(final Knight knight) {
        knight.setCurrentHp(knight.getCurrentHp() - Modifiers.KNIGHT);
    }

    public void visit(final Pyromancer pyromancer) {
        pyromancer.setCurrentHp(pyromancer.getCurrentHp() - Modifiers.PYRO);
    }

    public void visit(final Wizard wizard) {
        wizard.setCurrentHp(wizard.getCurrentHp() - Modifiers.WIZARD);
    }

    public void visit(final Rogue rogue) {
        rogue.setCurrentHp(rogue.getCurrentHp() - Modifiers.ROGUE);
    }
}
