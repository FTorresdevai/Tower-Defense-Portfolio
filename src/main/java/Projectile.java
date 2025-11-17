import com.googlecode.lanterna.graphics.TextGraphics;

public class Projectile implements GameObject{

    private Size size;
    private float damage;
    private float speed;

    public void Projectile(Size size, float damage, float speed){
        this.size = size;
        this.damage = damage;
        this.speed = speed;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setSize(Size size) {
        this.size = size;
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
