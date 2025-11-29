import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PixelTest {

    private int dx, dy;
    private char c;
    private Pixel pixel;

    @BeforeEach
    void setup(){
        dx = 2;
        dy = 2;
        c = 'c';
    }

    @Test
    void Pixeltest(){
        pixel = new Pixel(dx,dy,c);
        assertNotNull(pixel, "Pixel should not be null");
        assertEquals(dx, pixel.getDx(), "Pixel dx should be the same as dx input");
        assertEquals(dy, pixel.getDy(), "Pixel dy should be the same as dy input");
        assertEquals(c, pixel.getChar(), "Pixel c should be the same as c input");
    }
}
