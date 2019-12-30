package angels;

import common.HeroVisitor;
import common.Observable;
import wizard.GreatWizard;
import wizard.Observer;

import java.awt.Point;

public abstract class Angel implements HeroVisitor, Observable {
    protected Point position;
    protected AngelType angelType;
    protected Observer greatWizard;

    public abstract boolean isGood();

    public Angel(AngelType type, Point coordinates) {
        position = coordinates;
        angelType = type;
    }

    public AngelType getAngelType() {
        return angelType;
    }

    public Point getPosition() {
        return position;
    }

    public void notifyObserver() {
        greatWizard = GreatWizard.getInstance();
        greatWizard.update(this);
    }
}
