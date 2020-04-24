/*package OCR;

import java.awt.Point;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.nio.ByteBuffer;
import java.util.Hashtable;

import com.sun.jna.Pointer;
import net.sourceforge.lept4j.Pix;
import org.bridj.Pointer;

import de.vorb.leptonica.LibLept;
import de.vorb.leptonica.Pix;

/**
 * Functions for converting between various image formats.
 *
 * @author Paul Vorbach
 */
/*
public class PixConversions {
    private PixConversions() {
    }

    /**
     * Converts a BufferedImage to an instance of Pix.
     *
     * @param img
     * @return
     *//*
    public static Pointer<Pix> img2pix(BufferedImage img) {
        final int width = img.getWidth();
        final int height = img.getHeight();
        final int depth;
        final int spp;

        switch (img.getType()) {
            case BufferedImage.TYPE_BYTE_BINARY:
                depth = 1;
                spp = 1;
                break;
            case BufferedImage.TYPE_BYTE_GRAY:
                depth = 8;
                spp = 1;
                break;
            default:
                // if the given image is neither binary or grayscale, convert it to
                // grayscale
                final ColorConvertOp op = new ColorConvertOp(
                        ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
                final BufferedImage gray = new BufferedImage(img.getWidth(),
                        img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
                op.filter(img, gray);

                // replace input image with grayscale version
                img = gray;

                depth = 8;
                spp = 1;
        }

        final Pointer<Pix> ppix = LibLept.pixCreate(width, height,
                depth);

        // set samples per pixel
        LibLept.pixSetSpp(ppix, spp);

        // calculate words (ints) per line
        final int wpl = (width * depth + 31) / 32; // words (ints) per line
        LibLept.pixSetWpl(ppix, wpl);

        // get the raw bytes of the image
        final DataBufferByte dataBuf =
                (DataBufferByte) img.getData().getDataBuffer();
        final ByteBuffer bytes = ByteBuffer.wrap(dataBuf.getData());

        final Pointer<Integer> pixData = LibLept.pixGetData(ppix);
        // convert the raw bytes to pix data
        setBytesToPix(pixData, bytes, width, height, depth, wpl);

        return ppix;
    }
}
*/