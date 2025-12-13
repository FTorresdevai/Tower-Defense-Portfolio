package game.model;

import com.googlecode.lanterna.TextColor;
import game.model.strategies.TargetMovementStrategy;

public class Projectile implements GameObject {
    private Position position;
    private Enemy target;
    private boolean alive = true;
    private boolean hitTarget = false;
    private Shape shape;
    private TargetMovementStrategy movementStrategy;
    private int damage = 1;
    private String towerType = "basic";
    private float speedModifier = 1.0f;
    private TextColor projectileColor = TextColor.ANSI.WHITE;

    public Projectile(int x, int y, Shape shape, Enemy target) {
        this.position = new Position(x, y);
        this.shape = shape;
        this.target = target;
        this.movementStrategy = new TargetMovementStrategy(target, 0.4f);
    }

    public Position getPosition() { return position; }
    public Shape getShape() { return shape; }
    public Enemy getTarget() { return target; }
    public void setDamage(int dmg) { this.damage = dmg; }
    public int getDamage() { return damage; }
    public void setTowerType(String type) { this.towerType = type; }
    public String getTowerType() { return towerType; }
    public void setProjectileColor(TextColor color) {
        this.projectileColor = color;
    }
    public TextColor getProjectileColor() {
        return projectileColor;
    }

    public boolean isAlive() { return alive; }
    public boolean hasHitTarget() { return hitTarget; }

    public void setSpeed(float speed) {
        this.movementStrategy.setSpeed(speed);
    }

    public void update() {
        if (!alive) return;
        movementStrategy.move(position,speedModifier);

        Position tPos = target.getPosition();
        if (Math.abs(position.getX() - tPos.getX()) < 1.0 && Math.abs(position.getY() - tPos.getY()) < 1.0) {
            alive = false;
            hitTarget = true;
        }
    }
}