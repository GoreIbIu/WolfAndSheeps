package vsu.vinokurov.main;

import vsu.vinokurov.main.Dots.Coord;
import vsu.vinokurov.main.Field.Field;
import vsu.vinokurov.main.Player.*;

import java.util.Objects;
import java.util.Scanner;


public class Game {
    public void gameStart() {
        boolean sheepWin = false;
        boolean wolfWin = false;
        boolean sheepTurn = false;
        boolean wolfTurn = true;
        Field field = new Field();
        Player[] sheep = new Sheep[13];
        for (int l = 1; l <= 7; l++) {
            Coord c = new Coord(l, 5);
            sheep[l - 1] = new Sheep(field, c);
        }
        for (int l = 8; l <= 10; l++) {
            Coord c = new Coord(l - 5, 6);
            sheep[l - 1] = new Sheep(field, c);
        }
        for (int l = 11; l <= 13; l++) {
            Coord c = new Coord(l - 8, 7);
            sheep[l - 1] = new Sheep(field, c);
        }
        Player wolf = new Wolf(field);

        field.visualise();
        while ((!wolfWin) && (!sheepWin)) {
            boolean wolfCanDo = canWin(field, wolf.getPosition());
            if (!wolfCanDo) {
                System.out.println("Победа овец");
                sheepWin = true;
                wolfTurn = false;
            }

            if (wolfTurn) {
                wolf.move(field);
                wolfTurn = false;
                sheepTurn = true;
            }
            int a = field.recountSheepSize();
            if ((a <= 5) && (!sheepWin)) {
                System.out.println("Победа волка");
                wolfWin = true;
                sheepTurn = false;
            }
            if (sheepTurn) {
                Player sh = chooseShip(field, sheep);
                sh.move(field);
                sheepTurn = false;
                wolfTurn = true;
            }
        }

    }

    public Player chooseShip(Field field, Player[] sheep) {
        Player sh;
        boolean sheepIsOk = false;
        System.out.println("Выберете овцу:");
        int c = 0;
        int c1 = -1;
        while (!sheepIsOk) {
            Scanner in = new Scanner(System.in);
            int y1 = in.nextInt();
            int x1 = in.nextInt();
            Coord checkCoord = new Coord(x1, y1);
            c = field.numCell(checkCoord);
            c1 = findSheepNum(sheep, checkCoord);
            if (c1 >= 0) {
                boolean t = sheep[c1].checkAround(field, checkCoord);
                if (field.tryCell(checkCoord)) {
                    if (Objects.equals(field.findCell(checkCoord).getLabel(), "sheep")) {
                        if (t) {
                            sheepIsOk = true;
                        } else {
                            System.out.println("Выберете другую овцу:");
                        }
                    } else {
                        System.out.println("Выберете другую овцу:");
                    }
                }
            } else {
                System.out.println("Выберете другую овцу:");
            }
        }
        sh = sheep[c1];
        return sh;
    }

    private int findSheepNum(Player[] sheeps, Coord coord) {
        for (int i = 0; i < sheeps.length; i++) {
            if (sheeps[i].getPosition().eq(coord)) return i;
        }
        return -1;
    }

    private boolean canWin(Field field, Coord coord) {
        if (!canMove(field, coord)) {
            return false;
        }
        return true;
    }

    private boolean canMove(Field field, Coord pos) {
        boolean spaceCheck = false;
        boolean eatCheck = false;
        int x = pos.getX();
        int y = pos.getY();
        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++) {
                Coord c1 = new Coord(j, i);
                if (field.tryCell(c1)) {
                    if (Objects.equals(field.findCell(c1).getLabel(), "none")) {
                        spaceCheck = true;
                    }
                }
            }
        }
        if (spaceCheck) return true;
        for (int i = y - 2; i <= y + 2; i += 2) {
            for (int j = x - 2; j <= x + 2; j += 2) {
                Coord c1 = new Coord(j, i);
                int i1 = y + (y - i) / 2;
                int j1 = x + (x - j) / 2;
                Coord c2 = new Coord(j1, i1);
                if (field.tryCell(c1)) {
                    if (field.tryCell(c2)) {
                        if ((Objects.equals(field.findCell(c1).getLabel(), "none")) && (Objects.equals(field.findCell(c2).getLabel(), "sheep"))) {
                            eatCheck = true;
                        }
                    }
                }
            }
        }
        if (eatCheck) return true;
        return false;
    }
}
