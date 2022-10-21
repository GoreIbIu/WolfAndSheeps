package vsu.vinokurov.main.Player;

import vsu.vinokurov.main.Dots.Coord;
import vsu.vinokurov.main.Dots.Direction;
import vsu.vinokurov.main.Field.Cell;
import vsu.vinokurov.main.Field.Field;

import java.util.Objects;
import java.util.Scanner;

public class Sheep extends Player implements Players {
    private int SheepSize = 13;
    private Token[] Sheeps = new Token[SheepSize];


    @Override
    public void visualise() {

    }

    public int Size(){
        return SheepSize;
    }

    public void setSizeMinus(int a){
        SheepSize = SheepSize-a;
    }


    @Override
    public void init(Field field) {
        int count = 0;
        for(int i=5;i<=7;i++){
            for(int j=1;j<=7;j++){
                Coord c1 = new Coord(j,i);
                if(field.tryCell(c1)){
                    int Num = field.numCell(c1);
                    Sheeps[count] = field.newToken(Num,"sheep");
                    Cell cell = field.findCell(c1);
                    cell.ThereIsToken();
                    count++;
                }
            }
        }
    }

    @Override
    public Coord walk(Direction d) {
        return null;
    }

    int sheepsFromCells(Field field,int a){
        int c =-1;
        for(int i=0;i<SheepSize;i++){
            if(Sheeps[i].position.eq(field.giveCell(a).getPosition())){
                c = i;
            }else{
               // System.out.println(field.giveCell(a).getPosition());
               // System.out.println(Sheeps[i].position);
            }
        }
        return c;
    }

    public void relocate(Field field,Coord coord, int num){
        boolean sans = false;
        while(!sans){

            Scanner in = new Scanner(System.in);
            Direction dir = new Direction(0, 0);

            System.out.println("Выберете направление:");
            System.out.println("1)Вверх");
            System.out.println("2)Вниз");
            System.out.println("3)Влево");
            System.out.println("4)Вправо");
            System.out.println("5)Влево-вверх");
            System.out.println("6)Влево-вниз");
            System.out.println("7)Вправо-вверх");
            System.out.println("8)Вправо-вниз");

            field.visualise();
            int moveDirection = in.nextInt();

            if (moveDirection == 1) {
                dir = new Direction(0, -1);
            }
            if (moveDirection == 2) {
                dir = new Direction(0, 1);
            }
            if (moveDirection == 3) {
                dir = new Direction(-1, 0);
            }
            if (moveDirection == 4) {
                dir = new Direction(1, 0);
            }
            if (moveDirection == 5) {
                dir = new Direction(-1, -1);
            }
            if (moveDirection == 6) {
                dir = new Direction(-1, 1);
            }
            if (moveDirection == 7) {
                dir = new Direction(1, -1);
            }
            if (moveDirection == 8) {
                dir = new Direction(1, 1);
            }
            int numero =numCellFromSheeps(field,num,coord);
            //System.out.println(coord);
            Coord n = coord;//field.findToken(coord).position;//Sheeps[num].position;
            Coord sheepPos = walk(dir,numero);
            if (field.tryCell(sheepPos)) {
                //if(((field.findCell(sheepPos).TokenIsThere()) && ((!Objects.equals(field.findToken(sheepPos).label, "sheep"))||(!Objects.equals(field.findToken(sheepPos).label, "wolf")))||(!field.findCell(sheepPos).TokenIsThere()))){
                if(!field.findCell(sheepPos).TokenIsThere()){
                    field.moveToken(n,sheepPos,"sheep");
                    field.visualise();
                    sans=true;
                    turn = false;

                }else{
                    Direction dirM = new Direction(dir.x * (-1), dir.y * (-1));
                    sheepPos = walk(dirM,numero);
                    System.out.println("Выбрано неверное направление, измените выбор:");
                }
            } else {
                Direction dirM = new Direction(dir.x * (-1), dir.y * (-1));
                sheepPos = walk(dirM,numero);
                System.out.println("Выбрано неверное направление, измените выбор:");
            }
        }
    }

//    public boolean checkSheep(int a){
//        return false;
//    }

    public int numCellFromSheeps(Field field, int a,Coord coord){
        int t = -1;
       // System.out.println("+"+coord);
        for(int i=0;i<13;i++){
           // System.out.println("-"+Sheeps[i].position);
            if (Sheeps[i].position.eq(coord)) {
                t = i;
                break;
            }}
        return t;
    }

    @Override
    public Coord walk(Direction d, int num) {
        Coord position = Sheeps[num].position;
        Coord newPosition = new Coord(position.getX()+d.x, position.getY()+d.y);
        this.Sheeps[num].position = this.Sheeps[num].position.move(d);
        return this.Sheeps[num].position;
    }

    boolean checkAround(Field field,Coord coord){
        int x = coord.getX();
        int y = coord.getY();
        boolean thereIs = false;
        for(int i=x-1;i<=x+1;i++){
            for(int j=y-1;j<=y+1;j++){
                Coord c = new Coord(i,j);
                if(field.tryCell(c)){
                if(!field.findCell(c).TokenIsThere()){thereIs=true;
//                    System.out.println("11111");
//                    System.out.println(c);
                }}
            }
        }
        return thereIs;
    }

    @Override
    public void move(Field field){
        boolean sheepIsOk = false;
        System.out.println("Выберете овцу:");
        while (!sheepIsOk) {
            Scanner in = new Scanner(System.in);

            int y1 = in.nextInt();
            int x1 = in.nextInt();
            Coord checkCoord = new Coord(x1,y1);
            int c = field.numCell(checkCoord);
            boolean t = checkAround(field,checkCoord);
//            System.out.println(t);
//            System.out.println(c);
            if(field.tryCell(checkCoord)){
                if(field.findCell(checkCoord).TokenIsThere()){
            if(Objects.equals(field.findToken(checkCoord).label, "sheep")){
            if((c!=-1)&&(t)){relocate(field,checkCoord,c);sheepIsOk=true;}else{
                System.out.println("Выберете другую овцу:");
            }}}else{
                    System.out.println("Выберете другую овцу:");
                }}
        }
    }

    @Override
    public boolean canWin(){
        if(SheepSize<=5)return false;
        return true;
    }
}
