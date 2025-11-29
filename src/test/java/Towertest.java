import game.Position;
import game.Shape;
import game.Tower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TowerTest {
    private Tower tower;
    private Shape shape;
    private float cooldown;
    private float maxcooldown;

    @BeforeEach
    void Setup() {
        shape = new Shape();
        tower = new Tower(54, 13, shape);
        cooldown = 0;
        maxcooldown = 30;
    }
    @Test
    public void testIsInRange() {
        assertTrue(tower.isInRange(new Position(50, 13)), "game.Enemy should be in range");
        assertTrue(tower.isInRange(new Position(54, 19)), "game.Enemy should be at max range");
        assertFalse(tower.isInRange(new Position(61, 13)), "game.Enemy should be out of range");
    }

    @Test
    public void testShape(){
        assertEquals(shape,tower.getShape());
    }

    @Test
    public void testCanshoot_yes(){
        assertTrue(tower.canShoot(),"game.Tower should be able to shoot");
    }
    @Test
    public void testCanshoot_no(){
        tower.setCooldown(1);
        assertFalse(tower.canShoot(),"Should return false");
    }
    @Test
    public void testTickCooldown(){
        tower.setCooldown(3);
        tower.tickCooldown();
        assertEquals(2.0f, tower.getCooldown(),"Tick did not properly decrease");
    }

    @Test
    public void testresetCooldown(){
        tower.setCooldown(3);
        tower.resetCooldown();
        assertEquals(tower.getCooldown(), tower.getMaxCooldown(),"Cooldown did not reset");
    }

}

