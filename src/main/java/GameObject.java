import com.googlecode.lanterna.graphics.TextGraphics;
import java.awt.*; // só se precisares de cores ou posições (opcional)

public interface GameObject {



    // Desenha o objeto na tela usando Lanterna
    void draw(TextGraphics g);

    // Retorna a posição mais a esquerda e a lista dos pixels
     Shape getShape();
     Position getPosition();
}