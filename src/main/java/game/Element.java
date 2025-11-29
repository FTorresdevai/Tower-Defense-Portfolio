package game;

import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Element {
    private Position pos;

    public Element(Position position) { this.pos = position; }
    public Position getPosition() { return pos; }
    public void setPosition(Position position) { this.pos= position; }

    public abstract void draw(TextGraphics graphics);
}
