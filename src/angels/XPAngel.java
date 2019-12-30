package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

import java.awt.Point;

public class XPAngel extends Angel {

    public XPAngel(AngelType type, Point coordinates) {
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

    public void visit(Knight knight) {
        if(knight.isAlive()) {
            knight.setXp(knight.getXp() + Modifiers.KNIGHT);
            knight.levelUp();
        }
    }

    public void visit(Pyromancer pyromancer) {
        if(pyromancer.isAlive()) {
            pyromancer.setXp(pyromancer.getXp() + Modifiers.PYRO);
            pyromancer.levelUp();
        }
    }

    public void visit(Wizard wizard) {
        if(wizard.isAlive()) {
            wizard.setXp(wizard.getXp() + Modifiers.WIZARD);
            wizard.levelUp();
        }
    }

    public void visit(Rogue rogue) {
        if(rogue.isAlive()) {
            rogue.setXp(rogue.getXp() + Modifiers.ROGUE);
            rogue.levelUp();
        }
    }
}
