package src.PlayerStuff;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import src.Main.*;
import src.Accessories.ImageChange;
import src.Accessories.Imaging;
import src.Enviorment.EnviornmentControl.Platforms;
import src.Enviorment.EnviornmentControl.Parents.Enviornment;

public class PlayerMain {
    private final double targetSpeed = 30;

    public static double x = ControlPanel.frameW / 2;
    public static double y = ControlPanel.frameH / 2;
    private double targety = ControlPanel.frameH / 2;
    public boolean isJumping = false;
    private boolean colliding = false;
    private double speed = 10;
    private int area = 1;
    private double MaxSpeed;
    private BufferedImage player;
    private double fps;
    public boolean right = false;
    public boolean left = false;
    private double jumpHeight = 200;
    private boolean upOnJump = true;

    private void jump() {
        if (isJumping) {

            if (upOnJump)
                y -= speed;

            if (targety >= y) {
                isJumping = false;
                upOnJump = false;
            }

        } else {
            targety = y - jumpHeight;
            upOnJump = true;
        }

    }

    public PlayerMain(double fps) {
        fps = ControlPanel.fps;

        MaxSpeed = targetSpeed * 50;

        try {

            File inputFile = new File("res/im/playerTemp.png");
            BufferedImage originalImage = ImageIO.read(inputFile);

            // player = changeHue(originalImage, 0);

            player = ImageChange.adjustBrightness(originalImage, 1);

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public void changeInFps(double f) {
        fps = f;
        speed = MaxSpeed / fps;

    }

    public void setSpeed(double s) {
        speed = s;
    }

    public void updateArea(int a) {
        area = a;
    }

    boolean b = true;

    public void draw(Graphics2D g2) {
        jump();

        AffineTransform playerSpec = new AffineTransform();
        playerSpec.translate(x, y);
        playerSpec.translate(-player.getWidth() / 2, -player.getHeight() / 2); // center image on x,y pos

        g2.drawImage(Imaging.correct(player), playerSpec, null);
        g2.fillRect((int) (x), (int) (y + player.getHeight() / 2), 10, 10);

    }

    public void coll() {
        new Thread(()->{

        
        Platforms platform = Enviornment.getCurPlat();
        if (!isJumping) {
        try {
//System.out.println(platform.isColliding((int) (x), (int) ((y + player.getHeight() / 2))));
            if (platform.isColliding((int) (x), (int) ((y + player.getHeight() / 2)))) {
                colliding = true;
                while (platform.isColliding((int) (x), (int) (y + player.getHeight() / 2) - 5)) {
                    y -= 5;
                }

            } else {
                colliding = false;
                y += 10;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
         }
         }).start();
    }

}
