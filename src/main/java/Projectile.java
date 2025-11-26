import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;

public class Projectile implements GameObject{

    private Position pos;
    private Enemy target;
    private int speed = 2;
    private boolean alive = true;
    private Shape shape;

    public  Projectile(int x, int y, Shape shape,Enemy target){
        this.pos = new Position(x, y);
        this.shape = shape;
        this.target = target;
    }
    public Position getPosition() { return pos; }
    public Shape getShape() { return shape; }
    public Enemy  getTarget() { return target; }
    public boolean isAlive() { return alive; }
    public boolean update() {
        if (!alive) return false;
        Position t = target.getPosition();

        // move toward target balloon
        if (pos.getX() < t.getX()) pos.setX(pos.getX() + speed);
        else if (pos.getX() > t.getX()) pos.setX(pos.getX() - speed);

        if (pos.getY() < t.getY()) pos.setY(pos.getY() + speed);
        else if (pos.getY() > t.getY()) pos.setY(pos.getY() - speed);

        // collision check (simple)
        if (Math.abs(pos.getX() - t.getX()) < 1 &&
                Math.abs(pos.getY() - t.getY()) < 1) {
            alive = false;
            return true; // hit target
        }

        return false;
    }





    @Override
    public void draw(TextGraphics g) {
        g.setForegroundColor(TextColor.ANSI.WHITE);
        for (Pixel px : shape.getPixels()) {
            int x = (int)pos.getX() + px.getDx();
            int y = (int)pos.getY() + px.getDy();
            g.putString(x, y, String.valueOf(px.getChar()));
        }

    }


}
