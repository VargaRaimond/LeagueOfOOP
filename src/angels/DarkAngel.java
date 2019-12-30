package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

import java.awt.Point;

public class DarkAngel extends Angel {
    public DarkAngel(AngelType type, Point coordinates) {
        super(type, coordinates);
    }

    private static class Modifiers {
        public static final int KNIGHT = 40;
        public static final int PYRO = 30;
        public static final int ROGUE = 10;
        public static final int WIZARD = 20;
    }
    // TODO update obervable to know if i killed someone
    public void visit(final Knight knight) {
        if(knight.isAlive()) {
            knight.setCurrentHp(knight.getCurrentHp() - Modifiers.KNIGHT);
        }
    }

    public void visit(final Pyromancer pyromancer) {
        if(pyromancer.isAlive()) {
            pyromancer.setCurrentHp(pyromancer.getCurrentHp() - Modifiers.PYRO);
        }
    }

    public void visit(final Wizard wizard) {
        if(wizard.isAlive()) {
            wizard.setCurrentHp(wizard.getCurrentHp() - Modifiers.WIZARD);
        }
    }

    public void visit(final Rogue rogue) {
        if(rogue.isAlive()) {
            rogue.setCurrentHp(rogue.getCurrentHp() - Modifiers.ROGUE);
        }
    }
}
