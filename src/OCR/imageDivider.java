package OCR;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//This Class is responsible for the dividing the ballot image into parts that the OCR will then read from.
public class imageDivider {

    public imageDivider(){

    }

    public BufferedImage[] divideImage(String path) throws IOException {

        final BufferedImage source = ImageIO.read(new File(path)); //path: "D:\\Michal\\Uni Work\\4th Year\\FYP\\Images\\PNG2016BallotPaperTyped.png"
        System.out.println(source.getHeight());
        System.out.println(source.getWidth());

        BufferedImage [] bufferedImages = new BufferedImage[7];
        int idx = 0;
        int candidates = 8;
        int boxW = 133;
        int boxH = 129;
        int firstBoxY = 526;
        int boxLeftX = 1471;
        int spaceBetween = 51;

        for (int y = 0; y < candidates; y++) {

            bufferedImages[y] = source.getSubimage(boxLeftX,firstBoxY, boxW, boxH);
                // String vote =  tesseract.doOCR(source.getSubimage(1471, localY, boxW, boxH));
                // System.out.println(vote);
                // ImageIO.write(source.getSubimage(1471, localY, boxW, boxH), "png", new File("D:\\Michal\\Uni Work\\4th Year\\FYP\\Images\\dividedImages\\Pref#" + idx++ + ".png"));
            try {

                File tempFile = new File("D:\\Michal\\Uni Work\\4th Year\\FYP\\Images\\dividedImages\\Pref#" + y + ".png");
                boolean exists = tempFile.exists();

                if (exists == true) {
                    tempFile.delete();
                }

                ImageIO.write(bufferedImages[y], "png", new File("D:\\Michal\\Uni Work\\4th Year\\FYP\\Images\\dividedImages\\Pref#" + y + ".png"));
            }
            catch (IOException e) {
                System.out.println("Exception occured :" + e.getMessage());
            }

            firstBoxY =+ firstBoxY + (boxH + spaceBetween);
        }

    return bufferedImages;

    }
}


/*
Notes:

public BufferedImage getSubimage(int x,
                        int y,
                        int w,
                        int h)
Returns a subimage defined by a specified rectangular region. The returned BufferedImage shares the same data array as the original image.
Parameters:
x - the X coordinate of the upper-left corner of the specified rectangular region
y - the Y coordinate of the upper-left corner of the specified rectangular region
w - the width of the specified rectangular region
h - the height of the specified rectangular region
Returns:
a BufferedImage that is the subimage of this BufferedImage.
Throws:
RasterFormatException - if the specified area is not contained within this BufferedImage.

 */