package vsu.vinokurov.main.Player;

import vsu.vinokurov.main.Dots.Coord;
import vsu.vinokurov.main.Dots.Direction;
import vsu.vinokurov.main.Field.Cell;
import vsu.vinokurov.main.Field.Field;

import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.*;

public class Wolf extends Player implements Players{
    private Token wolfToken; //= new Token(1,1,"wolf");
   // public int count = 0;
    public boolean canMove(){
        return true;
    }
    @Override
    public boolean canWin(){
        if(!canMove()){return false;}
        return true;
    }
    @Override
    public void visualise() {

    }

//    public int getCount(){
//        return count;
//    }

    @Override
    public Coord walk(Direction d, int num) {
        return null;
    }

    @Override
    public void move(Field field) {
        Scanner in = new Scanner(System.in);
        System.out.println("Ход волка");
        System.out.println("Выберете действие:");
        System.out.println("1)Двигаться");
        System.out.println("2)Съесть овцу");
        //vis
        int action = in.nextInt();
        int act = 0;
        if(action==2){act = eatSheep(field, act);}
        if((action==1)||(act==1)){relocate(field);}
    }

    public Coord getStartCoord(){
        Scanner in = new Scanner(System.in);
        int y = in.nextInt();
        int x = in.nextInt();
        Coord c = new Coord(x,y);
        return c;
    }

    @Override
    public int Size() {
        return 1;
    }

    @Override
    public void init(Field field) {
        out.println("Выберете место для волка:");
        boolean t = false;
        while (!t) {
            Coord c1 = getStartCoord();
            Cell cell = field.findCell(c1);
            if (field.tryCell(c1)) {
                if (((field.findCell(c1).TokenIsThere()) && (!Objects.equals(field.findToken(c1).label, "sheep")))||(!field.findCell(c1).TokenIsThere())) {
                    int Num = field.numCell(c1);
                   // System.out.println(c1.getX()+" "+c1.getY());
                    wolfToken = field.newToken(Num, "wolf");
                    t=true;
                }else{
                    System.out.println("Повторите попытку:");
                }
            }else{
                System.out.println("Повторите попытку:");
            }
        }
        }

    @Override
    public Coord walk(Direction d) {
        Coord position = wolfToken.position;
        Coord newPosition = new Coord(position.getX()+d.x, position.getY()+d.y);
        this.wolfToken.position = this.wolfToken.position.move(d);
        return this.wolfToken.position;
    }

    public int eatSheep(Field field, int act){
        //count = 0;
        int x = wolfToken.getX(); int y = wolfToken.getY();

        boolean sheepCheck = false;
        for(int i = y-1;i<=y+1;i++){
            for(int j=x-1;j<=x+1;j++){
                Coord c1 = new Coord(j,i);
                if(field.tryCell(c1)){
                    if(field.findCell(c1).TokenIsThere()){
                        if(Objects.equals(field.findCell(c1).getToken().label, "sheep")){
                            sheepCheck=true;
                        }
                    }
                }
            }
        }

            if(!sheepCheck){
                System.out.println("Вокруг нет овец. Вы вынуждены ходить.");
                act = 1;
            }else {
                ////////////
                boolean sans = false;
                while(!sans) {
                    Scanner in = new Scanner(System.in);

                    Direction dir = new Direction(0, 0);

                    System.out.println("Выберете в каком направлении съесть овцу:");
                    System.out.println("1)Сверху");
                    System.out.println("2)Снизу");
                    System.out.println("3)Слева");
                    System.out.println("4)Справа");
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


//                wolfX = wolfPos.x;
//                wolfY = wolfPos.y;
                    Coord n = wolfToken.position;
                    Coord wolfPos1 = walk(dir);
                    Coord wolfPos = walk(dir);
                    if ((field.tryCell(wolfPos)) && (field.tryCell(wolfPos1))) {

                        out.println("token "+field.findCell(wolfPos).TokenIsThere()+" coord = "+wolfPos);
                        out.println("token1 "+field.findCell(wolfPos1).TokenIsThere()+" coord = "+wolfPos1);
                        if((((field.findCell(wolfPos).TokenIsThere()) && (!Objects.equals(field.findToken(wolfPos).label, "sheep")))||(!field.findCell(wolfPos).TokenIsThere()))&&(((field.findCell(wolfPos1).TokenIsThere()) && (Objects.equals(field.findToken(wolfPos1).label, "sheep"))))){
                        //if(Objects.equals(field.findToken(wolfPos1).label, "sheep")){
                            //
                            field.moveToken(n,wolfPos,"wolf");
                            field.deleteToken(wolfPos1,"sheep");
                            field.visualise();
                            //SheepReduceSize
                            //count++;
                            sans=true;

                            //
                        }else{
                            Direction dirM = new Direction(dir.x * (-2), dir.y * (-2));
                            wolfPos = walk(dirM);
                            System.out.println("Выбрано неверное направление, измените выбор:");
                        }

                    } else {
                        Direction dirM = new Direction(dir.x * (-2), dir.y * (-2));
                        wolfPos = walk(dirM);
                        System.out.println("Выбрано неверное направление, измените выбор:");
                    }


                }









                ////////////
            }

            return act;

        }
        public void relocate(Field field){
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
                Coord n = wolfToken.position;
                Coord wolfPos = walk(dir);
                if (field.tryCell(wolfPos)) {
                    if(((field.findCell(wolfPos).TokenIsThere()) && (!Objects.equals(field.findToken(wolfPos).label, "sheep")))||(!field.findCell(wolfPos).TokenIsThere())){
                    field.moveToken(n,wolfPos,"wolf");
                    field.visualise();
                    sans=true;
                    turn = false;

                    }else{
                        Direction dirM = new Direction(dir.x * (-1), dir.y * (-1));
                        wolfPos = walk(dirM);
                        System.out.println("Выбрано неверное направление, измените выбор:");
                    }
                } else {
                    Direction dirM = new Direction(dir.x * (-1), dir.y * (-1));
                    wolfPos = walk(dirM);
                    System.out.println("Выбрано неверное направление, измените выбор:");
                }
            }
        }
}
