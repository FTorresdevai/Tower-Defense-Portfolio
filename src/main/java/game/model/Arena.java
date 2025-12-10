package game.model;

import game.model.factories.ProjectileFactory;
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
    private int gold = 1500;
    private int lives = 1;
    private int wave = 1;
    private final List<Observer> observers = new ArrayList<>();
    private EnemySpawner spawner;
    private boolean waitingForPlayer = true;



    public Arena(int width, int height, Path path) {
        this.width = width;
        this.height = height;
        this.path = path;
        this.spawner = new EnemySpawner(this);
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
    private boolean waitingNextWave = false;
    private int waveDelayTimer = 0;


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

    public boolean isWaitingForPlayer() {
        return waitingForPlayer;
    }

    private void explodeAOE(Enemy center) {
        int radius = 3;

        for (Enemy e : new ArrayList<>(enemies)) {
            float dx = e.getPosition().getX() - center.getPosition().getX();
            float dy = e.getPosition().getY() - center.getPosition().getY();

            if (dx*dx + dy*dy <= radius * radius) {
                e.takeDamage(3);
                if (e.isDead()) {
                    enemies.remove(e);
                    gold += 5;
                    notifyObservers();
                }

            }
        }
    }
    private void chainLightning(Enemy firstTarget) {

        firstTarget.takeDamage(2);

        if (firstTarget.isDead()) {
            enemies.remove(firstTarget);
            gold += 5;
        }

        int chains = 3;
        float chainRange = 5;

        Enemy source = firstTarget;

        for (int i = 0; i < chains; i++) {
            Enemy next = findClosestEnemy(source, chainRange);
            if (next == null) break;

            next.takeDamage(2);

            if (next.isDead()) {
                enemies.remove(next);
                gold += 5;
            }

            source = next;
        }

        notifyObservers();
    }

    private Enemy findClosestEnemy(Enemy from, float range) {
        Enemy best = null;
        float bestDist = Float.MAX_VALUE;

        for (Enemy e : enemies) {
            if (e == from) continue;

            float dx = e.getPosition().getX() - from.getPosition().getX();
            float dy = e.getPosition().getY() - from.getPosition().getY();
            float dist = dx*dx + dy*dy;

            if (dist < bestDist && dist <= range * range) {
                bestDist = dist;
                best = e;
            }
        }
        return best;
    }


    public void update() {
        if (waitingForPlayer) {
            return;
        }
        spawner.update();
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

        boolean waveFinished = spawner.hasFinishedSpawning() && enemies.isEmpty();

        if (waveFinished && !waitingNextWave) {
            waitingNextWave = true;
            waveDelayTimer = 0;
        }

        if (waitingNextWave) {
            waveDelayTimer++;

            if (waveDelayTimer >= 100) {
                wave++;
                waitingNextWave = false;
                waitingForPlayer = true;
            }

            return;
        }

        for (Tower t : towers) {
            t.tickCooldown();
            if (!t.canShoot()) continue;

            for (Enemy e : enemies) {
                if(e.isCamo() && !t.canSeeCamo()) continue;

                if (t.isInRange(e.getPosition())) {
                    Projectile p = ProjectileFactory.createBasicProjectile(
                            (int) t.getPosition().getX(),
                            (int) t.getPosition().getY(),
                            e
                    );
                    p.setDamage(t.getDamage());
                    p.setTowerType(t.getTowerType());
                    projectiles.add(p);
                    t.resetCooldown();
                    break;
                }
            }
        }

        List<Projectile> projectilesToRemove = new ArrayList<>();
        List<Enemy> deadEnemiesToRemove = new ArrayList<>();

        for (Projectile p : projectiles) {
            p.update();
            if (p.hasHitTarget()) {
                Enemy target = p.getTarget();
                if (p.getTowerType().equals("bomb")) {
                    explodeAOE(target);
                }
                else if (p.getTowerType().equals("tesla")) {
                    chainLightning(target);
                }
                else if (p.getTowerType().equals("frost")) {
                    target.applySlow(0.5f,50);
                }
                else {
                    target.takeDamage(p.getDamage());


                    if (target.isDead()) {
                        deadEnemiesToRemove.add(target);
                        gold += target.getBounty();
                        notifyObservers();
                    }
                }
                projectilesToRemove.add(p);
            } else if (!p.isAlive()) {
                projectilesToRemove.add(p);
            }
        }

        projectiles.removeAll(projectilesToRemove);
        enemies.removeAll(deadEnemiesToRemove);

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
        Shape temp = new Shape();
        temp.add(0, 0, ' ');
        return isPlaceable(x, y, temp);
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

    public void playerStartsWave() {
        waitingForPlayer = false;
        waitingNextWave = false;
        waveDelayTimer = 0;
        spawner.startWave(wave);
    }
}