package src.Main;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import src.Enviorment.EnviornmentControl.Enviornments;
import src.PlayerStuff.PlayerMain;



public class Main {
    private Enviornments enviornments;
    public static PlayerMain player;
    private int frameW = 1920;
    private int frameH = 1080;
    private int area = 1;
    private String lastInput;
    private ControlPanel panel;

    /*
     * pass in game speed - null, nothing yet
     * player speed - speed the side scrolling will be and the player in non-scrolling areas
     * enviornment speed - rate at which enemies and projectiles will move at(will be a multiple of it)
     * 
     */
    public Main(double gs, double ps, double es, ControlPanel p, int fps) {

        panel = p; 
        player = new PlayerMain(ps);
        enviornments = new Enviornments(fps, ps);

      

        setKeyBoard();

        updateArea();
    }

    protected void setSpeeds(double gs, double ps, double es) {
        player.setSpeed(ps);


    }

    private void updateArea() {
       // enviornments.updateArea(area);
    }

    public void MainDraw(Graphics2D g2) {
        Graphics g = (Graphics) g2;

        loading(g);


    }

    public void keyActions() {

    }



    private InputMap inputMap;
    private ActionMap actionMap;

    
    public void paintComponent(Graphics g) {

      //  super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (ControlPanel.gameDraw) {
            MainDraw(g2);
        }

