package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

import java.awt.Point;

public final class DamageAngel extends Angel {
    public DamageAngel(final AngelType type, final Point coordinates) {
        super(type, coordinates);
    }

    private static class Modifiers {
        public static final Float KNIGHT = 0.15f;
        public static final Float PYRO = 0.2f;
        public static final Float ROGUE = 0.3f;
        public static final Float WIZARD = 0.4f;
    }

    public boolean isGood() {
        return true;
    }

    // increase damage for the hero
    public void visit(final Knight knight) {
        knight.updateAbilities(Modifiers.KNIGHT);
    }
    public void visit(final Pyromancer pyromancer) {
        pyromancer.updateAbilities(Modifiers.PYRO);
    }
    public void visit(final Wizard wizard) {
        wizard.updateAbilities(Modifiers.WIZARD);
    }
    public void visit(final Rogue rogue) {
        rogue.updateAbilities(Modifiers.ROGUE);
    }
}
