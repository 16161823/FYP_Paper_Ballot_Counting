package OCR;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.util.ImageIOHelper;

import java.awt.*;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.nio.ByteBuffer;

import static net.sourceforge.tess4j.ITessAPI.TessPageSegMode.PSM_SINGLE_CHAR;

public class readerTess4J extends Tesseract {

    public readerTess4J(){
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("D:\\Michal\\Uni Work\\4th Year\\FYP\\Tess4J\\Tess4J\\tessdata");
        tesseract.setPageSegMode(PSM_SINGLE_CHAR);

    }

    /**
     * A wrapper for {@link #setImage(int, int, ByteBuffer, Rectangle, int)}.
     *
     * @param image a rendered image
     * @param rect region of interest
     * @throws java.io.IOException
     */
    protected void setImage(RenderedImage image, Rectangle rect) throws IOException {
        ByteBuffer buff = ImageIOHelper.getImageByteBuffer(image);
        int bpp;
        DataBuffer dbuff = image.getData(new Rectangle(1,1)).getDataBuffer();
        if (dbuff instanceof DataBufferByte) {
            bpp = image.getColorModel().getPixelSize();
        } else {
            bpp = 8; // BufferedImage.TYPE_BYTE_GRAY image
        }
       this.setImage(image.getWidth(), image.getHeight(), buff, rect, bpp);
    }


}
