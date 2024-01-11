package src.Enviorment.puzzles.Childs;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

import src.Accessories.Imaging;
import src.Enviorment.EnviornmentControl.Parents.Enviornment;
import src.Enviorment.puzzles.Parents.ActivationObject;

public class Door extends ActivationObject {

    private BufferedImage door;
    private BufferedImage openDoor;
    private boolean open = false;
    private boolean isLocked = true;

    //0 is default any others will connect with a certain key
    public int doorType = 0;

    public Door(String doorURL, int x, int y, double sx, double sy) {
        super(x, y, sx, sy, 0);

        try {
            File inputFile = new File(doorURL);

            door = ImageIO.read(inputFile);

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public Door(String openDoorURL, String closedDoorURL, int x, int y, double sx, double sy) {
        super(x, y, sx, sy, 0);

        try {
            File inputFile = new File(openDoorURL);
            File inputFile2 = new File(openDoorURL);

            door = ImageIO.read(inputFile);
            openDoor = ImageIO.read(inputFile2);

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void open(boolean b) {
        if((isLocked)&&(Enviornment.keys>1)){
        open = b;
        Enviornment.keys--;
        }else if(!isLocked)
        open = b;

    }

    public void drawDoor(Graphics2D g2) {
        if (!open)
            g2.drawImage(Imaging.correct(door), super.getPos(), null);
        else if (openDoor != null)
            g2.drawImage(Imaging.correct(door), super.getPos(), null);

        open = super.Activated;
        // else g2.drawImage(door, super.getPos(), null);
    }
}
