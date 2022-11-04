package vsu.vinokurov.main.Field;

import vsu.vinokurov.main.Dots.Coord;

import java.util.Objects;

public class Field {
    String space = "- ";
    String forbiden = "# ";
    String sheep = "* ";
    String wolf = "^ ";
    Cell[] cells = new Cell[33];

    public void init() {
        int count = 0;
        Coord c;
        for (int i = 1; i <= 7; i++) {
            if ((i <= 2 || (i >= 6))) {
                for (int j = 3; j <= 5; j++) {
                    c = new Coord(j, i);
                    cells[count] = new Cell(c);
                    count++;
                }
            } else {
                for (int j = 1; j <= 7; j++) {
                    c = new Coord(j, i);
                    cells[count] = new Cell(c);
                    count++;
                }
            }
        }
    }

    public int recountSheepSize() {
        int count = 0;
        for (int i = 0; i < 33; i++) {
            if (Objects.equals(cells[i].getLabel(), "sheep")) {
                count++;
            }
        }
        return count;
    }

    public void visualise() {
        Coord c1;
        for (int i = 1; i <= 7; i++) {
            for (int j = 1; j <= 7; j++) {
                c1 = new Coord(j, i);
                Cell cell = findCell(c1);

                if (!tryCell(c1)) {
                    System.out.print(forbiden);
                } else {
                    if (Objects.equals(cells[numCell(c1)].getLabel(), "sheep")) {
                        System.out.print(sheep);
                    }
                    if (Objects.equals(cells[numCell(c1)].getLabel(), "wolf")) {
                        System.out.print(wolf);
                    }
                    if (Objects.equals(cells[numCell(c1)].getLabel(), "none")) {
                        System.out.print(space);
                    }
                }
            }
            System.out.println();
        }
    }

    public Cell findCell(Coord coord) {
        Cell c = null;
        for (int i = 0; i < 33; i++) {
            if (cells[i].position.eq(coord)) {
                c = cells[i];
                break;
            }
        }
        return c;
    }

    public int numCell(Coord coord) {
        int t = -1;
        for (int i = 0; i < 33; i++) {
            if (cells[i].position.eq(coord)) {
                t = i;
                break;
            }
        }
        return t;
    }

    public Cell giveCell(int num) {
        return cells[num];
    }

    public boolean tryCell(Coord coord) {
        boolean t = false;
        for (int i = 0; i < 33; i++) {
            if (cells[i].position.eq(coord)) {
                t = true;
                break;
            }
        }
        return t;
    }


    public void deleteToken(Coord coord1, String label) {
        int x1 = numCell(coord1);
        cells[x1].setLabel("none");
    }

    public void moveToken(Coord coord1, Coord coord2, String label) {
        int x1 = numCell(coord1);
        int x2 = numCell(coord2);
        cells[x1].setLabel("none");
        cells[x2].setLabel(label);
    }

    public Field() {
        init();
    }
}
