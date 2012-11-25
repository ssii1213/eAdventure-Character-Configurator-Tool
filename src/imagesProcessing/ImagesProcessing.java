package imagesProcessing;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class ImagesProcessing
{
    BufferedImage skin;
    BufferedImage trousers;
    BufferedImage tShirt;
    BufferedImage eyes;
    BufferedImage shoes;
    ArrayList<BufferedImage> images;
    int num = 0;
	
    public ImagesProcessing(String skinPath,String trousersPath,String tShirtPath,String eyesPath,String shoesPath)
    {
        images = new ArrayList<BufferedImage>();
        try 
        {
            skin = ImageIO.read(new File(skinPath));
            trousers = ImageIO.read(new File(trousersPath));
            tShirt = ImageIO.read(new File(tShirtPath));
            eyes = ImageIO.read(new File(eyesPath));
            shoes = ImageIO.read(new File(shoesPath));
            images.add(skin);
            images.add(trousers);
            images.add(tShirt);
            images.add(eyes);
            images.add(shoes);
        } 
        catch (IOException e) 
        {
            System.out.println("Failed loading file");
        }		
    }

    public void fusionaImagenes(String destinationPath)
    {
        int w = skin.getWidth();
        int h= skin.getHeight();  
        BufferedImage finalImage = new BufferedImage(w,h,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = finalImage.getGraphics();
        for (int i = 0; i < images.size(); i++) 
        {
            BufferedImage aux = images.get(i);
            g.drawImage(aux, 0, 0, null);
        }

        //Rotate 180 grades
        double rotationRequired = Math.toRadians(180);
        double locationX = w / 2;
        double locationY = h / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        // Drawing the rotated image at the required drawing locations
        g.drawImage(op.filter(finalImage, null), 0, 0, null);        
        //Swaping the image
        g.drawImage(finalImage, 0, 0, w, h, w, 0, 0, h, null);

        try 
        {
            ImageIO.write(finalImage, "png", new File(destinationPath));
        } 
        catch (IOException e) 
        {
            System.out.println("Failed saving image");
        }  
    }
}