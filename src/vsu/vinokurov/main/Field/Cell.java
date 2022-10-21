package vsu.vinokurov.main.Field;

import vsu.vinokurov.main.Dots.Coord;
import vsu.vinokurov.main.Player.Token;

public class Cell {
    boolean thereIsToken=false;
    Coord position;
    Token token;
    public Cell(Coord position) {
        this.thereIsToken = false;
        this.position = position;
    }
    public boolean TokenIsThere(){
        if(thereIsToken){return true;}
        return false;
    }
    public void ThereIsToken(){
        thereIsToken = true;
    }
    public Coord getPosition(){return position;}
    public Token getToken(){
        return token;
    }
}
