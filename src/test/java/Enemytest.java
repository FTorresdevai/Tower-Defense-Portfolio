import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EnemyTest {

    private Enemy enemy;
    private Path path;

    @BeforeEach
    public void setUp() {
        path = new Path();
        path.addNode(10, 10);

        Shape shape = new Shape();

        enemy = new Enemy(0, 10, shape, path);
        enemy.setSpeed(1);
    }

    @Test
    public void testMoveTowardsTarget() {
        enemy.update();

        assertEquals(1.0f, enemy.getPosition().getX(), "Enemy X should have moved 1 unit");
        assertEquals(10.0f, enemy.getPosition().getY(), "Enemy Y position should remain the same");
    }
}