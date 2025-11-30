package game;

import game.model.Enemy;
import game.model.Position;
import game.model.strategies.TargetMovementStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

class TargetMovementStrategyTest {

    private TargetMovementStrategy strategy;
    private Enemy mockTarget;
    private float speed;

    @BeforeEach
    void setup() {
        speed = 2.0f;
        mockTarget = Mockito.mock(Enemy.class);
        strategy = new TargetMovementStrategy(mockTarget, speed);
    }

    @Test
    void testMoveTowardsTarget() {
        Mockito.when(mockTarget.getPosition()).thenReturn(new Position(10, 0));
        Position currentPos = new Position(0, 0);

        strategy.move(currentPos);

        assertEquals(2.0f, currentPos.getX(), 0.01, "Position X should increase by speed towards target");
        assertEquals(0.0f, currentPos.getY(), 0.01, "Position Y should remain 0");
    }

    @Test
    void testReachTargetWithinSpeed() {
        Mockito.when(mockTarget.getPosition()).thenReturn(new Position(1.5f, 0));
        Position currentPos = new Position(0, 0);

        strategy.move(currentPos);

        assertEquals(1.5f, currentPos.getX(), 0.01, "Position X should snap to target position");
        assertEquals(0.0f, currentPos.getY(), 0.01, "Position Y should snap to target position");
    }

    @Test
    void testNullTarget() {
        strategy = new TargetMovementStrategy(null, speed);
        Position currentPos = new Position(0, 0);

        strategy.move(currentPos);

        assertEquals(0.0f, currentPos.getX(), "Position should not change if target is null");
        assertEquals(0.0f, currentPos.getY(), "Position should not change if target is null");
    }
}