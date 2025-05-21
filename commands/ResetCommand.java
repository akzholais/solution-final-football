package commands;

import Game.Main;

public class ResetCommand implements Command {
    private final Main game;

    public ResetCommand(Main game) {
        this.game = game;
    }

    @Override
    public void execute() {
        game.reset();
    }
}
