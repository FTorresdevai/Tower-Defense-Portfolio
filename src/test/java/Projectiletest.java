import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

class ProjectileTest {
    private Projectile projectile;
    private Enemy enemyMock;

    @BeforeEach
    void setup() {
        enemyMock = Mockito.mock(Enemy.class);
        projectile = new Projectile(10, 10, new Shape(), enemyMock);
        // Força a velocidade 2.0 para os testes matemáticos funcionarem
        projectile.setSpeed(2.0f);
    }

    @Test
    void testUpdateMove_Q1() {
        Mockito.when(enemyMock.getPosition()).thenReturn(new Position(20, 14));
        projectile.update();
        assertEquals(12.0f, projectile.getPosition().getX(), "The x should increase");
        assertEquals(12.0f, projectile.getPosition().getY(), "The y should increase");
    }

    @Test
    void testUpdateMove_Q3() {
        Mockito.when(enemyMock.getPosition()).thenReturn(new Position(8, 6));
        projectile.update();
        assertEquals(8.0f, projectile.getPosition().getX(), "The x should decrease");
        assertEquals(8.0f, projectile.getPosition().getY(), "The y should decrease");
    }

    @Test
    void testCollision_hit() {
        Mockito.when(enemyMock.getPosition()).thenReturn(new Position(10, 10.5f));
        boolean hasHit = false;

        projectile.update();
        if (projectile.hasHitTarget()) hasHit = true;

        assertTrue(hasHit, "Should hit target");
        assertFalse(projectile.isAlive(), "Should die after hitting");
    }
}