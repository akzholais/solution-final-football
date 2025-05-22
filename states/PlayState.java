package states;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayState implements GameState {

    private final StateManager stateManager;

    public PlayState(StateManager manager) {
        this.stateManager = manager;
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 1200, 800);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.drawString("Тут идёт игра", 500, 400);
    }

    @Override
    public void onKeyPressed(KeyEvent e) {
    }
}

