package vsu.vinokurov.main.Player;

import vsu.vinokurov.main.Dots.Coord;

public class Token {
    String label="";
    Coord position;

    public Token(Coord position,String label) {
        this.position = position;
        this.label=label;
    }
    public String getLabel(){
        return label;
    }
    public int getX(){
        return position.getX();
    }
    public int getY(){
        return position.getY();
    }
}
