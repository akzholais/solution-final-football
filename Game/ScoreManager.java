package Game;

public class ScoreManager {
    private static final ScoreManager instance = new ScoreManager();

    private int t1Score = 0;
    private int t2Score = 0;

    private ScoreManager() {}

    public static ScoreManager getInstance() {
        return instance;
    }

    public void addGoalForPlayer1() {
        t1Score++;
    }

    public void addGoalForPlayer2() {
        t2Score++;
    }

    public int getPlayer1Score() {
        return t1Score;
    }

    public int getPlayer2Score() {
        return t2Score;
    }

    public String getScoreText(String name1, String name2) {
        return name1 + " " + t1Score + "  |  " + name2 + " " + t2Score;
    }
}
