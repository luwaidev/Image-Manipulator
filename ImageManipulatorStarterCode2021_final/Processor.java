/**
 * Starter code for Processor - the class that processes images.
 * <p> This class manipulated Java BufferedImages, which are effectively 2d arrays
 * of pixels. Each pixel is a single integer packed with 4 values inside it.</p>
 * 
 * <p>All methods added to this class should be static. In other words, you do not
 *    have to instantiate (create an object of) this class to use it - simply call
 *    the methods with Processor.methodName and pass a GreenfootImage to be manipulated.
 *    Note that you do not have to return the processed image, as you will be passing a
 *    reference to your image, and it will be altered by the method, as seen in the Blueify
 *    example.</p>
 *    
 * <p>Some methods, especially methods that change the size of the image (such as rotation
 *    or scaling) may require a GreenfootImage return type. This is because while it is
 *    possible to alter an image passed as a parameter, it is not possible to re-instantiate it, 
 *    which is required to change the size of a GreenfootImage</p>
 * 
 * <p>
 * I have included two useful methods for dealing with bit-shift operators so
 * you don't have to. These methods are unpackPixel() and packagePixel() and do
 * exactly what they say - extract red, green, blue and alpha values out of an
 * int, and put the same four integers back into a special packed integer. </p>
 * 
 * @author Jordan Cohen 
 * @version November 2013
 */

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import greenfoot.*;

public class Processor  
{
    /**
     * Example colour altering method by Mr. Cohen. This method will
     * increase the blue value while reducing the red and green values.
     * 
     * Demonstrates use of packagePixel() and unpackPixel() methods.
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */
    public static void blueify (BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Using array size as limit
        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgba = bi.getRGB(x, y);
                
                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgba);

                int alpha = rgbValues[0]; // 0-255
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                // make the pic BLUE-er
                if (blue < 253)
                    blue += 2;
                if (red >= 50)
                    red--;
                if (green >= 50)
                    green--;

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }

    }

    public static BufferedImage rotate90Clockwise (BufferedImage bi){
        BufferedImage newBi = new BufferedImage (bi.getHeight(), bi.getWidth(), 3);

        return newBi;
    }

    public static void flipHorizontal (BufferedImage bi)
    {


        // Make a copy of the original image so we can "flip" it without
        // losing any information along the way
        BufferedImage tempBI = Background.deepCopy (bi);

        /**
         *  Your task here is to use a nested pair of for loops to move
         *  pixels from the temporary BufferedImage tempBI back to bi in 
         *  the opposite order horizontally. Look at our solution to the
         *  character array flip task from class for hints.
         */ 
        
        for (int y = 0; y < bi.getHeight(); y++){
            for (int x = 0; x < bi.getWidth(); x++){
            tempBI.setRGB(bi.getWidth()-x,bi.getHeight()-y,bi.getRGB(x,y)) ;
        }
        }

    }

    /**
     * Takes in a BufferedImage and returns a GreenfootImage.
     * 
     * @param newBi The BufferedImage to convert.
     * 
     * @return GreenfootImage   A GreenfootImage built from the BufferedImage provided.
     */
    public static GreenfootImage createGreenfootImageFromBI (BufferedImage newBi)
    {
        GreenfootImage returnImage = new GreenfootImage (newBi.getWidth(), newBi.getHeight());
        BufferedImage backingImage = returnImage.getAwtImage();
        Graphics2D backingGraphics = (Graphics2D)backingImage.getGraphics();
        backingGraphics.drawImage(newBi, null, 0, 0);

        return returnImage;
    }

    /**
     * Takes in an rgb value - the kind that is returned from BufferedImage's
     * getRGB() method - and returns 4 integers for easy manipulation.
     * 
     * By Jordan Cohen
     * Version 0.2
     * 
     * @param rgbaValue The value of a single pixel as an integer, representing<br>
     *                  8 bits for red, green and blue and 8 bits for alpha:<br>
     *                  <pre>alpha   red     green   blue</pre>
     *                  <pre>00000000000000000000000000000000</pre>
     * @return int[4]   Array containing 4 shorter ints<br>
     *                  <pre>0       1       2       3</pre>
     *                  <pre>alpha   red     green   blue</pre>
     */
    public static int[] unpackPixel (int rgbaValue)
    {
        int[] unpackedValues = new int[4];
        // alpha
        unpackedValues[0] = (rgbaValue >> 24) & 0xFF;
        // red
        unpackedValues[1] = (rgbaValue >> 16) & 0xFF;
        // green
        unpackedValues[2] = (rgbaValue >>  8) & 0xFF;
        // blue
        unpackedValues[3] = (rgbaValue) & 0xFF;

        return unpackedValues;
    }

    /**
     * Takes in a red, green, blue and alpha integer and uses bit-shifting
     * to package all of the data into a single integer.
     * 
     * @param   int red value (0-255)
     * @param   int green value (0-255)
     * @param   int blue value (0-255)
     * @param   int alpha value (0-255)
     * 
     * @return int  Integer representing 32 bit integer pixel ready
     *              for BufferedImage
     */
    public static int packagePixel (int r, int g, int b, int a)
    {
        int newRGB = (a << 24) | (r << 16) | (g << 8) | b;
        return newRGB;
    }
}
