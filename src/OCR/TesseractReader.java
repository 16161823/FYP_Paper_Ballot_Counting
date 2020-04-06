package OCR;
//https://www.geeksforgeeks.org/tesseract-ocr-with-java-with-examples/
import java.awt.image.BufferedImage;

import net.sourceforge.tess4j.Tesseract;

public class TesseractReader {
/*
    private static TesseractReader instance = new TesseractReader();
    public Tesseract tesseract = new Tesseract();
    tesseract.setDatapath("D:/Tess4J/tessdata");
    tesseract.setPageSegMode(10);

    private Tesseract TesseractReader() {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("D:/Tess4J/tessdata");
        tesseract.setPageSegMode(10);

       public Tesseract getEngine(){
            return tesseract;
        }

    }

    public static TesseractReader getInstance() {
        return instance;
    }

    public String[] read(BufferedImage[] bufferedImages){
        Tesserac tesseractReader = TesseractReader.getInstance();
        for (int i = 0; i < bufferedImages.length; i++) {

            TesseractReader.getInstance().
            if (b == "") {
                b = "0";
            }
            System.out.println(b);
        }
    }

    public static void initiateTesseractReader(){

    }

    }
}

/*

    public TesseractReader(BufferedImage[] bufferedImages) {

        try {

            // the path of your tess data folder
            // inside the extracted file
            tesseract.setDatapath("D:/Tess4J/tessdata");
            tesseract.setPageSegMode(10);

            // path of your image file
           // String text = tesseract.doOCR(new File("image.jpg"));

            String a = "";

            for(int i = 0;i < bufferedImages.length;i++){

                String b = tesseract.doOCR(bufferedImages[i]);
                if(b==""){
                    b = "0";
                }
                System.out.println(b);

            }

            //System.out.print(text);

        } catch (TesseractException e) {
            e.printStackTrace();
        }

    }



*/
}