package game.model;

import game.model.factories.EnemyFactory;
import game.model.factories.ProjectileFactory;
import game.model.factories.TowerFactory;
import java.util.ArrayList;
import java.util.List;

public class Arena implements Subject {
    private int width;
    private int height;
    private Path path;
    private List<Enemy> enemies = new ArrayList<>();
    private List<Projectile> projectiles = new ArrayList<>();
    private List<Tower> towers = new ArrayList<>();
    private int cursorX = 10;
    private int cursorY = 10;
    private int gold = 100;
    private int lives = 10;
    private int wave = 1;
    private final List<Observer> observers = new ArrayList<>();

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        createPath();
        createExampleTowers();
        createExampleEnemies();
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getCursorX() { return cursorX; }
    public int getCursorY() { return cursorY; }
    public List<Observer> getObservers() { return observers; }
    public List<Tower> getTowers() { return towers; }
    public List<Projectile> getProjectiles() { return projectiles; }
    public List<Enemy>  getEnemies() { return enemies; }
    public Path getPath() { return path; }
    public int getGold() { return gold; }
    public int getLives() { return lives; }
    public int getWave() { return wave; }

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }

    public void startNextWave() {
        wave++;
        notifyObservers();
    }

    private void createPath() {
        path = new Path();
        path.addNode(0, 5);
        path.addNode(20, 5);
        path.addNode(20, 15);
        path.addNode(60, 15);
        path.addNode(60, 10);
        path.addNode(75, 10);
        path.addNode(79, 10);
    }

    private void createExampleEnemies() {
        enemies.add(EnemyFactory.createBasicEnemy(0, 5, path));
    }

    private void createExampleTowers() {
        towers.add(TowerFactory.createBasicTower(54, 13));
    }

    public void update() {
        List<Enemy> enemiesToRemove = new ArrayList<>();
        for (Enemy e : enemies) {
            e.update();
            if (e.hasReachedEnd()) enemiesToRemove.add(e);
        }
        enemies.removeAll(enemiesToRemove);

        if (!enemiesToRemove.isEmpty()) {
            lives -= enemiesToRemove.size();
            notifyObservers();
        }

        for (Tower t : towers) {
            t.tickCooldown();
            if (!t.canShoot()) continue;

            for (Enemy e : enemies) {
                if (t.isInRange(e.getPosition())) {
                    projectiles.add(ProjectileFactory.createBasicProjectile(
                            (int) t.getPosition().getX(),
                            (int) t.getPosition().getY(),
                            e
                    ));
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

        if (!hitEnemies.isEmpty()) {
            gold += 5;
            notifyObservers();
        }
    }

    public void moveCursorUp() {
        cursorY = Math.max(0, cursorY - 1);
    }

    public void moveCursorDown() {
        cursorY = Math.min(height - 1, cursorY + 1);
    }

    public void moveCursorLeft() {
        cursorX = Math.max(0, cursorX - 1);
    }

    public void moveCursorRight() {
        cursorX = Math.min(width - 1, cursorX + 1);
    }

    public boolean isGameOver() {
        return lives <= 0;
    }

    public void addTower(Tower t){
        towers.add(t);
    }

    public void removeGold(int cost) {
        if (gold >= cost) {
            gold -= cost;
            notifyObservers();
        }
    }

    public boolean isPlaceable(int x, int y) {
        Position pos = new Position(x, y);
        Shape dummyShape = new Shape();
        dummyShape.getPixels().add(new Pixel(0,0,' '));

        if (path.isOnPath(pos, dummyShape)) return false;

        for (Tower t : towers) {
            if ((int)t.getPosition().getX() == x && (int)t.getPosition().getY() == y) return false;
        }
        return true;
    }

    public boolean isPlaceable(int x, int y, Shape shape) {
        Position pos = new Position(x, y);

        if (path.isOnPath(pos, shape)) return false;

        for (Tower t : towers) {
            if (shapesCollide(pos, shape, t.getPosition(), t.getShape())) {
                return false;
            }
        }
        return true;
    }

    private boolean shapesCollide(Position pos1, Shape shape1, Position pos2, Shape shape2) {
        for (Pixel p1 : shape1.getPixels()) {
            int x1 = (int) pos1.getX() + p1.getDx();
            int y1 = (int) pos1.getY() + p1.getDy();

            for (Pixel p2 : shape2.getPixels()) {
                int x2 = (int) pos2.getX() + p2.getDx();
                int y2 = (int) pos2.getY() + p2.getDy();

                if (x1 == x2 && y1 == y2) return true;
            }
        }
        return false;
    }
}