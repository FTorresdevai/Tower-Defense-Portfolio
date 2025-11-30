package game;

import game.model.Shape;
import game.model.Tower;
import game.model.factories.TowerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TowerFactorytest {

    private Tower tower;
    private int x;
    private int y;
    private Shape shape;

    @BeforeEach

    void setup(){
        x = 10;
        y = 10;

    }
    @Test
    void testCreateBasicTower(){
        tower = TowerFactory.createBasicTower(x,y);
        assertEquals(x,tower.getPosition().getX(),"The tower x should be the same as the x input");
        assertEquals(y,tower.getPosition().getY(),"The tower y should be the same as the y input");
        assertNotNull(tower,"The factory should return a tower");

        shape = tower.getShape();
        assertNotNull(shape,"The tower should have a shape");
    }

}
