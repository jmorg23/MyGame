package src.EnemyStuff.Enemies;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class UFO extends Personality{

    private AffineTransform ufoSpecs = new AffineTransform();
    private double scaleX = 1;
    private double scaleY = 1;
    String ufoURL = "res/im/enemies/en1.png";
    

    private boolean show = true;
    BufferedImage u;

    public UFO(int difficulty, int speed) {
                super();

        try {
        u = ImageIO.read(new File(ufoURL));
        super.initImage(u);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void setVis(Boolean b) {
        show = b;
    }

    public void draw(Graphics2D g2) {
        ufoSpecs.scale(scaleX, scaleY);

        if (show)
            g2.drawImage(u, super.getMovement(), null);
    }

}
