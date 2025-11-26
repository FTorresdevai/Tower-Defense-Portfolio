public class PathMovementStrategy implements MovementStrategy {
    private Path path;
    private float speed;
    private int currentNodeIndex = 0;

    public PathMovementStrategy(Path path, float speed) {
        this.path = path;
        this.speed = speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public void move(Position position) {
        if (isFinished()) return;
        Position targetNode = path.getNodes().get(currentNodeIndex);

        if (position.getX() < targetNode.getX()) position.setX(position.getX() + speed);
        if (position.getX() > targetNode.getX()) position.setX(position.getX() - speed);
        if (position.getY() < targetNode.getY()) position.setY(position.getY() + speed);
        if (position.getY() > targetNode.getY()) position.setY(position.getY() - speed);

        if (Math.abs(position.getX() - targetNode.getX()) < 1.0 && Math.abs(position.getY() - targetNode.getY()) < 1.0) {
            currentNodeIndex++;
        }
    }

    public boolean isFinished() {
        return currentNodeIndex >= path.getNodes().size();
    }
}