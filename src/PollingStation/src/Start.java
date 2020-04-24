import net.sourceforge.tess4j.Tesseract.*;
import OCR.imageDivider;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import sun.awt.image.ToolkitImage;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Start {

    public Start() throws IOException {
    }
/*
    public static void main(String[] args) throws IOException, TesseractException {

        //client pollingStation = new client();
        imageDivider divider = new imageDivider();
        readerTess4J readerTess4J = new readerTess4J();
        BufferedImage [] bufferedImages = divider.divideImage("D:\\Michal\\Uni Work\\4th Year\\FYP\\Images\\PNG2016BallotPaperTyped.png");
        String s;
        BufferedImage localBufferedImage = null;
        Image image = null;
        int i = 0;
        readerTess4J.setDatapath("D:\\Michal\\Uni Work\\4th Year\\FYP\\Tess4J\\Tess4J\\tessdata");
        //readerTess4J.setTessVariable("tessedit_char_whitelist", "0123456789");
        for(i = 0; i < 8;i++) {

            image = ImageIO.read(new File("D:\\Michal\\Uni Work\\4th Year\\FYP\\Images\\dividedImages\\Pref#1.png")) ;
            image = image.getScaledInstance(399,387,Image.SCALE_DEFAULT);
            //localBufferedImage = ((ToolkitImage)image).getBufferedImage();
            localBufferedImage = bufferedImages[i];
            //BufferedImage newImage = new BufferedImage((localBufferedImage.getWidth()*2),(localBufferedImage.getHeight()*2),localBufferedImage.getType());

            //Graphics g = newImage.createGraphics();
            //g.drawImage(localBufferedImage, 0, 0, (localBufferedImage.getWidth()*2), (localBufferedImage.getHeight()*2), null);


            //Error .getScaledInstance(399,187,Image.SCALE_DEFAULT)
            //readerTess4J.setDatapath("D:\\Michal\\Uni Work\\4th Year\\FYP\\Tess4J\\Tess4J\\tessdata");

            //readerTess4J.setImage(localBufferedImage, new Rectangle(133,129));
            //s = readerTess4J.doOCR(localBufferedImage, new Rectangle(133, 129));
            //s = readerTess4J.doOCR(new File("D:\\Michal\\Uni Work\\4th Year\\FYP\\Images\\dividedImages\\Pref#" + i + ".png"));
            s = readerTess4J.doOCR(localBufferedImage);
            System.out.println(("This is image: " + (i)));
            System.out.println(s);
            //g.dispose();

        }
    }
*/
}

//Tesseract will read each of the images in order
//reading each preference number or "0" if no preference
//This will then be converted to an int array with the first index being the position of the votes first preference and so on untill no more preferences.
//Example Ballot would be 2014003
//This would be stored array[0] = 3, array[1] = 1, array[2] = 7, array[3] = 4
