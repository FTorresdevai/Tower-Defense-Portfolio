package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {
    private Enemy enemy;
    private Path path;
    private Shape shape;

    @BeforeEach
    public void setUp() {
        path = new Path();
        path.addNode(10, 10);
        shape = new Shape();
        enemy = new Enemy(0, 10, shape, path);
        enemy.setSpeed(1.0f);
    }

    @Test
    public void testMoveTowardsTarget() {
        enemy.update();
        assertEquals(1.0f, enemy.getPosition().getX(), "game.Enemy X should have moved 1 unit");
        assertEquals(10.0f, enemy.getPosition().getY(), "game.Enemy Y position should remain the same");
    }

    @Test
    public void testHasreachend_1node(){
        enemy.getPosition().setX(9.0f);
        enemy.update();

        assertEquals(10.0f, enemy.getPosition().getX());
        assertTrue(enemy.hasReachedEnd(), "game.Enemy should have ended");

    }

    @Test
    public void testHasreachend_2node(){
        path.addNode(11,10);
        enemy.getPosition().setX(9.0f);
        enemy.update();

        assertEquals(10.0f, enemy.getPosition().getX());
        assertFalse(enemy.hasReachedEnd(), "game.Enemy should not have ended");
        enemy.update();
        assertTrue(enemy.hasReachedEnd(), "game.Enemy should have ended");

    }

    @Test
    public void testShape(){
        assertEquals(shape,enemy.getShape());
    }
}