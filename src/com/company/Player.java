package com.company;
public abstract class Player {
    boolean winCondition = false;
    boolean turn = false;

    abstract Coord walk(Direction d);


}
