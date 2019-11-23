package com.Map;

public class MapCell {

    Character type;

    MapCell(Character type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MapCell{" +
                "type=" + type +
                '}';
    }
}
