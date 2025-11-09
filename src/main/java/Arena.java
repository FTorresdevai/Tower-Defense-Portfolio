import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class Arena {
    private int width;
    private int height;

    //construtor e getters
    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    //atualiza o jogo
    public void update() {
        //atualizar: inimigos, torres, projéteis, etc.
        System.out.println("a atualizar");
    }

    //define o que faz cada "key" recebida (semelhante ao hero)
    void processKey(KeyStroke key) throws IOException {
        /*switch (key.getKeyType()) {
            case ... :

            default:
                break;
        }*/
    }

    public void draw(TextGraphics graphics) {
        // Fundo
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0),
                new TerminalSize(width, height), ' ');

        // Cada elemento desenha-se igual ao hero(falta definir elementos)
        /*for (Tower tower : towers) tower.draw(graphics);
        for (Enemy enemy : enemies) enemy.draw(graphics);
        for (Projectile projectile : projectiles) projectile.draw(graphics);*/
    }
}

