import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Enemy implements GameObject {
    private float health;
    private float damage;
    private Shape shape;
    private Position position;
    private PathMovementStrategy movementStrategy;

    public Enemy(int x, int y, Shape shape, Path path) {
        this.position = new Position(x, y);
        this.shape = shape;
        this.movementStrategy = new PathMovementStrategy(path, 1.0f);
    }

    public boolean update() {
        movementStrategy.move(position);
        return movementStrategy.isFinished();
    }

    public Position getPosition() { return position; }
    public Shape getShape() { return shape; }
    public void setDamage(float damage) { this.damage = damage; }
    public void setHealth(float health) { this.health = health; }
    public void setSpeed(float speed) { }

    @Override
    public void draw(TextGraphics g) {
        g.setForegroundColor(TextColor.ANSI.RED);
        for (Pixel px : shape.getPixels()) {
            int x = (int)position.getX() + px.getDx();
            int y = (int)position.getY() + px.getDy();
            g.putString(x, y, String.valueOf(px.getChar()));
        }
    }
}