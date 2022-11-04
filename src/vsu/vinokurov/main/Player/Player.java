package vsu.vinokurov.main.Player;

import vsu.vinokurov.main.Dots.Coord;
import vsu.vinokurov.main.Dots.Direction;
import vsu.vinokurov.main.Field.Field;

import java.util.Objects;
import java.util.Scanner;

public abstract class Player {
    Coord position;

    abstract public void move(Field field);

    public Coord walk(Direction d) {
        Coord newPosition = new Coord(position.getX() + d.x, position.getY() + d.y);
        this.position = this.position.move(d);
        return this.position;
    }

    abstract void init(Field field, Coord coord);

    public boolean checkAround(Field field, Coord coord) {
        int x = coord.getX();
        int y = coord.getY();
        boolean thereIs = false;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                Coord c = new Coord(i, j);
                if (field.tryCell(c)) {
                    if (Objects.equals(field.findCell(c).getLabel(), "none")) {
                        thereIs = true;
                    }
                }
            }
        }
        return thereIs;
    }

    Coord getStartCoord() {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int y = in.nextInt();
        Coord c = new Coord(y, x);
        return c;
    }

    public void relocate(Field field, String label) {
        boolean sans = false;
        while (!sans) {

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
            Coord n = position;
            Coord wolfPos = walk(dir);
            if (field.tryCell(wolfPos)) {
                if (((Objects.equals(field.findCell(wolfPos).getLabel(), "none")) && (!Objects.equals(field.findCell(wolfPos).getLabel(), "sheep")))) {
                    field.moveToken(n, wolfPos, label);
                    field.visualise();
                    sans = true;
                } else {
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


    public Coord getPosition() {
        return position;
    }

}
