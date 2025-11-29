import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

class ProjectileFactoryTest {

    private int startX;
    private int startY;

    @BeforeEach
    void setup(){
        startX = 10;
        startY = 10;
    }

    @Test
    void testcreateBasicProjectile(){
        Enemy mockTarget = Mockito.mock(Enemy.class);
        Projectile projectile = ProjectileFactory.createBasicProjectile(startX,startY,mockTarget);

        assertNotNull(projectile,"Projectile should not be null");
        assertEquals(startX,projectile.getPosition().getX(),"Projectile x should be the same as the startX input");
        assertEquals(startX,projectile.getPosition().getX(),"Projectile x should be the same as the startX input");
        assertNotNull(projectile.getShape(),"Projectile should have a shape");
    }
}