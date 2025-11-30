package game;

import game.model.Path;
import game.model.Position;
import game.model.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PathTest {

    private Path path;
    private Shape singlePixel;


    @BeforeEach
    void setup() {


        path = new Path();
        path.addNode(5, 10);
        path.addNode(15, 10);
        path.addNode(15, 20);


        singlePixel = new Shape();
        singlePixel.add(0, 0, 'X');



    }


    @Test
    void shapeOnNodeShouldReturnTrue() {
        Position pos = new Position(5, 10);

        assertTrue(path.isOnPath(pos, singlePixel), "Shape exactly on a path node must return true");
    }


    @Test
    void shapeOnHorizontalSegmentShouldReturnTrue() {
        Position pos = new Position(10, 10);

        assertTrue(path.isOnPath(pos, singlePixel), "Shape on the horizontal segment should return true");
    }


    @Test
    void shapeOnVerticalSegmentShouldReturnTrue() {
        Position pos = new Position(15, 15);

        assertTrue(path.isOnPath(pos, singlePixel), "Shape on the vertical segment should return true");
    }


    @Test
    void shapeFarAwayShouldReturnFalse() {
        Position pos = new Position(50, 50);

        assertFalse(path.isOnPath(pos, singlePixel), "Shape far away from the path must return false");
    }


}