package game;

public class TargetMovementStrategy implements MovementStrategy {
    private Enemy target;
    private float speed;

    public TargetMovementStrategy(Enemy target, float speed) {
        this.target = target;
        this.speed = speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public void move(Position position) {
        if (target == null) return;
        Position tPos = target.getPosition();

        float dx = tPos.getX() - position.getX();
        float dy = tPos.getY() - position.getY();
        float dist = (float) Math.sqrt(dx * dx + dy * dy);

        if (dist <= speed) {
            position.setX(tPos.getX());
            position.setY(tPos.getY());
        } else {
            position.setX(position.getX() + (dx / dist) * speed);
            position.setY(position.getY() + (dy / dist) * speed);
        }
    }
}