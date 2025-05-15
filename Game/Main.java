package Game;

import Engine.Engine;
import Engine.TimedEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Engine {
    static Player[] players;
    static Player player1;
    static Player player2;
    public static String name1 = "Ansar";
    public static String name2 = "Akzhol";
    static Ball ball;
    static Shapes topBound;
    static Shapes botBound;
    static Shapes leftBound1;
    static Shapes leftBound2;
    static Shapes rightBound1;
    static Shapes rightBound2;
    static Direk tlDirek;
    static Direk blDirek;
    static Direk trDirek;
    static Direk brDirek;
    static boolean goal = true;
    static byte t1Score = 0;
    static byte t2Score = 0;
    private JMenuItem m12;//reset
    private JMenuItem m13;
    private JMenuItem m14;
    private JMenuItem m15;
    private JMenuItem m16;
    private JMenuItem m17;
    private JMenuItem m18;
    private JMenuItem m19;
    private JMenuItem m110;
    static byte arti1t = 0;
    static byte arti2t = 0;

    static ArrayList<Mutator> mutators = new ArrayList<>();
    static ArrayList<Mutator> mutatorsToRemove = new ArrayList<>();


    private Main() {
        super();
        variables.addAll(new ArrayList<String>(Arrays.asList("name1", "name2")));
        System.out.println("sub created");

        players = new Player[2];
        players[0] = new Player(name1, width / 3, height / 2, true);
        player1 = players[0];
        player1.color = Color.RED;
        players[1] = new Player(name2, width * 2 / 3, height / 2, false);
        player2 = players[1];
        player2.color = Color.MAGENTA;

        ball = new Ball();
        ball.color = Color.cyan;
        ball.addObserver(new GoalManager());

        tlDirek = new Direk(true, true);
        blDirek = new Direk(true, false);
        trDirek = new Direk(false, true);
        brDirek = new Direk(false, false);
        topBound = new Shapes(50, 20, 1100, 10);
        botBound = new Shapes(50, 770, 1100, 10);
        leftBound1 = new Shapes(50, 20, 10, -1);
        leftBound2 = new Shapes(50, -1, 10, -1);
        rightBound1 = new Shapes(1140, 20, 10, -1);
        rightBound2 = new Shapes(1140, -1, 10, -1);
        player1.setGoalSize(160);
        player2.setGoalSize(160);

        for (Player p : players) p.enem = p.enemy();

        player1.left = 'A';
        player1.right = 'D';
        player1.up = 'W';
        player1.down = 'S';
        player2.left = KeyEvent.VK_LEFT;
        player2.right = KeyEvent.VK_RIGHT;
        player2.up = KeyEvent.VK_UP;
        player2.down = KeyEvent.VK_DOWN;


        System.out.println("objects initialized");
    }

    public void resolutions() {
        resolutions.add("1200*800");
        resolutions.add("900*600");
    }

    public static void main(String[] args) {
        System.out.println("Main main() started");
        engine = new Main();
        engine.run();
    }

    static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.abs((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)));
    }

    static double distance(Circly p1, Circly p2) {
        return Math.sqrt(Math.abs((p1.xPos - p2.xPos) * (p1.xPos - p2.xPos) + (p1.yPos - p2.yPos) * (p1.yPos - p2.yPos)));
    }

    public void initialize() {
        super.initialize();
        reset();
        for (String s : variables) System.out.println(s);
        new TimedEvent(13000) {
            @Override
            public void run() {
                while (run) {
                    super.run();
                    mutators.add(MutatorFactory.createRandomMutator());
                }
            }
        }.start();
    }

    public void gameCodes() {
        for (Direk d : Direk.direkler) {
            if (distance(d, ball) <= (d.radius + ball.radius)) {
                d.color = d.teamColor;
                new TimedEvent(200) {
                    public void run() {
                        super.run();
                        if (goal) d.color = Direk.defColor;
                    }
                }.start();
                ball.hit(d);
            }
            for (Mutator m : mutators)
                if (distance(d, m) <= (d.radius + m.radius)) m.hit(d);
        }

        for (Player p : players) {
            if (distance(p, ball) <= (p.radius + ball.radius)) {
                ball.hit(p);
            }
            for (Mutator m : mutators) {
                if (distance(p, m) <= (p.radius + m.radius)) {
                    m.hit(p);
                }
            }
            p.move();
        }

        ball.move();

        if (ball.xPos >= 1150 && goal) {
            ball.notifyGoalScored("Player1");
        } else if (ball.xPos <= 50 && goal) {
            ball.notifyGoalScored("Player2");
        }

        for (Mutator m : mutators) {
            m.move();
            if (m.xPos >= 1150 && goal) {
                m.activate(false);
            } else if (m.xPos <= 50 && goal) {
                m.activate(true);
            }
        }

        for (Mutator m : mutatorsToRemove) {
            mutators.remove(m);
        }
    }


    public void menuBar() {
        m12 = new JMenuItem("Player 1 name");
        m12.addActionListener(this);
        menu1.add(m12);
        m13 = new JMenuItem("Player 2 name");
        m13.addActionListener(this);
        menu1.add(m13);
        m14 = new JMenuItem("Player 1 color");
        m14.addActionListener(this);
        menu1.add(m14);
        m15 = new JMenuItem("Player 2 color");
        m15.addActionListener(this);
        menu1.add(m15);
        m16 = new JMenuItem("Send bot to:");
        m16.addActionListener(this);
        menu1.add(m16);
        m17 = new JMenuItem("Send bot1 to random location");
        m17.addActionListener(this);
        menu1.add(m17);
        m18 = new JMenuItem("Bot1 hit the ball");
        m18.addActionListener(this);
        menu1.add(m18);
        m19 = new JMenuItem("Set player1 goalsize:");
        m19.addActionListener(this);
        menu1.add(m19);
        m110 = new JMenuItem("Set player2 goalsize:");
        m110.addActionListener(this);
        menu1.add(m110);
    }

    protected void actions(ActionEvent e) {
        if ((e.getSource()) == m12) {
            String s = (String) JOptionPane.showInputDialog(
                    frame,
                    "Enter name",
                    "Player 1",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    name1);
            if (s.length() > 0 && s.length() < 12) {
                name1 = s;
            }
        } else if ((e.getSource()) == m13) {
            String s = (String) JOptionPane.showInputDialog(
                    frame,
                    "Enter name",
                    "Player 2",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    name2);
            if (s.length() > 0 && s.length() < 12) {
                name2 = s;
            }
        } else if ((e.getSource()) == m14) {
            String s = (String) JOptionPane.showInputDialog(
                    frame,
                    "Select color",
                    "Player 1",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    new String[]{"Red", "Blue", "Orange"},
                    name1);
            if (s.equals("Red")) {
                players[0].setColor(Color.red);
            } else if (s.equals("Blue")) {
                players[0].setColor(Color.blue);
            } else if (s.equals("Orange")) {
                players[0].setColor(Color.orange);
            }
        } else if ((e.getSource()) == m15) {
            String s = (String) JOptionPane.showInputDialog(
                    frame,
                    "Select color",
                    "Player 2",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    new String[]{"Purple", "Green", "Pink"},
                    name1);
            if (s.equals("Purple")) {
                players[1].setColor(Color.magenta);
            } else if (s.equals("Green")) {
                players[1].setColor(Color.green);
            } else if (s.equals("Pink")) {
                players[1].setColor(Color.pink);
            }
        } else if (e.getSource() == m16) {

        } else if (e.getSource() == m19) {
            String input = (String) JOptionPane.showInputDialog(
                    frame,
                    "Enter goalsize",
                    "Player 1",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    player1.goalsize
            );

            if (input != null && !input.isEmpty()) {
                int newSize = Integer.parseInt(input);
                player1.setGoalSize(newSize);
                SettingsManager.getInstance().setDefaultGoalSize(newSize);
            }

        } else if (e.getSource() == m110) {
            String input = (String) JOptionPane.showInputDialog(
                    frame,
                    "Enter goalsize",
                    "Player 2",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    player2.goalsize
            );

            if (input != null && !input.isEmpty()) {
                int newSize = Integer.parseInt(input);
                player2.setGoalSize(newSize);
                SettingsManager.getInstance().setDefaultGoalSize(newSize);
            }
        }

    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (showStats) {
            g.drawString("p1 speed: " + Math.sqrt(players[0].xSpeed * players[0].xSpeed + players[0].ySpeed * players[0].ySpeed), 5, 20);
            g.drawString("p1 x: " + players[0].xSpeed + " y: " + players[0].ySpeed, 5, 30);
            g.drawString("p2 speed: " + Math.sqrt(players[1].xSpeed * players[1].xSpeed + players[1].ySpeed * players[1].ySpeed), 5, 40);
            g.drawString("ball speed: " + Math.sqrt(ball.xSpeed * ball.xSpeed + ball.ySpeed * ball.ySpeed), 5, 50);
        }
        topBound.draw(g);
        botBound.draw(g);
        leftBound1.draw(g);
        rightBound1.draw(g);
        leftBound2.draw(g);
        rightBound2.draw(g);
        tlDirek.draw(g);
        blDirek.draw(g);
        trDirek.draw(g);
        brDirek.draw(g);
        player1.draw(g);
        player2.draw(g);
        ball.draw(g);
        for (Mutator m : mutators) m.draw(g);

        g.drawString(name1 + " " + ScoreManager.getInstance().getPlayer1Score(), getWidth() / 3, 12);
        g.drawString(name2 + " " + ScoreManager.getInstance().getPlayer2Score(), getWidth() * 2 / 3, 12);
    }

    public void reset() {
        int i = 1;
        for (Player p : players) {
            p.xSpeed = 0;
            p.i = 0;
            p.ySpeed = 0;
            p.j = 0;
            p.xPos = i * 400;
            p.yPos = 400;
            i++;
            p.setGoalSize(SettingsManager.getInstance().getDefaultGoalSize());
        }
        ball.xPos = 600;
        ball.yPos = 400;
        ball.xSpeed = 0;
        ball.ySpeed = 0;
        tlDirek.color = Color.white;
        blDirek.color = Color.white;
        trDirek.color = Color.white;
        brDirek.color = Color.white;
        goal = true;

        mutators.clear();
    }

    private void score(boolean team1) {
        goal = false;
        if (team1) {
            trDirek.color = players[1].color;
            brDirek.color = players[1].color;
            t1Score++;
        } else {
            tlDirek.color = players[0].color;
            blDirek.color = players[0].color;
            t2Score++;
        }
        new TimedEvent(1500) {
            public void run() {
                super.run();
                reset();
            }
        }.start();
    }
}
