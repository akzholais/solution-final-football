package commands;

import Game.Player;

import javax.swing.*;

public class ChangeNameCommand implements Command {
    private final Player player;
    private final JFrame frame;
    private final String prompt;

    public ChangeNameCommand(Player player, JFrame frame, String prompt) {
        this.player = player;
        this.frame = frame;
        this.prompt = prompt;
    }

    @Override
    public void execute() {
        String name = JOptionPane.showInputDialog(frame, prompt);
        if (name != null && !name.isEmpty()) {
            player.setName(name);
        }
    }
}
