import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TowerTest {

    private Tower tower;

    @BeforeEach
    void Setup(){
       tower =  new Tower(10,10,new Shape());
    }

    @Test
    public void testIsInRange(){

        assertTrue(tower.isInRange(new Position(15,20)),"Enemy at position(15,20) should be in range");
        assertTrue(tower.isInRange(new Position(30,10)),"Enemy at position(15,20) should be at max range");
        assertFalse(tower.isInRange(new Position(50,10)),"Enemy at position(50,10) should be out of range");
    }

}