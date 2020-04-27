package PollingStation;

import OCR.readerTess4J;
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
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Start {

    public Start() throws IOException {
    }

    public static void main(String[] args) throws IOException, TesseractException {
        ArrayList<Integer> aBallot = new ArrayList<Integer>();

        String name = "";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        imageDivider divider = new imageDivider();
        readerTess4J readerTess4J = new readerTess4J();
        BufferedImage [] bufferedImages = divider.divideImage("D:\\Michal\\Uni Work\\4th Year\\FYP\\Images\\PNG2016BallotPaperTyped.png");//This will need to be set to location of ballot.png that will demo OCR.
        String s;
        BufferedImage localBufferedImage = null;
        Image image = null;
        int i = 0;
        readerTess4J.setDatapath("D:\\Michal\\Uni Work\\4th Year\\FYP\\Tess4J\\Tess4J\\tessdata");//This has to be set to local Tess4J tessdata folder.
        readerTess4J.setTessVariable("tessedit_char_whitelist", "0123456789");// This sets readerTess4J to only look for digits
        for(i = 0; i < 8;i++) {

            //image = ImageIO.read(new File("D:\\Michal\\Uni Work\\4th Year\\FYP\\Images\\dividedImages\\Pref#1.png")) ;
            //image = image.getScaledInstance(399,387,Image.SCALE_DEFAULT);
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

            System.out.println(("Is this the preference in ballot location: " + (i)));
            System.out.println(s.trim());
            System.out.println("____________");
            System.out.println("If yes press Enter else type in the preference is should have been in the ballot location followed by pressing enter.");
            System.out.println("Type a 0 if there is no preference on ballot");
            name = bufferedReader.readLine();
            if(name.matches("")&& !s.matches("")){
                aBallot.add(0,Integer.parseInt(s.trim()));
            }
            else
            {
                if(!name.matches("0")) {
                    aBallot.add(0, Integer.parseInt(name));
                }
            }
            //g.dispose();
        }

        System.out.println(aBallot);
    }

}

//Tesseract will read each of the images in order
//reading each preference number or "0" if no preference
//This will then be converted to an int array with the first index being the position of the votes first preference and so on until no more preferences.
//Example Ballot would be 2014003
//This would be stored array[0] = 3, array[1] = 1, array[2] = 7, array[3] = 4
