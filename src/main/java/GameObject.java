import com.googlecode.lanterna.graphics.TextGraphics;
import java.awt.*; // só se precisares de cores ou posições (opcional)

public interface GameObject {

    // Atualiza o objeto (lógica, movimento, cooldown, etc)
    void update(long now);

    // Desenha o objeto na tela usando Lanterna
    void draw(TextGraphics graphics);

    // Retorna a lista de posições atual do objeto
    Size getSize();
}