package map;


import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public final class Map {

    private static Map instance;

    private List<List<MapCell>> map;

    private Map(final int row, final int col, final List<List<Character>> landTypeList) {
        LandFactory landFactory = LandFactory.getInstance();
        map = new ArrayList<>(row);
        for (int i = 0; i < row; i++) {
            map.add(new ArrayList<>());
            for (int j = 0; j < col; j++) {
                map.get(i).add(landFactory.createLand(landTypeList.get(i).get(j)));
            }
        }
    }

    public static Map getInstance(final int row, final int col,
                                  final List<List<Character>> landTypeList) {
        if (instance == null) {
            instance = new Map(row, col, landTypeList);
        }
        return instance;
    }

    public static Map getInstance() {
        return instance;
    }

    public MapCell getCellAt(final Point coordinates) {
        return map.get(coordinates.x).get(coordinates.y);
    }
}
