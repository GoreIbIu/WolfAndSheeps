package vsu.vinokurov.main.Player;

import vsu.vinokurov.main.Dots.Coord;
import vsu.vinokurov.main.Dots.Direction;
import vsu.vinokurov.main.Field.Field;

public interface Players {
    void visualise();

    Coord walk(Direction d, int num);

    void move(Field field);
    void init(Field field);
    abstract Coord walk(Direction d);
}
