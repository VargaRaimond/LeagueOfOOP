package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

import java.awt.Point;

public class GoodBoy extends Angel {
    public GoodBoy(AngelType type, Point coordinates) {
        super(type, coordinates);
    }

    private static class Modifiers {
        public static final int KNIGHT_HP = 20;
        public static final int PYRO_HP = 30;
        public static final int ROGUE_HP = 40;
        public static final int WIZARD_HP = 50;

        public static final float KNIGHT_DMG = 0.4f;
        public static final float PYRO_DMG = 0.5f;
        public static final float ROGUE_DMG = 0.4f;
        public static final float WIZARD_DMG = 0.3f;
    }

    public void visit(Knight knight) {
        if (knight.isAlive()) {
            knight.updateAbilities(Modifiers.KNIGHT_DMG);
            knight.setCurrentHp(knight.getCurrentHp() + Modifiers.KNIGHT_HP);
        }
    }
    public void visit(Pyromancer pyromancer) {
        if (pyromancer.isAlive()) {
            pyromancer.updateAbilities(Modifiers.PYRO_DMG);
            pyromancer.setCurrentHp(pyromancer.getCurrentHp() + Modifiers.PYRO_HP);
        }
    }
    public void visit(Wizard wizard) {
        if (wizard.isAlive()) {
            wizard.updateAbilities(Modifiers.WIZARD_DMG);
            wizard.setCurrentHp(wizard.getCurrentHp() + Modifiers.WIZARD_HP);
        }
    }
    public void visit(Rogue rogue) {
        if (rogue.isAlive()) {
            rogue.updateAbilities(Modifiers.ROGUE_DMG);
            rogue.setCurrentHp(rogue.getCurrentHp() + Modifiers.ROGUE_HP);
        }
    }
}
