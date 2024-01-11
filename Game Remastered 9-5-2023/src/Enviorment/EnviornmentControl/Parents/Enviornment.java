package src.Enviorment.EnviornmentControl.Parents;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.*;

import javax.imageio.ImageIO;

import src.Accessories.Imaging;
import src.Enviorment.EnviornmentControl.Enviornments;
import src.Enviorment.EnviornmentControl.Platforms;
import src.Enviorment.puzzles.Childs.Door;
import src.Enviorment.puzzles.Parents.ActivationObject;
import src.Enviorment.puzzles.Parents.Sensor;
import src.Main.Main;
import src.PlayerStuff.PlayerMain;

public class Enviornment extends JPanel {

    private boolean Rain = false;
    private static ArrayList<Sensor> sensors = new ArrayList<>();
    private static ArrayList<Platforms> platforms = new ArrayList<>();
    private static ArrayList<ActivationObject> actObjects = new ArrayList<>();
    private InputMap inputMap = getInputMap();
    private ActionMap actionMap = getActionMap();
    private boolean sRight = false;
    private boolean sLeft = false;
    private boolean visible = true;
    private ImageIcon gifIcon = new ImageIcon("res/gifs/rain2.gif");
    private Image originalImage = gifIcon.getImage();
    private PlayerMain player;
    private Image resizedImage = originalImage.getScaledInstance(1450, 1020, Image.SCALE_DEFAULT);
    private ImageIcon rain = new ImageIcon(resizedImage);
    private AffineTransform starySpecs = new AffineTransform();

    private BufferedImage[] images = new BufferedImage[10];
    private AffineTransform[] transformations = new AffineTransform[10];

    public static int keys = 0;
Font BFonts;
        Font customFont;

    public Enviornment(String backUrl) {
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/aAbstractGroovy.ttf"));
            BFonts = customFont.deriveFont(18f);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        keys();
        player = Main.player;
        try {
            images[0] = ImageIO.read(new File(backUrl));

        } catch (IOException e) {
            System.out.println(e);
        }

        starySpecs.scale(3, 3);
        starySpecs.translate(0, 0);
        requestFocus();
    }

    public static Platforms getCurPlat() {
        return platforms.get(0);
    }
    /*
     * 
     * public void scroll() {
     * if (right) {
     * platform.scroll(-speed);
     * }
     * if (left) {
     * platform.scroll(speed);
     * 
     * }
     * }
     */

    public boolean scroll(double platformSpeed) {
        if (sRight) {
            if (platforms.get(0).scroll(platformSpeed)) {
                actObjects.get(0).scroll(platformSpeed);
                return true;
            } else
                return false;
        } else if (sLeft) {
            if (platforms.get(0).scroll(-platformSpeed)) {
                actObjects.get(0).scroll(-platformSpeed);
                return true;
            } else
                return false;
        } else
            return false;

    }

    public void setVis(boolean b) {
        visible = b;
    }

    public BufferedImage[] getImages() {
        return images;
    }

    public AffineTransform[] getAff() {
        return transformations;
    }

    public boolean addImage(String URL) throws IOException {
        if (getNumImages() == images.length) {
            return false;
        } else
            images[getNumImages()] = ImageIO.read(new File(URL));

        return true;
    }

    public void addSensor(Sensor s) {
        sensors.add(s);

    }

    public void addPlatform(Platforms p) {
        platforms.add(p);

    }

    public void addActivationObject(ActivationObject a) {
        actObjects.add(a);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        draw((Graphics2D) g);
        // repaint();
    }

    public int getNumImages() {

        for (int i = 0; i < images.length; i++) {
            if (images[i] == null) {
                return i;
            }
        }
        return images.length;

    }

    public void addRain(boolean b) {
        Rain = b;
    }

    public void stopRain() {
        Rain = false;
    }

