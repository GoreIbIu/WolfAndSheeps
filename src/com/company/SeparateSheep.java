package com.company;

public class SeparateSheep extends Player{
    Coord position;
    boolean isDead;

    public SeparateSheep(//Coord position,
                         boolean isDead) {
        //this.position = position;
        this.isDead = isDead;
    }





    Coord walk(Direction d){
        Coord newPosition = new Coord(position.x+d.x, position.y+d.y);
        this.position = this.position.move(d);
        return this.position;

    }
}
