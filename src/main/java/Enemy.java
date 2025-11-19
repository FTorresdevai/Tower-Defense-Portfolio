import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Enemy implements GameObject{

    private float health;
    private float damage;
    private float speed = 1;
    private int currentnode = 0;
    private Shape shape;
    private Position position;
    private Path path;

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setHealth(float health) {
        this.health = health;
    }
    public Enemy(int x, int y, Shape shape, Path path) {
        this.position = new Position(x, y);
        this.shape = shape;
        this.path = path;
    }
    public Position getPosition() { return position; }
    public Shape getShape() { return shape; }


    public boolean update() {
        if (currentnode >= path.getNodes().size()) return true; // reached end

        Position target = path.getNodes().get(currentnode);

        // move towards target
        if (position.getX() < target.getX()) position.setX(position.getX() + speed);
        if (position.getX() > target.getX()) position.setX(position.getX() - speed);
        if (position.getY() < target.getY()) position.setY(position.getY() + speed);
        if (position.getY() > target.getY()) position.setY(position.getY() - speed);

        // check if arrived to target node
        if (position.getX() == target.getX() && position.getY() == target.getY())
            currentnode++;

        return false; // not escaped
    }

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




