import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Tower implements GameObject {

    private Position position;
    private Shape shape;
    private float cooldown;
    private int range;


    public Tower(int x, int y, Shape shape) {
        this.position = new Position(x, y);
        this.shape = shape;
    }

    public Position getPosition() { return position; }
    public Shape getShape() { return shape; }

    public float getCooldown(){
        return cooldown;
    }
    public boolean isInRange(Position balloonPos) {
        float dx = balloonPos.getX() - position.getX();
        float dy = balloonPos.getY() - position.getY();
        return dx*dx + dy*dy <= range*range;
    }




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




