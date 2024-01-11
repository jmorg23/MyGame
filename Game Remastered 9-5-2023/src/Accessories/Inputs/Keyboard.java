package src.Accessories.Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import src.Main.Main;

public class Keyboard implements KeyListener {
    Main main;
    public Keyboard(Main m){
        main = m;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // Handle key presses
        switch (keyCode) {
            case KeyEvent.VK_UP:
                System.out.println("space");

                // Handle the up arrow key press,
                main.isPressed("up");
                break;
            case KeyEvent.VK_DOWN:
                // Handle the down arrow key press
                break;
            case KeyEvent.VK_LEFT:
                // Handle the left arrow key press
                break;
            case KeyEvent.VK_RIGHT:
                // Handle the right arrow key press
                break;
            case KeyEvent.VK_SPACE:
                            System.out.println("space");

                main.isPressed("space");
                System.out.println("space");
            // Add more cases for other keys as needed
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // Handle key releases
        switch (keyCode) {
            case KeyEvent.VK_UP:
                // Handle the up arrow key release
                break;
            case KeyEvent.VK_DOWN:
                // Handle the down arrow key release
                break;
            case KeyEvent.VK_LEFT:
                // Handle the left arrow key release
                break;
            case KeyEvent.VK_RIGHT:
                // Handle the right arrow key release
                break;
            // Add more cases for other keys as needed
        }
    }
}
