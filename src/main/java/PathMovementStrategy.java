import java.util.List;

public class PathMovementStrategy implements MovementStrategy {
    private Path path;
    private float speed;
    private int currentNodeIndex = 0;

    public PathMovementStrategy(Path path, float speed) {
        this.path = path;
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

        if (hasReachedNode(position, targetNode)) {
            currentNodeIndex++;
        }
    }

    private boolean hasReachedNode(Position current, Position target) {
        return Math.abs(current.getX() - target.getX()) < 0.5 &&
                Math.abs(current.getY() - target.getY()) < 0.5;
    }

    public boolean isFinished() {
        return currentNodeIndex >= path.getNodes().size();
    }
}