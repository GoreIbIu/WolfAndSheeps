package com.company;

public class Wolf extends Player{
    Coord position;

    public Wolf(Coord position) {
        this.position = position;
    }



    Coord walk(Direction d){
        Coord newPosition = new Coord(position.x+d.x, position.y+d.y);
        this.position = this.position.move(d);
        return this.position;

    }
//    boolean winCondition() {
//        return false;
//    }
//
//
//    public boolean turn(boolean t) {
//        return t;
//    }

}
