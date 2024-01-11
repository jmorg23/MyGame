package src.Enviorment.puzzles.Childs;

import java.awt.Graphics2D;

import src.Enviorment.puzzles.Parents.Sensor;
import src.PlayerStuff.PlayerMain;

public class Key extends Sensor{

    //0 is default, any others will have unique numbers
    public int keyType = 0;

    
    public Key(String url, int x, int y) {
        super(url, x, y);
    }

    public void draw(Graphics2D g2){
        
       // g2.drawImage()

    }
    public void check(){
        if(checkContact(PlayerMain.x, PlayerMain.y, 10, 10)){

        }
    }
    
    
}
