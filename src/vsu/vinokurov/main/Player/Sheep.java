package vsu.vinokurov.main.Player;

import vsu.vinokurov.main.Dots.Coord;
import vsu.vinokurov.main.Field.Cell;
import vsu.vinokurov.main.Field.Field;

public class Sheep extends Player {
    @Override
    public void move(Field field) {
        relocate(field, "sheep");
    }

    @Override
    void init(Field field, Coord coord) {
        position = coord;
        int Num = field.numCell(coord);
        Cell c = field.giveCell(Num);
        c.setLabel("sheep");

    }

    public Sheep(Field field, Coord coord) {
        init(field, coord);
    }
}
