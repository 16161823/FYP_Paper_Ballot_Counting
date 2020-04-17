import net.sourceforge.tess4j.Tesseract.*;
import OCR.imageDivider;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageReader;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Start {

    public Start() throws IOException {
    }

    public static void main(String[] args) throws IOException, TesseractException {
/*
        Socket socket = new Socket("localhost", 59898)
        System.out.println("Enter lines of text then Ctrl+D or Ctrl+C to quit");
        Scanner scanner = new Scanner(System.in);
        Scanner in = new Scanner(socket.getInputStream());
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
*/
           //while (scanner.hasNextLine()) {
        //out.println("Well");
            //System.out.println(in.nextLine());
        imageDivider divider = new imageDivider();
        BufferedImage [] bufferedImages = divider.divideImage("D:\\Michal\\Uni Work\\4th Year\\FYP\\Images\\PNG2016BallotPaperTyped.png");
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("D:\\Michal\\Uni Work\\4th Year\\FYP\\Tess4J\\Tess4J\\tessdata");
        tesseract.setPageSegMode(10);


        int i = 0;
        for (;i<7;) {
            BufferedImage localBufferedImage = bufferedImages[i];
            //Error
            //Cannot convert RAW image to Pix with bpp = 64
            //Please call SetImage before attempting recognition.
            String s = tesseract.doOCR(new File("D:\\Michal\\Uni Work\\4th Year\\FYP\\Images\\dividedImages\\Pref#" + i++ + ".png"));
            i++;
            System.out.println(s);
        }

    }

}

//Tesseract will read each of the images in order
//reading each preference number or "0" if no preference
//This will then be converted to an int array with the first index being the position of the votes first preference and so on untill no more preferences.
//Example Ballot would be 2014003
//This would be stored array[0] = 3, array[1] = 1, array[2] = 7, array[3] = 4
