package src.Accessories;

import java.awt.Color;
import java.awt.image.BufferedImage;

import src.Enviorment.EnviornmentControl.Enviornments;

public class Imaging {

    public static BufferedImage changeHue(BufferedImage image, float hueOffset) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage modifiedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = image.getRGB(x, y);
                Color originalColor = new Color(rgb);
                float[] hsb = Color.RGBtoHSB(originalColor.getRed(), originalColor.getGreen(), originalColor.getBlue(),
                        null);

                // Modify the hue component by the specified offset
                float modifiedHue = (hsb[0] + hueOffset / 360.0f) % 1.0f;
                if (modifiedHue < 0) {
                    modifiedHue += 1.0f;
                }

                // Convert modified HSB color back to RGB
                int modifiedRGB = Color.HSBtoRGB(modifiedHue, hsb[1], hsb[2]);

                // Set the pixel in the modified image
                modifiedImage.setRGB(x, y, modifiedRGB);
            }
        }

        return modifiedImage;
    }

    //the brightnessfactor is very sensitive having the range as 0-5 i think
    public static BufferedImage adjustBrightness(BufferedImage image, double brightnessFactor) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage modifiedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = image.getRGB(x, y);

                Color originalColor = new Color(rgb);
                float[] hsb = Color.RGBtoHSB(originalColor.getRed(), originalColor.getGreen(), originalColor.getBlue(),
                        null);

                // Adjust the brightness component
                hsb[2] = (float) Math.min(1.0, Math.max(0.0, hsb[2] * brightnessFactor));

                // Convert modified HSB color back to RGB
                int modifiedRGB = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);

                // Set the pixel in the modified image
                                if (((((rgb >> 16 & 0xFF) == 0)
                            && ((rgb >> 8 & 0xFF) == 0) &&
                            ((rgb & 0xFF) == 0)))) 
                modifiedImage.setRGB(x, y, rgb);
                               else modifiedImage.setRGB(x, y, modifiedRGB);

                            
            }
        }

        return modifiedImage;
    }

    public static BufferedImage correct(BufferedImage image){
        double time = Enviornments.timeOfDay;
        double factor = -(((time-12)*(time-12))/35)+4.5;


        return adjustBrightness(image,factor);


    }
}
