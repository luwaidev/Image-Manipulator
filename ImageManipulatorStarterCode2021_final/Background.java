import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.awt.image.ColorModel;

import java.util.ArrayList;
/**
 * Starter code for Image Manipulation Array Assignment.
 * 
 * The class Processor contains all of the code to actually perform
 * transformation. The rest of the classes serve to support that
 * capability. This World allows the user to choose transformations
 * and open files.
 * 
 * Add to it and make it your own!
 * 
 * @author Jordan Cohen
 * @version November 2013
 */
public class Background extends World
{
    // Constants:
    private final String STARTING_FILE = "colourful.jpg";
    public static final int MAX_WIDTH = 800;
    public static final int MAX_HEIGHT = 720;

    // Objects and Variables:
    private ImageHolder image;
    private TextButton hRevButton, vRevButton, rNinetyCWButton, rNinetyCCWButton, rOneEightyButton, redButton, greenButton, blueButton, purpleButton, greyScaleButton, negativeButton, brightenRedButton, addSaturationButton, removeSaturationButton;
    private TextButton openButton, undoButton, redoButton, saveButton, pixielateButton, bloomButton;
    private SuperTextBox openFile;
    private String fileName;
    
    //private Image
    private ArrayList<BufferedImage> history;
    private int curImg;
    /**
     * Constructor for objects of class Background.
     * 
     */
    public Background()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 720, 1); 

        // Initialize buttons and the image --> Performed first so that the details can be retrieved and displayed
        image = new ImageHolder(STARTING_FILE); // The image holder constructor does the actual image loading

        // Set up UI elements --> See appropriate constructor API for details
        int x = 136;
        int y = 90;
        int f = 14;
        
        hRevButton = new TextButton("Flip Horizontal", 8, x, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));  
        vRevButton = new TextButton("Flip Vertical", 8, x, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));  
        rNinetyCWButton = new TextButton("Rotate 90 CW", 8, x, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));  
        rNinetyCCWButton = new TextButton("Rotate 90 CCW", 8, x, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));  
        rOneEightyButton = new TextButton("Rotate 180", 8, x, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));  
        
        redButton = new TextButton("Redify", 8, y, true, Color.BLACK, Color.RED, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,f));
        greenButton = new TextButton("Greenify", 8, y, true, Color.BLACK, Color.GREEN, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,f));
        blueButton = new TextButton("Blueify", 8, y, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,f));
        purpleButton = new TextButton("Purpleify", 8, y, true, Color.BLACK, Color.BLACK, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,f));
        greyScaleButton = new TextButton("Greyscale", 8, y, true, Color.BLACK, Color.BLACK, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,f));
        negativeButton = new TextButton("Negative", 8, y, true, Color.BLACK, Color.BLACK, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,f));
        brightenRedButton = new TextButton("Brighten Red", 8, y +30, true, Color.BLACK, Color.BLACK, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,f));
        
        
        pixielateButton = new TextButton("Pixelate", 8, x, true, Color.BLACK, Color.BLACK, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        bloomButton = new TextButton("Bloom", 8, x, true, Color.BLACK, Color.BLACK, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        addSaturationButton = new TextButton("Add Saturation", 8, y + 40, true, Color.BLACK, Color.BLACK, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,f));
        removeSaturationButton = new TextButton("Remove Saturation", 8, y + 60, true, Color.BLACK, Color.BLACK, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,f));
        
        
        openButton = new TextButton ("Open", 8, 80, true, Color.BLACK, Color.GREEN, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        undoButton = new TextButton ("Undo", 8, 80, true, Color.BLACK, Color.RED, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        redoButton = new TextButton ("Redo", 8, 80, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        saveButton = new TextButton ("Save", 8, 80, true, Color.BLACK, Color.GREEN, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        
        
        // Populate text box with details about the image
        openFile = new SuperTextBox(new String[]{"File: " + STARTING_FILE,"Scale: " + image.getScale() + " W: " + image.getRealWidth() + " H: " + image.getRealHeight()}, new Font ("Comic Sans MS", false, false, 16), 600, true);//new TextButton(" [ Open File: " + STARTING_FILE + " ] ");

        // Add objects to the screen
        int a = 77;
        int b = x + 5;
        
        addObject (image, 1000/2 , 720/2 - 15);
        addObject (hRevButton, a, 654);
        addObject (vRevButton, a + b, 654);
        addObject (rNinetyCWButton, a + 2*b, 654);
        addObject (rNinetyCCWButton, a + 3*b, 654);
        addObject (rOneEightyButton, a + 4*b, 654);
        
        
        int c = 55;
        int d = y + 5;
        addObject (redButton, c, 696);
        addObject (greenButton, c + d, 696);
        addObject (blueButton, c + 2*d, 696);
        addObject (purpleButton, c + 3*d, 696);
        addObject (brightenRedButton, c + 4*d + 15, 696);
        addObject (greyScaleButton, c + 5*d + 30, 696);
        addObject (negativeButton, c + 6*d + 30, 696);
        
        
        
        addObject (pixielateButton, a + 5*b, 654);
        addObject (bloomButton, a + 6*b, 654);
        addObject (addSaturationButton, c + 7*d + 50, 696);
        addObject (removeSaturationButton, c + 8*d + 100, 696);
        
        // place the open file text box in the top left corner
        addObject (openFile, 1000/2 , openFile.getImage().getHeight() / 2);
        // place the open file button directly beside the open file text box
        addObject (openButton, 45, 24);
        addObject (saveButton, 130, 24);
        
        
        addObject (undoButton, 870, 24);
        addObject (redoButton, 955, 24);
        history = new ArrayList<BufferedImage>();
    }

    /**
     * Act() method just checks for mouse input
     */
    public void act ()
    {
        checkMouse();
    }
    
    private void checkMouse ()
    {
        // Avoid excess mouse checks - only check mouse if somethething is clicked.
        if (Greenfoot.mouseClicked(null))
        {
            
            updateHistory();
            if (Greenfoot.mouseClicked(openButton)){
                openFile ();

            }
            else if (Greenfoot.mouseClicked(saveButton)){
                
                
                
            }
            else if (Greenfoot.mouseClicked(undoButton) && curImg > 0){
                System.out.println("undo");
                if (!compareImages(history.get(history.size()-1), image.getBufferedImage())){
                    history.add(image.getBufferedImage());
                }
                curImg --;
                        
                image.setNewImage(createGreenfootImageFromBI(history.get(curImg)));
                image.redraw();
                openFile.update (image.getDetails ());
            }else if (Greenfoot.mouseClicked(redoButton) && curImg <= history.size()-2){
                if (!compareImages(history.get(history.size()-1), image.getBufferedImage())){
                    history.add(image.getBufferedImage());
                }
                curImg ++;
                        
                image.setNewImage(createGreenfootImageFromBI(history.get(curImg)));
                image.redraw();
                openFile.update (image.getDetails ());
            }
            else if (Greenfoot.mouseClicked(hRevButton)){
                Processor.flipHorizontal(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                changingImage();
            }
            else if (Greenfoot.mouseClicked(vRevButton)){
                Processor.flipVertical(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
            }
            else if (Greenfoot.mouseClicked(rNinetyCWButton)){
                image.setNewImage(Processor.rotate90CCW(image.getBufferedImage()));
                image.redraw();
                openFile.update (image.getDetails ());
                changingImage();
            }
            else if (Greenfoot.mouseClicked(rNinetyCCWButton)){
                image.setNewImage(Processor.rotate90CCW(image.getBufferedImage()));
                image.redraw();
                openFile.update (image.getDetails ());
                changingImage();
            }
            else if (Greenfoot.mouseClicked(rOneEightyButton)){
                Processor.rotate180(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                changingImage();
            }
            else if (Greenfoot.mouseClicked(redButton)){
                Processor.redify(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                changingImage();
            }
            else if (Greenfoot.mouseClicked(greenButton)){
                Processor.greenify(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                changingImage();
            }
            else if (Greenfoot.mouseClicked(blueButton)){
                Processor.blueify(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                changingImage();
            }
            else if (Greenfoot.mouseClicked(purpleButton)){
                Processor.purplify(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                changingImage();
            }
            else if (Greenfoot.mouseClicked(brightenRedButton)){
                Processor.brightenRed(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                changingImage();
            }
            else if (Greenfoot.mouseClicked(greyScaleButton)){
                Processor.greyScale(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                changingImage();
            }
            else if (Greenfoot.mouseClicked(negativeButton)){
                Processor.negative(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                changingImage();
            }
            else if (Greenfoot.mouseClicked(pixielateButton)){
                Processor.pixalate(image.getBufferedImage());
                changingImage();
            }
            else if (Greenfoot.mouseClicked(bloomButton)){
                System.out.println("Bloom");
                Processor.bloom(image.getBufferedImage());
                changingImage();
            }
            else if (Greenfoot.mouseClicked(addSaturationButton)){
                Processor.addSaturation(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                changingImage();
            }
            else if (Greenfoot.mouseClicked(removeSaturationButton)){
                Processor.removeSaturation(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                changingImage();
            }
        }
    }
    
    private void updateHistory(){
        if (history == null || history.size() ==0){
            history.add(deepCopy(image.getBufferedImage()));
        }
        
        if (curImg > history.size()-1 || !compareImages(history.get(curImg), image.getBufferedImage())){
            System.out.println("Adding");
            history.add(deepCopy(image.getBufferedImage()));
        }
        System.out.println(curImg +","+history.size());
    }
    
    private void changingImage(){
        // Clear future history if not at most recent
        if (curImg < history.size()-1){
            System.out.println("doing thing");

            for (int i = curImg+1;i < history.size(); i++){
                history.remove(history.get(i));
            }
        }
        
        curImg++;
        image.redraw();
        openFile.update (image.getDetails ());
    }
    // Code provided, but not yet implemented - This will save image as a png.
    private void saveFile () {
        
            // Create a UI frame (required to show a UI element from Java.Swing)
            JFrame frame = new JFrame();
            // Create a JFileChooser, a file chooser box included with Java 
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select a folder");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            //fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION){
                File selectedFile = fileChooser.getSelectedFile();
                    //String display = " [ Open File: " + selectedFile.getName() + " ] ";
                    //openFile.update (image.getDetails ());
                    
                    try{// This will pop up a text input box - You should improve this with a JFileChooser like for the open function
                        String fileName = JOptionPane.showInputDialog("Input file name (no extension)");
            
                        fileName += ".png";
                        fileName = selectedFile.getAbsolutePath()+"/"+fileName;
                        File f = new File (fileName);  
                        ImageIO.write(image.getBufferedImage(), "png", f); 
                    }
                    catch (IOException e){
                        // this code instead
                       System.out.println("Unable to save file: " + e);
                    }
                    
            }
    }

    /**
     * Allows the user to open a new image file.
     */
    private void openFile ()
    {
        
        // Create a UI frame (required to show a UI element from Java.Swing)
        JFrame frame = new JFrame();
        // Create a JFileChooser, a file chooser box included with Java 
        JFileChooser fileChooser = new JFileChooser();
        //fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION){
            File selectedFile = fileChooser.getSelectedFile();
            //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            if (image.openFile (selectedFile.getAbsolutePath(), selectedFile.getName()))
            {
                //String display = " [ Open File: " + selectedFile.getName() + " ] ";
                openFile.update (image.getDetails ());
            }
        }
        // If the file opening operation is successful, update the text in the open file button
        /**if (image.openFile (fileName))
        {
        String display = " [ Open File: " + fileName + " ] ";
        openFile.update (display);
        }*/

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

    public static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultip = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultip, null);
    }
    
    public  void SetImage(BufferedImage bi){
        BufferedImage img = image.getBufferedImage();
        for (int y = 0; y < bi.getHeight(); y++){
            for (int x = 0; x < bi.getWidth(); x++){
                img.setRGB(x,y,bi.getRGB(x,y)) ;
            }
        }
    }
    /**
     * Compares two images pixel by pixel.
     *
     * @param imgA the first image.
     * @param imgB the second image.
     * @return whether the images are both the same or not.
     * 
     * Taken from https://stackoverflow.com/questions/11006394/is-there-a-simple-way-to-compare-bufferedimage-instances
     */
    public static boolean compareImages(BufferedImage imgA, BufferedImage imgB) {
      // The images must be the same size.
      if (imgA.getWidth() != imgB.getWidth() || imgA.getHeight() != imgB.getHeight()) {
        return false;
      }
    
      int width  = imgA.getWidth();
      int height = imgA.getHeight();
    
      // Loop over every pixel.
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          // Compare the pixels for equality.
          if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
            return false;
          }
        }
      }
    
      return true;
    }
    
    
}

