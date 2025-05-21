package states;

import java.awt.*;
import java.awt.event.KeyEvent;

public class StateManager {

    private GameState currentState;

    public void setState(GameState state) {
        this.currentState = state;
    }

    public void update() {
        if (currentState != null) currentState.update();
    }

    public void render(Graphics g) {
        if (currentState != null) currentState.render(g);
    }

    public void keyPressed(KeyEvent e) {
        if (currentState != null) currentState.onKeyPressed(e);
    }
}
