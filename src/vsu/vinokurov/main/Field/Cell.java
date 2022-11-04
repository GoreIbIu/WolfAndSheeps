package vsu.vinokurov.main.Field;

import vsu.vinokurov.main.Dots.Coord;

public class Cell {
    private String label = "none";
    Coord position;

    public Cell(Coord position) {
        this.position = position;
    }

    public Coord getPosition() {
        return position;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}