    public void draw(Graphics2D g2) {
        g2.setFont(BFonts);
        player.coll();

        if (visible) {
            g2.drawImage(Imaging.correct(images[0]), starySpecs, null);
            // platforms.get(0).draw(g2);
            player.draw(g2);

            if (Rain) {
                g2.drawImage(rain.getImage(), 0, 0, null);
            }

            platforms.get(0).draw(g2);

            // if (platforms.get(0) != null)
            // platforms.get(0).draw(g2);
            if (actObjects.get(0) instanceof Door) {
                ((Door) actObjects.get(0)).drawDoor(g2);
            }

        }

        g2.drawString(Enviornments.eTick.getInfo(), 50, 50);
        g2.drawString(Enviornments.timeOfDay + "", 1000, 50);

    }

    public void interact() {
        if (PlayerMain.x - actObjects.get(0).x > 10) {
            actObjects.get(0).Activated = true;
        }
    }

    public void keys() {
        Action escapeAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("ASdfasdfasdfasdfasdfasdfasdf");
            }
        };
        Action pressedAction = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("S") || e.getActionCommand().equals("s")) {
                    Main.isPressed("s");

                }
                if (e.getActionCommand().equals("W") || e.getActionCommand().equals("w")) {
                    Main.isPressed("w");

                }
                if (e.getActionCommand().equals("A") || e.getActionCommand().equals("a")) {
                    Main.isPressed("a");
                    sRight = true;

                }

                if (e.getActionCommand().equals("D") || e.getActionCommand().equals("d")) {
                    Main.isPressed("w");
                    sLeft = true;

                }
            }

        };
        // Create an Action object for the key released event
        Action releasedAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getActionCommand().equals("S") || e.getActionCommand().equals("s")) {
                    Main.isPressed("s");

                }
                if (e.getActionCommand().equals("W") || e.getActionCommand().equals("w")) {
                    Main.isPressed("w");

                }
                if (e.getActionCommand().equals("A") || e.getActionCommand().equals("a")) {
                    Main.isPressed("a");
                    sRight = false;

                }

                if (e.getActionCommand().equals("D") || e.getActionCommand().equals("d")) {
                    Main.isPressed("w");
                    sLeft = false;

                }
                if (e.getActionCommand().equals(" ") || e.getActionCommand().equals(" ")) {
                    Main.isPressed(" ");
                    player.isJumping = true;

                }
                if (e.getActionCommand().equals("E") || e.getActionCommand().equals("e")) {
                    Main.isPressed("e");
                    interact();
                }

            }

        };

        // Create KeyStroke objects for the desired keys
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        KeyStroke keyStrokeS1 = KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false);
        KeyStroke keyStrokeS2 = KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true);
        KeyStroke keyStrokeW1 = KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false);
        KeyStroke keyStrokeW2 = KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true);
        KeyStroke keyStrokeA1 = KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false);
        KeyStroke keyStrokeA2 = KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true);
        KeyStroke keyStrokeD1 = KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false);
        KeyStroke keyStrokeD2 = KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true);
        KeyStroke keyStrokeE1 = KeyStroke.getKeyStroke(KeyEvent.VK_E, 0, false);
        KeyStroke keyStrokeE2 = KeyStroke.getKeyStroke(KeyEvent.VK_E, 0, true);
        KeyStroke keyStrokeSPACE1 = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false);
        KeyStroke keyStrokeSPACE2 = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true);
        // Associate the key strokes with the action
        // Associate the key strokes with the actions
        inputMap.put(keyStrokeS1, "pressedAction");
        inputMap.put(keyStrokeS2, "releasedAction");
        inputMap.put(keyStrokeE1, "pressedAction");
        inputMap.put(keyStrokeE2, "releasedAction");
        inputMap.put(keyStrokeW1, "pressedAction");
        inputMap.put(keyStrokeW2, "releasedAction");
        inputMap.put(keyStrokeA1, "pressedAction");
        inputMap.put(keyStrokeA2, "releasedAction");
        inputMap.put(keyStrokeD1, "pressedAction");
        inputMap.put(keyStrokeD2, "releasedAction");
        inputMap.put(keyStrokeSPACE1, "pressedAction");
        inputMap.put(keyStrokeSPACE2, "releasedAction");
        actionMap.put("pressedAction", pressedAction);
        actionMap.put("releasedAction", releasedAction);
        inputMap.put(escapeKeyStroke, "escape");
        actionMap.put("escape", escapeAction);
        // Associate the key strokes with the actions

    }

}
