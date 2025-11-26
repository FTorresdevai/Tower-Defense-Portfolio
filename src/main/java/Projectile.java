import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Projectile implements GameObject {
    private Position position;
    private Enemy target;
    private boolean alive = true;
    private Shape shape;
    private MovementStrategy movementStrategy;

    public Projectile(int x, int y, Shape shape, Enemy target) {
        this.position = new Position(x, y);
        this.shape = shape;
        this.target = target;
        this.movementStrategy = new TargetMovementStrategy(target, 2.0f);
    }

    public boolean update() {
        if (!alive) return false;

        movementStrategy.move(position);

        if (checkCollision()) {
            alive = false;
            return true;
        }
        return false;
    }

    private boolean checkCollision() {
        Position t = target.getPosition();
        return Math.abs(position.getX() - t.getX()) < 1 &&
                Math.abs(position.getY() - t.getY()) < 1;
    }

    public Position getPosition() { return position; }
    public Shape getShape() { return shape; }
    public Enemy getTarget() { return target; }
    public boolean isAlive() { return alive; }

    @Override
    public void draw(TextGraphics g) {
        g.setForegroundColor(TextColor.ANSI.WHITE);
        for (Pixel px : shape.getPixels()) {
            int x = (int)position.getX() + px.getDx();
            int y = (int)position.getY() + px.getDy();
            g.putString(x, y, String.valueOf(px.getChar()));
        }
    }
}