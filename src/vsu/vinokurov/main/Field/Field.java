package vsu.vinokurov.main.Field;

import vsu.vinokurov.main.Dots.Coord;
import vsu.vinokurov.main.Player.Token;

import java.util.Objects;

public class Field{
    String space = "- ";
    String forbiden = "# ";
    String sheep = "* ";
    String wolf = "^ ";
    Cell[] cells = new Cell[33];
    public void init(){
        int count=0;
        Coord c;
        for(int i=1;i<=7;i++){
            if((i<=2||(i>=6))){
                for(int j=3;j<=5;j++){c = new Coord(j,i);cells[count]=new Cell(c);count++;}
            }else{
                for(int j=1;j<=7;j++){
                    c = new Coord(j,i);
                    cells[count]=new Cell(c);
                    count++;
                }
            }
        }
    }
    public int recountSheepSize(){
        int count = 0;
        for(int i=0;i<33;i++){
            if (cells[i].TokenIsThere()) {
                if(Objects.equals(cells[i].token.getLabel(), "sheep")) {
                    count++;
                }
            }

        }
        return count;
    }
    public void visualise(){
        Coord c1;
        for(int i=1;i<=7;i++){
            for(int j=1;j<=7;j++){
                c1= new Coord(j,i);
                Cell cell  = findCell(c1);

                 if(!tryCell(c1)){
                    System.out.print(forbiden);
                }else{
                     if((numCell(c1)>=0)&&(cells[numCell(c1)].thereIsToken)&&(Objects.equals(cells[numCell(c1)].token.getLabel(), "sheep"))){
                         System.out.print(sheep);
                     }
                     if((numCell(c1)>=0)&&(cells[numCell(c1)].thereIsToken)&&(Objects.equals(cells[numCell(c1)].token.getLabel(), "wolf"))){
                         System.out.print(wolf);
                     }
                     if((numCell(c1)>=0)&&(!cells[numCell(c1)].thereIsToken)){
                    System.out.print(space);}
                }

            }
            System.out.println();
        }
    }
    public Cell findCell(Coord coord){
        Cell c = null;
        for(int i=0;i<33;i++){
            if (cells[i].position.eq(coord)) {
                c = cells[i];
                break;
            }

        }
        return c;
    }

    public int numCell(Coord coord){
        int t = -1;
        for(int i=0;i<33;i++){
            if (cells[i].position.eq(coord)) {
                t = i;
                break;
            }}
        return t;
    }
    public Cell giveCell(int num){
        return cells[num];
    }

    public Token newToken(int num,String label){
        cells[num].thereIsToken=true;
        cells[num].token = new Token(cells[num].position,label);
        return cells[num].token;
    }
    public boolean tryCell(Coord coord){
        boolean t = false;
        for(int i=0;i<33;i++){
            if (cells[i].position.eq(coord)) {
                t = true;
                break;
            }}
        return t;
    }
//    String findTokenLabel(Token token){
//
//    }
public void deleteToken(Coord coord1,String label){
    int x1 = numCell(coord1);
    cells[x1].thereIsToken=false;
    cells[x1].token = null;

}
    public void moveToken(Coord coord1,Coord coord2,String label){
        int x1 = numCell(coord1);
        int x2 = numCell(coord2);
        cells[x1].thereIsToken=false;
        cells[x1].token = null;
        cells[x2].thereIsToken=true;
        cells[x2].token = new Token(cells[x2].position,label);

    }
    public Token findToken(Coord coord){
        Token c = null;
        for(int i=1;i<8;i++){
            for(int j=1;j<8;j++) {
                Coord c1 = new Coord(j,i);
                if (c1.eq(coord)) {
                    c = findCell(c1).token;//
                    break;
                }
            }
        }
        return c;
    }
}
