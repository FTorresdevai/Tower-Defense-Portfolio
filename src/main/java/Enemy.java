import com.googlecode.lanterna.graphics.TextGraphics;

public class Enemy implements GameObject{

    private float health;
    private float damage;
    private float speed;

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    @Override
    public void update(long now) {

    }

    @Override
    public void draw(TextGraphics graphics) {

    }

    @Override
    public Size getSize() {
        return null;
    }
}

