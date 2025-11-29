package game;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Tower implements GameObject {
    private Position position;
    private Shape shape;
    private int range;
    private float cooldown = 0;
    private final float maxCooldown = 30;

    public Tower(int x, int y, Shape shape) {
        this.position = new Position(x, y);
        this.shape = shape;
        this.range = 6;
    }

    public Position getPosition() { return position; }
    public Shape getShape() { return shape; }
    public float getCooldown(){
        return cooldown;
    }
    public float getMaxCooldown(){
        return maxCooldown;
    }
    public void setCooldown(float cooldown) {
        this.cooldown = cooldown;
    }
    public boolean canShoot() { return cooldown <= 0; }
    public void tickCooldown() { if (cooldown > 0) cooldown--; }
    public void resetCooldown() { cooldown = maxCooldown; }

    public boolean isInRange(Position targetPos) {
        float dx = targetPos.getX() - position.getX();
        float dy = targetPos.getY() - position.getY();
        return dx * dx + dy * dy <= range * range;
    }

    @Override
    public void draw(TextGraphics g) {
        g.setForegroundColor(TextColor.ANSI.WHITE);
        for (Pixel px : shape.getPixels()) {
            int x = (int) position.getX() + px.getDx();
            int y = (int) position.getY() + px.getDy();
            g.putString(x, y, String.valueOf(px.getChar()));
        }
    }
}

