package map;

public class MapCell {

    private LandType type;
    private float landModifier;

    MapCell(final LandType type, final float modifier) {
        this.type = type;
        landModifier = modifier;
    }

    public final LandType getType() {
        return type;
    }

    public final float getLandModifier() {
        return landModifier;
    }
}
