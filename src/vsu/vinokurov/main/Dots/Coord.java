package vsu.vinokurov.main.Dots;

public class Coord {
    final int x;
    final int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coord move(Direction d) {
        int newX = d.x + x;
        int newY = d.y + y;
        return new Coord(newX, newY);
    }

    public boolean eq(Coord coord) {
        boolean t = false;
        if ((x == coord.x) && (y == coord.y)) {
            t = true;
        }
        return t;
    }

    @Override
    public String toString() {
        return "Coord{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}