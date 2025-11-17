public class Tower implements GameObject {

    private Size size;
    private float cooldown;


    public Tower(Size size,int damage) {
        this.size = size;
    }

    @Override
    public void update(long now) {
        // lógica futura
    }

    @Override
    public void draw(com.googlecode.lanterna.graphics.TextGraphics graphics) {
        // desenho futuro
    }

    @Override
    public Size getSize() {
        return size;
    }

    public float getCooldown(){
        return cooldown;
    }

}
