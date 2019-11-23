package com.Map;

import java.util.ArrayList;
import java.util.List;

public class Map {

    private static Map instance;

    public List<List<MapCell>> map;

    private Map(int row, int col, List<List<Character>> mapHelper) {
        map = new ArrayList<>(row);
        for(int i = 0; i < row; i++) {
            map.add(new ArrayList<>());
            for(int j = 0; j < col; j++) {
                map.get(i).add(new MapCell(mapHelper.get(i).get(j)));
            }
        }
    }

    public static Map getInstance(int row, int col, List<List<Character>> mapHelper) {
        if(instance == null) {
            instance = new Map(row, col, mapHelper);
        }
        return instance;
    }

    public static Map getInstance() {
        return instance;
    }

    public void print() {
        for(int i = 0; i < map.size(); i++) {
            for(int j = 0; j < map.get(0).size(); j++) {
                System.out.println(map.get(i).get(j));
            }
        }
    }
}
