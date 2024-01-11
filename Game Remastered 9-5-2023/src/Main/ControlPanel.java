package src.Main;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.*;

import src.Accessories.Inputs.Keyboard;
import src.Accessories.Sounds.*;

public class ControlPanel extends JPanel {
    private String introURL = "res/gifs/youtube-video-gif.gif";
    private static JFrame frame = new JFrame("Game");

    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    // Set the JFrame's size to match the screen's size

    public static int frameW = screenSize.width;
    public static int frameH = screenSize.height;

    // percentages
    private int gameSpeed = 100;
    private int playerSpeed = 10;
    private int enviormentSpeed = 100;
    public static int fps = 60;

    // private int gameVolume = 100;
    // private boolean controllerShake = true;
    private boolean fullScreen = true;

    private static ControlPanel pan;
    public static boolean gameDraw = true;

    // private void setSpeed(int gs, int ps, int es){
    // main.setSpeeds(gs, ps, es);
    // }

    /*
     * SETTINGS
     * game speed
     * player speed
     * fullscreen
     * resolution
     * controls-vibration
     * sensitivity
     * controller
     * mouse range when with controller
     * audio master vol
     * max fps
     * autosave
     * account
     * 
     * 
     */

    Main main;
    Thread introThread;

    private JButton skipIntro;
    private JButton settings;
    private JButton play;
    // private JButton multiplayer;
    // private JButton back;
    // private JButton leaveGame;
    // private JButton audio;
    private JButton maxFps;
    // private JButton controllerSettings;
    // private JButton resolution;
    // private JButton fullscreen;
    private JButton keyb;

    private ImageIcon x;
    private ImageIcon rx;
    private ImageIcon ry;
    private ImageIcon ra;
    private ImageIcon rb;
    ImageIcon introGif = new ImageIcon(introURL);
    private Font BFonts;

    private boolean escaped = true;

    public void escape() {

        if (!escaped) {
            setVisible(true);
            // main.setVisible(false);
        } else if (escaped) {
            // setVisible(false);
            // main.setVisible(true);
        }

    }

    public ControlPanel() {
        System.out.println("Frame Width = " + screenSize.getWidth() + "  and Frame Height = " + screenSize.getHeight());
        if (fullScreen)
            fullScreen();
        initGraphics();
        StartScreen();
        frame.addKeyListener(new Keyboard(main));
        try {

            BufferedImage resizedImagex = new BufferedImage(25, 25, BufferedImage.TYPE_INT_ARGB);
            BufferedImage resizedImagey = new BufferedImage(25, 25, BufferedImage.TYPE_INT_ARGB);
            BufferedImage resizedImagea = new BufferedImage(25, 25, BufferedImage.TYPE_INT_ARGB);
            BufferedImage resizedImageb = new BufferedImage(25, 25, BufferedImage.TYPE_INT_ARGB);

            ImageIcon y = new ImageIcon("res/im/xboxButtons/xbox y.png");
            Image yoi = y.getImage();
            resizedImagey.getGraphics().drawImage(yoi, 0, 0, 25, 25, null);
            ry = new ImageIcon(resizedImagey);

            ImageIcon a = new ImageIcon("res/im/xboxButtons/xbox a.png");
            Image aoi = a.getImage();
            resizedImagea.getGraphics().drawImage(aoi, 0, 0, 25, 25, null);
            ra = new ImageIcon(resizedImagea);

            ImageIcon b = new ImageIcon("res/im/xboxButtons/xbox b.png");
            Image boi = b.getImage();
            resizedImageb.getGraphics().drawImage(boi, 0, 0, 25, 25, null);
            rb = new ImageIcon(resizedImageb);

            x = new ImageIcon("res/im/xboxButtons/xbox x.png");
            Image oi = x.getImage();
            resizedImagex.getGraphics().drawImage(oi, 0, 0, 25, 25, null);
            rx = new ImageIcon(resizedImagex);

            File fontFile = new File("res/fonts/aAbstractGroovy.ttf");
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            BFonts = customFont.deriveFont(18f);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void main(String[] args) {
        pan = new ControlPanel();

    }

    public void StartScreen() {
        PlaySound.playSound("res/mus/gd.mp3");
        // main.setVisible(false);
        // intro.setVisible(true);
        Tim timer = new Tim(10);
        timer.start();
        skipIntro.setVisible(true);
        introThread = new Thread(() -> {
            while (true) {
                if (timer.timerCompleted()) {
                    // intro.setVisible(false);

                    skipIntro.setVisible(false);
                    settings.setVisible(true);
                    play.setVisible(true);

                    break;
                }
            }

        });

        introThread.start();

    }

    private boolean loadingLevel = false;

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        AffineTransform t = new AffineTransform();
        t.translate(0, 0);
        t.scale(5, 5);
        g2.drawImage(introGif.getImage(), t, null);

        if (loadingLevel)
            g2.drawImage(new ImageIcon("res/im/loadingTemp.gif").getImage(), 400, 400, null);

        repaint();
    }

    public void initButtons() {
        JLabel iconX = new JLabel(rx);
        iconX.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30)); // Adjust the padding as needed

