import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ProjectileTest{

    private Projectile projectile;
    private Enemy enemyMock;

    @BeforeEach
    void setup(){
        enemyMock = Mockito.mock(Enemy.class);
        projectile = new Projectile(10,10,new Shape(),enemyMock);
    }

    @Test
    void testUpdateMove_Q1(){

        Mockito.when(enemyMock.getPosition()).thenReturn(new Position(20,14));

        projectile.update();

        assertEquals(12.0f,projectile.getPosition().getX(),"The x should increase by speed amount");
        assertEquals(12.0f,projectile.getPosition().getY(),"The y should increase by speed amount");

    }
    @Test
    void testUpdateMove_Q3(){

        Mockito.when(enemyMock.getPosition()).thenReturn(new Position(8,6));
        projectile.update();

        assertEquals(8.0f,projectile.getPosition().getX(),"The x should decrease by speed amount");
        assertEquals(8.0f,projectile.getPosition().getY(),"The y should decrease by speed amount");
    }

    @Test
    void TestCollision_hit(){
        Mockito.when(enemyMock.getPosition()).thenReturn(new Position(10,10.5f));
        boolean Hit = projectile.update();
        assertTrue(Hit,"The projectile hits the target, so the updtae() needs to return true");
        assertFalse(projectile.isAlive(), "Projectile should die after hitting");
    }

    @Test
    void TestCollision_nohit(){
        Mockito.when(enemyMock.getPosition()).thenReturn(new Position(15,13.5f));
        boolean Hit = projectile.update();
        assertFalse(Hit,"The projectile does not hit the target, so the updtae() needs to return false");
        assertTrue(projectile.isAlive(), "Projectile should not die after hitting");
    }

}