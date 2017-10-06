public class Str_coord {
    int x;
    int y;
    String s;

    public Str_coord(int x, int y, String s) {
        this.x = x;
        this.y = y;
        this.s = s;
    }

    @Override
    public String toString() {
        return "Str_coord{" +
                "x=" + x +
                ", y=" + y +
                ", s='" + s + '\'' +
                '}';
    }
}
