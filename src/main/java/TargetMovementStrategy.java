public class TargetMovementStrategy implements MovementStrategy {
    private Enemy target;
    private float speed;

    public TargetMovementStrategy(Enemy target, float speed) {
        this.target = target;
        this.speed = speed;
    }

    @Override
    public void move(Position position) {
        Position tPos = target.getPosition();

        if (Math.abs(position.getX() - tPos.getX()) <= speed &&
                Math.abs(position.getY() - tPos.getY()) <= speed) {
            position.setX(tPos.getX());
            position.setY(tPos.getY());
            return;
        }

        if (position.getX() < tPos.getX()) position.setX(position.getX() + speed);
        else if (position.getX() > tPos.getX()) position.setX(position.getX() - speed);

        if (position.getY() < tPos.getY()) position.setY(position.getY() + speed);
        else if (position.getY() > tPos.getY()) position.setY(position.getY() - speed);
    }
}