package states;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState implements GameState {

    private final StateManager stateManager;

    public MenuState(StateManager manager) {
        this.stateManager = manager;
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1200, 800);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("Football Game", 420, 200);

        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.drawString("1. Играть", 520, 300);
        g.drawString("2. Настройки", 520, 350);
        g.drawString("3. Выйти", 520, 400);
    }

    @Override
    public void onKeyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_1 -> stateManager.setState(new PlayState(stateManager));
            case KeyEvent.VK_2 -> System.out.println("Открыть настройки...");
            case KeyEvent.VK_3 -> System.exit(0);
        }
    }
}
