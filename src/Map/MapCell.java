package Map;

public class MapCell {

    public LandType type;
    public float landModifier;

    MapCell(LandType type, float modifier) {
        this.type = type;
        landModifier = modifier;
    }

    @Override
    public String toString() {
        return "MapCell{" +
                "type=" + type +
                '}';
    }
}
