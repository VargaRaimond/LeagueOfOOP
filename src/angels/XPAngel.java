package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

import java.awt.Point;

public final class XPAngel extends Angel {

    public XPAngel(final AngelType type, final Point coordinates) {
        super(type, coordinates);
    }

    private static class Modifiers {
        public static final int KNIGHT = 45;
        public static final int PYRO = 50;
        public static final int ROGUE = 40;
        public static final int WIZARD = 60;
    }

    public boolean isGood() {
        return true;
    }

    // give xp to a hero
    public void visit(final Knight knight) {
        knight.setXp(knight.getXp() + Modifiers.KNIGHT);
    }

    public void visit(final Pyromancer pyromancer) {
        pyromancer.setXp(pyromancer.getXp() + Modifiers.PYRO);
    }

    public void visit(final Wizard wizard) {
        wizard.setXp(wizard.getXp() + Modifiers.WIZARD);
    }

    public void visit(final Rogue rogue) {
        rogue.setXp(rogue.getXp() + Modifiers.ROGUE);
    }
}
