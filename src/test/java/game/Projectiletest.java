package game;

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
        projectile.setSpeed(2.0f);
    }

    @Test
    void testUpdateMove_Right() {
        Mockito.when(enemyMock.getPosition()).thenReturn(new Position(20, 10));
        projectile.update();
        assertEquals(12.0f, projectile.getPosition().getX(), 0.01);
        assertEquals(10.0f, projectile.getPosition().getY(), 0.01);
    }

    @Test
    void testUpdateMove_Left() {
        Mockito.when(enemyMock.getPosition()).thenReturn(new Position(0, 10));
        projectile.update();
        assertEquals(8.0f, projectile.getPosition().getX(), 0.01);
        assertEquals(10.0f, projectile.getPosition().getY(), 0.01);
    }

    @Test
    void testUpdateMove_Up() {
        Mockito.when(enemyMock.getPosition()).thenReturn(new Position(10, 0));
        projectile.update();
        assertEquals(10.0f, projectile.getPosition().getX(), 0.01);
        assertEquals(8.0f, projectile.getPosition().getY(), 0.01);
    }

    @Test
    void testUpdateMove_Down() {
        Mockito.when(enemyMock.getPosition()).thenReturn(new Position(10, 20));
        projectile.update();
        assertEquals(10.0f, projectile.getPosition().getX(), 0.01);
        assertEquals(12.0f, projectile.getPosition().getY(), 0.01);
    }

    @Test
    void testUpdateMove_Diagonal_UpRight() {
        Mockito.when(enemyMock.getPosition()).thenReturn(new Position(20, 20));

        projectile.update();
        assertEquals(11.414f, projectile.getPosition().getX(), 0.01, "X should increase by ~1.41");
        assertEquals(11.414f, projectile.getPosition().getY(), 0.01, "Y should increase by ~1.41");
    }

    @Test
    void testUpdateMove_Diagonal_DownLeft() {
        Mockito.when(enemyMock.getPosition()).thenReturn(new Position(0, 0));

        projectile.update();

        assertEquals(8.586f, projectile.getPosition().getX(), 0.01, "X should decrease");
        assertEquals(8.586f, projectile.getPosition().getY(), 0.01, "Y should decrease");
    }

    @Test
    void testUpdateMove_Diagonal_DownRight() {
        Mockito.when(enemyMock.getPosition()).thenReturn(new Position(20, 0));

        projectile.update();

        assertEquals(11.414f, projectile.getPosition().getX(), 0.01, "X should increase");
        assertEquals(8.586f, projectile.getPosition().getY(), 0.01, "Y should decrease");
    }

    @Test
    void testUpdateMove_Diagonal_UpLeft() {
        Mockito.when(enemyMock.getPosition()).thenReturn(new Position(0, 20));

        projectile.update();

        assertEquals(8.586f, projectile.getPosition().getX(), 0.01, "X should decrease");
        assertEquals(11.414f, projectile.getPosition().getY(), 0.01, "Y should increase");
    }


    @Test
    void testCollision_Hit() {
        Mockito.when(enemyMock.getPosition()).thenReturn(new Position(10, 10.5f));
        projectile.update();
        assertTrue(projectile.hasHitTarget());
        assertFalse(projectile.isAlive());
    }

    @Test
    void testCollision_Miss() {
        Mockito.when(enemyMock.getPosition()).thenReturn(new Position(20, 20));
        projectile.update();
        assertFalse(projectile.hasHitTarget());
        assertTrue(projectile.isAlive());
    }
}