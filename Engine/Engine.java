package Engine;


import javax.swing.*;
import java.awt.event.*;
import java.util.*;


public abstract class Engine extends JPanel implements KeyListener, ActionListener, ItemListener{
    protected static boolean run=true;
    public static Engine engine;
    public final static Set<Integer> pressed=new TreeSet<>();

    public static int width=1200;
    public static int height=800;
    static final double maxDelta=0.02091;
    public static double delta=0;
    static int sleepTime=0;

