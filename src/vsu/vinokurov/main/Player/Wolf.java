package vsu.vinokurov.main.Player;

import vsu.vinokurov.main.Dots.Coord;
import vsu.vinokurov.main.Dots.Direction;
import vsu.vinokurov.main.Field.Cell;
import vsu.vinokurov.main.Field.Field;

import java.util.Objects;
import java.util.Scanner;

public class Wolf extends Player {
    @Override
    public void move(Field field) {
        Scanner in = new Scanner(System.in);
        System.out.println("Ход волка");
        System.out.println("Выберете действие:");
        System.out.println("1)Двигаться");
        System.out.println("2)Съесть овцу");
        int action = in.nextInt();
        int act = 0;
        if (action == 2) {
            act = eatSheep(field, act);
        }
        if ((action == 1) || (act == 1)) {
            relocate(field, "wolf");
        }
    }

    @Override
    void init(Field field, Coord coord) {
        position = coord;
        int Num = field.numCell(coord);
        Cell c = field.giveCell(Num);
        c.setLabel("wolf");
    }

    public int eatSheep(Field field, int act) {
        int x = position.getX();
        int y = position.getY();

        boolean sheepCheck = false;
        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++) {
                Coord c1 = new Coord(j, i);
                if (field.tryCell(c1)) {
                    if (!Objects.equals(field.findCell(c1).getLabel(), "none")) {
                        if (Objects.equals(field.findCell(c1).getLabel(), "sheep")) {
                            sheepCheck = true;
                        }
                    }
                }
            }
        }

        if (!sheepCheck) {
            System.out.println("Вокруг нет овец. Вы вынуждены ходить.");
            act = 1;
        } else {
            boolean sans = false;
            while (!sans) {
                Scanner in = new Scanner(System.in);

                Direction dir = new Direction(0, 0);

                System.out.println("Выберете в каком направлении съесть овцу:");
                System.out.println("0)Отмена");
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

                if (moveDirection == 0) {
                    move(field);
                }
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

                Coord n = position;
                Coord wolfPos1 = walk(dir);
                Coord wolfPos = walk(dir);
                if ((field.tryCell(wolfPos)) && (field.tryCell(wolfPos1))) {

                    if ((Objects.equals(field.findCell(wolfPos1).getLabel(), "sheep")) && (Objects.equals(field.findCell(wolfPos).getLabel(), "none"))) {
                        field.moveToken(n, wolfPos, "wolf");
                        field.deleteToken(wolfPos1, "sheep");
                        field.visualise();
                        sans = true;

                    } else {
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
        }
        return act;
    }

    public Wolf(Field field) {
        System.out.println("Выберете место для волка:");
        field.visualise();
        boolean t = false;
        Coord c1 = null;
        while (!t) {
            c1 = getStartCoord();
            if (field.tryCell(c1)) {
                if (Objects.equals(field.findCell(c1).getLabel(), "none")) {
                    int Num = field.numCell(c1);
                    Cell c = field.giveCell(Num);
                    c.setLabel("wolf");
                    t = true;
                } else {
                    System.out.println("Повторите попытку:");
                }
            } else {
                System.out.println("Повторите попытку:");
            }
        }
        init(field, c1);
    }
}
