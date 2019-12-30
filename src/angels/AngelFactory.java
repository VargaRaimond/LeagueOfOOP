package angels;

import java.awt.Point;

public final class AngelFactory {
    private static AngelFactory instance;


    public static AngelFactory getInstance() {
        if (instance == null) {
            instance = new AngelFactory();
        }
        return instance;
    }
    public static Angel createAngel(final String angelName, final Point angelPosition) {
        switch(angelName) {
            case("LifeGiver"):
                return new LifeGiver(AngelType.LifeGiver, angelPosition);
            case("Dracula"):
                return new Dracula(AngelType.Dracula, angelPosition);
            case("DamageAngel"):
                return new DamageAngel(AngelType.DamageAngel, angelPosition);
            case("DarkAngel"):
                return new DarkAngel(AngelType.DarkAngel, angelPosition);
            case("GoodBoy"):
                return new GoodBoy(AngelType.GoodBoy, angelPosition);
            case("LevelUpAngel"):
                return new LevelUpAngel(AngelType.LevelUpAngel, angelPosition);
            case("SmallAngel"):
                return new SmallAngel(AngelType.SmallAngel, angelPosition);
            case("Spawner"):
                return new Spawner(AngelType.Spawner, angelPosition);
            case("TheDoomer"):
                return new TheDoomer(AngelType.TheDoomer, angelPosition);
            case("XPAngel"):
                return new XPAngel(AngelType.XPAngel, angelPosition);
            default:
                return null;

        }
    }
}