        JLabel iconY = new JLabel(ry);
        iconY.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));

        JLabel iconB = new JLabel(rb);
        iconB.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));

        JLabel iconA = new JLabel(ra);
        iconA.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));

        skipIntro = new JButton("Skip Intro", ra);
        skipIntro.setLayout(new BorderLayout());

        // Add the label to the WEST (left) position of the button
        skipIntro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == skipIntro) {
                    // intro.setVisible(false);

                    skipIntro.setVisible(false);
                    settings.setVisible(true);
                    play.setVisible(true);
                }

            }
        });

        skipIntro.setFont(BFonts);
        skipIntro.setBounds(800, 600, 200, 40);
        skipIntro.setVisible(false);
        skipIntro.add(iconX, BorderLayout.WEST);

        settings = new JButton("Settings", ra);

        settings.setLayout(new BorderLayout());

        // Add the label to the WEST (left) position of the button
        settings.add(iconX, BorderLayout.WEST);
        settings.setRolloverEnabled(true);
        settings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == settings) {
                    settings.setVisible(false);
                    maxFps.setVisible(true);

                }

            }
        });

        settings.setFont(BFonts);
        settings.setBounds(400, 200, 200, 40);
        settings.setVisible(false);

        play = new JButton("play", ra);
        play.setLayout(new BorderLayout());

        // Add the label to the WEST (left) position of the button
        play.add(iconX, BorderLayout.WEST);
        play.setRolloverEnabled(true);
        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == play) {
                   // new Thread(() -> {
                        loadingLevel = true;
                        play.setVisible(false);
                        settings.setVisible(false);
                        main = new Main(gameSpeed, playerSpeed, enviormentSpeed, pan, fps);
                        removePanel();
                    //}).start();
                }

            }
        });

        play.setFont(BFonts);
        play.setBounds(400, 120, 200, 40);
        play.setVisible(false);

        ImageIcon fpsIcon = new ImageIcon("res/im/maxFpsText.png");
        JLabel fpsIm = new JLabel(fpsIcon);
        fpsIm.setBounds(200, 200, 200, 200);

        String fStr = new String((String.valueOf(fps)));
        JLabel fpsStr = new JLabel(fStr);
        fpsStr.setBounds(450, 200, 200, 200);

        maxFps = new JButton("maxFps", ra);
        maxFps.setLayout(new BorderLayout());

        add(fpsIm);
        add(fpsStr);

        // Add the label to the WEST (left) position of the button
        // maxFps.add(iconX, BorderLayout.WEST);

        keyb = new JButton("keyb", ra);
        keyb.setLayout(new BorderLayout());

        // Add the label to the WEST (left) position of the button
        keyb.add(iconX, BorderLayout.WEST);
        keyb.setRolloverEnabled(true);
        keyb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == keyb) {
                    keyb.setVisible(false);
                    keyBoardSettings();

                }

            }
        });

        keyb.setFont(BFonts);
        keyb.setBounds(800, 120, 200, 40);
        keyb.setVisible(true);
        // add(keyb);
    }

    public void removePanel() {
        setVisible(false);
    }

    public void initGraphics() {

        this.setLayout(null);
        frame.setSize(frameW, frameH);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initButtons();
        this.add(skipIntro);
        this.add(settings);
        this.add(play);
        // this.add(maxFps);
        // this.add(intro);
        frame.add(this);

    }

    String toButton = "";

    public void keyBoardSettings() {
        // right or d
        JButton right = new JButton(Main.keyBoard.get(25));
        right.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == right) {
                    toButton = main.nextInput();

                }
            }
        });
        right.setBounds(800, 800, 150, 50);
        right.setVisible(true);
        add(right);

    }

    public static void addComp(JPanel pan) {
        frame.add(pan);
    }

    public void fullScreen() {
        SwingUtilities.invokeLater(() -> {
            // Create a JFrame
            // frame.setUndecorated(true); // Remove window decorations

            // Set the program to full screen
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gd = ge.getDefaultScreenDevice();
            gd.setFullScreenWindow(frame);
        });
    }
}
