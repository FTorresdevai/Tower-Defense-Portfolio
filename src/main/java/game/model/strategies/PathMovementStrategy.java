package game.model.strategies;

import game.model.Path;
import game.model.Position;

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
    public void move(Position position, float speedModifier) {
        if (isFinished()) return;
        Position targetNode = path.getNodes().get(currentNodeIndex);
        float step = speed * speedModifier;

        if (position.getX() < targetNode.getX()) position.setX(position.getX() + step);
        if (position.getX() > targetNode.getX()) position.setX(position.getX() - step);
        if (position.getY() < targetNode.getY()) position.setY(position.getY() + step);
        if (position.getY() > targetNode.getY()) position.setY(position.getY() - step);

        if (Math.abs(position.getX() - targetNode.getX()) < 1.0 && Math.abs(position.getY() - targetNode.getY()) < 1.0) {
            position.setX(targetNode.getX());
            position.setY(targetNode.getY());
            currentNodeIndex++;
        }
    }

    public boolean isFinished() {
        return currentNodeIndex >= path.getNodes().size();
    }
}