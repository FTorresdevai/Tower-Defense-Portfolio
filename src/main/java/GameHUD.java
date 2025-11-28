import com.googlecode.lanterna.graphics.TextGraphics;

public class GameHUD implements Observer {

    private final Arena arena;

    private int gold;
    private int lives;
    private int wave;

    public GameHUD(Arena arena) {
        this.arena = arena;
        this.arena.attach(this);
        update();
    }

    @Override
    public void update() {
        gold = arena.getGold();
        lives = arena.getLives();
        wave = arena.getWave();
    }

    public void draw(TextGraphics g) {
        g.putString(2, 0, "Gold: " + gold);
        g.putString(15, 0, "Lives: " + lives);
        g.putString(30, 0, "Wave: " + wave);
    }
}