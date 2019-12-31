package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

import java.awt.Point;

public final class Dracula extends Angel {
    public Dracula(final AngelType type, final Point coordinates) {
        super(type, coordinates);
    }

    private static class Modifiers {
        public static final int KNIGHT_HP = 60;
        public static final int PYRO_HP = 40;
        public static final int ROGUE_HP = 35;
        public static final int WIZARD_HP = 20;

        public static final float KNIGHT_DMG = -0.2f;
        public static final float PYRO_DMG = -0.3f;
        public static final float ROGUE_DMG = -0.1f;
        public static final float WIZARD_DMG = -0.4f;
    }

    public boolean isGood() {
        return false;
    }

    // decrease hero's hp and dmg modifiers
    public void visit(final Knight knight) {
        knight.updateAbilities(Modifiers.KNIGHT_DMG);
        knight.setCurrentHp(knight.getCurrentHp() - Modifiers.KNIGHT_HP);
    }
    public void visit(final Pyromancer pyromancer) {
        pyromancer.updateAbilities(Modifiers.PYRO_DMG);
        pyromancer.setCurrentHp(pyromancer.getCurrentHp() - Modifiers.PYRO_HP);
    }
    public void visit(final Wizard wizard) {
        wizard.updateAbilities(Modifiers.WIZARD_DMG);
        wizard.setCurrentHp(wizard.getCurrentHp() - Modifiers.WIZARD_HP);
    }
    public void visit(final Rogue rogue) {
        rogue.updateAbilities(Modifiers.ROGUE_DMG);
        rogue.setCurrentHp(rogue.getCurrentHp() - Modifiers.ROGUE_HP);
    }
}
