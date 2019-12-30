package angels;

import common.HeroVisitor;

import java.awt.Point;

public abstract class Angel implements HeroVisitor {
    protected Point position;
    protected AngelType angelType;

    public Angel(AngelType type, Point coordinates) {
        position = coordinates;
        angelType = type;
    }

    public Point getPosition() {
        return position;
    }
}
