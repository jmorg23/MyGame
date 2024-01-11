package src.Enviorment.EnviornmentControl;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import src.Accessories.Imaging;

public class Platforms {

    private ArrayList<BufferedImage> platImages = new ArrayList<>();
    public int x = 0;
    public int y = 54;

    public Platforms(int a) {
        try {
            // images
            File inputFile = new File("res/im/Untitled-1.png");
            platImages.add(ImageIO.read(inputFile));

            
            createPixelArray(platImages.get(0));
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    


    int conx = 0;
    BufferedImage con;  

    public void draw(Graphics2D g2) {

        if (conx == 0) {
            conx++;
            con = hideColor(platImages.get(0), new Color(0, 0, 0));
        }
       // g2.drawImage(Imaging.correct(con.getSubimage(-x, 0, 1536, 864)), 0, 54, null);

    }



    public boolean scroll(double speed) {

        x += speed;
        if (x > 0){
            x = 0;
            return false;
        }else
        return true;
        

        // conx-=speed;
    }

    static int[][] rgbArray;

    private static int[][] createPixelArray(BufferedImage image) throws IOException {
        // Get the width and height of the image
        int width = image.getWidth();
        int height = image.getHeight();
        rgbArray = new int[width][height];
        System.out.println(width);
        System.out.println("H == ===    " + height);
        // Create a 2D array to store RGB values
        // for (int i = 0; i < (width / widthOfEachSub); i++)
        // Iterate through each pixel
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // int pixel = image.getSubimage(width, 0, width, height).getRGB(x, y);
                int pixel = image.getRGB(x, y);

                // Extract RGB values
                int red = (pixel >> 16) & 0xFF;
                int green = (pixel >> 8) & 0xFF;
                int blue = pixel & 0xFF;

                // Store RGB values in the array
                rgbArray[x][y] = (red << 16) | (green << 8) | blue;
            }
        }

        // Now rgbArray contains RGB values of each pixel
        // You can access them using rgbArray[x][y]

        // Example: Printing RGB values of the pixel at (10, 10)
        // int pixelAt1010 = rgbArray[0][0];
        // int red = (pixelAt1010 >> 16) & 0xFF;
        // int green = (pixelAt1010 >> 8) & 0xFF;
        // int blue = pixelAt1010 & 0xFF;

        return rgbArray;

    }

    public boolean isColliding(int x1, int y1) throws IOException {

        int x2 = x;
        int y2 = y;

        int difx = (x1 - x2);
        int dify = (y1 - y2);

        try {

            // if((rgbArray[difx][dify] >> 16 &
            // 0xFF)==255)System.out.println("asdfpoiopoiuytfghjytf");

            if ((((rgbArray[difx][dify] >> 16 & 0xFF) == 255) && ((rgbArray[difx][dify] >> 8 & 0xFF) == 255)
                    && ((rgbArray[difx][dify] & 0xFF) == 255))
                    || (((rgbArray[difx][dify] >> 16 & 0xFF) == 0)
                            && ((rgbArray[difx][dify] >> 8 & 0xFF) == 0) &&
                            ((rgbArray[difx][dify] & 0xFF) == 0))) {
                // System.out.println(i);
                return false;
            }

        } catch (Exception e) {
            System.out.println("asdfsdfdf");

            // this means that there is an out of bounds error so no way its touching
            // platform

            return false;
            // e.printStackTrace();
        }
        return true;

    }

    public static BufferedImage hideColor(BufferedImage image, Color targetColor) {
        // Create a copy of the original image
        BufferedImage filteredImage = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                BufferedImage.TYPE_INT_ARGB);

        // Create a Graphics2D object to manipulate the image
        Graphics2D g2d = filteredImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);

        // Define a tolerance for color matching
        int tolerance = 100; // You can adjust this value

        // Iterate through each pixel and check if it matches the target color
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color pixelColor = new Color(image.getRGB(x, y));
                int deltaRed = Math.abs(pixelColor.getRed() - targetColor.getRed());
                int deltaGreen = Math.abs(pixelColor.getGreen() - targetColor.getGreen());
                int deltaBlue = Math.abs(pixelColor.getBlue() - targetColor.getBlue());

                // If the color is close enough to the target color, set it to transparent
                if (deltaRed <= tolerance && deltaGreen <= tolerance && deltaBlue <= tolerance) {
                    filteredImage.setRGB(x, y, 0x00FFFFFF); // Transparent color
                }
            }
        }

        g2d.dispose();
        return filteredImage;
    }

}
