package map;

public final class LandFactory {
    private static LandFactory instance;

    public static LandFactory getInstance() {
        if (instance == null) {
            instance = new LandFactory();
        }
        return instance;
    }

    private static class LandModifiers {
        public static final float LAND_BONUS = 0.15f;
        public static final float VOLCANIC_BONUS = 0.25f;
        public static final float WOODS_BONUS = 0.15f;
        public static final float DESERT_BONUS = 0.1f;
    }

     MapCell createLand(final Character type) {
        switch (type) {
            case('L'):
                return new MapCell(LandType.Land, LandModifiers.LAND_BONUS);
            case('V'):
                return new MapCell(LandType.Volcanic, LandModifiers.VOLCANIC_BONUS);
            case('W'):
                return new MapCell(LandType.Woods, LandModifiers.WOODS_BONUS);
            case('D'):
                return new MapCell(LandType.Desert, LandModifiers.DESERT_BONUS);
            default: return null;
        }
    }
}
