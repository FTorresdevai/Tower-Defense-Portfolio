package game.model;

import game.model.strategies.PathMovementStrategy;

public class Enemy implements GameObject {
    private Shape shape;
    private Position position;
    private float speedModifier = 1.0f;
    private int slowDuration = 0;
    private PathMovementStrategy movementStrategy;
    private int bounty = 1;
    private int hp;
    private int maxHp;
    private boolean camo;

    public Enemy(int x, int y, Shape shape, Path path, int hp, boolean camo) {
        this.position = new Position(x, y);
        this.shape = shape;
        this.hp = hp;
        this.maxHp = hp;
        this.camo = camo;
        this.movementStrategy = new PathMovementStrategy(path, 0.15f);
    }

    public Position getPosition() { return position; }
    public Shape getShape() { return shape; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public boolean isDead() { return hp <= 0; }

    public boolean isCamo() { return camo; }

    public void takeDamage(int dmg) {
        hp -= dmg;
        if (hp < 0) hp = 0;
    }

    public void setSpeed(float speed) {
        this.movementStrategy.setSpeed(speed);
    }

    public void update() {
        movementStrategy.move(position,speedModifier);
        if (slowDuration > 0) {
            slowDuration--;
            if (slowDuration == 0) {
                speedModifier = 1.0f;
            }
        }
    }
    public void applySlow(float modifier, int duration) {
        speedModifier = modifier;
        slowDuration = duration;
    }

    public void setBounty(int b) { this.bounty = b; }
    public int getBounty() { return bounty; }

    public boolean hasReachedEnd() {
        return movementStrategy.isFinished();
    }

}