package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

import java.awt.Point;

public class LifeGiver extends Angel{
    public LifeGiver(AngelType type, Point coordinates) {
        super(type, coordinates);
    }

    private static class Modifiers {
        public static final int KNIGHT = 100;
        public static final int PYRO = 80;
        public static final int ROGUE = 90;
        public static final int WIZARD = 120;
    }

    public void visit(final Knight knight) {
        if(knight.isAlive()) {
            knight.setCurrentHp(knight.getCurrentHp() + Modifiers.KNIGHT);
        }
    }

    public void visit(final Pyromancer pyromancer) {
        if(pyromancer.isAlive()) {
            pyromancer.setCurrentHp(pyromancer.getCurrentHp() + Modifiers.PYRO);
        }
    }

    public void visit(final Wizard wizard) {
        if(wizard.isAlive()) {
            wizard.setCurrentHp(wizard.getCurrentHp() + Modifiers.WIZARD);
        }
    }

    public void visit(final Rogue rogue) {
        if(rogue.isAlive()) {
            rogue.setCurrentHp(rogue.getCurrentHp() + Modifiers.ROGUE);
        }
    }
}
