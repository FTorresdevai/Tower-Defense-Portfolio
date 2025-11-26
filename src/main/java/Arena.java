
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int width;
    private int height;
    private Path path;
    private List<Enemy> enemies = new ArrayList<>();
    private List<Projectile> projectiles = new ArrayList<>();
    private List<Tower> towers = new ArrayList<>();

    //construtor e getters
    public Arena(int width, int height) {
        this.width = width;
        this.height = height;

        createPath();
        createExampleTowers();
        createExampleEnemies();
    }
    private void createPath() {
        path = new Path();
        path.addNode(5, 10);
        path.addNode(30, 10);
        path.addNode(30, 25);
        path.addNode(100, 25); // EXIT
    }
    private void createExampleEnemies() {
        Shape enemyShape = new Shape();
        enemyShape.add(0, 0, 'o');

        enemies.add(new Enemy(5, 5, enemyShape,path));
    }
    private void createExampleTowers() {
        // Triangle tower example:
        Shape pyramid = new Shape();

// Top layer (1 wide)
        pyramid.add(0, -2, '^');

// Middle layer (3 wide)
        pyramid.add(-1, -1, '^');
        pyramid.add(0,  -1, '^');
        pyramid.add(1,  -1, '^');

// Bottom layer (5 wide)
        pyramid.add(-2, 0, '^');
        pyramid.add(-1, 0, '^');
        pyramid.add(0,  0, '^');
        pyramid.add(1,  0, '^');
        pyramid.add(2,  0, '^');



        towers.add(new Tower(40, 20, pyramid));
    }

    private void updateTowers() {
        Shape projShape = new Shape();
        projShape.add(0, 0, '*');
        for (Tower t : towers) {
            for (Enemy e : enemies) {
                if (t.isInRange(e.getPosition())) {
                    projectiles.add(new Projectile(
                            (int)t.getPosition().getX(),
                            (int)t.getPosition().getY(),
                            projShape,
                            e
                    ));
                    break; // one target at a time
                }
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    //atualiza o jogo

        //atualizar: inimigos, torres, projéteis, etc.
        public void update() {
            // update balloons
            enemies.removeIf(b -> b.update()); // removes escaped balloons

            // tower logic
            updateTowers();

            // projectiles
            for (Projectile p : projectiles) {
                boolean hit = p.update();
                if (hit) {
                    // destroy balloon on hit
                    enemies.remove(p.getTarget());
                }
            }

            projectiles.removeIf(p -> !p.isAlive());
        }



    //define o que faz cada "key" recebida (semelhante ao hero)
    void processKey(KeyStroke key) throws IOException {



    }

    public void draw(TextGraphics graphics) {
        // Fundo
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0),
                new TerminalSize(width, height), ' ');
        for (Tower t : towers) t.draw(graphics);
        for (Enemy b : enemies) b.draw(graphics);
        for (Projectile p : projectiles) p.draw(graphics);
        };
        // Cada elemento desenha-se igual ao hero(falta definir elementos)
        /*for (Tower tower : towers) tower.draw(graphics);
        for (Enemy enemy : enemies) enemy.draw(graphics);
        for (Projectile projectile : projectiles) projectile.draw(graphics);*/


    }


