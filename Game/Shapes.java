package Game;

import java.awt.*;

class Shapes extends Rectangle {
    private int highlightTimer = 0;

    Shapes(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    void triggerHighlight() {
        highlightTimer = 10;
    }

    void draw(Graphics g) {
        if (highlightTimer > 0) {
            g.setColor(Color.YELLOW);
            highlightTimer--;
        } else {
            g.setColor(Color.WHITE);
        }
        g.fillRect(Main.engine.scaleX(x), Main.engine.scaleY(y),
                Main.engine.scaleSizeX(width), Main.engine.scaleSizeY(height));
    }
}
