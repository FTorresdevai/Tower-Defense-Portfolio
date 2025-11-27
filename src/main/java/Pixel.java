public class Pixel {
    private int dx, dy;
    private char c;

    public Pixel(int dx, int dy, char c) {
        this.dx = dx;
        this.dy = dy;
        this.c = c;
    }

    public int getDx() { return dx; }
    public int getDy() { return dy; }
    public char getChar() { return c; }
}