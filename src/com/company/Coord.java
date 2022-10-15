package com.company;

public class Coord {
    final int x;
    final int y;
    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Coord move(Direction d){
        int newX = d.x+x;
        int newY = d.y+y;
        return new Coord(newX,newY);
    }

    //Coord move(){}
}
