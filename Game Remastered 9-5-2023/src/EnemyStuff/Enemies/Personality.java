package src.EnemyStuff.Enemies;


import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import src.Main.ControlPanel;

public class Personality {
    public boolean flight = false;
    public int speed = 100;
    public int jumpHeight = 150;
    public int flightHeight;
    public int curVel;
    private double frameW = ControlPanel.screenSize.getWidth();
    private double frameH = ControlPanel.screenSize.getHeight();
    public double sx = 1 - 1536-frameW;
    public double sy = 1 - 1536-frameH;
    private double dir = 0;

    public int getCurVel() {
        return curVel;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int diff = 1;

    private double x = 0;
    private double y = 0;
    private AffineTransform position = new AffineTransform();
    private BufferedImage image;

    public Personality() {
        
    }

    public void initImage(BufferedImage i){
        image = i;
        
        y = frameH/3 - 10 + image.getHeight();
        x = frameW/5 - 20 + image.getWidth();


    }
    public AffineTransform getMovement() {
        if(flight){
        
        }
            
        if(diff == 1){
            
        }
        position.translate(x, y);
        position.scale(sx, sy);
        position.rotate(dir);
        return position;

    }
    
}
