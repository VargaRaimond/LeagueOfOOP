package Map;

import java.util.ArrayList;
import java.util.List;

public class Map {

    List<List<MapCell>> map;

    public Map(int height, int width) {
        map = new ArrayList<List<MapCell>>(height);
        for(int i = 0; i < map.size(); i++) {
            map.add(new ArrayList<>(width));
        }
    }
}
