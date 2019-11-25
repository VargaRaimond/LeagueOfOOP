package map;

public class MapCell {

    public LandType type;
    public float landModifier;

    MapCell(final LandType type, final float modifier) {
        this.type = type;
        landModifier = modifier;
    }
}
