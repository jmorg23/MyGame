package src.Enviorment.puzzles.Parents;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


public class Sensor {
    private double x, y;
    private double sx, sy;
    private double rotation;
    private boolean contact;
    private boolean visible = true;
    private AffineTransform sensorPos = new AffineTransform();
    private BufferedImage image;
    
    public Sensor(String url, int x, int y) {

    }
    public static void setAll(){

    }
    

    public void setSensor() {

    }
    
    public void setVisible(boolean b){
        visible = b;
    }

    public AffineTransform getPos(){
        sensorPos.translate(x,y);
        sensorPos.scale(sx,sy);
        sensorPos.rotate(rotation);
        
        return sensorPos;
    }

    public boolean checkContact(double ox, double oy, int xtolerance, int ytolerance) {
        if ((ox + xtolerance > x - xtolerance) && (ox - xtolerance < x + xtolerance)) {
            if ((oy + ytolerance > y - ytolerance) && (oy + ytolerance < x + ytolerance)) {
                contact = true;
                return contact;
            }
        }
        return contact;
    }
}
