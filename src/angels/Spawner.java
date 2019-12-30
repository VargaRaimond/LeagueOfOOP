package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

import java.awt.Point;

public class Spawner extends Angel{
    public Spawner(AngelType type, Point coordinates) {
        super(type, coordinates);
    }

    private static class Modifiers {
        public static final int KNIGHT = 200;
        public static final int PYRO = 150;
        public static final int ROGUE = 180;
        public static final int WIZARD = 120;
    }

    public void visit(Knight knight) {
        if(!knight.isAlive()) {
            knight.setCurrentHp(Modifiers.KNIGHT);
        }
    }

    public void visit(Pyromancer pyromancer) {
        if(!pyromancer.isAlive()) {
            pyromancer.setCurrentHp(Modifiers.PYRO);
        }
    }

    public void visit(Wizard wizard) {
        if(!wizard.isAlive()) {
            wizard.setCurrentHp(Modifiers.WIZARD);
        }
    }

    public void visit(Rogue rogue) {
        if(!rogue.isAlive()) {
            rogue.setCurrentHp(Modifiers.ROGUE);
        }
    }
}