        // repaint();

    }

    public void loading(Graphics g) {
        while (!load()) {
            g.setColor(Color.GREEN);
            g.fillRect(0, 0, frameW, frameH);

        }
    }

    public boolean load() {
        if (area == 1) {
            // platforms.load(area);
            // player.load(area);
            // backg.load(area);

        }
        return true;
    }

    public static ArrayList<String> keyBoard = new ArrayList<>();

    public void changeKeyboard(String s, String fs) {
        keyBoard.set(keyBoard.indexOf(s), fs);
    }

    public void setKeyBoard() {
        // index 0
        keyBoard.add("0");
        keyBoard.add("1");
        keyBoard.add("2");
        keyBoard.add("3");
        keyBoard.add("4");

        // index 5
        keyBoard.add("5");
        keyBoard.add("6");
        keyBoard.add("7");
        keyBoard.add("8");
        keyBoard.add("9");

        // index 10
        keyBoard.add("q");
        keyBoard.add("w");
        keyBoard.add("e");
        keyBoard.add("r");
        keyBoard.add("t");

        // index 15
        keyBoard.add("y");
        keyBoard.add("u");
        keyBoard.add("i");
        keyBoard.add("o");
        keyBoard.add("p");

        // index 20
        keyBoard.add("[");
        keyBoard.add("]");
        keyBoard.add("y");
        keyBoard.add("a");
        keyBoard.add("s");

        // index 25
        keyBoard.add("d");
        keyBoard.add("f");
        keyBoard.add("g");
        keyBoard.add("h");
        keyBoard.add("j");

        // index 30
        keyBoard.add("k");
        keyBoard.add("l");
        keyBoard.add(";");
        keyBoard.add("'");
        keyBoard.add("z");

        // index 35
        keyBoard.add("x");
        keyBoard.add("c");
        keyBoard.add("v");
        keyBoard.add("b");
        keyBoard.add("n");

        // index 40
        keyBoard.add("m");
        keyBoard.add(".");
        keyBoard.add("/");
        keyBoard.add(" ");
        keyBoard.add("LEFT");

        // index 45
        keyBoard.add("UP");
        keyBoard.add("RIGHT");
        keyBoard.add("DOWN");
        keyBoard.add("ESC");
        keyBoard.add("TAB");

        // index 50
        keyBoard.add("SHIFT");
        keyBoard.add("CTRL");
        keyBoard.add("-");
        keyBoard.add("=");
        keyBoard.add("`");

    }

    public static <t> void isPressed(t pressed) {
        // a
        if (pressed == keyBoard.get(23)) {
//            player.left = true;
            
        }
        // w
        if (pressed == keyBoard.get(11)) {

        }
        // s
        if (pressed == keyBoard.get(24)) {

        }
        // d or right
        if (pressed == keyBoard.get(25)) {
            //player.right = true;

        }
        // SPACE
        if (pressed == keyBoard.get(43)) {

        }
        // ESC
        if (pressed == keyBoard.get(48)) {

        }
        // m
        if (pressed == keyBoard.get(40)) {

        }
        // SHIFT
        if (pressed == keyBoard.get(50)) {

        }
        // e
        if (pressed == keyBoard.get(12)) {

        }
        if (pressed == keyBoard.get(45)) {
            System.out.println("up!!!");
        }
    }

    public void isReleased(String pressed) {

        if (pressed == "a") {
            player.left = false;

        }
        if (pressed == "w") {

        }
        if (pressed == "s") {

        }
        if (pressed == "d") {
            player.right = false;

        }
        if (pressed == " ") {
            player.isJumping = true;

        }
        if (pressed == "ESC") {
            panel.escape();
        }
        if (pressed == "m") {

        }
        if (pressed == "SHIFT") {

        }
        if (pressed == "e") {

        }

    }

    public void keys() {
        Action escapeAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                isReleased("ESC");
            }
        };
        // Create an Action object for the key pressed event
        Action pressedAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

                if (e.getActionCommand().equals("D") || e.getActionCommand().equals("d")) {
                    isPressed("d");
                }
                if (e.getActionCommand().equals("A") || e.getActionCommand().equals("a")) {
                    isPressed("a");
                }
                if (e.getActionCommand().equals("S") || e.getActionCommand().equals("s")) {
                    isPressed("s");
                }

            }
        };
        // Create an Action object for the key released event
        Action releasedAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals(" ")) {
                    isReleased(" ");
                    lastInput = " ";
                    System.out.println("space");
                }

                if (e.getActionCommand().equals("D") || e.getActionCommand().equals("d")) {
                    isReleased("d");
                    lastInput = "d";

                }
                if (e.getActionCommand().equals("A") || e.getActionCommand().equals("a")) {
                    isReleased("a");
                    lastInput = "a";

                }
                if (e.getActionCommand().equals("W") || e.getActionCommand().equals("w")) {
                    isReleased("w");
                    lastInput = "w";

                }
                if (e.getActionCommand().equals("L") || e.getActionCommand().equals("l")) {
                    isReleased("l");
                    lastInput = "l";

                }

                if (e.getActionCommand().equals("E") || e.getActionCommand().equals("e")) {
                    isReleased("e");
                    lastInput = "e";

                }

                if (e.getActionCommand().equals("S") || e.getActionCommand().equals("s")) {
                    isReleased("s");
                    lastInput = "s";

                }
                if (e.getActionCommand().equals("Q") || e.getActionCommand().equals("q")) {
                    isReleased("q");
                    lastInput = "q";

                }
                if (e.getActionCommand().equals("P")) {
                    isReleased("p");
                    lastInput = "p";

                }
            }

        };

        // Create KeyStroke objects for the desired keys
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);

        KeyStroke keyStrokeA1 = KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false);
        KeyStroke keyStrokeA2 = KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true);
        KeyStroke keyStrokeS1 = KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false);
        KeyStroke keyStrokeS2 = KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true);
        KeyStroke keyStrokeD1 = KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false);
        KeyStroke keyStrokeD2 = KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true);
        KeyStroke keyStrokeW1 = KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false);
        KeyStroke keyStrokeW2 = KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true);
        KeyStroke keyStrokeSPACE1 = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false);
        KeyStroke keyStrokeSPACE2 = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true);
        KeyStroke keyStrokeL2 = KeyStroke.getKeyStroke(KeyEvent.VK_L, 0, true);
        KeyStroke keyStrokeE2 = KeyStroke.getKeyStroke(KeyEvent.VK_E, 0, true);
        KeyStroke keyStrokeQ2 = KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0, true);
        KeyStroke keyStrokeP2 = KeyStroke.getKeyStroke(KeyEvent.VK_P, 0, true);

        // Associate the key strokes with the actions
        inputMap.put(keyStrokeA1, "pressedAction");
        inputMap.put(keyStrokeA2, "releasedAction");
        actionMap.put("pressedAction", pressedAction);
        actionMap.put("releasedAction", releasedAction);
        // Associate the key strokes with the actions
        inputMap.put(keyStrokeS1, "pressedAction");
        inputMap.put(keyStrokeS2, "releasedAction");
        actionMap.put("pressedAction", pressedAction);
        actionMap.put("releasedAction", releasedAction);
        // Associate the key strokes with the actions
        inputMap.put(keyStrokeD1, "pressedAction");
        inputMap.put(keyStrokeD2, "releasedAction");
        actionMap.put("pressedAction", pressedAction);
        actionMap.put("releasedAction", releasedAction);
        // Associate the key strokes with the actions
        inputMap.put(keyStrokeW1, "pressedAction");
        inputMap.put(keyStrokeW2, "releasedAction");
        actionMap.put("pressedAction", pressedAction);
        actionMap.put("releasedAction", releasedAction);
        // Associate the key strokes with the actions
        inputMap.put(keyStrokeSPACE1, "pressedAction");
        inputMap.put(keyStrokeSPACE2, "releasedAction");
        actionMap.put("pressedAction", pressedAction);
        actionMap.put("releasedAction", releasedAction);

        inputMap.put(keyStrokeE2, "releasedAction");
        actionMap.put("releasedAction", releasedAction);

        inputMap.put(keyStrokeL2, "releasedAction");
        actionMap.put("releasedAction", releasedAction);

        inputMap.put(keyStrokeQ2, "releasedAction");
        actionMap.put("releasedAction", releasedAction);

        inputMap.put(keyStrokeP2, "releasedAction");
        actionMap.put("releasedAction", releasedAction);
        inputMap.put(escapeKeyStroke, "escape");
        actionMap.put("escape", escapeAction);

    }

    public String nextInput() {
        String lpCon = lastInput;
        while (lastInput.equals(lpCon)){}
        return lastInput;
    }

}