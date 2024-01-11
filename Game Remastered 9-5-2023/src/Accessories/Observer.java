package src.Accessories;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Observer implements ImageObserver {
       private  Observer observer = new Observer();

       public Observer(){

       }
    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        if ((infoflags & ImageObserver.ALLBITS) != 0) {
            // The image has been fully loaded.
            System.out.println("Image fully loaded.");
            return false; // Stop observing
        }
        return true; // Continue observing
    }

    public boolean prepareImage(BufferedImage img){
       // = Toolkit.getDefaultToolkit().getImage("path_to_image.png");
        boolean loaded = Toolkit.getDefaultToolkit().prepareImage(img, -1, -1, observer);
        
        if (loaded) {
            // The image was already fully loaded.
            System.out.println("Image fully loaded.");
            return true;
        }
        return false;

    }

    public boolean premareMultipleImages(BufferedImage[] images){
        for(int i =0; i<images.length-1; i++){
        boolean loaded = Toolkit.getDefaultToolkit().prepareImage(images[i], -1, -1, observer);
        if (!loaded) {
            return false;
        }
        }
        
            return true;
        
    }




    
}
