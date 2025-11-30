package game;

import game.model.Enemy;
import game.model.Path;
import game.model.factories.EnemyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

class EnemyFactoryTest {

    private int startX;
    private int startY;

    @BeforeEach
    void setup(){
        startX = 10;
        startY = 10;

    }

    @Test
    void testCreateBasicEnemy() {
        Path mockPath = Mockito.mock(Path.class);
        Enemy enemy = EnemyFactory.createBasicEnemy(startX, startY, mockPath);
        assertNotNull(enemy,"Enemy should not be null");
        assertEquals(startX, enemy.getPosition().getX(),"Enemy x should be the same as the startX input");
        assertEquals(startY, enemy.getPosition().getY(),"Enemy Y should be the same as the startY input");
        assertNotNull(enemy.getShape(),"Enemy should have a shape");
    }
}