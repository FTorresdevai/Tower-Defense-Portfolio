import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PathMovementStrategyTest {

    private PathMovementStrategy strategy;
    private Path path;
    private float speed;

    @BeforeEach
    void setup() {
        speed = 1.0f;
        path = new Path();
    }

    @Test
    void testMoveAlongXAxis() {
        path.addNode(10, 0);
        strategy = new PathMovementStrategy(path, speed);
        Position currentPos = new Position(0, 0);

        strategy.move(currentPos);

        assertEquals(1.0f, currentPos.getX(), 0.01, "Should move +1 on X axis");
        assertEquals(0.0f, currentPos.getY(), 0.01, "Should not move on Y axis");
    }

    @Test
    void testMoveAlongYAxis() {
        path.addNode(0, 10);
        strategy = new PathMovementStrategy(path, speed);
        Position currentPos = new Position(0, 0);

        strategy.move(currentPos);

        assertEquals(0.0f, currentPos.getX(), 0.01, "Should not move on X axis");
        assertEquals(1.0f, currentPos.getY(), 0.01, "Should move +1 on Y axis");
    }

    @Test
    void testMoveDiagonalUpRight() {
        path.addNode(10, 10);
        strategy = new PathMovementStrategy(path, speed);
        Position currentPos = new Position(0, 0);

        strategy.move(currentPos);

        assertEquals(1.0f, currentPos.getX(), 0.01, "Should move +1 on X axis");
        assertEquals(1.0f, currentPos.getY(), 0.01, "Should move +1 on Y axis");
    }

    @Test
    void testMoveDiagonalDownLeft() {
        path.addNode(0, 0);
        strategy = new PathMovementStrategy(path, speed);
        Position currentPos = new Position(10, 10);

        strategy.move(currentPos);

        assertEquals(9.0f, currentPos.getX(), 0.01, "Should move -1 on X axis");
        assertEquals(9.0f, currentPos.getY(), 0.01, "Should move -1 on Y axis");
    }

    @Test
    void testUnevenMovement() {
        path.addNode(2, 10);
        strategy = new PathMovementStrategy(path, speed);
        Position currentPos = new Position(2, 0);

        strategy.move(currentPos);

        assertEquals(2.0f, currentPos.getX(), 0.01, "X is already at target, should not change");
        assertEquals(1.0f, currentPos.getY(), 0.01, "Y should move closer to target");
    }

    @Test
    void testSwitchToNextNode() {
        path.addNode(10, 10);
        strategy = new PathMovementStrategy(path, speed);
        Position currentPos = new Position(9.5f, 9.5f);

        strategy.move(currentPos);

        assertEquals(10.0f, currentPos.getX(), 0.01, "Should snap to node X");
        assertEquals(10.0f, currentPos.getY(), 0.01, "Should snap to node Y");
        assertTrue(strategy.isFinished(), "Strategy should be finished");
    }

    @Test
    void testFinishedPath() {
        path.addNode(10, 10);
        strategy = new PathMovementStrategy(path, speed);
        Position currentPos = new Position(9.5f, 10);

        strategy.move(currentPos);

        assertTrue(strategy.isFinished(), "Strategy should be finished after reaching last node");
    }
}