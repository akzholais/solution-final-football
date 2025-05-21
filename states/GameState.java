package states;

import java.awt.*;
import java.awt.event.KeyEvent;

public interface GameState {
    void update();
    void render(Graphics g);
    void onKeyPressed(KeyEvent e);
}
