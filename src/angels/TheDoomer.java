package angels;

import common.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

import java.awt.Point;

public class TheDoomer extends Angel {
    public TheDoomer(AngelType type, Point coordinates) {
        super(type, coordinates);
    }

    public boolean isGood() {
        return false;
    }

    public void visit(Knight knight) {
        knight.setCurrentHp(Constants.DEAD_PLAYER);
    }
    public void visit(Pyromancer pyromancer) {
        pyromancer.setCurrentHp(Constants.DEAD_PLAYER);
    }
    public void visit(Wizard wizard) {
        wizard.setCurrentHp(Constants.DEAD_PLAYER);
    }
    public void visit(Rogue rogue) {
        rogue.setCurrentHp(Constants.DEAD_PLAYER);
    }
}
