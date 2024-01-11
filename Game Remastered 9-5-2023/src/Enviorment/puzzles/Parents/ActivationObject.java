package src.Enviorment.puzzles.Parents;

import java.awt.geom.AffineTransform;

public class ActivationObject {
    public boolean Activated = false;
    public double sx,sy,rotation;
    public int x,y;
    public AffineTransform getPos(){
            AffineTransform obPos = new AffineTransform();

        obPos.translate((int)x,y);
        obPos.scale(sx,sy);
        //obPos.rotate(rotation);
        
        return obPos;
    }
    
    public ActivationObject(int ox, int oy, double osx, double osy, double rot){
        x=ox;
        y=oy;
        sx=osx;
        sy=osy;
        rotation = rot;
    }
    public void scroll(double speed){
        x+=speed;
    }
}
