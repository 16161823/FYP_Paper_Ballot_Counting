package OCR;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//This Class is responsible for the dividing the ballot image into parts that the OCR will then read from.
public class imageDivider {


    public static void divideImage() throws IOException {


        final BufferedImage source = ImageIO.read(new File("D:\\Michal\\Uni Work\\4th Year\\FYP\\Images\\PNG2016BallotPaper.png"));

        System.out.println(source.getHeight());
        System.out.println(source.getWidth());

        int idx = 0;

        for (int y = 0; y < source.getHeight(); y += 32) {
            ImageIO.write(source.getSubimage(0, y, 32, 32), "png", new File("<sourceDir>/1fby-6t-555d_" + idx++ + ".png"));

        }
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