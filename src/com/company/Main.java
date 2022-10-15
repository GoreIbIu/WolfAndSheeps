package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int sheepCount = 13;
        Field field = new Field();
        field.fill();
        Scanner in = new Scanner(System.in);

        SeparateSheep[] sheeps = new SeparateSheep[sheepCount];
        for(int i=0;i<sheepCount;i++){sheeps[i] = new SeparateSheep(false);}
        for(int i=0;i<7;i++){
            field.cells[4][i]=1;
            sheeps[i].position = new Coord(5,i+1);
        }
        for(int i =2;i<5;i++){
            field.cells[5][i]=1;
            sheeps[i+5].position = new Coord(6,i+1);
            field.cells[6][i]=1;
            sheeps[i+8].position = new Coord(7,i+1);
        }
        Sheep sheep = new Sheep();/////////////////////////////////////////////////////////////////////////////////

        System.out.println("Выберете место для волка");

        field.view();


        int x = in.nextInt();
        int y = in.nextInt();
        if(field.cells[x-1][y-1]!=0){while (field.cells[x-1][y-1]!=0){
            System.out.println("Неверные координаты");
            x = in.nextInt();
            y = in.nextInt();
        }
        }
        Coord wolfPos = new Coord(x,y);
        Player wolf = new Wolf(wolfPos);
        field.cells[wolfPos.x-1][wolfPos.y-1] = 2;


        wolf.turn = true;
        //.out.println(sheeps[0].position.x);
        int action,moveDirection;

        while ((!sheep.winCondition)&&(!wolf.winCondition)) {
            if (sheepCount==5){
                System.out.println("Победа волка");wolf.winCondition=true;break;}
            boolean wolfCanDo = true;

//            if(wolfPos.y>0) {
//                for (int i = wolfPos.x - 1; i <= wolfPos.x + 1; i++) {
//                    for (int j = wolfPos.y - 1; i <= wolfPos.y + 1; i++) {
//                        if (field.cells[i][j] == 0) {
//                            wolfCanDo = true;
//                        }
//                    }
//                }
//            }
//
//
//            if(!wolfCanDo){sheep.winCondition=true;break;}



//            int iRIGHT = 1;
//            int iLEFT = 1;
//            int jUP = 1;
//            int jDOWN = 1;
           int wolfX = wolfPos.x;
            int wolfY = wolfPos.y;

//            if(wolfX>4){iRIGHT--;}
//            if(wolfX<2){iLEFT--;}
//            if(wolfY<2){jUP--;}
//            if(wolfY>4){jDOWN--;}

//            if(wolfX>6){iRIGHT--;}
//            if(wolfX<2){iLEFT--;}
//            if(wolfY<2){jUP--;}
//            if(wolfY>6){jDOWN--;}
//
//            boolean noMove = true;
//
//            for (int i = wolfPos.x - iLEFT; i <= wolfPos.x + iRIGHT; i++) {
//                    for (int j = wolfPos.y - jUP; i <= wolfPos.y + jDOWN; i++) {
//                        System.out.println("i = "+i);
//                        System.out.println("j = "+j);
//                        if (field.cells[j-1][i-1] == 0) {
//                            noMove = false;
//                            System.out.println("i = "+i+" j = "+j);
//                        }
//                        if(field.cells[i-1][j-1] == 1){
//                            int newX = 2*i-wolfPos.x-1;
//                            int newY = 2*i-wolfPos.y-1;
//                            if((newX>-1)&&(newY>-1)){
//                                if(field.cells[newY][newX]==0){noMove=false;}
//                            }
//                        }
//                    }
//                }
//
//            if(noMove){
//                wolfCanDo = false;
//            }
            boolean allOne = true;
            for(int i=-1;i<=1;i++){
                for(int j=-1;j<=1;j++){
                    if(wolfX+i>0){
                        if(wolfY+j>0){
                           if(field.cells[wolfX+i-1][wolfY+j-1]==0){
                               allOne=false;
                           }
                        }
                    }
                }
            }

            boolean isSpace = false;

            if(allOne){

                for(int i=-1;i<=1;i++){
                    for(int j=-1;j<=1;j++){
                        if(wolfX+i>0){
                            if(wolfY+j>0){
                                if(field.cells[wolfX+i-1][wolfY+j-1]==1){
                                   int xNew =2*i-wolfX;
                                   int yNew =2*j-wolfY;
                                   if(xNew>0){
                                       if(yNew>0){
                                           if(field.cells[xNew-1][yNew-1]==0){
                                               isSpace=true;

                                       }
                                   }
                                   // allOne=false;
                                }
                            }
                        }
                    }
                }

            }}

                if((allOne)&&(!isSpace)){wolfCanDo=false;}

            if(!wolfCanDo){
                System.out.println("Победа овец");sheep.winCondition=true;break;}
            if(wolf.turn){
                System.out.println("Ход волка");
                System.out.println("Выберете действие:");
                System.out.println("1)Двигаться");
                System.out.println("2)Съесть овцу");
                field.view();
                action = in.nextInt();
                if(action==2){
                    boolean sheepCheck = false;
                    Coord checkCoord = wolfPos;//new Coord(wolfPos.x, wolfPos.y)
                    for(int i = 0;i<sheepCount;i++){
                        if(((sheeps[i].position.x==checkCoord.x)||(sheeps[i].position.x==checkCoord.x-1)||(sheeps[i].position.x==checkCoord.x+1))&&((sheeps[i].position.y==checkCoord.y)||(sheeps[i].position.y==checkCoord.y-1)||(sheeps[i].position.y==checkCoord.y+1))){
                            sheepCheck=true;
                        }
                    }
                    for(int i=0;i<sheepCount;i++){
                       // System.out.println(checkCoord.x+" "+checkCoord.y);
                        //System.out.println("x = "+sheeps[i].position.x+" y = "+sheeps[i].position.y);
                    }
                    if(!sheepCheck){
                        System.out.println("Вокруг нет овец. Вы вынуждены ходить.");
                        action=1;
                    }else {
                        ////////////////ДОДЕЛАТЬ
                        System.out.println("Выберете овцу, которую вы хотите съесть.");
                        Direction dir = new Direction(0,0);

                        System.out.println("Выберете в каком направлении съесть овцу:");
                        System.out.println("1)Сверху");
                        System.out.println("2)Снизу");
                        System.out.println("3)Слева");
                        System.out.println("4)Справа");
                        System.out.println("5)Влево-вверх");
                        System.out.println("6)Влево-вниз");
                        System.out.println("7)Вправо-вверх");
                        System.out.println("8)Вправо-вниз");

                        field.view();
                        moveDirection = in.nextInt();

                        if(moveDirection==1){dir = new Direction(-1,0);}
                        if(moveDirection==2){dir = new Direction(1,0);}
                        if(moveDirection==3){dir = new Direction(0,-1);}
                        if(moveDirection==4){dir = new Direction(0,1);}
                        if(moveDirection==5){dir = new Direction(-1,-1);}
                        if(moveDirection==6){dir = new Direction(1,-1);}
                        if(moveDirection==7){dir = new Direction(-1,1);}
                        if(moveDirection==8){dir = new Direction(1,1);}



                        wolfX = wolfPos.x;
                        wolfY = wolfPos.y;
                        wolfPos = wolf.walk(dir);
                        int sheepX=wolfPos.x;
                        int sheepY=wolfPos.y;
                        wolfPos = wolf.walk(dir);
                        if((wolfPos.x>0)&&(wolfPos.y>0)) {
                            if ((field.cells[sheepX - 1][sheepY - 1] == 1) && (field.cells[wolfPos.x - 1][wolfPos.y - 1] == 0)) {
                                field.cells[wolfX - 1][wolfY - 1] = 0;
                                field.cells[sheepX - 1][sheepY - 1] = 0;
                                int Num = 0;
                                int j = 0;
                                for (int i = 0; i < sheepCount; i++) {
                                    if ((sheeps[i].position.x == sheepX) && (sheeps[i].position.y == sheepY)) {
                                        Num = i;
                                    }
                                }
                                SeparateSheep[] sheeps2 = new SeparateSheep[sheepCount - 1];
                                for (int i = 0; i < sheepCount; i++) {
                                    if (i != Num) {
                                        sheeps2[j] = sheeps[i];
                                        j++;
                                    }
                                }
                                sheeps = sheeps2;
                                field.cells[wolfPos.x - 1][wolfPos.y - 1] = 2;
                                field.view();
                                sheepCount--;
                            } else {
                                //System.out.println("field.cells[sheepX][sheepY] = " + field.cells[sheepX - 1][sheepY - 1] + " sheepX = " + sheepX + " sheepY = " + sheepY);
                                //System.out.println("field.cells[wolfPos.x][wolfPos.y] = " + field.cells[wolfPos.x - 1][wolfPos.y - 1] + " wolfPos.x = " + wolfPos.x + " wolfPos.y = " + wolfPos.y);
                                Direction dirM = new Direction(dir.x * (-2), dir.y * (-2));
                                wolfPos = wolf.walk(dirM);
                                System.out.println("Неверно выбрана овца");

                            }

                        }else{
                            Direction dirM = new Direction(dir.x * (-1), dir.y * (-1));
                            wolfPos = wolf.walk(dirM);//
                        wolf.turn=false;
                        sheep.turn=true;

                    }////////

                    }
                }
                if(action==1){
                    Direction dir = new Direction(0,0);

                    System.out.println("Выберете направление:");
                    System.out.println("1)Вверх");
                    System.out.println("2)Вниз");
                    System.out.println("3)Влево");
                    System.out.println("4)Вправо");
                    System.out.println("5)Влево-вверх");
                    System.out.println("6)Влево-вниз");
                    System.out.println("7)Вправо-вверх");
                    System.out.println("8)Вправо-вниз");

                    field.view();
                    moveDirection = in.nextInt();

                    if(moveDirection==1){dir = new Direction(-1,0);}
                    if(moveDirection==2){dir = new Direction(1,0);}
                    if(moveDirection==3){dir = new Direction(0,-1);}
                    if(moveDirection==4){dir = new Direction(0,1);}
                    if(moveDirection==5){dir = new Direction(-1,-1);}
                    if(moveDirection==6){dir = new Direction(1,-1);}
                    if(moveDirection==7){dir = new Direction(-1,1);}
                    if(moveDirection==8){dir = new Direction(1,1);}

                     wolfX = wolfPos.x;
                     wolfY = wolfPos.y;


                    wolfPos = wolf.walk(dir);
                    //System.out.println("wolfPos.x="+wolfPos.x+" wolfPos.y"+wolfPos.y);
                    if((wolfPos.x>0)&&(wolfPos.y>0)) {
                        if (field.cells[wolfPos.x - 1][wolfPos.y - 1] == 0) {
                            field.cells[wolfX - 1][wolfY - 1] = 0;
                            field.cells[wolfPos.x - 1][wolfPos.y - 1] = 2;
                            field.view();
                        }else{
                            Direction dirM = new Direction(dir.x * (-1), dir.y * (-1));
                            wolfPos = wolf.walk(dirM);
                            boolean directionIsOk = false;
                            System.out.println("Выбрано неверное направление, измените выбор:");
                            while (!directionIsOk) {
                                moveDirection = in.nextInt();
                               // System.out.println("move direction = " + moveDirection);
                                if (moveDirection == 1) {
                                    dir = new Direction(-1, 0);
                                }
                                if (moveDirection == 2) {
                                    dir = new Direction(1, 0);
                                }
                                if (moveDirection == 3) {
                                    dir = new Direction(0, -1);
                                }
                                if (moveDirection == 4) {
                                    dir = new Direction(0, 1);
                                }
                                if (moveDirection == 5) {
                                    dir = new Direction(-1, -1);
                                }
                                if (moveDirection == 6) {
                                    dir = new Direction(1, -1);
                                }
                                if (moveDirection == 7) {
                                    dir = new Direction(-1, 1);
                                }
                                if (moveDirection == 8) {
                                    dir = new Direction(1, 1);
                                }


                                wolfX = wolfPos.x;
                                wolfY = wolfPos.y;
                                wolfPos = wolf.walk(dir);
                                //System.out.println("wolfPos.x=" + wolfPos.x + " wolfPos.y" + wolfPos.y);
                                if ((wolfPos.x > 0) && (wolfPos.y > 0)) {
                                    if (field.cells[wolfPos.x - 1][wolfPos.y - 1] == 0) {
                                      //  System.out.println("wolfPos.x=" + wolfPos.x + " wolfPos.y" + wolfPos.y);
                                      //  System.out.println("wolfX="+wolfX+" wolfY="+wolfY);
                                        field.cells[wolfX - 1][wolfY - 1] = 0;
                                        field.cells[wolfPos.x - 1][wolfPos.y - 1] = 2;
                                        field.view();
                                        directionIsOk = true;
                                    }else{
                                    dirM = new Direction(dir.x * (-1), dir.y * (-1));
                                    wolfPos = wolf.walk(dirM);
                                    System.out.println("Выберете другое направление:");
                                }
                               }else{dirM = new Direction(dir.x * (-1), dir.y * (-1));
                                    wolfPos = wolf.walk(dirM);}//
                            }
                        }//
                    }else{Direction dirM = new Direction(dir.x * (-1), dir.y * (-1));
                        wolfPos = wolf.walk(dirM);}//
                    wolf.turn=false;
                    sheep.turn=true;
                }
            }
            if(sheep.turn){
                System.out.println("Ход овец");
                System.out.println("Выберите овцу:");
                field.view();
                x = in.nextInt();
                y = in.nextInt();
                Coord sheepCoord = new Coord(x,y);
                int Number = -1;
                boolean thereIsSheep = false;
                for(int i=0;i<sheepCount;i++){
                    if((sheeps[i].position.x==sheepCoord.x)&&(sheeps[i].position.y==sheepCoord.y)){thereIsSheep=true;Number=i;}
                    //System.out.println("x1="+sheeps[i].position.x+" y1="+sheeps[i].position.y);
                    //System.out.println("x2="+sheepCoord.x+" y2="+sheepCoord.y);
                }
                //System.out.println(thereIsSheep);
                if(!thereIsSheep){
                    while(!thereIsSheep) {
                        System.out.println("Овца выбрана неверно");
                        System.out.println("Выберите овцу:");
                        field.view();
                        x = in.nextInt();
                        y = in.nextInt();
                        sheepCoord = new Coord(x,y);
                        for(int i=0;i<sheepCount;i++){
                            if((sheeps[i].position.x==sheepCoord.x)&&(sheeps[i].position.y==sheepCoord.y)){thereIsSheep=true;Number=i;}
                        }
                    }
                }else{
                    boolean spaceCoord=false;
                    Coord checkCoord = sheepCoord;
                    for (int i=checkCoord.x-1;i<=checkCoord.x+1;i++){
                        for(int j=checkCoord.y-1;j<=checkCoord.y+1;j++){
                            if((j-1>-1)&&(i-1>-1)){
                            if((j-1<7)&&(i-1<7)){
                                   // System.out.println("x=" + i + " y=" + j + " cells=" + field.cells[i - 1][j - 1]);
                                    if (field.cells[i - 1][j - 1] == 0) {
                                        spaceCoord = true;
                                    }
                                }
                            }
                        }
                    }
                   // System.out.println(spaceCoord);
                    if(spaceCoord){
                        Direction dir = new Direction(0,0);

                        System.out.println("Выберете направление:");
                        System.out.println("1)Вверх");
                        System.out.println("2)Вниз");
                        System.out.println("3)Влево");
                        System.out.println("4)Вправо");
                        System.out.println("5)Влево-вверх");
                        System.out.println("6)Влево-вниз");
                        System.out.println("7)Вправо-вверх");
                        System.out.println("8)Вправо-вниз");

                        field.view();
                        moveDirection = in.nextInt();

                        if(moveDirection==1){dir = new Direction(-1,0);}
                        if(moveDirection==2){dir = new Direction(1,0);}
                        if(moveDirection==3){dir = new Direction(0,-1);}
                        if(moveDirection==4){dir = new Direction(0,1);}
                        if(moveDirection==5){dir = new Direction(-1,-1);}
                        if(moveDirection==6){dir = new Direction(1,-1);}
                        if(moveDirection==7){dir = new Direction(-1,1);}
                        if(moveDirection==8){dir = new Direction(1,1);}

                        int sheepX = sheepCoord.x;
                        int sheepY = sheepCoord.y;


                        field.cells[sheepX-1][sheepY-1] = 0;
                        sheepCoord = sheeps[Number].walk(dir);
                        if((sheepCoord.x>0)&&(sheepCoord.y>0)) {
                            if (field.cells[sheepCoord.x - 1][sheepCoord.y - 1] == 0) {
                                field.cells[sheepCoord.x - 1][sheepCoord.y - 1] = 1;
                                field.view();
                            } else {
                                Direction dirM = new Direction(dir.x * (-1), dir.y * (-1));
                                sheepCoord = sheeps[Number].walk(dirM);
                                boolean directionIsOk = false;
                                System.out.println("Выбрано неверное направление, измените выбор:");
                                while (!directionIsOk) {
                                    moveDirection = in.nextInt();
                                   // System.out.println("move direction = " + moveDirection);
                                    if (moveDirection == 1) {
                                        dir = new Direction(-1, 0);
                                    }
                                    if (moveDirection == 2) {
                                        dir = new Direction(1, 0);
                                    }
                                    if (moveDirection == 3) {
                                        dir = new Direction(0, -1);
                                    }
                                    if (moveDirection == 4) {
                                        dir = new Direction(0, 1);
                                    }
                                    if (moveDirection == 5) {
                                        dir = new Direction(-1, -1);
                                    }
                                    if (moveDirection == 6) {
                                        dir = new Direction(1, -1);
                                    }
                                    if (moveDirection == 7) {
                                        dir = new Direction(-1, 1);
                                    }
                                    if (moveDirection == 8) {
                                        dir = new Direction(1, 1);
                                    }

                                    sheepX = sheepCoord.x;
                                    sheepY = sheepCoord.y;


                                    sheepCoord = sheeps[Number].walk(dir);
                                    //System.out.println("wolfPos2.x="+sheepCoord2.x+" wolfPos2.y="+sheepCoord2.y);
                                    if (field.cells[sheepCoord.x - 1][sheepCoord.y - 1] == 0) {
                                        field.cells[sheepX - 1][sheepY - 1] = 0;
                                        field.cells[sheepCoord.x - 1][sheepCoord.y - 1] = 1;
                                        field.view();
                                        directionIsOk = true;
                                    } else {
                                        dirM = new Direction(dir.x * (-1), dir.y * (-1));
                                        //wolfPos2 =
                                        sheeps[Number].walk(dirM);
                                        System.out.println("Выберете другое направление:");
                                    }
                                }
                            }
                        }else{Direction dirM = new Direction(dir.x * (-1), dir.y * (-1));
                            sheepCoord = sheeps[Number].walk(dirM);}






                        sheep.turn=false;
                        wolf.turn=true;
                    }else{
                        while(!spaceCoord){
                            System.out.println("Овца не может ходить, выберите другую:");
                            field.view();
                            x = in.nextInt();
                            y = in.nextInt();
                            sheepCoord = new Coord(x,y);
                            Number = -1;
                            thereIsSheep = false;
                            for(int i=0;i<sheepCount;i++){
                                if((sheeps[i].position.x==sheepCoord.x)&&(sheeps[i].position.y==sheepCoord.y)){thereIsSheep=true;Number=i;}
                            }

                            if(!thereIsSheep){
                                while(!thereIsSheep) {
                                    System.out.println("Овца выбрана неверно");
                                    System.out.println("Выберите овцу:");
                                    field.view();
                                    x = in.nextInt();
                                    y = in.nextInt();
                                    sheepCoord = new Coord(x,y);
                                    for(int i=0;i<sheepCount;i++){
                                        if((sheeps[i].position.x==sheepCoord.x)&&(sheeps[i].position.y==sheepCoord.y)){thereIsSheep=true;Number=i;}
                                    }
                                }
                            }else{

                                //spaceCoord=false;
                                checkCoord = sheepCoord;

                                for (int i=checkCoord.x-1;i<=checkCoord.x+1;i++){
                                    for(int j=checkCoord.y-1;j<=checkCoord.y+1;j++){
                                        if((j-1>-1)&&(i-1>-1)) {
                                            if((j-1<7)&&(i-1<7)){
                                          //  System.out.println("x="+i+" y="+j+" cells="+field.cells[i-1][j-1]);
                                            if(field.cells[i-1][j-1] == 0){spaceCoord=true;}
                                        }}
                                    }
                                }
                                //System.out.println(spaceCoord);
                                if(spaceCoord){
                                    Direction dir = new Direction(0,0);

                                    System.out.println("Выберете направление:");
                                    System.out.println("1)Вверх");
                                    System.out.println("2)Вниз");
                                    System.out.println("3)Влево");
                                    System.out.println("4)Вправо");
                                    System.out.println("5)Влево-вверх");
                                    System.out.println("6)Влево-вниз");
                                    System.out.println("7)Вправо-вверх");
                                    System.out.println("8)Вправо-вниз");

                                    field.view();
                                    moveDirection = in.nextInt();

                                    if(moveDirection==1){dir = new Direction(-1,0);}
                                    if(moveDirection==2){dir = new Direction(1,0);}
                                    if(moveDirection==3){dir = new Direction(0,-1);}
                                    if(moveDirection==4){dir = new Direction(0,1);}
                                    if(moveDirection==5){dir = new Direction(-1,-1);}
                                    if(moveDirection==6){dir = new Direction(1,-1);}
                                    if(moveDirection==7){dir = new Direction(-1,1);}
                                    if(moveDirection==8){dir = new Direction(1,1);}

                                    int sheepX = sheepCoord.x;
                                    int sheepY = sheepCoord.y;



                                    sheepCoord = sheeps[Number].walk(dir);
                                    if((sheepCoord.x>0)&&(sheepCoord.y>0)) {
                                        if (field.cells[sheepCoord.x - 1][sheepCoord.y - 1] == 0) {
                                            field.cells[sheepX - 1][sheepY - 1] = 0;
                                            field.cells[sheepCoord.x - 1][sheepCoord.y - 1] = 1;
                                            field.view();
                                        } else {
                                            Direction dirM = new Direction(dir.x * (-1), dir.y * (-1));
                                            sheepCoord = sheeps[Number].walk(dirM);
                                            boolean directionIsOk = false;
                                            System.out.println("Выбрано неверное направление, измените выбор:");
                                            while (!directionIsOk) {
                                                moveDirection = in.nextInt();
                                              //  System.out.println("move direction = " + moveDirection);
                                                if (moveDirection == 1) {
                                                    dir = new Direction(-1, 0);
                                                }
                                                if (moveDirection == 2) {
                                                    dir = new Direction(1, 0);
                                                }
                                                if (moveDirection == 3) {
                                                    dir = new Direction(0, -1);
                                                }
                                                if (moveDirection == 4) {
                                                    dir = new Direction(0, 1);
                                                }
                                                if (moveDirection == 5) {
                                                    dir = new Direction(-1, -1);
                                                }
                                                if (moveDirection == 6) {
                                                    dir = new Direction(1, -1);
                                                }
                                                if (moveDirection == 7) {
                                                    dir = new Direction(-1, 1);
                                                }
                                                if (moveDirection == 8) {
                                                    dir = new Direction(1, 1);
                                                }

                                                field.cells[sheepCoord.x - 1][sheepCoord.y - 1] = 0;
                                                sheepCoord = sheeps[Number].walk(dir);
                                                //System.out.println("wolfPos2.x="+sheepCoord2.x+" wolfPos2.y="+sheepCoord2.y);
                                                if (field.cells[sheepCoord.x - 1][sheepCoord.y - 1] == 0) {
                                                    field.cells[sheepCoord.x - 1][sheepCoord.y - 1] = 1;
                                                    field.view();
                                                    directionIsOk = true;
                                                } else {
                                                    dirM = new Direction(dir.x * (-1), dir.y * (-1));
                                                    //wolfPos2 =
                                                    sheeps[Number].walk(dirM);
                                                    System.out.println("Выберете другое направление:");
                                                }
                                            }
                                        }
                                    }else{Direction dirM = new Direction(dir.x * (-1), dir.y * (-1));
                                        sheepCoord = sheeps[Number].walk(dirM);}




                                    sheep.turn=false;
                                    wolf.turn=true;
                                }








                            }








                        }
                    }

                    ///////////////

                }

            }
            }//////////////////////////////////////.................
    }
}
