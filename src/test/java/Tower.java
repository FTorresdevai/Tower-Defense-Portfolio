import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TowerTest {
    private Tower tower;

    @BeforeEach
    void Setup() {
        tower = new Tower(10, 10, new Shape());
    }

    @Test
    public void testIsInRange() {
        assertTrue(tower.isInRange(new Position(15, 20)), "Enemy should be in range");
        assertTrue(tower.isInRange(new Position(30, 10)), "Enemy should be at max range");
        assertFalse(tower.isInRange(new Position(50, 10)), "Enemy should be out of range");
    }
}