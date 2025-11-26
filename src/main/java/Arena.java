import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int width;
    private int height;
    private Path path;
    private List<Enemy> enemies = new ArrayList<>();
    private List<Projectile> projectiles = new ArrayList<>();
    private List<Tower> towers = new ArrayList<>();

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        createPath();
        createExampleTowers();
        createExampleEnemies();
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    private void createPath() {
        path = new Path();
        path.addNode(5, 5);
        path.addNode(20, 5);
        path.addNode(20, 15);
        path.addNode(60, 15);
    }

    private void createExampleEnemies() {
        Shape enemyShape = new Shape();
        enemyShape.add(0, 0, 'o');
        enemies.add(new Enemy(5, 5, enemyShape, path));
    }

    private void createExampleTowers() {
        Shape pyramid = new Shape();
        pyramid.add(0, -2, '^');
        pyramid.add(-1, -1, '^');
        pyramid.add(0, -1, '^');
        pyramid.add(1, -1, '^');
        pyramid.add(-2, 0, '^');
        pyramid.add(-1, 0, '^');
        pyramid.add(0, 0, '^');
        pyramid.add(1, 0, '^');
        pyramid.add(2, 0, '^');

        towers.add(new Tower(20, 8, pyramid));
    }

    public void update() {
        List<Enemy> enemiesToRemove = new ArrayList<>();
        for (Enemy e : enemies) {
            e.update();
            if (e.hasReachedEnd()) enemiesToRemove.add(e);
        }
        enemies.removeAll(enemiesToRemove);

        for (Tower t : towers) {
            t.tickCooldown();
            if (!t.canShoot()) continue;

            for (Enemy e : enemies) {
                if (t.isInRange(e.getPosition())) {
                    Shape projShape = new Shape();
                    projShape.add(0, 0, '*');
                    projectiles.add(new Projectile((int) t.getPosition().getX(), (int) t.getPosition().getY(), projShape, e));
                    t.resetCooldown();
                    break;
                }
            }
        }

        List<Projectile> projectilesToRemove = new ArrayList<>();
        List<Enemy> hitEnemies = new ArrayList<>();

        for (Projectile p : projectiles) {
            p.update();
            if (p.hasHitTarget()) {
                projectilesToRemove.add(p);
                hitEnemies.add(p.getTarget());
            } else if (!p.isAlive()) {
                projectilesToRemove.add(p);
            }
        }

        projectiles.removeAll(projectilesToRemove);
        enemies.removeAll(hitEnemies);
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        for (Tower t : towers) t.draw(graphics);
        for (Enemy e : enemies) e.draw(graphics);
        for (Projectile p : projectiles) p.draw(graphics);
    }
}