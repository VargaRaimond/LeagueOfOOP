package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

import java.awt.Point;

public class DamageAngel extends Angel {
    public DamageAngel(AngelType type, Point coordinates) {
        super(type, coordinates);
    }

    private static class Modifiers {
        public static final float KNIGHT = 0.15f;
        public static final float PYRO = 0.2f;
        public static final float ROGUE = 0.3f;
        public static final float WIZARD = 0.4f;
    }

    public boolean isGood() {
        return true;
    }

    public void visit(Knight knight) {
        if (knight.isAlive()) {
            knight.updateAbilities(Modifiers.KNIGHT);
        }
    }
    public void visit(Pyromancer pyromancer) {
        if (pyromancer.isAlive()) {
            pyromancer.updateAbilities(Modifiers.PYRO);
        }
    }
    public void visit(Wizard wizard) {
        if (wizard.isAlive()) {
            wizard.updateAbilities(Modifiers.WIZARD);
        }
    }
    public void visit(Rogue rogue) {
        if (rogue.isAlive()) {
            rogue.updateAbilities(Modifiers.ROGUE);
        }
    }
}
