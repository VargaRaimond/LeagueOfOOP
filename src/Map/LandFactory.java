package Map;

public class LandFactory {
    private static LandFactory instance;

    public static LandFactory getInstance() {
        if(instance == null) {
            instance = new LandFactory();
        }
        return instance;
    }

    private static class LandModifiers {
        public static float LAND_BONUS = 0.15f;
        public static float VOLCANIC_BONUS = 0.25f;
        public static float WOODS_BONUS = 0.15f;
        public static float DESERT_BONUS = 0.1f;
    }

    public MapCell createLand(Character type) {
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
