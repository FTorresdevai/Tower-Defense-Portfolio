import com.googlecode.lanterna.graphics.TextGraphics;

public interface GameObject {
    void draw(TextGraphics g);
    Shape getShape();
    Position getPosition();
}