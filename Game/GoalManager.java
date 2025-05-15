package Game;

import Engine.TimedEvent;

public class GoalManager implements BallObserver {
    @Override
    public void onGoalScored(String team) {
        if (team.equals("Player1")) {
            Main.goal = false;
            Main.trDirek.color = Main.player2.color;
            Main.brDirek.color = Main.player2.color;
            ScoreManager.getInstance().addGoalForPlayer1();
        } else {
            Main.goal = false;
            Main.tlDirek.color = Main.player1.color;
            Main.blDirek.color = Main.player1.color;
            ScoreManager.getInstance().addGoalForPlayer2();
        }

        Main.engine.repaint();

        new TimedEvent(1500) {
            public void run() {
                super.run();
                Main.engine.reset();
            }
        }.start();
    }
}