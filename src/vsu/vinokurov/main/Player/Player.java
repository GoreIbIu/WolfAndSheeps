package vsu.vinokurov.main.Player;

import vsu.vinokurov.main.Dots.Coord;
import vsu.vinokurov.main.Dots.Direction;
import vsu.vinokurov.main.Field.Field;

public abstract class Player {
    public boolean turn = false;
    public boolean win = false;
    abstract public int Size();
    abstract public void init(Field field);
    abstract public void move(Field field);
    abstract public boolean canWin();
    abstract Coord walk(Direction d);
}
