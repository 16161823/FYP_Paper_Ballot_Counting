package OCR;
//https://www.geeksforgeeks.org/tesseract-ocr-with-java-with-examples/
import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TesseractReader {

    public TesseractReader() {
        Tesseract tesseract = new Tesseract();
        try {

            // the path of your tess data folder
            // inside the extracted file
            tesseract.setDatapath("D:/Tess4J/tessdata");

            // path of your image file
            String text = tesseract.doOCR(new File("image.jpg"));

            System.out.print(text);

        } catch (TesseractException e) {
            e.printStackTrace();
        }
    }
}
