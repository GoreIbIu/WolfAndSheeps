package vsu.vinokurov.main;

import vsu.vinokurov.main.Field.Field;
import vsu.vinokurov.main.Player.*;

public class Game {
    public void gameStart(){
        /**
         * while(!smnWin){
         *     if(wolfTurn){
         *         wolf.move;
         *     }
         *     if(sheepTurn){
         *         sheep.move
         *     }
         * }
         * **/
        Field field = new Field();
        field.init();
       // field.visualise();
        Player sheep = new Sheep();
        Player wolf = new Wolf();
        sheep.init(field);
        field.visualise();
        wolf.init(field);
        field.visualise();
        wolf.turn = true;
        int a = field.recountSheepSize();
        while ((!sheep.win)&&(!wolf.win)){
            if(!wolf.canWin()){
                System.out.println(" Победа овец");sheep.win=true;break;}
            if(wolf.turn){
                wolf.move(field);
                sheep.turn=true;
            }
             a = field.recountSheepSize();
           // System.out.println("a = "+a);
            if(a<=5){
                System.out.println("Победа волка");wolf.win=true;break;}
            if(sheep.turn){
                sheep.move(field);
                wolf.turn=true;
            }
        }
    }
}
