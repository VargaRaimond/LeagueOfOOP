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

    public boolean isGood() {
        return true;
    }

    public void visit(final Knight knight) {
        knight.setCurrentHp(knight.getCurrentHp() + Modifiers.KNIGHT);
        if(knight.getCurrentHp() > knight.getMaxHp()) {
            knight.setCurrentHp(knight.getMaxHp());
        }

    }

    public void visit(final Pyromancer pyromancer) {
        pyromancer.setCurrentHp(pyromancer.getCurrentHp() + Modifiers.PYRO);
        if(pyromancer.getCurrentHp() > pyromancer.getMaxHp()) {
            pyromancer.setCurrentHp(pyromancer.getMaxHp());
        }
    }

    public void visit(final Wizard wizard) {
        wizard.setCurrentHp(wizard.getCurrentHp() + Modifiers.WIZARD);
        if(wizard.getCurrentHp() > wizard.getMaxHp()) {
            wizard.setCurrentHp(wizard.getMaxHp());
        }
    }

    public void visit(final Rogue rogue) {
        rogue.setCurrentHp(rogue.getCurrentHp() + Modifiers.ROGUE);
        if(rogue.getCurrentHp() > rogue.getMaxHp()) {
            rogue.setCurrentHp(rogue.getMaxHp());
        }
    }
}
